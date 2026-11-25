;; Tests for the public CEL API: program building, evaluation,
;; value conversion, optionals, errors and custom functions.
(ns exoscale.cel.parser-test
  (:require [clojure.test :refer [deftest is testing]]
            [exoscale.cel.parser :as parser])
  (:import (java.time Duration Instant)))

(deftest parse-eval-test
  (is (true? (parser/parse-eval "true")))
  (is (= "hello, world!"
         (parser/parse-eval {:bindings {:x "world"}} "'hello, ' + x + '!'"))))

(deftest make-program-eval-for-test
  (let [program (parser/make-program "'hello, ' + x + '!'")]
    (is (= "hello, world!" (parser/eval-for program {:x "world"})))
    (is (= "hello, you!" (parser/eval-for program {:x "you"})))))

(deftest bindings-test
  (testing "keyword and string binding names"
    (is (= 42 (parser/parse-eval {:bindings {:x 41}} "x + 1")))
    (is (= 42 (parser/parse-eval {:bindings {"x" 41}} "x + 1"))))
  (testing "nested maps with keyword keys"
    (is (= "deep" (parser/parse-eval {:bindings {:m {:a {:b "deep"}}}} "m.a.b")))
    (is (= "baz" (parser/parse-eval {:bindings {:m {:foo/bar "baz"}}} "m.bar"))))
  (testing "arbitrary precision numbers are narrowed"
    (is (= 2 (parser/parse-eval {:bindings {:x 1N}} "x + 1")))
    (is (= 2 (parser/parse-eval {:bindings {:x (biginteger 1)}} "x + 1")))
    (is (= 2.5 (parser/parse-eval {:bindings {:x 1.5M}} "x + 1.0")))
    (is (= 1.5 (parser/parse-eval {:bindings {:x 3/2}} "x")))
    (is (thrown? IllegalArgumentException
                 (parser/parse-eval {:bindings {:x (bigint 1e30)}} "x"))))
  (testing "numeric types are widened"
    (is (= 2 (parser/parse-eval {:bindings {:i (int 1)}} "i + 1")))
    (is (= 2 (parser/parse-eval {:bindings {:i (short 1)}} "i + 1")))
    (is (= 2.5 (parser/parse-eval {:bindings {:f (float 1.5)}} "f + 1.0"))))
  (testing "keywords become strings"
    (is (true? (parser/parse-eval {:bindings {:k :foo}} "k == 'foo'"))))
  (testing "nil becomes null"
    (is (true? (parser/parse-eval {:bindings {:n nil}} "n == null")))
    (is (true? (parser/parse-eval {:bindings {:m {:a nil}}} "m.a == null"))))
  (testing "collections become lists"
    (is (true? (parser/parse-eval {:bindings {:l [1 2 3]}} "l.exists(x, x > 2)")))
    (is (true? (parser/parse-eval {:bindings {:l '(1 2 3)}} "2 in l")))
    (is (true? (parser/parse-eval {:bindings {:l #{1}}} "1 in l"))))
  (testing "bytes"
    (is (true? (parser/parse-eval {:bindings {:b (.getBytes "ab")}} "b == b'ab'"))))
  (testing "timestamps and durations"
    (is (true? (parser/parse-eval {:bindings {:t (Instant/parse "2009-02-13T23:31:30Z")}}
                                  "t == timestamp('2009-02-13T23:31:30Z')")))
    (is (true? (parser/parse-eval {:bindings {:t #inst "2009-02-13T23:31:30Z"}}
                                  "t == timestamp('2009-02-13T23:31:30Z')")))
    (is (true? (parser/parse-eval {:bindings {:d (Duration/ofSeconds 1)}}
                                  "d == duration('1s')")))))

(deftest results-test
  (is (= 3 (parser/parse-eval "1u + 2u")))
  (is (= [2 3] (parser/parse-eval "[1, 2, 3].filter(x, x > 1)")))
  (is (vector? (parser/parse-eval "[1, 2, 3].filter(x, x > 1)")))
  (is (= {"a" [1 2.0]} (parser/parse-eval "{'a': [1, 2.0]}")))
  (is (nil? (parser/parse-eval "null")))
  (is (= "ab" (String. ^bytes (parser/parse-eval "b'ab'"))))
  (is (= (Instant/parse "2009-02-13T23:31:30Z")
         (parser/parse-eval "timestamp('2009-02-13T23:31:30Z')")))
  (is (= (Duration/ofSeconds 1) (parser/parse-eval "duration('1s')"))))

(deftest optionals-test
  (is (= 3 (parser/parse-eval {:bindings {:m {}}} "m.?a.orValue(3)")))
  (is (= 9 (parser/parse-eval {:bindings {:m {:a 9}}} "m.?a.orValue(3)")))
  (is (true? (parser/parse-eval {:bindings {:m {"a" 1}}} "m[?'a'].hasValue()")))
  (is (= [1 3] (parser/parse-eval "[1, ?optional.none(), 3]")))
  (testing "optional results are unwrapped"
    (is (= 1 (parser/parse-eval "optional.of(1)")))
    (is (nil? (parser/parse-eval "optional.none()")))
    (is (= [1 nil] (parser/parse-eval "[optional.of(1), optional.none()]")))))

(deftest errors-test
  (testing "invalid syntax throws at program creation"
    (let [e (try (parser/make-program "x +") (catch Exception e e))]
      (is (= "Unable to parse CEL expression" (ex-message e)))
      (is (string? (-> e ex-data :errors first :msg)))
      (is (= 1 (-> e ex-data :errors first :line)))))
  (testing "evaluation errors are returned"
    (let [res (parser/parse-eval "1 / 0")]
      (is (instance? clojure.lang.ExceptionInfo res))
      (is (re-find #"by zero" (ex-message res)))))
  (testing "evaluation errors are thrown with throw-on-error?"
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"by zero"
                          (parser/parse-eval {:throw-on-error? true} "1 / 0"))))
  (testing "unbound variables are errors"
    (is (= "no such attribute" (ex-message (parser/parse-eval "y + 1"))))
    (is (thrown-with-msg? clojure.lang.ExceptionInfo #"no such attribute"
                          (parser/eval-for (parser/make-program "y") {} {:throw-on-error? true}))))
  (testing "unbound variables on unevaluated branches are fine"
    (is (true? (parser/parse-eval "true || y")))))

(deftest comprehension-variables-test
  (is (true? (parser/parse-eval {:bindings {:x 1 :l [2 3]}}
                                "x > 0 && l.all(x, x > 1)")))
  (is (= [3 4] (parser/parse-eval {:bindings {:y 1 :l [2 3]}}
                                  "l.map(x, x + y)"))))

(deftest type-names-test
  (is (true? (parser/parse-eval {:bindings {:x 1}} "type(x) == int")))
  (is (true? (parser/parse-eval {:bindings {:x "a"}} "type(x) == string")))
  (is (true? (parser/parse-eval "type(dyn) == type")))
  (is (true? (parser/parse-eval "type(optional.none()) == optional_type"))))

(defn- in-range [ip range]
  (= ip range))

(deftest functions-test
  (let [env (parser/env {:functions [{:name "inIpRange"
                                      :args [:string :string]
                                      :result :bool
                                      :fn in-range}]})]
    (testing "global and member call styles"
      (is (true? (parser/eval-for (parser/make-program env "inIpRange('a', 'a')") {})))
      (is (false? (parser/eval-for (parser/make-program env "inIpRange('a', 'b')") {})))
      (is (true? (parser/eval-for (parser/make-program env "'a'.inIpRange('a')") {}))))
    (testing "on bound variables"
      (is (true? (parser/eval-for (parser/make-program env "ip.inIpRange('a')") {:ip "a"}))))
    (testing "argument type mismatch is an error"
      (is (re-find #"No matching overload"
                   (ex-message (parser/eval-for (parser/make-program env "ip.inIpRange('a')") {:ip 1})))))
    (testing "env through parse-eval"
      (is (true? (parser/parse-eval {:env env :bindings {:ip "a"}} "inIpRange(ip, 'a')"))))
    (testing "unknown functions fail at program creation"
      (is (thrown? clojure.lang.ExceptionInfo (parser/make-program "inIpRange('a', 'a')")))))
  (testing "overloads with same name"
    (let [env (parser/env {:functions [{:name "twice" :args [:int] :result :int :fn #(* 2 %)}
                                       {:name "twice" :args [:string] :result :string :fn #(str % %)}]})]
      (is (= 4 (parser/eval-for (parser/make-program env "twice(2)") {})))
      (is (= "abab" (parser/eval-for (parser/make-program env "'ab'.twice()") {})))))
  (testing "overloads of standard functions"
    (let [env (parser/env {:functions [{:name "size" :args [:int] :result :int :fn #(* 10 %)}]})]
      (is (= 30 (parser/eval-for (parser/make-program env "size(3)") {})))
      (is (= 3 (parser/eval-for (parser/make-program env "size('abc')") {})))))
  (testing "declarations with the signature of a standard function override it"
    (let [env (parser/env {:functions [{:name "size" :args [:string] :result :int :fn (constantly 42)}]})]
      (is (= 42 (parser/eval-for (parser/make-program env "'abc'.size()") {})))))
  (testing "declarations ambiguous with standard functions fail at env creation"
    (let [e (try (parser/env {:functions [{:name "first" :args [:list] :result :dyn :fn first}]})
                 (catch Exception e e))]
      (is (= "Invalid CEL environment" (ex-message e)))
      (is (re-find #"overlapping overload" (-> e ex-data :errors first :msg)))))
  (testing "functions receive and return clojure values"
    (let [env (parser/env {:functions [{:name "keys" :args [:map] :result :list
                                        :fn (fn [m] (vec (sort (keys m))))}
                                       {:name "head" :args [:list] :result :dyn
                                        :fn (fn [l] (when (vector? l) (first l)))}
                                       {:name "later" :args [:timestamp :duration] :result :timestamp
                                        :fn (fn [^Instant t ^Duration d] (.plus t d))}
                                       {:name "big" :args [:uint] :result :uint
                                        :fn (fn [u] (inc u))}
                                       {:name "nothing" :args [] :result :dyn
                                        :fn (constantly nil)}]})]
      (is (= ["a" "b"] (parser/eval-for (parser/make-program env "keys(m)") {:m {:b 1 :a 2}})))
      (is (= 1 (parser/eval-for (parser/make-program env "head([1, 2])") {})))
      (is (= (Instant/parse "2009-02-13T23:31:31Z")
             (parser/eval-for (parser/make-program env "later(timestamp('2009-02-13T23:31:30Z'), duration('1s'))") {})))
      (is (= 3 (parser/eval-for (parser/make-program env "big(2u)") {})))
      (is (true? (parser/eval-for (parser/make-program env "big(2u) + 1u == 4u") {})))
      (is (true? (parser/eval-for (parser/make-program env "nothing() == null") {}))))))
