(ns ^:no-doc exoscale.cel.bindings
  "Helpers to create binding maps."
  (:require [exoscale.cel.expr :as expr]))

(set! *warn-on-reflection* true)

(defprotocol Bindable
  (as-binding [this]
    "Yield a value that is valid for use as a CEL parser binding"))

(defn translate-binding
  "Translate a value into another valid for use as a CEL parser binding.
   Handles aggregate types (which `as-binding` does not)."
  [b]
  (cond (expr/typed-value? b) b
        (map? b) (as-binding (into {} b))
        (coll? b) (as-binding (into [] b))
        :else (as-binding b)))

(def defaults
  "Default bindings to install"
  {:bool (expr/make-type :bool)
   :int (expr/make-type :int)
   :uint (expr/make-type :uint)
   :double (expr/make-type :double)
   :string (expr/make-type :string)
   :null (expr/make-type :null)
   :map (expr/make-type :map)
   :list (expr/make-type :list)
   :bytes (expr/make-type :bytes)
   :null_type (expr/make-type :null_type)
   :type (expr/make-type :type)})

(defn build
  "Build a binding map, throws on invalid input."
  [m]
  (when-not (or (map? m) (nil? m))
    (throw (IllegalArgumentException. "build expects a map")))
  (merge defaults
         (reduce-kv #(assoc %1 %2 (translate-binding %3))
                    {}
                    m)))

(defn add
  "Update a binding map with a new one. Only useful outside of this namespace."
  [b k v]
  (assoc b k v))

(extend-protocol Bindable
  Boolean
  (as-binding [this]
    (expr/bool this))
  String
  (as-binding [this]
    (expr/string this))
  clojure.lang.Keyword
  (as-binding [this]
    (expr/string (name this)))
  Long
  (as-binding [this]
    (expr/int this))
  Integer
  (as-binding [this]
    (expr/int this))
  Short
  (as-binding [this]
    (expr/int this))
  Double
  (as-binding [this]
    (expr/make-double this))
  Float
  (as-binding [this]
    (expr/make-double this))
  nil
  (as-binding [_]
    (expr/null))
  clojure.lang.PersistentArrayMap
  (as-binding [this]
    (expr/make-map
     (reduce-kv #(assoc %1 (translate-binding %2) (translate-binding %3))
                {}
                this)))
  clojure.lang.PersistentHashMap
  (as-binding [this]
    (expr/make-map
     (reduce-kv #(assoc %1 (translate-binding %2) (translate-binding %3))
                {}
                this)))
  clojure.lang.PersistentVector
  (as-binding [this]
    (expr/make-list (mapv translate-binding this)))
  java.util.Date
  (as-binding [this]
    (expr/->TimestampType (java.sql.Date. (.getTime this))))
  java.sql.Date
  (as-binding [this]
    (expr/->TimestampType this))
  java.time.Duration
  (as-binding [this]
    (expr/->DurationType this))
  Exception
  (as-binding [this]
    (expr/error (ex-message this))))

(extend (Class/forName "[B")
  Bindable
  {:as-binding expr/bytes})
