(ns
 exoscale.cel.generated.lists-test
  "Generated test - Tests for list operations."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  lists-concatentation-list_append-test
  (testing
   "Tests for list concatenation.\n[0, 1, 2] + [3, 4, 5] == [0, 1, 2, 3, 4, 5]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 2] + [3, 4, 5] == [0, 1, 2, 3, 4, 5]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  lists-concatentation-list_not_commutative-test
  (testing
   "Tests for list concatenation.\n[0, 1, 2] + [3, 4, 5] == [3, 4, 5, 0, 1, 2]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 2] + [3, 4, 5] == [3, 4, 5, 0, 1, 2]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  lists-concatentation-list_repeat-test
  (testing
   "Tests for list concatenation.\n[2] + [2]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[2] + [2]")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue {:values [{:int64Value "2"} {:int64Value "2"}]}})
        res)))))

(deftest
  lists-concatentation-empty_empty-test
  (testing
   "Tests for list concatenation.\n[] + []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[] + []")]
      (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
  lists-concatentation-left_unit-test
  (testing
   "Tests for list concatenation.\n[] + [3, 4]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[] + [3, 4]")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue {:values [{:int64Value "3"} {:int64Value "4"}]}})
        res)))))

(deftest
  lists-concatentation-right_unit-test
  (testing
   "Tests for list concatenation.\n[1, 2] + []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2] + []")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue {:values [{:int64Value "1"} {:int64Value "2"}]}})
        res)))))

(deftest
  lists-index-zero_based-test
  (testing
   "List indexing tests.\n[7, 8, 9][0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[7, 8, 9][0]")]
      (is (helper/equal? (helper/translate {:int64Value "7"}) res)))))

(deftest
  lists-index-singleton-test
  (testing
   "List indexing tests.\n['foo'][0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['foo'][0]")]
      (is (helper/equal? (helper/translate {:stringValue "foo"}) res)))))

(deftest
  lists-index-middle-test
  (testing
   "List indexing tests.\n[0, 1, 1, 2, 3, 5, 8, 13][4]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 1, 2, 3, 5, 8, 13][4]")]
      (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))

(deftest
  lists-index-last-test
  (testing
   "List indexing tests.\n['George', 'John', 'Paul', 'Ringo'][3]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['George', 'John', 'Paul', 'Ringo'][3]")]
      (is (helper/equal? (helper/translate {:stringValue "Ringo"}) res)))))

(deftest
  lists-index-range-test
  (testing
   "List indexing tests.\n[1, 2, 3][3]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3][3]")]
      (is (helper/error? res)))))

(deftest
  lists-in-empty-test
  (testing
   "List membership tests.\n7 in []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "7 in []")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  lists-in-singleton-test
  (testing
   "List membership tests.\n4u in [4u]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "4u in [4u]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  lists-in-first-test
  (testing
   "List membership tests.\n'alpha' in ['alpha', 'beta', 'gamma']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'alpha' in ['alpha', 'beta', 'gamma']")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  lists-in-middle-test
  (testing
   "List membership tests.\n3 in [5, 4, 3, 2, 1]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "3 in [5, 4, 3, 2, 1]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  lists-in-last-test
  (testing
   "List membership tests.\n20u in [4u, 6u, 8u, 12u, 20u]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "20u in [4u, 6u, 8u, 12u, 20u]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  lists-in-missing-test
  (testing
   "List membership tests.\n'hawaiian' in ['meat', 'veggie', 'margarita', 'cheese']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'hawaiian' in ['meat', 'veggie', 'margarita', 'cheese']")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  lists-size-list_empty-test
  (testing
   "List and map size tests.\nsize([])"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size([])")]
      (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
  lists-size-list-test
  (testing
   "List and map size tests.\nsize([1, 2, 3])"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size([1, 2, 3])")]
      (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))

(deftest
  lists-size-map_empty-test
  (testing
   "List and map size tests.\nsize({})"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size({})")]
      (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
  lists-size-map-test
  (testing
   "List and map size tests.\nsize({1: 'one', 2: 'two', 3: 'three'})"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size({1: 'one', 2: 'two', 3: 'three'})")]
      (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))
