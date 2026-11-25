;; Public API to build and evaluate CEL programs, backed by cel-java.
;; Provides environments with custom functions, programs and evaluation.
(ns exoscale.cel.parser
  "A Clojure parser and interpreter for the Google
   Common Expression Language (CEL) syntax as defined
   in https://github.com/google/cel-spec/blob/master/doc/langdef.md"
  (:require [clojure.string :as str]
            [exoscale.cel.values :as values])
  (:import (clojure.lang ExceptionInfo)
           (com.google.common.primitives UnsignedLong)
           (dev.cel.bundle Cel CelFactory)
           (dev.cel.common CelAbstractSyntaxTree CelFunctionDecl CelIssue
                           CelOptions CelOverloadDecl CelSourceLocation
                           CelValidationException CelVarDecl)
           (dev.cel.common.ast CelExpr CelExpr$CelComprehension CelExpr$ExprKind$Kind)
           (dev.cel.common.navigation CelNavigableAst CelNavigableExpr)
           (dev.cel.common.types CelType ListType MapType SimpleType)
           (dev.cel.common.values CelByteString)
           (dev.cel.extensions CelOptionalLibrary)
           (dev.cel.parser CelStandardMacro)
           (dev.cel.runtime CelEvaluationException CelFunctionBinding
                            CelFunctionOverload CelRuntime$Program
                            CelUnknownSet)
           (java.time Duration Instant)
           (java.util HashMap List Map Optional)))

(set! *warn-on-reflection* true)

(def ^:private types
  "Supported function argument and result types: CEL type and the
   class of the values the CEL runtime passes for it."
  {:bool [SimpleType/BOOL Boolean]
   :int [SimpleType/INT Long]
   :uint [SimpleType/UINT UnsignedLong]
   :double [SimpleType/DOUBLE Double]
   :string [SimpleType/STRING String]
   :bytes [SimpleType/BYTES CelByteString]
   :timestamp [SimpleType/TIMESTAMP Instant]
   :duration [SimpleType/DURATION Duration]
   :list [(ListType/create SimpleType/DYN) List]
   :map [(MapType/create SimpleType/DYN SimpleType/DYN) Map]
   :dyn [SimpleType/DYN Object]})

(defn- type-of
  [t]
  (or (get types t)
      (throw (IllegalArgumentException. (str "Unsupported CEL type: " t)))))

(defn- cel-type ^CelType [t] (first (type-of t)))

(defn- arg-class ^Class [t] (second (type-of t)))

(defn- overload-ids
  "Overload ids for the global `f(x,y)` and, when it takes arguments,
   member `x.f(y)` call styles of a function. Their format cannot clash
   with the ids of standard functions."
  [{:keys [name args]}]
  (let [arg-names (map clojure.core/name args)]
    (cond-> {:global (str name "(" (str/join "," arg-names) ")")}
      (seq args)
      (assoc :member (str (first arg-names) "." name
                          "(" (str/join "," (rest arg-names)) ")")))))

(defn- overload-decls
  [{:keys [args result] :as function}]
  (let [{:keys [global member]} (overload-ids function)
        result-type (cel-type result)
        arg-types ^List (mapv cel-type args)]
    (cond-> [(CelOverloadDecl/newGlobalOverload ^String global result-type arg-types)]
      member
      (conj (CelOverloadDecl/newMemberOverload ^String member result-type arg-types)))))

(defn- function-decls
  [functions]
  (for [[fname overloads] (group-by :name functions)]
    (CelFunctionDecl/newFunctionDeclaration ^String fname
                                            ^Iterable (mapcat overload-decls overloads))))

(defn- result->cel
  "Convert a function result to a CEL value of the declared `result` type.
   Unsigned integers are represented as longs on the Clojure side."
  [result x]
  (if (and (= :uint result) (number? x))
    (UnsignedLong/fromLongBits (long x))
    (values/->cel x)))

(defn- function-bindings
  [{:keys [args result] f :fn :as function}]
  (let [impl (reify CelFunctionOverload
               (apply [_ arguments]
                 (result->cel result (apply f (map values/->clj arguments)))))
        classes ^Iterable (mapv arg-class args)]
    (for [id (vals (overload-ids function))]
      (CelFunctionBinding/from ^String id classes impl))))

(defn- issue->map
  [^CelIssue issue]
  (let [^CelSourceLocation location (.getSourceLocation issue)]
    {:msg (.getMessage issue)
     :line (.getLine location)
     :column (.getColumn location)}))

(defn- validation-error
  [^String msg ^CelValidationException e]
  (ex-info msg
           {:exoscale.ex/type :exoscale.ex/fault
            :errors (mapv issue->map (.getErrors e))}
           e))

(defn- validate-env
  "Yields `cel-env`, throws when its declarations or function bindings
   are invalid. Building any program surfaces these errors."
  ^Cel [^Cel cel-env]
  (try
    (.createProgram cel-env (.getAst (.check cel-env (.getAst (.parse cel-env "true")))))
    cel-env
    (catch CelValidationException e
      (throw (validation-error "Invalid CEL environment" e)))))

(defn env
  "Build a CEL environment, used to create programs. `config` is a map
   of the following keys:

|        Option |                                            Value |
|---------------+--------------------------------------------------|
|    :functions |       A sequence of custom function definitions. |

  A function definition is a map of `:name` (string), `:args` (a vector
  of types), `:result` (a type) and `:fn` (the implementation, receiving
  and returning Clojure values). Functions can be called both as global
  `f(x, y)` and as member `x.f(y)` functions. Several definitions with
  the same `:name` and different `:args` are overloads. A definition with
  the signature of a standard function overrides it. Throws for invalid
  or ambiguous definitions.

  Supported types: `:bool`, `:int`, `:uint`, `:double`, `:string`,
  `:bytes`, `:timestamp`, `:duration`, `:list`, `:map` and `:dyn`."
  (^Cel [] (env {}))
  (^Cel [{:keys [functions]}]
   (-> (CelFactory/standardCelBuilder)
       (.setOptions (-> (CelOptions/current)
                        (.enableOptionalSyntax true)
                        (.build)))
       (.setStandardMacros ^Iterable (vec (CelStandardMacro/values)))
       (.addCompilerLibraries ^Iterable (vector CelOptionalLibrary/INSTANCE))
       (.addRuntimeLibraries ^Iterable (vector CelOptionalLibrary/INSTANCE))
       (.addFunctionDeclarations ^Iterable (vec (function-decls functions)))
       (.addFunctionBindings ^Iterable (vec (mapcat function-bindings functions)))
       (.build)
       (validate-env))))

(def ^:private default-env (env))

(defn- parent ^CelNavigableExpr [^CelNavigableExpr node]
  (.orElse ^Optional (.parent node) nil))

(defn- bound-by-comprehension?
  "Whether identifier `node` refers to a variable introduced by an
   enclosing comprehension. The iteration range and accumulator
   initializer are evaluated outside of the comprehension scope."
  [^CelNavigableExpr node ident]
  (loop [child node
         p (parent node)]
    (cond
      (nil? p) false

      (and (= CelExpr$ExprKind$Kind/COMPREHENSION (.getKind p))
           (let [^CelExpr$CelComprehension c (.comprehension ^CelExpr (.expr p))]
             (and (contains? #{(.iterVar c) (.iterVar2 c) (.accuVar c)} ident)
                  (not (contains? #{(.id ^CelExpr (.iterRange c))
                                    (.id ^CelExpr (.accuInit c))}
                                  (.id ^CelExpr (.expr child)))))))
      true

      :else (recur p (parent p)))))

(defn- ident-name ^String [^CelNavigableExpr node]
  (.name (.ident ^CelExpr (.expr node))))

(defn- declared?
  "Whether `ident` already resolves in `cel-env`, such as builtin type
   names (`int`, `dyn`, `optional_type`...), which variable declarations
   must not shadow"
  [^Cel cel-env ^String ident]
  (not (.hasError (.check cel-env (.getAst (.parse cel-env ident))))))

(defn- free-variables
  "Names of the variables referenced by `ast` that it does not bind itself
   and that are not declared in `cel-env`"
  [^Cel cel-env ^CelAbstractSyntaxTree ast]
  (into #{}
        (comp (filter #(= CelExpr$ExprKind$Kind/IDENT (.getKind ^CelNavigableExpr %)))
              (remove #(bound-by-comprehension? % (ident-name %)))
              (map ident-name)
              (remove #(declared? cel-env %)))
        (.toList (.allNodes (.getRoot (CelNavigableAst/fromAst ast))))))

(defrecord ^:private Program [program variables])

(defn make-program
  "Build a program from the given input string, in the environment
   built by `env` or the default one. Throws for invalid expressions."
  ([input]
   (make-program default-env input))
  ([^Cel cel-env ^String input]
   (try
     (let [ast (.getAst (.parse cel-env input))
           variables (free-variables cel-env ast)
           ^Cel cel (-> (.toCelBuilder cel-env)
                        (.addVarDeclarations ^Iterable (mapv #(CelVarDecl/newVarDeclaration ^String % SimpleType/DYN)
                                                             variables))
                        (.build))]
       (->Program (.createProgram cel (.getAst (.check cel ast)))
                  variables))
     (catch CelValidationException e
       (throw (validation-error "Unable to parse CEL expression" e))))))

(defn- activation
  "Map of the program variables found in `bindings`, converted to CEL
   values. Bindings can be named by keywords or strings."
  ^Map [variables bindings]
  (let [m (HashMap.)]
    (doseq [^String v variables]
      (let [k (keyword v)]
        (cond
          (contains? bindings k) (.put m v (values/->cel (get bindings k)))
          (contains? bindings v) (.put m v (values/->cel (get bindings v))))))
    m))

(defn- evaluate
  "Evaluate `program`, yields its result or an `ExceptionInfo` on error"
  [{:keys [^CelRuntime$Program program variables]} bindings]
  (try
    (let [res (.eval program (activation variables bindings))]
      (if (instance? CelUnknownSet res)
        (ex-info "no such attribute" {})
        (values/->clj res)))
    (catch CelEvaluationException e
      (ex-info (ex-message e) {} e))))

(defn- handle-output
  "Output handler, handles the `throw-on-error?` key in the provided
   configuration."
  [res {:keys [throw-on-error?]}]
  (when (and (instance? ExceptionInfo res) (true? throw-on-error?))
    (throw res))
  res)

(defn eval-for
  "Evaluate a parsed program (as given by `make-program`) with
  bindings. `bindings` is a map of bindings to install in the program's
  namespace. `opts` is a map of the following keys:

|             Option |                                                    Value |
|--------------------+----------------------------------------------------------|
|   :throw-on-error? |        Whether to throw on error results. Default: false |"
  ([program bindings]
   (eval-for program bindings {}))
  ([program bindings opts]
   (-> (evaluate program bindings)
       (handle-output opts))))

(defn parse-eval
  "Merged parsing and evaluation of a CEL expression.
  `config` accepts the following keys:

|             Option |                                                    Value |
|--------------------+----------------------------------------------------------|
|               :env |                  An environment, as returned by `env` |
|          :bindings |  A map of bindings to install in the program's namespace |
|   :throw-on-error? |        Whether to throw on error results. Default: false |"
  ([config input]
   (eval-for (make-program (:env config default-env) input)
             (:bindings config)
             config))
  ([input]
   (parse-eval {} input)))
