(ns
 exoscale.cel.generated.logic-test
  "Generated test - Tests for logical special operators."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  logic-conditional-true_case-test
  (testing
   "Tests for the conditional operator.\ntrue ? 1 : 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true ? 1 : 2")]
      (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
  logic-conditional-false_case-test
  (testing
   "Tests for the conditional operator.\nfalse ? 'foo' : 'bar'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false ? 'foo' : 'bar'")]
      (is (helper/equal? (helper/translate {:stringValue "bar"}) res)))))

(deftest
  logic-conditional-error_case-test
  (testing
   "Tests for the conditional operator.\n2 / 0 > 4 ? 'baz' : 'quux'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2 / 0 > 4 ? 'baz' : 'quux'")]
      (is (helper/error? res)))))

(deftest
  logic-conditional-mixed_type-test
  (testing
   "Tests for the conditional operator.\ntrue ? 'cows' : 17"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true ? 'cows' : 17")]
      (is (helper/equal? (helper/translate {:stringValue "cows"}) res)))))

(deftest
  logic-conditional-bad_type-test
  (testing
   "Tests for the conditional operator.\n'cows' ? false : 17"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'cows' ? false : 17")]
      (is (helper/error? res)))))

(deftest
  logic-AND-all_true-test
  (testing
   "Tests for logical AND.\ntrue && true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true && true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-AND-all_false-test
  (testing
   "Tests for logical AND.\nfalse && false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false && false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-false_left-test
  (testing
   "Tests for logical AND.\nfalse && true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false && true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-false_right-test
  (testing
   "Tests for logical AND.\ntrue && false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true && false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-short_circuit_type_left-test
  (testing
   "Tests for logical AND.\nfalse && 32"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false && 32")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-short_circuit_type_right-test
  (testing
   "Tests for logical AND.\n'horses' && false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'horses' && false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-short_circuit_error_left-test
  (testing
   "Tests for logical AND.\nfalse && (2 / 0 > 3 ? false : true)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false && (2 / 0 > 3 ? false : true)")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-short_circuit_error_right-test
  (testing
   "Tests for logical AND.\n(2 / 0 > 3 ? false : true) && false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "(2 / 0 > 3 ? false : true) && false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-AND-error_right-test
  (testing
   "Tests for logical AND.\ntrue && 1/0 != 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true && 1/0 != 0")]
      (is (helper/error? res)))))

(deftest
  logic-AND-error_left-test
  (testing
   "Tests for logical AND.\n1/0 != 0 && true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1/0 != 0 && true")]
      (is (helper/error? res)))))

(deftest
  logic-AND-no_overload-test
  (testing
   "Tests for logical AND.\n'less filling' && 'tastes great'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'less filling' && 'tastes great'")]
      (is (helper/error? res)))))

(deftest
  logic-OR-all_true-test
  (testing
   "Tests for logical OR\ntrue || true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true || true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-all_false-test
  (testing
   "Tests for logical OR\nfalse || false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false || false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-OR-false_left-test
  (testing
   "Tests for logical OR\nfalse || true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false || true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-false_right-test
  (testing
   "Tests for logical OR\ntrue || false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true || false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-short_circuit_type_left-test
  (testing
   "Tests for logical OR\ntrue || 32"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true || 32")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-short_circuit_type_right-test
  (testing
   "Tests for logical OR\n'horses' || true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'horses' || true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-short_circuit_error_left-test
  (testing
   "Tests for logical OR\ntrue || (2 / 0 > 3 ? false : true)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true || (2 / 0 > 3 ? false : true)")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-short_circuit_error_right-test
  (testing
   "Tests for logical OR\n(2 / 0 > 3 ? false : true) || true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "(2 / 0 > 3 ? false : true) || true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-OR-error_right-test
  (testing
   "Tests for logical OR\nfalse || 1/0 != 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false || 1/0 != 0")]
      (is (helper/error? res)))))

(deftest
  logic-OR-error_left-test
  (testing
   "Tests for logical OR\n1/0 != 0 || false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1/0 != 0 || false")]
      (is (helper/error? res)))))

(deftest
  logic-OR-no_overload-test
  (testing
   "Tests for logical OR\n'less filling' || 'tastes great'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'less filling' || 'tastes great'")]
      (is (helper/error? res)))))

(deftest
  logic-NOT-not_true-test
  (testing
   "Tests for logical NOT.\n!true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  logic-NOT-not_false-test
  (testing
   "Tests for logical NOT.\n!false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  logic-NOT-no_overload-test
  (testing
   "Tests for logical NOT.\n!0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!0")]
      (is (helper/error? res)))))
