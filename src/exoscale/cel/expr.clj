(ns ^:no-doc exoscale.cel.expr
  "Expression evaluation primitives"
  (:require [clojure.instant :as instant]
            [clojure.string :as str])
  (:import java.nio.ByteBuffer
           java.nio.charset.CharsetDecoder
           java.nio.charset.StandardCharsets
           java.sql.Timestamp
           java.time.Duration)
  (:refer-clojure :exclude [bool int bool? int? string? double?
                            number? nil? bytes bytes? update
                            any? map? val true? false? eval]))

(set! *warn-on-reflection* true)

(defprotocol TypedValue
  (typeof [this] "report value type")
  (val [this] "extract value")
  (equal? [this other] "equality test")
  (unwrap [this])
  (typed-value? [this] "satisfies? shim"))

(extend-protocol TypedValue
  Object
  (typed-value? [_] false)
  nil
  (typed-value? [_] false))

(defprotocol Sizable
  (sizeof [this] "report size")
  (sizable? [this] "satisfies? shim"))

(extend-protocol Sizable
  Object
  (sizable? [_] false)
  nil
  (sizable? [_] false))

(defprotocol Stringable
  (as-string [this] "coerce to string")
  (stringable? [this] "satisfies? shim"))

(extend-protocol Stringable
  Object
  (stringable? [_] false)
  nil
  (stringable? [_] false))

(defprotocol Indexable
  (index [this k] "select an index or key")
  (indexable? [this] "satisfies? shim"))

(extend-protocol indexable?
  Object
  (indexable? [_] false)
  nil
  (indexable? [_] false))

(defprotocol Container
  (has-element [this e]))

(defrecord BoolType [x]
  TypedValue
  (typeof [_] :bool)
  (val [_] x)
  (equal? [this o] (assoc this :x (= this o)))
  (unwrap [_] x)
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_] (str x)))

(defrecord NullType []
  TypedValue
  (typeof [_] :null_type)
  (val [_] nil)
  (unwrap [_] nil)
  (equal? [this o] (BoolType. (= this o)))
  Stringable
  (as-string [_] "null_type"))

(defrecord ErrorType [msg]
  TypedValue
  (typeof [_] :error)
  (val [_] msg)
  (unwrap [_] (ex-info msg {}))
  (equal? [_ _]
    (BoolType. false)))

(defn bool-or
  [x y]
  (if-not (and (instance? BoolType x)
               (instance? BoolType y))
    (ErrorType. "no such overload")
    (BoolType. (or (val x) (val y)))))

(defn bool-and
  [x y]
  (if-not (and (instance? BoolType x)
               (instance? BoolType y))
    (ErrorType. "no such overload")
    (BoolType. (and (val x) (val y)))))

(defn bool-not
  [x]
  (if-not (instance? BoolType x)
    (ErrorType. "no such overload")
    (BoolType. (not (val x)))))

(defrecord IntType [x]
  TypedValue
  (typeof [_] :int)
  (val [_] x)
  (unwrap [_] x)
  (equal? [this o] (BoolType. (= this o)))
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_] (str x)))

(defrecord UintType [x]
  TypedValue
  (typeof [_] :uint)
  (val [_] x)
  (unwrap [_] x)
  (equal? [this o] (BoolType. (= this o)))
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_] (str x)))

(defrecord DoubleType [x]
  TypedValue
  (typeof [_] :double)
  (val [_] x)
  (unwrap [_] x)
  (equal? [this o] (BoolType. (= this o)))
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_] (str x)))

(defrecord StringType [x]
  TypedValue
  (typeof [_] :string)
  (val [_] x)
  (equal? [this o] (BoolType. (= this o)))
  (unwrap [_] x)
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Sizable
  (sizeof [_]
    (IntType. (count x)))
  Stringable
  (as-string [_] x))

(def decoder
  (delay (.newDecoder StandardCharsets/UTF_8)))

(defrecord BytesType [x]
  TypedValue
  (typeof [_] :bytes)
  (val [_] x)
  (equal? [_ other]
    (BoolType.
     (java.util.Arrays/equals ^bytes x ^bytes (val other))))
  (unwrap [_] x)
  Sizable
  (sizeof [_]
    (IntType. (count x)))
  Stringable
  (as-string [_]
    (.decode ^CharsetDecoder @decoder (ByteBuffer/wrap ^bytes x)))
  Comparable
  (compareTo [_ other]
    (loop [x (seq x)
           y (seq (val other))]
      (cond
        (clojure.core/nil? x)
        -1

        (clojure.core/nil? y)
        1

        :else
        (let [order (compare (first x) (first y))]
          (if (zero? order)
            (recur (seq (rest x)) (seq (rest y)))
            order))))))

(defrecord CollType [elems]
  TypedValue
  (typeof [_] :list)
  (val [_] elems)
  (unwrap [_] (mapv unwrap elems))
  (equal? [_ other]
    (let [other-elems (val other)
          c1types (set (map typeof elems))
          c2types (set (map typeof other-elems))]
      (cond
        (= (count elems) (count other-elems) 1)
        (if (= c1types c2types)
          (equal? (first elems) (first other-elems))
          (ErrorType. "no such overload"))

        (not= (count elems) (count other-elems))
        (BoolType. false)

        :else
        (BoolType.
         (every?
          clojure.core/true?
          (for [[e1 e2] (->> (interleave elems other-elems)
                             (partition 2))]
            (and (= (typeof e1) (typeof e2))
                 (:x (equal? e1 e2)))))))))
  Sizable
  (sizeof [_]
    (IntType. (count elems)))
  Indexable
  (index [_ index]
    (nth elems (val index) (ErrorType. "index out of range")))
  (indexable? [_] true)
  Container
  (has-element [_ e]
    (let [types (set (map typeof elems))
          uniform? (= 1 (count types))
          found? (boolean
                  (seq
                   (for [candidate elems
                         :let [eq? (and (= (typeof candidate) (typeof e))
                                        (equal? candidate e))]
                         :when (and eq? (instance? BoolType eq?) (val eq?))]
                     true)))]
      (cond
        found?
        (BoolType. true)

        (empty? elems)
        (BoolType. false)

        uniform?
        (BoolType. false)

        :else
        (ErrorType. "no such overload")))))

(defrecord MapType [entries]
  TypedValue
  (typeof [_] :map)
  (val [_] entries)
  (unwrap [_] (into {} (for [[k v] entries] [(unwrap k) (unwrap v)])))
  (equal? [_ other]
    (let [other-entries (val other)
          m1ktypes (set (map typeof (keys entries)))
          m1vtypes (set (map typeof (vals entries)))
          m2ktypes (set (map typeof (keys other-entries)))]
      (cond
        (= (count entries) (count other-entries) 1)
        (if (and (= m1ktypes m2ktypes)
                 (= m1vtypes m1vtypes))
          (bool-and
           (equal? (first (keys entries))
                   (first (keys other-entries)))
           (equal? (first (vals entries))
                   (first (vals other-entries))))
          (ErrorType. "no such overload"))

        (not= (count entries) (count other-entries))
        (BoolType. false)

        (or (and (= 1 (count m1ktypes)) (< 1 (count m2ktypes)))
            (and (= 1 (count m2ktypes)) (< 1 (count m1ktypes))))
        (ErrorType. "no such overload")

        :else
        (BoolType.
         (every?
          clojure.core/true?
          (for [k (set (concat (keys entries) (keys other-entries)))
                :let [v1 (get entries k)
                      v2 (get other-entries k)]]
            (and (= (typeof v1) (typeof v2))
                 (val (equal? v1 v2)))))))))
  Sizable
  (sizeof [_]
    (IntType. (count entries)))
  Container
  (has-element [_ e]
    (let [types (set (map typeof (keys entries)))
          found? (boolean
                  (first (for [candidate (keys entries)]
                           (and (= (typeof candidate) (typeof e))
                                (equal? candidate e)))))]
      (cond
        (and found? (= :bytes (typeof e)))
        (ErrorType. "unhashable type")

        found?
        (BoolType. true)

        (empty? entries)
        (BoolType. false)

        (contains? types :bytes)
        (ErrorType. "unhashable type")

        (not (contains? types (typeof e)))
        (ErrorType. "unsupported key type")

        :else
        (BoolType. false))))
  Indexable
  (index [_ k]
    (get entries k (ErrorType. "no such key")))
  (indexable? [_] true))

(defrecord ObjectType [subtype x]
  TypedValue
  (typeof [_] subtype)
  (val [_] x)
  (unwrap [_] x)
  (equal? [_ other]
    (BoolType.
     (and (subtype (typeof other)) (= x (val other))))))

(defrecord TypeType [x]
  TypedValue
  (typeof [_] :type)
  (val [_] x)
  (unwrap [this] this)
  (equal? [this other]
    (BoolType.
     (= this other)))
  Stringable
  (as-string [_]
    (name x)))

(defrecord TimestampType [x]
  TypedValue
  (typeof [_] "google.protobuf.Timestamp")
  (val [_] x)
  (unwrap [_] x)
  (equal? [this other]
    (BoolType.
     (= this other)))
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_]
    (str x)))

(defrecord DurationType [x]
  TypedValue
  (typeof [_] "google.protobuf.Duration")
  (val [_] x)
  (unwrap [_] x)
  (equal? [this other]
    (BoolType.
     (= this other)))
  Comparable
  (compareTo [this other]
    (compare (val this) (val other)))
  Stringable
  (as-string [_]
    (str x)))

(defn make-timestamp
  [t]
  (if (or (pos? (compare (instant/read-instant-timestamp
                          "0001-01-01T00:00:00Z") t))
          (pos? (compare t (instant/read-instant-timestamp
                            "9999-12-31T23:59:59.999999999Z"))))
    (ErrorType. "timestamp overflow")
    (TimestampType. t)))

;; builders
;; ========

(defn null [] (NullType.))
(defn bool [x] (BoolType. (boolean x)))
(defn int [x] (IntType. x))
(defn uint [x] (UintType. x))
(defn make-double [x] (DoubleType. x))
(defn string [x] (StringType. x))
(defn bytes [x] (BytesType. (cond-> x (coll? x) byte-array)))
(defn make-list [x] (CollType. (vec x)))
(defn make-map [x] (MapType. x))
(defn object [t o] (ObjectType. t o))
(defn make-type [t] (TypeType. t))
(defn error [m] (ErrorType. m))

;; predicates
;; ==========

(def null? (partial instance? NullType))
(def bool? (partial instance? BoolType))
(def true? #(and (bool? %) (= true (val %))))
(def false? #(and (bool? %) (= false (val %))))
(def int? (partial instance? IntType))
(def uint? (partial instance? UintType))
(def double? (partial instance? DoubleType))
(def number? (some-fn int? uint? double?))
(def string? (partial instance? StringType))
(def bytes? (partial instance? BytesType))
(def collection? (partial instance? CollType))
(def map? (partial instance? MapType))
(def object? (partial instance? ObjectType))
(def any? (comp some? typeof))
(def error? (partial instance? ErrorType))
(def comparable? (partial instance? Comparable))
;; (def indexable?  (partial satisfies? Indexable))
;; (def sizable?    (partial satisfies? Sizable))
;; (def stringable? (partial satisfies? Stringable))
(def timestamp? (partial instance? TimestampType))
(def duration? (partial instance? DurationType))

(defn update
  [x f & args]
  (apply clojure.core/update x :x f args))

(defn select-map
  [m x]
  (if-not (or (int? x)
              (uint? x)
              (string? x)
              (bool? x))
    (error "no such overload")
    (or (get (val m) x)
        (error "no such key"))))

(defn select-list
  [l x]
  (if (int? x)
    (nth (val l) (val x) (error "out of range"))
    (error "no such overload")))

(defn long!
  [x]
  (if (error? x)
    x
    (try
      (if (and (uint? x) (neg? (val x)))
        (error "integer overflow")
        (update x long))
      (catch Exception _
        (error "integer overflow")))))

(defn read-timestamp
  [x]
  (try
    (make-timestamp (instant/read-instant-timestamp (val x)))
    (catch Exception e
      (error (or (ex-message e) "nil")))))

;; Helpers
;; =======

(defn modulo [x y] (long! (update x rem (val y))))
(defn mul [x y] (long! (update x * (val y))))
(defn mul-double [x y] (DoubleType. (* (double (val x)) (double (val y)))))
(defn add [x y] (long! (update x + (val y))))
(defn add-double [x y] (DoubleType. (+ (double (val x)) (double (val y)))))
(defn add-string [x y] (update x str (val y)))
(defn add-coll [x y] (update x concat (val y)))
(defn add-bytes [x y] (bytes (byte-array (concat (seq (val x))
                                                 (seq (val y))))))
(defn sub [x y] (long! (update x - (val y))))
(defn sub-double [x y] (DoubleType. (- (double (val x)) (double (val y)))))
(defn div [x y] (long!
                 (if (= #{Long/MIN_VALUE -1} (set [(val x) (val y)]))
                   (ErrorType. "integer overflow")
                   (update x #(quot %1 %2) (val y)))))
(defn div-double [x y] (DoubleType. (/ (double (val x)) (double (val y)))))
(defn lte-compare [x y] (bool (<= (compare x y) 0)))
(defn lt-compare [x y] (bool (< (compare x y) 0)))
(defn gte-compare [x y] (bool (>= (compare x y) 0)))
(defn gt-compare [x y] (bool (> (compare x y) 0)))
(defn s-matches [s re] (bool (re-matches (re-pattern (val re)) (val s))))
(defn s-contains [s re] (bool (str/includes? (val s) (val re))))
(defn s-starts-with [s re] (bool (str/starts-with? (val s) (val re))))
(defn s-ends-with [s re] (bool (str/ends-with? (val s) (val re))))
(defn not-equal? [e1 e2] (update (equal? e1 e2) not))
(defn negate-int [x] (long! (update x * -1)))
(defn negate-double [x] (update x * -1.0))
(defn report-type [x] (if (error? x) x (TypeType. (typeof x))))
(defn to-double [x] (DoubleType. (double (val x))))
(defn to-bytes [x] (BytesType. (.getBytes ^String (val x))))

(defn m-contains [x y] (bool (contains? (val x) y)))

(defn to-int [x]
  (let [i (long! x)]
    (if (error? i)
      i
      (IntType. (val i)))))

(defn to-uint [x]
  (let [i (long! x)]
    (cond
      (error? i) i
      (neg? (val i)) (ErrorType. "conversion error")
      :else (UintType. (val i)))))

(defn s->double [x]
  (try
    (DoubleType. (Double/parseDouble (val x)))
    (catch Exception _
      (ErrorType. "conversion error"))))

(defn s->int [x]
  (try
    (IntType.
     (Long/parseLong (val x)))
    (catch Exception _
      (ErrorType. "conversion error"))))

(defn s->uint [x]
  (try
    (let [u (Long/parseLong (val x))]
      (if (neg? u)
        (ErrorType. "conversion error")
        (UintType. u)))
    (catch Exception e
      (ErrorType. (ex-message e)))))

(defn s->bool [x]
  (try
    (BoolType. (Boolean/parseBoolean (val x)))
    (catch Exception e
      (ErrorType. (ex-message e)))))

(defn t->int [x]
  (-> x ^Timestamp val .toInstant .getEpochSecond int))

(defn make-duration [x]
  (if (> (long (/ (.toDays (.abs ^Duration x)) 365)) 291)
    (ErrorType. "duration too long")
    (DurationType. x)))

(defn read-simple-duration [x]
  (try
    (when (str/ends-with? x "ns")
      (make-duration
       (java.time.Duration/ofNanos
        (Long/parseLong (subs x 0 (- (count x) 2))))))
    (catch Exception e
      (ex-message e))))

(defn read-duration [x]
  (try
    (make-duration
     (java.time.Duration/parse (str "PT" (str/upper-case (val x)))))
    (catch Exception e
      (or (read-simple-duration (str/lower-case (val x)))
          (ErrorType. (ex-message e))))))

(defn same?
  [args]
  (= 1 (count (distinct (map typeof args)))))

(defn add-time-duration
  [t d]
  (try
    (cond
      (and (instance? DurationType t)
           (instance? TimestampType d))
      (add-time-duration d t)

      (instance? TimestampType t)
      (make-timestamp
       (java.util.Date/from (.plus (.toInstant ^Timestamp (val t)) ^Duration (val d))))

      :else
      (update t #(.plus ^Duration %1 ^Duration %2) (val d)))
    (catch Exception e
      (ErrorType. (ex-message e)))))

(defn sub-time-duration
  [t d]
  (try
    (cond
      (and (instance? TimestampType t) (instance? TimestampType d))
      (make-duration (java.time.Duration/between (.toInstant ^Timestamp (val t))
                                                 (.toInstant ^Timestamp (val d))))

      (and (instance? DurationType t) (instance? TimestampType d))
      (sub-time-duration d t)

      (instance? TimestampType t)
      (make-timestamp
       (java.util.Date/from (.minus (.toInstant ^Timestamp (val t)) ^Duration (val d))))

      :else
      (update t #(.minus ^Duration %1 ^Duration %2) (val d)))
    (catch Exception e
      (ErrorType. (or (ex-message e) "npe")))))

(defn get-date
  ([t]
   (get-date t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/DATE)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-full-year
  ([t]
   (get-full-year t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/YEAR)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-day-of-week
  ([t]
   (get-day-of-week t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/DAY_OF_WEEK)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-day-of-month
  ([t]
   (get-day-of-month t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/DAY_OF_MONTH)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-day-of-year
  ([t]
   (get-day-of-year t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/DAY_OF_YEAR)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-month
  ([t]
   (get-month t (StringType. "UTC")))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/MONTH)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-hours
  ([t]
   (try
     (if (duration? t)
       (IntType. (-> t ^Duration val .toHours))
       (get-hours t (StringType. "UTC")))
     (catch Exception e
       (ErrorType. (ex-message e)))))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/HOUR)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-minutes
  ([t]
   (try
     (if (duration? t)
       (IntType. (-> t ^Duration val .toMinutes))
       (get-minutes t (StringType. "UTC")))
     (catch Exception e
       (ErrorType. (ex-message e)))))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/MINUTE)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-milliseconds
  ([t]
   (try
     (if (duration? t)
       (IntType. (-> t ^Duration val .toSeconds))
       (get-milliseconds t (StringType. "UTC")))
     (catch Exception e
       (ErrorType. (ex-message e)))))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/MILLISECOND)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn get-seconds
  ([t]
   (try
     (if (duration? t)
       (IntType. (-> t ^Duration val .toSeconds))
       (get-seconds t (StringType. "UTC")))
     (catch Exception e
       (ErrorType. (ex-message e)))))
  ([t tz]
   (try
     (let [calendar (doto (java.util.Calendar/getInstance
                           (java.util.TimeZone/getTimeZone
                            (str (val tz))))
                      (.setTime (val t)))]
       (IntType. (.get calendar java.util.Calendar/SECOND)))
     (catch Exception e
       (ErrorType. (ex-message e))))))

(defn any->string
  [x]
  (try
    (StringType.
     (as-string x))
    (catch Exception e
      (error (ex-message e)))))

;; Overloads
;; =========

(def standard-overloads
  [{:when :inequality :on [any? any?] :guard same? :handler not-equal?}
   {:when :equality :on [any? any?] :guard same? :handler equal?}
   {:when :index :on [map? any?] :handler select-map}
   {:when :index :on [collection? int?] :handler select-list}
   {:when :select :on [map? string?] :handler select-map}
   {:when :modulo :on [int? int?] :handler modulo}
   {:when :modulo :on [uint? uint?] :handler modulo}
   {:when :mul :on [uint? uint?] :handler mul}
   {:when :mul :on [int? int?] :handler mul}
   {:when :mul :on [double? double?] :handler mul-double}
   {:when :add :on [uint? uint?] :handler add}
   {:when :add :on [int? int?] :handler add}
   {:when :add :on [double? double?] :handler add-double}
   {:when :add :on [string? string?] :handler add-string}
   {:when :add :on [collection? collection?] :handler add-coll}
   {:when :add :on [bytes? bytes?] :handler add-bytes}
   {:when :add :on [timestamp? duration?] :handler add-time-duration}
   {:when :sub :on [timestamp? duration?] :handler sub-time-duration}
   {:when :sub :on [timestamp? timestamp?] :handler sub-time-duration}
   {:when :add :on [duration? timestamp?] :handler add-time-duration}
   {:when :add :on [duration? duration?] :handler add-time-duration}
   {:when :sub :on [duration? duration?] :handler sub-time-duration}
   {:when :sub :on [int? int?] :handler sub}
   {:when :sub :on [uint? uint?] :handler sub}
   {:when :sub :on [double? double?] :handler sub-double}
   {:when :div :on [uint? uint?] :handler div}
   {:when :div :on [int? int?] :handler div}
   {:when :div :on [double? double?] :handler div-double}
   {:when :negate :on [int?] :handler negate-int}
   {:when :negate :on [double?] :handler negate-double}
   {:when :lte :on [comparable? comparable?] :guard same? :handler lte-compare}
   {:when :gte :on [comparable? comparable?] :guard same? :handler gte-compare}
   {:when :gt :on [comparable? comparable?] :guard same? :handler gt-compare}
   {:when :lt :on [comparable? comparable?] :guard same? :handler lt-compare}
   {:when :in :on [any? map?] :handler #(has-element %2 %1)}
   {:when :in :on [any? collection?] :handler #(has-element %2 %1)}
   {:when :has :on [map? string?] :handler m-contains}
   {:when :matches :on [string? string?] :handler s-matches}
   {:when :contains :on [string? string?] :handler s-contains}
   {:when :size :on [sizable?] :handler sizeof}
   {:when :startsWith :on [string? string?] :handler s-starts-with}
   {:when :endsWith :on [string? string?] :handler s-ends-with}
   {:when :string :on [stringable?] :handler any->string}
   {:when :type :on [any?] :handler report-type}
   {:when :double :on [number?] :handler to-double}
   {:when :double :on [string?] :handler s->double}
   {:when :double :on [double?] :handler identity}
   {:when :int :on [int?] :handler identity}
   {:when :int :on [number?] :handler to-int}
   {:when :uint :on [number?] :handler to-uint}
   {:when :int :on [string?] :handler s->int}
   {:when :int :on [int?] :handler identity}
   {:when :int :on [timestamp?] :handler t->int}
   {:when :uint :on [string?] :handler s->uint}
   {:when :uint :on [uint?] :handler identity}
   {:when :bool :on [string?] :handler s->bool}
   {:when :bytes :on [string?] :handler to-bytes}
   {:when :bytes :on [bytes?] :handler identity}
   {:when :dyn :on [any?] :handler identity}
   {:when :timestamp :on [string?] :handler read-timestamp}
   {:when :timestamp :on [timestamp?] :handler identity}
   {:when :duration :on [string?] :handler read-duration}
   {:when :duration :on [duration?] :handler identity}
   {:when :getDate :on [timestamp?] :handler get-date}
   {:when :getFullYear :on [timestamp?] :handler get-full-year}
   {:when :getFullYear :on [timestamp? string?] :handler get-full-year}
   {:when :getHours :on [timestamp?] :handler get-hours}
   {:when :getHours :on [duration?] :handler get-hours}
   {:when :getHours :on [timestamp? string?] :handler get-hours}
   {:when :getMinutes :on [timestamp?] :handler get-minutes}
   {:when :getMinutes :on [duration?] :handler get-minutes}
   {:when :getMinutes :on [timestamp? string?] :handler get-minutes}
   {:when :getSeconds :on [timestamp?] :handler get-seconds}
   {:when :getSeconds :on [duration?] :handler get-seconds}
   {:when :getSeconds :on [timestamp? string?] :handler get-seconds}
   {:when :getMilliseconds :on [timestamp?] :handler get-milliseconds}
   {:when :getMilliseconds :on [duration?] :handler get-milliseconds}
   {:when :getMilliseconds :on [timestamp? string?] :handler get-milliseconds}
   {:when :getMonth :on [timestamp?] :handler get-month}
   {:when :getMonth :on [timestamp? string?] :handler get-month}
   {:when :getDate :on [timestamp?] :handler get-date}
   {:when :getDate :on [timestamp? string?] :handler get-date}
   {:when :getDayOfWeek :on [timestamp?] :handler get-day-of-week}
   {:when :getDayOfWeek :on [timestamp? string?] :handler get-day-of-week}
   {:when :getDayOfMonth :on [timestamp?] :handler get-day-of-month}
   {:when :getDayOfMonth :on [timestamp? string?] :handler get-day-of-month}
   {:when :getDayOfYear :on [timestamp?] :handler get-day-of-year}
   {:when :getDayOfYear :on [timestamp? string?] :handler get-day-of-year}])

(def overloads
  (reduce #(clojure.core/update %1 (:when %2) conj
                                (select-keys %2 [:on :handler :guard]))
          {}
          standard-overloads))

(defn args-match?
  [arg-defs args]
  (and (= (count arg-defs) (count args))
       (every? (fn [[pred arg]] (pred arg))
               (partition 2 (interleave arg-defs args)))))

(defn find-match
  [matchers args]
  (first
   (for [{:keys [handler] :as m} matchers
         :when (and (if-let [on (:on m)]
                      (args-match? on args)
                      true)
                    (if-let [guard (:guard m)]
                      (guard args)
                      true))]
     handler)))

(defn eval
  [overloads f args]
  (or
   (when-let [matchers (get overloads f)]
     (when-let [handler (find-match matchers args)]
       (apply handler args)))
   (error "no such overload")))

(defn relation-op-name
  [input]
  (case input
    ">" :gt
    ">=" :gte
    "<" :lt
    "<=" :lte
    "==" :equality
    "!=" :inequality
    "in" :in
    "*" :mul
    "/" :div
    "%" :modulo
    "-" :sub
    "+" :add))
