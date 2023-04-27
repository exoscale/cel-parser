(ns ^:no-doc exoscale.cel.visitor
  "Visitor based parser."
  (:refer-clojure :exclude [eval parse-long])
  (:require [clojure.string :as str]
            [exoscale.cel.antlr :as antlr]
            [exoscale.cel.bindings :as bindings]
            [exoscale.cel.expr :as expr]
            [exoscale.cel.unescape :as unescape])
  (:import exoscale.cel.CELBaseVisitor
           exoscale.cel.CELParser$ExprContext))

(set! *warn-on-reflection* true)

(defn- ctx->bytes->text
  ^String [^exoscale.cel.CELParser$BytesContext ctx]
  (-> ctx .BYTES .getText))

(defn- ctx->string->text
  ^String [^exoscale.cel.CELParser$StringContext ctx]
  (-> ctx .STRING .getText))

(defn- ctx->num-uint->text
  ^String [^exoscale.cel.CELParser$UintContext ctx]
  (-> ctx .NUM_UINT .getText))

(defn- ctx->num-int->text
  ^String [^exoscale.cel.CELParser$IntContext ctx]
  (-> ctx .NUM_INT .getText))

(defn- ctx->num-float->text
  ^String [^exoscale.cel.CELParser$DoubleContext ctx]
  (-> ctx .NUM_FLOAT .getText))

(defn- parse-long
  [s radix]
  (try (Long/parseLong s radix)
       (catch IllegalArgumentException _
         (BigInteger. ^String s (int radix)))))

(defn- parse-int
  [s]
  (if (str/starts-with? s "0x")
    (expr/int (parse-long (subs s 2) 16))
    (expr/int (parse-long s 10))))

(defn- parse-uint
  [s]
  (let [tail? (str/ends-with? s "u")
        hex? (str/starts-with? s "0x")
        input (subs s (if hex? 2 0) (cond-> (count s) tail? dec))]
    (expr/uint (parse-long input (if hex? 16 10)))))

(defn eval
  "Evaluate a parsetree against the provided visitor.
   Parse trees are usually generated through `antlr/make-program`,
   while the visitor is an implementation of `CELVisitor` as given
   by `build` in this namespace, for example."
  [^CELBaseVisitor visitor input]
  (.visit visitor input))

(defn build
  "Yields a new instance of `CELVisitor` which yields the computed
  result"
  [{:keys [bindings overloads] :as cfg
    :or {overloads expr/overloads}}]
  (proxy [CELBaseVisitor] []
    (visitBoolTrue [^exoscale.cel.CELParser$BoolTrueContext _]
      (expr/bool true))
    (visitBoolFalse [^exoscale.cel.CELParser$BoolFalseContext _]
      (expr/bool false))
    (visitNull [^exoscale.cel.CELParser$NullContext _]
      (expr/null))
    (visitBytes [^exoscale.cel.CELParser$BytesContext ctx]
      (-> (subs (ctx->bytes->text ctx) 1)
          unescape/bytes expr/bytes))
    (visitString [^exoscale.cel.CELParser$StringContext ctx]
      (try
        (-> ctx ctx->string->text unescape/string expr/string)
        (catch Exception e
          (expr/error (ex-message e)))))
    (visitUint [ctx]
      (-> ctx ctx->num-uint->text parse-uint))
    (visitInt [^exoscale.cel.CELParser$IntContext ctx]
      (cond-> (-> ctx ctx->num-int->text parse-int)
        (some? (.MINUS ctx))
        (expr/update (comp long (partial * -1)))))
    (visitDouble [^exoscale.cel.CELParser$DoubleContext ctx]
      (cond-> (-> ctx ctx->num-float->text Double/parseDouble expr/make-double)
        (some? (.MINUS ctx))
        (expr/update * -1.0)))
    (visitNested [^exoscale.cel.CELParser$NestedContext ctx]
      (.visit ^CELBaseVisitor this (.e ctx)))
    (visitLogicalNot [^exoscale.cel.CELParser$LogicalNotContext ctx]
      (let [expr (.visit ^CELBaseVisitor this (.member ctx))]
        (if (expr/bool? expr)
          (expr/update expr not)
          (expr/error "no such overload"))))
    (visitNegate [^exoscale.cel.CELParser$NegateContext ctx]
      (let [expr (.visit ^CELBaseVisitor this (.member ctx))]
        (try
          (expr/eval overloads :negate [expr])
          (catch ArithmeticException e
            (expr/error (ex-message e))))))
    (visitCreateList [^exoscale.cel.CELParser$CreateListContext ctx]
      (let [elems (for [e (some-> ctx .elems .expr)]
                    (.visit ^CELBaseVisitor this e))]
        (expr/make-list elems)))
    (visitCreateStruct [^exoscale.cel.CELParser$CreateStructContext ctx]
      (let [keys (for [k (some-> ctx .entries .keys)]
                   (.visit ^CELBaseVisitor this k))
            vals (for [v (some-> ctx .entries .values)]
                   (.visit ^CELBaseVisitor this v))]
        (expr/make-map (reduce conj {}
                               (->> (interleave keys vals)
                                    (partition 2)
                                    (map vec))))))
    (visitConstantLiteral [^exoscale.cel.CELParser$ConstantLiteralContext ctx]
      (.visit ^CELBaseVisitor this (.literal ctx)))

    (visitIdentOrGlobalCall [^exoscale.cel.CELParser$IdentOrGlobalCallContext ctx]
      (let [id (-> ctx .id .getText keyword)
            op? (nil? (.op ctx))
            argseq (some->> ctx .args .expr)]
        (cond
          op?
          (or (get bindings id)
              (expr/error "no such attribute"))

          (= :has id)
          (if (not= (count argseq) 1)
            (expr/error "wrong argument count for has")
            (let [arg (.getText (first argseq))
                  last-index (str/last-index-of arg ".")]
              (if (nil? last-index)
                (expr/error "invalid arguments for has()")
                (let [input (subs arg 0 last-index)
                      m (.visit ^CELBaseVisitor this (antlr/make-program input))
                      field (expr/string (subs arg (inc last-index)))]
                  (expr/eval overloads :has [m field])))))

          :else
          (or (expr/eval overloads id (map #(.visit ^CELBaseVisitor this %) argseq))
              (expr/error "no such overload")))))
    (visitCreateMessage [^exoscale.cel.CELParser$CreateMessageContext ctx]
      (let [_ (keyword (str (-> ctx .member .getText)))]
        (expr/error "not implemented")))
    (visitIndex [^exoscale.cel.CELParser$IndexContext ctx]
      (let [member (.visit ^CELBaseVisitor this (.member ctx))
            index (.visit ^CELBaseVisitor this (.index ctx))]
        (try
          (expr/eval overloads :index [member index])
          (catch IndexOutOfBoundsException e
            (expr/error (ex-message e))))))
    (visitSelectOrCall [^exoscale.cel.CELParser$SelectOrCallContext ctx]
      (let [id (-> ctx .id .getText str keyword)
            member (.visit ^CELBaseVisitor this (.member ctx))
            select? (nil? (.open ctx))
            argseq (some->> ctx .args .expr)]
        (cond
          select?
          (expr/eval overloads :select [member (expr/string (name id))])

          ;; Macro handling
          (= :map id)
          (cond
            (not= (count argseq) 2)
            (expr/error "wrong argument count for map")

            (not (expr/collection? member))
            (expr/error "wrong input for map")

            :else
            (let [binding (keyword (.getText (first argseq)))
                  input (.getText (second argseq))]
              (loop [[head & tail] (expr/val member)
                     res []]
                (if (nil? head)
                  (expr/make-list res)
                  (let [e (eval
                           (build (update cfg :bindings
                                          bindings/add binding head))
                           (antlr/make-program input))]
                    (if (expr/error? e)
                      e
                      (recur tail (conj res e))))))))

          (= :all id)
          (cond
            (not= (count argseq) 2)
            (expr/error "wrong argument count for map")

            (not (or (expr/collection? member) (expr/map? member)))
            (expr/error "wrong input for all")

            :else
            (let [binding (keyword (.getText (first argseq)))
                  input (.getText (second argseq))]
              (loop [[head & tail] (cond-> (expr/val member)
                                     (expr/map? member)
                                     keys)
                     last-error nil]
                (if (nil? head)
                  (if (some? last-error) last-error (expr/bool true))
                  (let [e (eval
                           (build (update cfg :bindings
                                          bindings/add binding head))
                           (antlr/make-program input))]
                    (cond
                      (expr/true? e) (recur tail last-error)
                      (expr/false? e) e
                      (expr/error? e) (recur tail e)
                      :else (recur tail (expr/error "no such overload"))))))))

          (= :exists id)
          (cond
            (not= (count argseq) 2)
            (expr/error "wrong argument count for map")

            (not (or (expr/collection? member) (expr/map? member)))
            (expr/error "wrong input for exists")

            :else
            (let [binding (keyword (.getText (first argseq)))
                  input (.getText (second argseq))]
              (loop [[head & tail] (cond-> (expr/val member)
                                     (expr/map? member)
                                     keys)
                     last-error nil]
                (if (nil? head)
                  (if (some? last-error) last-error (expr/bool false))
                  (let [e (eval
                           (build (update cfg :bindings
                                          bindings/add binding head))
                           (antlr/make-program input))]
                    (cond
                      (expr/false? e) (recur tail last-error)
                      (expr/true? e) e
                      (expr/error? e) (recur tail e)
                      :else (recur tail (expr/error "no such overload"))))))))

          (= :exists_one id)
          (cond
            (not= (count argseq) 2)
            (expr/error "wrong argument count for map")

            (not (or (expr/collection? member) (expr/map? member)))
            (expr/error "wrong input for exists")

            :else
            (let [binding (keyword (.getText (first argseq)))
                  input (.getText (second argseq))]
              (loop [[head & tail] (cond-> (expr/val member)
                                     (expr/map? member)
                                     keys)
                     true-count 0]
                (if (nil? head)
                  (expr/bool (= 1 true-count))
                  (let [e (eval
                           (build (update cfg :bindings
                                          bindings/add binding head))
                           (antlr/make-program input))]
                    (cond
                      (expr/false? e) (recur tail true-count)
                      (expr/true? e) (recur tail (inc true-count))
                      (expr/error? e) e
                      :else (expr/error "no such overload")))))))

          (= :filter id)
          (cond
            (not= (count argseq) 2)
            (expr/error "wrong argument count for filter")

            (not (expr/collection? member))
            (expr/error "wrong input for filter")

            :else
            (let [binding (keyword (.getText (first argseq)))
                  input (.getText (second argseq))]
              (loop [[head & tail] (expr/val member)
                     res []]
                (if (nil? head)
                  (expr/make-list res)
                  (let [test (eval
                              (build (update cfg :bindings
                                             bindings/add binding head))
                              (antlr/make-program input))]
                    (cond
                      (expr/error? test) test
                      (not (expr/bool? test)) (expr/error "no such overload")
                      (expr/true? test) (recur tail (conj res head))
                      :else (recur tail res)))))))

          :else
          (expr/eval overloads
                     id
                     (concat [member] (map #(.visit ^CELBaseVisitor this %) argseq))))))
    (visitPrimaryExpr [^exoscale.cel.CELParser$PrimaryExprContext ctx]
      (.visit ^CELBaseVisitor this (.primary ctx)))

    (visitCalcAddSub [^exoscale.cel.CELParser$CalcAddSubContext ctx]
      (let [op (-> ctx .op .getText)
            args (for [a (.calc ctx)] (.visit ^CELBaseVisitor this a))]
        (try
          (or
           (expr/eval overloads (expr/relation-op-name op) args)
           (expr/error "no such overload"))
          (catch ArithmeticException e
            (expr/error (ex-message e))))))
    (visitCalcMulDiv [^exoscale.cel.CELParser$CalcMulDivContext ctx]
      (let [op (-> ctx .op .getText)
            args (for [a (.calc ctx)] (.visit ^CELBaseVisitor this a))]
        (try
          (expr/eval overloads (expr/relation-op-name op) args)
          (catch Exception e
            (expr/error (ex-message e))))))
    (visitCalcUnary [^exoscale.cel.CELParser$CalcUnaryContext ctx]
      (.visit ^CELBaseVisitor this (.unary ctx)))
    (visitRelationOp [^exoscale.cel.CELParser$RelationOpContext ctx]
      (let [op (-> ctx .op .getText)
            args (for [a (->> ctx .relation)]
                   (.visit ^CELBaseVisitor this a))]
        (try
          (or
           (expr/eval overloads (expr/relation-op-name op) args)
           (expr/error "no such overload"))
          (catch ArithmeticException e
            (expr/error (ex-message e))))))
    (visitRelationCalc [^exoscale.cel.CELParser$RelationCalcContext ctx]
      (.visit ^CELBaseVisitor this (.calc ctx)))
    (visitConditionalOr [^exoscale.cel.CELParser$ConditionalOrContext ctx]
      (if (seq (.e1 ctx))
        (let [args (concat [(.e ctx)] (seq (.e1 ctx)))]
          (loop [[head & tail] args
                 last-error nil]
            (if (nil? head)
              (if (some? last-error)
                last-error
                (expr/bool false))
              (let [res (.visit ^CELBaseVisitor this head)]
                (cond
                  (and (expr/bool? res) (expr/true? res))
                  res

                  (expr/bool? res)
                  (recur tail last-error)

                  (expr/error? res)
                  (recur tail res)

                  :else
                  (recur tail (expr/error "no such overload")))))))
        (.visit ^CELBaseVisitor this (.e ctx))))
    (visitConditionalAnd [^exoscale.cel.CELParser$ConditionalAndContext ctx]
      (if (seq (.e1 ctx))
        (let [args (concat [(.e ctx)] (seq (.e1 ctx)))]
          (loop [[head & tail] args
                 last-error nil]
            (if (nil? head)
              (if (some? last-error)
                last-error
                (expr/bool true))
              (let [res (.visit ^CELBaseVisitor this head)]
                (cond
                  (expr/true? res)
                  (recur tail last-error)

                  (expr/false? res)
                  res

                  (expr/error? res)
                  (recur tail res)

                  :else
                  (recur tail (expr/error "no such overload")))))))
        (.visit ^CELBaseVisitor this (.e ctx))))
    (visitExpr [^exoscale.cel.CELParser$ExprContext ctx]
      (let [e (.visit ^CELBaseVisitor this
                      ^exoscale.cel.CELParser$ConditionalOrContext (.e ctx))
            e1 (.e1 ctx)
            e2 (.e2 ctx)]
        (if (some? e1)
          (cond
            (expr/true? e) (.visit ^CELBaseVisitor this e1)
            (expr/false? e) (.visit ^CELBaseVisitor this e2)
            :else (expr/error "no such overload"))
          e)))
    (visitStart [^exoscale.cel.CELParser$StartContext ctx]
      (.visit ^CELBaseVisitor this (.expr ctx)))))
