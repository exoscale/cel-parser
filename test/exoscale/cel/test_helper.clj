(ns exoscale.cel.test-helper
  (:require [exoscale.cel.expr :as expr])
  (:import java.util.Base64))

(defn equal?
  [x y]
  (and (= (expr/typeof x) (expr/typeof y))
       (expr/equal? x y)))

(def decoder
  (Base64/getDecoder))

(defn decode-b64
  [s]
  (.decode decoder (str s)))

(def error? expr/error?)

(declare translate)

(defn make-int
  [x]
  (if (string? x)
    (Long/parseLong x)
    x))

(defn make-double
  [x]
  (if (string? x)
    (Double/parseDouble x)
    (double x)))

(defn make-object
  [x]
  (cond
    (= "type.googleapis.com/google.protobuf.Duration" (get x "@type"))
    (expr/read-duration (expr/string (:value x)))

    :else
    (expr/object (get x "@type") (:value x))))

(defn make-coll
  [x]
  (mapv translate (:values x)))

(defn make-map
  [x]
  (reduce conj {}
          (for [e (:entries x )]
            [(translate (:key e))
             (translate (:value e))])))

(def pb-types
  {:uint64Value (comp expr/uint make-int)
   :int64Value  (comp expr/int make-int)
   :boolValue   expr/bool
   :doubleValue (comp expr/make-double make-double)
   :stringValue expr/string
   :objectValue make-object
   :listValue   (comp expr/make-list make-coll)
   :bytesValue  (comp expr/bytes decode-b64)
   :mapValue    (comp expr/make-map make-map)
   :nullValue   (constantly (expr/null))
   :typeValue   expr/make-type})

(defn translate
  [v]
  (let [[t v]   (first v)
        coercer (or (get pb-types t)
                    (throw (ex-info (str "unknown output value type:" t) {})))]
    (coercer v)))

(defn bindings
  [bindings]
  (reduce-kv #(assoc %1 %2 (translate (:value %3))) {} bindings))
