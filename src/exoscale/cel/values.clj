;; Converts values between their Clojure representation and the one
;; expected or produced by the CEL runtime.
(ns ^:no-doc exoscale.cel.values
  "Conversion of values between Clojure and CEL"
  (:import (clojure.lang BigInt)
           (com.google.common.primitives UnsignedLong)
           (dev.cel.common.values CelByteString NullValue)
           (java.math BigInteger)
           (java.time Instant)
           (java.util Date List Map Map$Entry Optional)))

(set! *warn-on-reflection* true)

(defn ->cel
  "Convert a Clojure value into one the CEL runtime can evaluate.
   Map keys and keywords become unqualified strings, collections
   become lists, integral types become longs (throws when out of range)
   and other numbers become doubles."
  [x]
  (cond
    (nil? x) NullValue/NULL_VALUE
    (keyword? x) (name x)
    (map? x) (persistent!
              (reduce-kv (fn [m k v] (assoc! m (->cel k) (->cel v)))
                         (transient {})
                         x))
    (coll? x) (mapv ->cel x)
    (or (instance? Integer x) (instance? Short x) (instance? Byte x)
        (instance? BigInt x) (instance? BigInteger x)) (long x)
    (or (instance? Float x) (instance? BigDecimal x) (ratio? x)) (double x)
    (instance? Date x) (Instant/ofEpochMilli (.getTime ^Date x))
    (bytes? x) (CelByteString/of ^bytes x)
    :else x))

(defn ->clj
  "Convert a value produced by the CEL runtime into a Clojure value.
   Optionals are unwrapped to their value or nil."
  [x]
  (cond
    (instance? Optional x) (->clj (.orElse ^Optional x nil))
    (instance? NullValue x) nil
    (instance? Map x) (persistent!
                       (reduce (fn [m ^Map$Entry e]
                                 (assoc! m (->clj (.getKey e)) (->clj (.getValue e))))
                               (transient {})
                               (.entrySet ^Map x)))
    (instance? List x) (mapv ->clj x)
    (instance? UnsignedLong x) (.longValue ^UnsignedLong x)
    (instance? CelByteString x) (.toByteArray ^CelByteString x)
    :else x))
