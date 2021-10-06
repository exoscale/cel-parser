(ns
 exoscale.cel.generated.macros-test
  "Generated test - Tests for CEL macros."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  macros-exists-list_elem_all_true-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 2, 3].exists(e, e > 0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].exists(e, e > 0)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-list_elem_some_true-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 2, 3].exists(e, e == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].exists(e, e == 2)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-list_elem_none_true-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 2, 3].exists(e, e > 3)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].exists(e, e > 3)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists-list_elem_type_shortcircuit-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 'foo', 3].exists(e, e != '1')"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 'foo', 3].exists(e, e != '1')")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-list_elem_type_exhaustive-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 'foo', 3].exists(e, e == '10')"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 'foo', 3].exists(e, e == '10')")]
      (is (helper/error? res)))))

(deftest
  macros-exists-list_elem_all_error-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[1, 2, 3].exists(e, e / 0 == 17)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].exists(e, e / 0 == 17)")]
      (is (helper/error? res)))))

(deftest
  macros-exists-list_empty-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n[].exists(e, e == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[].exists(e, e == 2)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists-map_key-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n{'key1':1, 'key2':2}.exists(k, k == 'key2')"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'key1':1, 'key2':2}.exists(k, k == 'key2')")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-not_map_key-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n!{'key1':1, 'key2':2}.exists(k, k == 'key3')"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!{'key1':1, 'key2':2}.exists(k, k == 'key3')")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-map_key_type_shortcircuit-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n{'key':1, 1:21}.exists(k, k != 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'key':1, 1:21}.exists(k, k != 2)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists-map_key_type_exhaustive-test
  (testing
   "Tests for the .exists() macro, which is equivalent to joining the evaluated elements with logical-OR.\n!{'key':1, 1:42}.exists(k, k == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!{'key':1, 1:42}.exists(k, k == 2)")]
      (is (helper/error? res)))))

(deftest
  macros-all-list_elem_all_true-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 2, 3].all(e, e > 0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].all(e, e > 0)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-all-list_elem_some_true-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 2, 3].all(e, e == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].all(e, e == 2)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-all-list_elem_none_true-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 2, 3].all(e, e == 17)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].all(e, e == 17)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-all-list_elem_type_shortcircuit-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 'foo', 3].all(e, e == 1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 'foo', 3].all(e, e == 1)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-all-list_elem_type_exhaustive-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 'foo', 3].all(e, e % 2 == 1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 'foo', 3].all(e, e % 2 == 1)")]
      (is (helper/error? res)))))

(deftest
  macros-all-list_elem_error_shortcircuit-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 2, 3].all(e, 6 / (2 - e) == 6)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].all(e, 6 / (2 - e) == 6)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-all-list_elem_error_exhaustive-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[1, 2, 3].all(e, e / 0 != 17)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].all(e, e / 0 != 17)")]
      (is (helper/error? res)))))

(deftest
  macros-all-list_empty-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n[].all(e, e > 0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[].all(e, e > 0)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-all-map_key-test
  (testing
   "Tests for the .all() macro, which is equivalent to joining the evaluated elements with logical-AND.\n{'key1':1, 'key2':2}.all(k, k == 'key2')"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'key1':1, 'key2':2}.all(k, k == 'key2')")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_empty-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[].exists_one(a, a == 7)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[].exists_one(a, a == 7)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_one_true-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[7].exists_one(a, a == 7)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[7].exists_one(a, a == 7)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists_one-list_one_false-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[8].exists_one(a, a == 7)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[8].exists_one(a, a == 7)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_none-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[1, 2, 3].exists_one(x, x > 20)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].exists_one(x, x > 20)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_one-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[6, 7, 8].exists_one(foo, foo % 5 == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[6, 7, 8].exists_one(foo, foo % 5 == 2)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-exists_one-list_many-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[0, 1, 2, 3, 4].exists_one(n, n % 2 == 1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 2, 3, 4].exists_one(n, n % 2 == 1)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_all-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n['foal', 'foo', 'four'].exists_one(n, n.startsWith('fo'))"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['foal', 'foo', 'four'].exists_one(n, n.startsWith('fo'))")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  macros-exists_one-list_no_shortcircuit-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n[3, 2, 1, 0].exists_one(n, 12 / n > 1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[3, 2, 1, 0].exists_one(n, 12 / n > 1)")]
      (is (helper/error? res)))))

(deftest
  macros-exists_one-map_one-test
  (testing
   "Tests for exists_one() macro. An expression 'L.exists_one(I, E)' is equivalent to 'size(L.filter(I, E)) == 1'.\n{6: 'six', 7: 'seven', 8: 'eight'}.exists_one(foo, foo % 5 == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{6: 'six', 7: 'seven', 8: 'eight'}.exists_one(foo, foo % 5 == 2)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  macros-map-list_empty-test
  (testing
   "Tests for map() macro.\n[].map(n, n / 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[].map(n, n / 2)")]
      (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
  macros-map-list_one-test
  (testing
   "Tests for map() macro.\n[3].map(n, n * n)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[3].map(n, n * n)")]
      (is
       (helper/equal?
        (helper/translate {:listValue {:values [{:int64Value "9"}]}})
        res)))))

(deftest
  macros-map-list_many-test
  (testing
   "Tests for map() macro.\n[2, 4, 6].map(n, n / 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[2, 4, 6].map(n, n / 2)")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue
          {:values
           [{:int64Value "1"} {:int64Value "2"} {:int64Value "3"}]}})
        res)))))

(deftest
  macros-map-list_error-test
  (testing
   "Tests for map() macro.\n[2, 1, 0].map(n, 4 / n)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[2, 1, 0].map(n, 4 / n)")]
      (is (helper/error? res)))))

(deftest
  macros-filter-list_empty-test
  (testing
   "Tests for filter() macro.\n[].filter(n, n % 2 == 0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[].filter(n, n % 2 == 0)")]
      (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
  macros-filter-list_one_true-test
  (testing
   "Tests for filter() macro.\n[2].filter(n, n == 2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[2].filter(n, n == 2)")]
      (is
       (helper/equal?
        (helper/translate {:listValue {:values [{:int64Value "2"}]}})
        res)))))

(deftest
  macros-filter-list_one_false-test
  (testing
   "Tests for filter() macro.\n[1].filter(n, n > 3)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1].filter(n, n > 3)")]
      (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
  macros-filter-list_none-test
  (testing
   "Tests for filter() macro.\n[1, 2, 3].filter(e, e > 3)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].filter(e, e > 3)")]
      (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
  macros-filter-list_some-test
  (testing
   "Tests for filter() macro.\n[0, 1, 2, 3, 4].filter(x, x % 2 == 1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 2, 3, 4].filter(x, x % 2 == 1)")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue {:values [{:int64Value "1"} {:int64Value "3"}]}})
        res)))))

(deftest
  macros-filter-list_all-test
  (testing
   "Tests for filter() macro.\n[1, 2, 3].filter(n, n > 0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3].filter(n, n > 0)")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue
          {:values
           [{:int64Value "1"} {:int64Value "2"} {:int64Value "3"}]}})
        res)))))

(deftest
  macros-filter-list_no_shortcircuit-test
  (testing
   "Tests for filter() macro.\n[3, 2, 1, 0].filter(n, 12 / n > 4)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[3, 2, 1, 0].filter(n, 12 / n > 4)")]
      (is (helper/error? res)))))

(deftest
  macros-nested-filter_all-test
  (testing
   "Tests with nested macros.\n['signer'].filter(signer, ['artifact'].all(artifact, true))"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['signer'].filter(signer, ['artifact'].all(artifact, true))")]
      (is
       (helper/equal?
        (helper/translate
         {:listValue {:values [{:stringValue "signer"}]}})
        res)))))

(deftest
  macros-nested-all_all-test
  (testing
   "Tests with nested macros.\n['signer'].all(signer, ['artifact'].all(artifact, true))"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['signer'].all(signer, ['artifact'].all(artifact, true))")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))
