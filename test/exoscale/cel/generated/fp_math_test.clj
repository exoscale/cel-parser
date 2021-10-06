(ns
 exoscale.cel.generated.fp_math-test
  "Generated test - Tests for floating-point math."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  fp_math-fp_math-add_positive_positive-test
  (testing
   "Simple tests for floating point.\n4.25 + 15.25"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "4.25 + 15.25")]
      (is (helper/equal? (helper/translate {:doubleValue 19.5}) res)))))

(deftest
  fp_math-fp_math-add_positive_negative-test
  (testing
   "Simple tests for floating point.\n17.75 + (-7.75)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "17.75 + (-7.75)")]
      (is (helper/equal? (helper/translate {:doubleValue 10}) res)))))

(deftest
  fp_math-fp_math-add_negative_negative-test
  (testing
   "Simple tests for floating point.\n-4.125 + (-2.125)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-4.125 + (-2.125)")]
      (is (helper/equal? (helper/translate {:doubleValue -6.25}) res)))))

(deftest
  fp_math-fp_math-sub_positive_positive-test
  (testing
   "Simple tests for floating point.\n42.0 - 12.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42.0 - 12.0")]
      (is (helper/equal? (helper/translate {:doubleValue 30}) res)))))

(deftest
  fp_math-fp_math-sub_positive_negative-test
  (testing
   "Simple tests for floating point.\n42.875 - (-22.0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42.875 - (-22.0)")]
      (is (helper/equal? (helper/translate {:doubleValue 64.875}) res)))))

(deftest
  fp_math-fp_math-sub_negative_negative-test
  (testing
   "Simple tests for floating point.\n-4.875 - (-0.125)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-4.875 - (-0.125)")]
      (is (helper/equal? (helper/translate {:doubleValue -4.75}) res)))))

(deftest
  fp_math-fp_math-multiply_positive_positive-test
  (testing
   "Simple tests for floating point.\n42.5 * 0.2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42.5 * 0.2")]
      (is (helper/equal? (helper/translate {:doubleValue 8.5}) res)))))

(deftest
  fp_math-fp_math-multiply_positive_negative-test
  (testing
   "Simple tests for floating point.\n40.75 * (-2.25)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "40.75 * (-2.25)")]
      (is
       (helper/equal? (helper/translate {:doubleValue -91.6875}) res)))))

(deftest
  fp_math-fp_math-multiply_negative_negative-test
  (testing
   "Simple tests for floating point.\n-3.0 * (-2.5)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-3.0 * (-2.5)")]
      (is (helper/equal? (helper/translate {:doubleValue 7.5}) res)))))

(deftest
  fp_math-fp_math-divide_positive_positive-test
  (testing
   "Simple tests for floating point.\n0.0625 / 0.002"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0.0625 / 0.002")]
      (is (helper/equal? (helper/translate {:doubleValue 31.25}) res)))))

(deftest
  fp_math-fp_math-divide_positive_negative-test
  (testing
   "Simple tests for floating point.\n-2.0 / 2.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-2.0 / 2.0")]
      (is (helper/equal? (helper/translate {:doubleValue -1}) res)))))

(deftest
  fp_math-fp_math-divide_negative_negative-test
  (testing
   "Simple tests for floating point.\n-8.875 / (-0.0625)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-8.875 / (-0.0625)")]
      (is (helper/equal? (helper/translate {:doubleValue 142}) res)))))

(deftest
  fp_math-fp_math-mod_not_support-test
  (testing
   "Simple tests for floating point.\n47.5 % 5.5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "47.5 % 5.5")]
      (is (helper/error? res)))))

(deftest
  fp_math-fp_math-negative-test
  (testing
   "Simple tests for floating point.\n-(4.5)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(4.5)")]
      (is (helper/equal? (helper/translate {:doubleValue -4.5}) res)))))

(deftest
  fp_math-fp_math-double_negative-test
  (testing
   "Simple tests for floating point.\n-(-1.25)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(-1.25)")]
      (is (helper/equal? (helper/translate {:doubleValue 1.25}) res)))))

(deftest
  fp_math-fp_math-negative_zero-test
  (testing
   "Simple tests for floating point.\n-(0.0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(0.0)")]
      (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
  fp_math-fp_math-divide_zero-test
  (testing
   "Simple tests for floating point.\n15.75 / 0.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15.75 / 0.0")]
      (is
       (helper/equal? (helper/translate {:doubleValue "Infinity"}) res)))))

(deftest
  fp_math-fp_math-multiply_zero-test
  (testing
   "Simple tests for floating point.\n15.36 * 0.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15.36 * 0.0")]
      (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
  fp_math-fp_math-add_left_identity-test
  (testing
   "Simple tests for floating point.\n0.0 + 1.75"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0.0 + 1.75")]
      (is (helper/equal? (helper/translate {:doubleValue 1.75}) res)))))

(deftest
  fp_math-fp_math-add_right_identity-test
  (testing
   "Simple tests for floating point.\n 2.5 + 0.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       " 2.5 + 0.0")]
      (is (helper/equal? (helper/translate {:doubleValue 2.5}) res)))))

(deftest
  fp_math-fp_math-add_commutative-test
  (testing
   "Simple tests for floating point.\n7.5 + 1.5 == 1.5 + 7.5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "7.5 + 1.5 == 1.5 + 7.5")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  fp_math-fp_math-add_associative-test
  (testing
   "Simple tests for floating point.\n5.625 + (15.75 + 2.0) == (5.625 + 15.75) + 2.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5.625 + (15.75 + 2.0) == (5.625 + 15.75) + 2.0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  fp_math-fp_math-mul_left_identity-test
  (testing
   "Simple tests for floating point.\n1.0 * 45.25"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 * 45.25")]
      (is (helper/equal? (helper/translate {:doubleValue 45.25}) res)))))

(deftest
  fp_math-fp_math-mul_right_identity-test
  (testing
   "Simple tests for floating point.\n-25.25 * 1.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-25.25 * 1.0")]
      (is (helper/equal? (helper/translate {:doubleValue -25.25}) res)))))

(deftest
  fp_math-fp_math-mul_commutative-test
  (testing
   "Simple tests for floating point.\n1.5 * 25.875 == 25.875 * 1.5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.5 * 25.875 == 25.875 * 1.5")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  fp_math-fp_math-mul_associative-test
  (testing
   "Simple tests for floating point.\n1.5 * (23.625 * 0.75) == (1.5 * 23.625) * 0.75"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.5 * (23.625 * 0.75) == (1.5 * 23.625) * 0.75")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  fp_math-fp_math-add_mul_distribute-test
  (testing
   "Simple tests for floating point.\n5.75 * (1.5 + 2.5)  == 5.75 * 1.5 + 5.75 * 2.5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5.75 * (1.5 + 2.5)  == 5.75 * 1.5 + 5.75 * 2.5")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  fp_math-fp_math-fp_overflow_positive-test
  (testing
   "Simple tests for floating point.\n2.0 * 8.988466e+307 "
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2.0 * 8.988466e+307 ")]
      (is
       (helper/equal? (helper/translate {:doubleValue "Infinity"}) res)))))

(deftest
  fp_math-fp_math-fp_overflow_negative-test
  (testing
   "Simple tests for floating point.\n2.0 * -8.988466e+307 "
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2.0 * -8.988466e+307 ")]
      (is
       (helper/equal?
        (helper/translate {:doubleValue "-Infinity"})
        res)))))

(deftest
  fp_math-fp_math-fp_underflow-test
  (testing
   "Simple tests for floating point.\n1e-324  / 2.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1e-324  / 2.0")]
      (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))
