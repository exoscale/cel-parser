(ns ^:no-doc exoscale.cel.unescape
  "Handlers for the escaping logic of unicode and byte strings in CEL"
  (:refer-clojure :exclude [bytes])
  (:require [clojure.string :as str]))

(set! *warn-on-reflection* true)

(defn ex-unable-to-escape-input! [input]
  (throw (ex-info "unable to escape input" {:input input})))

(defn- replace-newlines
  "CEL strings normalize all newline types to line feeds."
  [s]
  (-> s
      (str/replace "\r\n" "\n")
      (str/replace "\r" "\n")))

(defn- extract-body
  "Extract body from a valid string representation"
  [^String input]
  (let [literal? (contains? #{\r \R} (first input))
        input (cond-> input literal? (subs 1))
        input-len (count input)]
    (when (not= (first input) (.charAt input (dec input-len)))
      (ex-unable-to-escape-input! input))
    [literal?
     (cond
       (and (str/starts-with? input "'''")
            (str/ends-with? input "'''"))
       (subs input 3 (- input-len 3))

       (and (str/starts-with? input "\"\"\"")
            (str/ends-with? input "\"\"\""))
       (subs input 3 (- input-len 3))

       :else
       (subs input 1 (- input-len 1)))]))

(defn- ubyte->byte
  "Unsigned to signed conversion for bytes"
  [i]
  (-> i short Short. .byteValue))

(defn- unescape-chars
  [s]
  (-> s
      (str/replace "\\\\" "\\")
      (str/replace "\\?" "?")
      (str/replace "\\'" "'")
      (str/replace "\\\"" "\"")
      (str/replace "\\`" "`")
      (str/replace "\\a" "\u0007")
      (str/replace "\\b" "\b")
      (str/replace "\\f" "\f")
      (str/replace "\\n" "\n")
      (str/replace "\\r" "\r")
      (str/replace "\\t" "\t")
      (str/replace "\\v" "")
      (str/replace #"(?i)\\u[0-9a-f]{8,8}" #(Character/toString
                                             (Long/parseLong (subs % 2) 16)))
      (str/replace #"(?i)\\u[0-9a-f]{4,4}" #(Character/toString
                                             (Long/parseLong (subs % 2) 16)))
      (str/replace #"(?i)\\x[0-9a-f]{2,2}" #(Character/toString
                                             (Long/parseLong (subs % 2) 16)))
      (str/replace #"\\[0-3][0-7]{2,2}" #(Character/toString
                                          (Long/parseLong (subs % 1) 8)))))

(defn- unescape-bytes
  [s]
  (loop [s s
         ba []]
    (cond
      (empty? s)
      (byte-array (count ba) ba)

      (str/starts-with? s "\\\\")
      (recur (subs s 2) (conj ba (byte \\)))

      (str/starts-with? s "\\?")
      (recur (subs s 2) (conj ba (byte \?)))

      (str/starts-with? s "\\'")
      (recur (subs s 2) (conj ba (byte \')))

      (str/starts-with? s "\\\"")
      (recur (subs s 2) (conj ba (byte \")))

      (re-find #"(?i)^\\x[0-9a-f]{2,2}" s)
      (recur (subs s 4) (conj ba (ubyte->byte (Long/parseLong (subs s 2 4) 16))))

      (re-find #"^\\[0-3][0-7]{2,2}" s)
      (recur (subs s 4) (conj ba (ubyte->byte (Long/parseLong (subs s 1 4) 8))))

      :else
      (recur (subs s 1) (conj ba (-> s first byte))))))

(defn string
  "Apply escape rules for UTF-8 strings as explained in
   https://github.com/google/cel-spec/blob/master/doc/langdef.md#string-and-bytes-values"
  [input]
  (let [input (replace-newlines input)]
    (when (< (count input) 2)
      (ex-unable-to-escape-input! input))
    (let [[literal? s] (extract-body input)]
      (cond
        literal? s
        (not (str/includes? s "\\")) s
        :else (unescape-chars s)))))

(defn bytes
  "Apply escape rules for byte strings as explained in
   https://github.com/google/cel-spec/blob/master/doc/langdef.md#string-and-bytes-values"
  [input]
  (let [input (replace-newlines input)]
    (when (< (count input) 2)
      (ex-unable-to-escape-input! input))
    (let [[literal? s] (extract-body input)]
      (if (or literal? (not (str/includes? s "\\")))
        (.getBytes ^String s)
        (unescape-bytes s)))))
