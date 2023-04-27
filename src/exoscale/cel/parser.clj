(ns exoscale.cel.parser
  "A Clojure parser and interpreter for the Google
   Common Expression Language (CEL) syntax as defined
   in https://github.com/google/cel-spec/blob/master/doc/langdef.md"
  (:require [exoscale.cel.antlr :as antlr]
            [exoscale.cel.bindings :as bindings]
            [exoscale.cel.expr :as expr]
            [exoscale.cel.visitor :as visitor]))

(set! *warn-on-reflection* true)

(defn make-program
  "Build a program from the given input string. Throws for invalid
   expressions."
  [input]
  (antlr/make-program input))

(defn- handle-output
  "Output handler, handles both `throw-on-error?` and
   `translate-result?` keys in the provided configuration."
  [res {:keys [throw-on-error? translate-result?]
        :or {translate-result? true
             throw-on-error? false}}]
  (when (and (expr/error? res) (true? throw-on-error?))
    (throw (RuntimeException. ^String (expr/val res))))
  (cond-> res (true? translate-result?) expr/unwrap))

(defn eval-for
  "Evaluate a parsed program (as given by `make-program`) with
  bindings. `bindings` is a map of bindings to install in the program's
  namespace. `opts` is a map of the following keys:

|             Option |                                                    Value |
|--------------------+----------------------------------------------------------|
|   :throw-on-error? |        Whether to throw on error results. Default: false |
| :translate-result? | Whether to transform results into values. Default: true. |"
  ([program bindings]
   (eval-for program bindings {}))
  ([program bindings opts]
   (-> (visitor/build (assoc opts :bindings (bindings/build bindings)))
       (visitor/eval program)
       (handle-output opts))))

(defn parse-eval
  "Merged parsing and evaluation of a CEL expression.
  `config` accepts the following keys:

|             Option |                                                    Value |
|--------------------+----------------------------------------------------------|
|          :bindings |  A map of bindings to install in the program's namespace |
|   :throw-on-error? |        Whether to throw on error results. Default: false |
| :translate-result? | Whether to transform results into values. Default: true. |"
  ([config input]
   (-> (visitor/build (update config :bindings bindings/build))
       (visitor/eval (make-program input))
       (handle-output config)))
  ([input]
   (parse-eval {} input)))

(parse-eval "true == true && true == 1")
