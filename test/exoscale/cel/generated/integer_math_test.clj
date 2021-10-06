(ns
 exoscale.cel.generated.integer_math-test
  "Generated test - Tests for int and uint math."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  integer_math-int64_math-add_positive_positive-test
  (testing
   "Simple tests for int64.\n40 + 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "40 + 2")]
      (is (helper/equal? (helper/translate {:int64Value "42"}) res)))))

(deftest
  integer_math-int64_math-add_positive_negative-test
  (testing
   "Simple tests for int64.\n42 + (-7)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 + (-7)")]
      (is (helper/equal? (helper/translate {:int64Value "35"}) res)))))

(deftest
  integer_math-int64_math-add_negative_negative-test
  (testing
   "Simple tests for int64.\n-4 + (-2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-4 + (-2)")]
      (is (helper/equal? (helper/translate {:int64Value "-6"}) res)))))

(deftest
  integer_math-int64_math-sub_positive_positive-test
  (testing
   "Simple tests for int64.\n42 - 12"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 - 12")]
      (is (helper/equal? (helper/translate {:int64Value "30"}) res)))))

(deftest
  integer_math-int64_math-sub_positive_negative-test
  (testing
   "Simple tests for int64.\n42 - (-22)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 - (-22)")]
      (is (helper/equal? (helper/translate {:int64Value "64"}) res)))))

(deftest
  integer_math-int64_math-sub_negative_negative-test
  (testing
   "Simple tests for int64.\n-42 - (-12)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-42 - (-12)")]
      (is (helper/equal? (helper/translate {:int64Value "-30"}) res)))))

(deftest
  integer_math-int64_math-multiply_positive_positive-test
  (testing
   "Simple tests for int64.\n42 * 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 * 2")]
      (is (helper/equal? (helper/translate {:int64Value "84"}) res)))))

(deftest
  integer_math-int64_math-multiply_positive_negative-test
  (testing
   "Simple tests for int64.\n40 * (-2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "40 * (-2)")]
      (is (helper/equal? (helper/translate {:int64Value "-80"}) res)))))

(deftest
  integer_math-int64_math-multiply_negative_negative-test
  (testing
   "Simple tests for int64.\n-30 * (-2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-30 * (-2)")]
      (is (helper/equal? (helper/translate {:int64Value "60"}) res)))))

(deftest
  integer_math-int64_math-divide_positive_positive-test
  (testing
   "Simple tests for int64.\n42 / 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 / 2")]
      (is (helper/equal? (helper/translate {:int64Value "21"}) res)))))

(deftest
  integer_math-int64_math-divide_positive_negative-test
  (testing
   "Simple tests for int64.\n-20 / 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-20 / 2")]
      (is (helper/equal? (helper/translate {:int64Value "-10"}) res)))))

(deftest
  integer_math-int64_math-divide_negative_negative-test
  (testing
   "Simple tests for int64.\n-80 / (-2)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-80 / (-2)")]
      (is (helper/equal? (helper/translate {:int64Value "40"}) res)))))

(deftest
  integer_math-int64_math-mod_positive_positive-test
  (testing
   "Simple tests for int64.\n47 % 5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "47 % 5")]
      (is (helper/equal? (helper/translate {:int64Value "2"}) res)))))

(deftest
  integer_math-int64_math-mod_positive_negative-test
  (testing
   "Simple tests for int64.\n43 % (-5)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "43 % (-5)")]
      (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))

(deftest
  integer_math-int64_math-mod_negative_negative-test
  (testing
   "Simple tests for int64.\n-42 % (-5)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-42 % (-5)")]
      (is (helper/equal? (helper/translate {:int64Value "-2"}) res)))))

(deftest
  integer_math-int64_math-mod_negative_positive-test
  (testing
   "Simple tests for int64.\n-3 % 5"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-3 % 5")]
      (is (helper/equal? (helper/translate {:int64Value "-3"}) res)))))

(deftest
  integer_math-int64_math-unary_minus_pos-test
  (testing
   "Simple tests for int64.\n-(42)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(42)")]
      (is (helper/equal? (helper/translate {:int64Value "-42"}) res)))))

(deftest
  integer_math-int64_math-unary_minus_neg-test
  (testing
   "Simple tests for int64.\n-(-42)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(-42)")]
      (is (helper/equal? (helper/translate {:int64Value "42"}) res)))))

(deftest
  integer_math-int64_math-unary_minus_no_overload-test
  (testing
   "Simple tests for int64.\n-(42u)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(42u)")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-unary_minus_not_bool-test
  (testing
   "Simple tests for int64.\n-false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-false")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-mod_zero-test
  (testing
   "Simple tests for int64.\n34 % 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "34 % 0")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-negtive_zero-test
  (testing
   "Simple tests for int64.\n-(0)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(0)")]
      (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
  integer_math-int64_math-double_negative-test
  (testing
   "Simple tests for int64.\n-(-42)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(-42)")]
      (is (helper/equal? (helper/translate {:int64Value "42"}) res)))))

(deftest
  integer_math-int64_math-divide_zero-test
  (testing
   "Simple tests for int64.\n15 / 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15 / 0")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-multiply_zero-test
  (testing
   "Simple tests for int64.\n15 * 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15 * 0")]
      (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
  integer_math-int64_math-add_left_identity-test
  (testing
   "Simple tests for int64.\n0 + 17"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0 + 17")]
      (is (helper/equal? (helper/translate {:int64Value "17"}) res)))))

(deftest
  integer_math-int64_math-add_right_identity-test
  (testing
   "Simple tests for int64.\n 29 + 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       " 29 + 0")]
      (is (helper/equal? (helper/translate {:int64Value "29"}) res)))))

(deftest
  integer_math-int64_math-add_commutative-test
  (testing
   "Simple tests for int64.\n75 + 15 == 15 + 75"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "75 + 15 == 15 + 75")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-int64_math-add_associative-test
  (testing
   "Simple tests for int64.\n5 + (15 + 20) == (5 + 15) + 20"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5 + (15 + 20) == (5 + 15) + 20")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-int64_math-mul_left_identity-test
  (testing
   "Simple tests for int64.\n1 * 45"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 * 45")]
      (is (helper/equal? (helper/translate {:int64Value "45"}) res)))))

(deftest
  integer_math-int64_math-mul_right_identity-test
  (testing
   "Simple tests for int64.\n-25 * 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-25 * 1")]
      (is (helper/equal? (helper/translate {:int64Value "-25"}) res)))))

(deftest
  integer_math-int64_math-mul_commutative-test
  (testing
   "Simple tests for int64.\n15 * 25 == 25 * 15"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15 * 25 == 25 * 15")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-int64_math-mul_associative-test
  (testing
   "Simple tests for int64.\n15 * (23 * 88) == (15 * 23) * 88"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15 * (23 * 88) == (15 * 23) * 88")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-int64_math-add_mul_distribute-test
  (testing
   "Simple tests for int64.\n5 * (15 + 25)  == 5 * 15 + 5 * 25"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5 * (15 + 25)  == 5 * 15 + 5 * 25")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-int64_math-int64_overflow_positive-test
  (testing
   "Simple tests for int64.\n9223372036854775807 + 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "9223372036854775807 + 1")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_overflow_negative-test
  (testing
   "Simple tests for int64.\n-9223372036854775808 - 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-9223372036854775808 - 1")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_overflow_add_negative-test
  (testing
   "Simple tests for int64.\n-9223372036854775808 + (-1)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-9223372036854775808 + (-1)")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_overflow_sub_positive-test
  (testing
   "Simple tests for int64.\n1 - (-9223372036854775807)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 - (-9223372036854775807)")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_min_negate-test
  (testing
   "Simple tests for int64.\n-(-9223372036854775808)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(-9223372036854775808)")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_min_negate_mul-test
  (testing
   "Simple tests for int64.\n(-9223372036854775808) * -1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "(-9223372036854775808) * -1")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_min_negate_div-test
  (testing
   "Simple tests for int64.\n(-9223372036854775808)/-1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "(-9223372036854775808)/-1")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_overflow_mul_positive-test
  (testing
   "Simple tests for int64.\n5000000000 * 5000000000"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5000000000 * 5000000000")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-int64_overflow_mul_negative-test
  (testing
   "Simple tests for int64.\n(-5000000000) * 5000000000"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "(-5000000000) * 5000000000")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-uint64_overflow_positive-test
  (testing
   "Simple tests for int64.\n18446744073709551615u + 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "18446744073709551615u + 1u")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-uint64_overflow_negative-test
  (testing
   "Simple tests for int64.\n0u - 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u - 1u")]
      (is (helper/error? res)))))

(deftest
  integer_math-int64_math-uint64_overflow_mul_positive-test
  (testing
   "Simple tests for int64.\n5000000000u * 5000000000u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5000000000u * 5000000000u")]
      (is (helper/error? res)))))

(deftest
  integer_math-uint64_math-add-test
  (testing
   "Simple tests for uint64.\n42u + 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42u + 2u")]
      (is (helper/equal? (helper/translate {:uint64Value "44"}) res)))))

(deftest
  integer_math-uint64_math-sub-test
  (testing
   "Simple tests for uint64.\n42u - 12u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42u - 12u")]
      (is (helper/equal? (helper/translate {:uint64Value "30"}) res)))))

(deftest
  integer_math-uint64_math-multiply-test
  (testing
   "Simple tests for uint64.\n40u * 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "40u * 2u")]
      (is (helper/equal? (helper/translate {:uint64Value "80"}) res)))))

(deftest
  integer_math-uint64_math-divide-test
  (testing
   "Simple tests for uint64.\n60u / 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "60u / 2u")]
      (is (helper/equal? (helper/translate {:uint64Value "30"}) res)))))

(deftest
  integer_math-uint64_math-mod-test
  (testing
   "Simple tests for uint64.\n42u % 5u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42u % 5u")]
      (is (helper/equal? (helper/translate {:uint64Value "2"}) res)))))

(deftest
  integer_math-uint64_math-negtive_no_overload-test
  (testing
   "Simple tests for uint64.\n-(5u)"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-(5u)")]
      (is (helper/error? res)))))

(deftest
  integer_math-uint64_math-mod_zero-test
  (testing
   "Simple tests for uint64.\n34u % 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "34u % 0u")]
      (is (helper/error? res)))))

(deftest
  integer_math-uint64_math-divide_zero-test
  (testing
   "Simple tests for uint64.\n15u / 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15u / 0u")]
      (is (helper/error? res)))))

(deftest
  integer_math-uint64_math-multiply_zero-test
  (testing
   "Simple tests for uint64.\n15u * 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15u * 0u")]
      (is (helper/equal? (helper/translate {:uint64Value "0"}) res)))))

(deftest
  integer_math-uint64_math-add_left_identity-test
  (testing
   "Simple tests for uint64.\n0u + 17u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u + 17u")]
      (is (helper/equal? (helper/translate {:uint64Value "17"}) res)))))

(deftest
  integer_math-uint64_math-add_right_identity-test
  (testing
   "Simple tests for uint64.\n 29u + 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       " 29u + 0u")]
      (is (helper/equal? (helper/translate {:uint64Value "29"}) res)))))

(deftest
  integer_math-uint64_math-add_commutative-test
  (testing
   "Simple tests for uint64.\n75u + 15u == 15u + 75u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "75u + 15u == 15u + 75u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-uint64_math-add_associative-test
  (testing
   "Simple tests for uint64.\n5u + (15u + 20u) == (5u + 15u) + 20u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5u + (15u + 20u) == (5u + 15u) + 20u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-uint64_math-mul_left_identity-test
  (testing
   "Simple tests for uint64.\n1u * 45u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u * 45u")]
      (is (helper/equal? (helper/translate {:uint64Value "45"}) res)))))

(deftest
  integer_math-uint64_math-mul_right_identity-test
  (testing
   "Simple tests for uint64.\n25u * 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "25u * 1u")]
      (is (helper/equal? (helper/translate {:uint64Value "25"}) res)))))

(deftest
  integer_math-uint64_math-mul_commutative-test
  (testing
   "Simple tests for uint64.\n15u * 25u == 25u * 15u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15u * 25u == 25u * 15u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-uint64_math-mul_associative-test
  (testing
   "Simple tests for uint64.\n15u * (23u * 88u) == (15u * 23u) * 88u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "15u * (23u * 88u) == (15u * 23u) * 88u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  integer_math-uint64_math-add_mul_distribute-test
  (testing
   "Simple tests for uint64.\n5u * (15u + 25u)  == 5u * 15u + 5u * 25u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "5u * (15u + 25u)  == 5u * 15u + 5u * 25u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))
