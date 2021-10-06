(ns
 exoscale.cel.generated.comparisons-test
  "Generated test - Tests for boolean-valued functions and operators."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  comparisons-eq_literal-eq_int-test
  (testing
   "Literals comparison on _==_\n1 == 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 == 1")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_int-test
  (testing
   "Literals comparison on _==_\n-1 == 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-1 == 1")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_uint-test
  (testing
   "Literals comparison on _==_\n2u == 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2u == 2u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_uint-test
  (testing
   "Literals comparison on _==_\n1u == 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u == 2u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_double-test
  (testing
   "Literals comparison on _==_\n1.0 == 1.0e+0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 == 1.0e+0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_double-test
  (testing
   "Literals comparison on _==_\n-1.0 == 1.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-1.0 == 1.0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_double_NaN-test
  (testing
   "Literals comparison on _==_\n1.0 / 0.0 == 1.0 / 0.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 / 0.0 == 1.0 / 0.0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-eq_string-test
  (testing
   "Literals comparison on _==_\n'' == \"\""
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' == \"\"")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_string-test
  (testing
   "Literals comparison on _==_\n'a' == 'b'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' == 'b'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_raw_string-test
  (testing
   "Literals comparison on _==_\n'abc' == r'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' == r'abc'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_string_case-test
  (testing
   "Literals comparison on _==_\n'abc' == 'ABC'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' == 'ABC'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_string_unicode-test
  (testing
   "Literals comparison on _==_\n'ίσος' == 'ίσος'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'ίσος' == 'ίσος'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_string_unicode_ascii-test
  (testing
   "Literals comparison on _==_\n'a' == 'à'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' == 'à'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-no_string_normalization-test
  (testing
   "Literals comparison on _==_\n'Am\\u00E9lie' == 'Ame\\u0301lie'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'Am\\u00E9lie' == 'Ame\\u0301lie'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-no_string_normalization_surrogate-test
  (testing
   "Literals comparison on _==_\n'\\U0001F436' == '\\xef\\xbf\\xbd\\xef\\xbf\\bd'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'\\U0001F436' == '\\xef\\xbf\\xbd\\xef\\xbf\\bd'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_null-test
  (testing
   "Literals comparison on _==_\nnull == null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null == null")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-eq_bool-test
  (testing
   "Literals comparison on _==_\ntrue == true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true == true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_bool-test
  (testing
   "Literals comparison on _==_\nfalse == true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false == true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_bytes-test
  (testing
   "Literals comparison on _==_\nb'ÿ' == b'\\303\\277'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'ÿ' == b'\\303\\277'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_bytes-test
  (testing
   "Literals comparison on _==_\nb'abc' == b'abcd'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'abc' == b'abcd'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_list_empty-test
  (testing
   "Literals comparison on _==_\n[] == []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[] == []")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-eq_list_numbers-test
  (testing
   "Literals comparison on _==_\n[1, 2, 3] == [1, 2, 3]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3] == [1, 2, 3]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_list_order-test
  (testing
   "Literals comparison on _==_\n[1, 2, 3] == [1, 3, 2]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 2, 3] == [1, 3, 2]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-not_eq_list_string_case-test
  (testing
   "Literals comparison on _==_\n['case'] == ['cAse']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['case'] == ['cAse']")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-not_eq_list_length-test
  (testing
   "Literals comparison on _==_\n['one'] == [2, 3]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['one'] == [2, 3]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-not_eq_list_false_vs_types-test
  (testing
   "Literals comparison on _==_\n[1, 'dos', 3] == [1, 2, 4]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1, 'dos', 3] == [1, 2, 4]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_map_empty-test
  (testing
   "Literals comparison on _==_\n{} == {}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{} == {}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-eq_map_onekey-test
  (testing
   "Literals comparison on _==_\n{'k':'v'} == {\"k\":\"v\"}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':'v'} == {\"k\":\"v\"}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-eq_map_doublevalue-test
  (testing
   "Literals comparison on _==_\n{'k':1.0} == {'k':1e+0}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':1.0} == {'k':1e+0}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_map_value-test
  (testing
   "Literals comparison on _==_\n{'k':'v'} == {'k':'v1'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':'v'} == {'k':'v1'}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-not_eq_map_extrakey-test
  (testing
   "Literals comparison on _==_\n{'k':'v','k1':'v1'} == {'k':'v'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':'v','k1':'v1'} == {'k':'v'}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_map_keyorder-test
  (testing
   "Literals comparison on _==_\n{'k1':'v1','k2':'v2'} == {'k2':'v2','k1':'v1'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k1':'v1','k2':'v2'} == {'k2':'v2','k1':'v1'}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-eq_literal-not_eq_map_key_casing-test
  (testing
   "Literals comparison on _==_\n{'key':'value'} == {'Key':'value'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'key':'value'} == {'Key':'value'}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-not_eq_map_false_vs_types-test
  (testing
   "Literals comparison on _==_\n{'k1': 1, 'k2': 'dos', 'k3': 3} == {'k1': 1, 'k2': 2, 'k3': 4}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k1': 1, 'k2': 'dos', 'k3': 3} == {'k1': 1, 'k2': 2, 'k3': 4}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-eq_literal-eq_mixed_types_error-test
  (testing
   "Literals comparison on _==_\n1.0 == 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 == 1")]
      (is (helper/error? res)))))

(deftest
  comparisons-eq_literal-eq_list_elem_mixed_types_error-test
  (testing
   "Literals comparison on _==_\n[1] == [1.0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1] == [1.0]")]
      (is (helper/error? res)))))

(deftest
  comparisons-ne_literal-ne_int-test
  (testing
   "Literals comparison on _!=_\n24 != 42"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "24 != 42")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_int-test
  (testing
   "Literals comparison on _!=_\n1 != 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 != 1")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_uint-test
  (testing
   "Literals comparison on _!=_\n1u != 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u != 2u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_uint-test
  (testing
   "Literals comparison on _!=_\n99u != 99u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "99u != 99u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_double-test
  (testing
   "Literals comparison on _!=_\n9.0e+3 != 9001.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "9.0e+3 != 9001.0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_double-test
  (testing
   "Literals comparison on _!=_\n1.0 != 1e+0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 != 1e+0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_string-test
  (testing
   "Literals comparison on _!=_\n'abc' != ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' != ''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_string-test
  (testing
   "Literals comparison on _!=_\n'abc' != 'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' != 'abc'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_string_unicode-test
  (testing
   "Literals comparison on _!=_\n'résumé' != 'resume'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'résumé' != 'resume'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_string_unicode-test
  (testing
   "Literals comparison on _!=_\n'ίδιο' != 'ίδιο'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'ίδιο' != 'ίδιο'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_bytes-test
  (testing
   "Literals comparison on _!=_\nb'\\x00\\xFF' != b'ÿ'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'\\x00\\xFF' != b'ÿ'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_bytes-test
  (testing
   "Literals comparison on _!=_\nb'\\303\\277' != b'ÿ'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'\\303\\277' != b'ÿ'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_bool-test
  (testing
   "Literals comparison on _!=_\nfalse != true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false != true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_bool-test
  (testing
   "Literals comparison on _!=_\ntrue != true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true != true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-not_ne_null-test
  (testing
   "Literals comparison on _!=_\nnull != null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null != null")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_list_empty-test
  (testing
   "Literals comparison on _!=_\n[] != [1]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[] != [1]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_list_empty-test
  (testing
   "Literals comparison on _!=_\n[] != []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[] != []")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_list_bool-test
  (testing
   "Literals comparison on _!=_\n[true, false, true] != [true, true, false]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[true, false, true] != [true, true, false]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_list_bool-test
  (testing
   "Literals comparison on _!=_\n[false, true] != [false, true]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[false, true] != [false, true]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-not_ne_list_of_list-test
  (testing
   "Literals comparison on _!=_\n[[]] != [[]]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[[]] != [[]]")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_map_by_value-test
  (testing
   "Literals comparison on _!=_\n{'k':'v'} != {'k':'v1'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':'v'} != {'k':'v1'}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-ne_map_by_key-test
  (testing
   "Literals comparison on _!=_\n{'k':true} != {'k1':true}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'k':true} != {'k1':true}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-ne_literal-not_ne_map_int_to_float-test
  (testing
   "Literals comparison on _!=_\n{1:1.0} != {1:1.0}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{1:1.0} != {1:1.0}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-not_ne_map_key_order-test
  (testing
   "Literals comparison on _!=_\n{'a':'b','c':'d'} != {'c':'d','a':'b'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{'a':'b','c':'d'} != {'c':'d','a':'b'}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-ne_literal-ne_mixed_types_error-test
  (testing
   "Literals comparison on _!=_\n2u != 2"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2u != 2")]
      (is (helper/error? res)))))

(deftest
  comparisons-lt_literal-lt_int-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n-1 < 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-1 < 0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_int-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n0 < 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0 < 0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-lt_uint-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n0u < 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u < 1u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_uint-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n2u < 2u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "2u < 2u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-lt_double-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n1.0 < 1.0000001"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 < 1.0000001")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_double-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n-0.0 < 0.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "-0.0 < 0.0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-lt_string-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'a' < 'b'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' < 'b'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-lt_string_empty_to_nonempty-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'' < 'a'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' < 'a'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-lt_string_case-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'Abc' < 'aBC'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'Abc' < 'aBC'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-lt_string_length-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'abc' < 'abcd'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' < 'abcd'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-lt_string_diacritical_mark_sensitive-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'a' < '\\u00E1'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' < '\\u00E1'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_string_empty-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'' < ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' < ''")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-not_lt_string_same-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'abc' < 'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' < 'abc'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-not_lt_string_case_length-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'a' < 'AB'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' < 'AB'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-unicode_order_lexical-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'f' < '\\u1EBF'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'f' < '\\u1EBF'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-lt_bytes-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\nb'a' < b'b'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'a' < b'b'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_bytes_same-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\nb'abc' < b'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'abc' < b'abc'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-not_lt_bytes_width-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\nb'á' < b'b'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'á' < b'b'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-lt_bool_false_first-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\nfalse < true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false < true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lt_literal-not_lt_bool_same-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\ntrue < true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true < true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-not_lt_bool_true_first-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\ntrue < false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true < false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lt_literal-lt_list_unsupported-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n[0] < [1]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0] < [1]")]
      (is (helper/error? res)))))

(deftest
  comparisons-lt_literal-lt_map_unsupported-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n{0:'a'} < {1:'b'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{0:'a'} < {1:'b'}")]
      (is (helper/error? res)))))

(deftest
  comparisons-lt_literal-lt_null_unsupported-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\nnull < null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null < null")]
      (is (helper/error? res)))))

(deftest
  comparisons-lt_literal-lt_mixed_types_error-test
  (testing
   "Literals comparison on _<_. (a < b) == (b > a) == !(a >= b) == !(b <= a)\n'foo' < 1024"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'foo' < 1024")]
      (is (helper/error? res)))))

(deftest
  comparisons-gt_literal-gt_int-test
  (testing
   "Literals comparison on _>_\n42 > -42"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "42 > -42")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_int-test
  (testing
   "Literals comparison on _>_\n0 > 0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0 > 0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_uint-test
  (testing
   "Literals comparison on _>_\n48u > 46u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "48u > 46u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_uint-test
  (testing
   "Literals comparison on _>_\n0u > 999u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u > 999u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_double-test
  (testing
   "Literals comparison on _>_\n1e+1 > 1e+0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1e+1 > 1e+0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_double-test
  (testing
   "Literals comparison on _>_\n.99 > 9.9e-1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       ".99 > 9.9e-1")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_string_case-test
  (testing
   "Literals comparison on _>_\n'abc' > 'aBc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' > 'aBc'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-gt_string_to_empty-test
  (testing
   "Literals comparison on _>_\n'A' > ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'A' > ''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_string_empty_to_empty-test
  (testing
   "Literals comparison on _>_\n'' > ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' > ''")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_string_unicode-test
  (testing
   "Literals comparison on _>_\n'α' > 'omega'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'α' > 'omega'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-gt_bytes_one-test
  (testing
   "Literals comparison on _>_\nb'' > b' '"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'' > b' '")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-gt_bytes_one_to_empty-test
  (testing
   "Literals comparison on _>_\nb' ' > b''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b' ' > b''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_bytes_sorting-test
  (testing
   "Literals comparison on _>_\nb' ' > b''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b' ' > b''")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_bool_true_false-test
  (testing
   "Literals comparison on _>_\ntrue > false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true > false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gt_literal-not_gt_bool_false_true-test
  (testing
   "Literals comparison on _>_\nfalse > true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false > true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-not_gt_bool_same-test
  (testing
   "Literals comparison on _>_\ntrue > true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true > true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gt_literal-gt_null_unsupported-test
  (testing
   "Literals comparison on _>_\nnull > null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null > null")]
      (is (helper/error? res)))))

(deftest
  comparisons-gt_literal-gt_list_unsupported-test
  (testing
   "Literals comparison on _>_\n[1] > [0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[1] > [0]")]
      (is (helper/error? res)))))

(deftest
  comparisons-gt_literal-gt_map_unsupported-test
  (testing
   "Literals comparison on _>_\n{1:'b'} > {0:'a'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{1:'b'} > {0:'a'}")]
      (is (helper/error? res)))))

(deftest
  comparisons-gt_literal-gt_mixed_types_error-test
  (testing
   "Literals comparison on _>_\n'foo' > 1024"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'foo' > 1024")]
      (is (helper/error? res)))))

(deftest
  comparisons-lte_literal-lte_int_lt-test
  (testing
   "Literals comparison on _<=_\n0 <= 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0 <= 1")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_int_eq-test
  (testing
   "Literals comparison on _<=_\n1 <= 1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 <= 1")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_int_gt-test
  (testing
   "Literals comparison on _<=_\n1 <= -1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1 <= -1")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_uint_lt-test
  (testing
   "Literals comparison on _<=_\n0u <= 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u <= 1u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_uint_eq-test
  (testing
   "Literals comparison on _<=_\n1u <= 1u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u <= 1u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_uint_gt-test
  (testing
   "Literals comparison on _<=_\n1u <= 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u <= 0u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_double_lt-test
  (testing
   "Literals comparison on _<=_\n0.0 <= 0.1e-31"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0.0 <= 0.1e-31")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_double_eq-test
  (testing
   "Literals comparison on _<=_\n0.0 <= 0e-1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0.0 <= 0e-1")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_double_gt-test
  (testing
   "Literals comparison on _<=_\n1.0 <= 0.99"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1.0 <= 0.99")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_string_empty-test
  (testing
   "Literals comparison on _<=_\n'' <= ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' <= ''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_string_from_empty-test
  (testing
   "Literals comparison on _<=_\n'' <= 'a'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' <= 'a'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_string_to_empty-test
  (testing
   "Literals comparison on _<=_\n'a' <= ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' <= ''")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_string_lexicographical-test
  (testing
   "Literals comparison on _<=_\n'aBc' <= 'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'aBc' <= 'abc'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_string_unicode_eq-test
  (testing
   "Literals comparison on _<=_\n'α' <= 'α'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'α' <= 'α'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_string_unicode_lt-test
  (testing
   "Literals comparison on _<=_\n'a' <= 'α'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' <= 'α'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_string_unicode-test
  (testing
   "Literals comparison on _<=_\n'α' <= 'a'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'α' <= 'a'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_bytes_empty-test
  (testing
   "Literals comparison on _<=_\nb'' <= b' '"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'' <= b' '")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-not_lte_bytes_length-test
  (testing
   "Literals comparison on _<=_\nb' ' <= b''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b' ' <= b''")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_bool_false_true-test
  (testing
   "Literals comparison on _<=_\nfalse <= true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false <= true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_bool_false_false-test
  (testing
   "Literals comparison on _<=_\nfalse <= false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false <= false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-lte_literal-lte_bool_true_false-test
  (testing
   "Literals comparison on _<=_\ntrue <= false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true <= false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-lte_literal-lte_null_unsupported-test
  (testing
   "Literals comparison on _<=_\nnull <= null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null <= null")]
      (is (helper/error? res)))))

(deftest
  comparisons-lte_literal-lte_list_unsupported-test
  (testing
   "Literals comparison on _<=_\n[0] <= [0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0] <= [0]")]
      (is (helper/error? res)))))

(deftest
  comparisons-lte_literal-lte_map_unsupported-test
  (testing
   "Literals comparison on _<=_\n{0:'a'} <= {1:'b'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{0:'a'} <= {1:'b'}")]
      (is (helper/error? res)))))

(deftest
  comparisons-lte_literal-lte_mixed_types_error-test
  (testing
   "Literals comparison on _<=_\n'foo' <= 1024"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'foo' <= 1024")]
      (is (helper/error? res)))))

(deftest
  comparisons-gte_literal-gte_int_gt-test
  (testing
   "Literals comparison on _>=_\n0 >= -1"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0 >= -1")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_int_eq-test
  (testing
   "Literals comparison on _>=_\n999 >= 999"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "999 >= 999")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_int_lt-test
  (testing
   "Literals comparison on _>=_\n999 >= 1000"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "999 >= 1000")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_uint_gt-test
  (testing
   "Literals comparison on _>=_\n1u >= 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u >= 0u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_uint_eq-test
  (testing
   "Literals comparison on _>=_\n0u >= 0u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0u >= 0u")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_uint_lt-test
  (testing
   "Literals comparison on _>=_\n1u >= 10u"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1u >= 10u")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_double_gt-test
  (testing
   "Literals comparison on _>=_\n1e+1 >= 1e+0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "1e+1 >= 1e+0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_double_eq-test
  (testing
   "Literals comparison on _>=_\n9.80665 >= 9.80665e+0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "9.80665 >= 9.80665e+0")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_double_lt-test
  (testing
   "Literals comparison on _>=_\n0.9999 >= 1.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "0.9999 >= 1.0")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_string_empty-test
  (testing
   "Literals comparison on _>=_\n'' >= ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' >= ''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_string_to_empty-test
  (testing
   "Literals comparison on _>=_\n'a' >= ''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'a' >= ''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_string_empty_to_nonempty-test
  (testing
   "Literals comparison on _>=_\n'' >= 'a'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'' >= 'a'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_string_length-test
  (testing
   "Literals comparison on _>=_\n'abcd' >= 'abc'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abcd' >= 'abc'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_string_lexicographical-test
  (testing
   "Literals comparison on _>=_\n'abc' >= 'abd'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'abc' >= 'abd'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_string_unicode_eq-test
  (testing
   "Literals comparison on _>=_\n'τ' >= 'τ'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'τ' >= 'τ'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_string_unicode_gt-test
  (testing
   "Literals comparison on _>=_\n'τ' >= 't'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'τ' >= 't'")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_get_string_unicode-test
  (testing
   "Literals comparison on _>=_\n't' >= 'τ'"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'t' >= 'τ'")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_bytes_to_empty-test
  (testing
   "Literals comparison on _>=_\nb' ' >= b''"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b' ' >= b''")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_bytes_empty_to_nonempty-test
  (testing
   "Literals comparison on _>=_\nb'' >= b' '"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b'' >= b' '")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_bytes_samelength-test
  (testing
   "Literals comparison on _>=_\nb' ' >= b' '"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "b' ' >= b' '")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_bool_gt-test
  (testing
   "Literals comparison on _>=_\ntrue >= false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true >= false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-gte_bool_eq-test
  (testing
   "Literals comparison on _>=_\ntrue >= true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true >= true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-gte_literal-not_gte_bool_lt-test
  (testing
   "Literals comparison on _>=_\nfalse >= true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false >= true")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-gte_literal-gte_null_unsupported-test
  (testing
   "Literals comparison on _>=_\nnull >= null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "null >= null")]
      (is (helper/error? res)))))

(deftest
  comparisons-gte_literal-gte_list_unsupported-test
  (testing
   "Literals comparison on _>=_\n['y'] >= ['x']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "['y'] >= ['x']")]
      (is (helper/error? res)))))

(deftest
  comparisons-gte_literal-gte_map_unsupported-test
  (testing
   "Literals comparison on _>=_\n{1:'b'} >= {0:'a'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{1:'b'} >= {0:'a'}")]
      (is (helper/error? res)))))

(deftest
  comparisons-gte_literal-gte_mixed_types_error-test
  (testing
   "Literals comparison on _>=_\n'foo' >= 1.0"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'foo' >= 1.0")]
      (is (helper/error? res)))))

(deftest
  comparisons-in_list_literal-elem_not_in_empty_list-test
  (testing
   "Set membership tests using list literals and the 'in' operator\n'empty' in []"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'empty' in []")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-in_list_literal-elem_in_list-test
  (testing
   "Set membership tests using list literals and the 'in' operator\n'elem' in ['elem', 'elemA', 'elemB']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'elem' in ['elem', 'elemA', 'elemB']")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-in_list_literal-elem_not_in_list-test
  (testing
   "Set membership tests using list literals and the 'in' operator\n'not' in ['elem1', 'elem2', 'elem3']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'not' in ['elem1', 'elem2', 'elem3']")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-in_list_literal-elem_in_mixed_type_list-test
  (testing
   "Set membership tests using list literals and the 'in' operator\n'elem' in [1, 'elem', 2]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'elem' in [1, 'elem', 2]")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-in_list_literal-elem_in_mixed_type_list_error-test
  (testing
   "Set membership tests using list literals and the 'in' operator\n'elem' in [1u, 'str', 2, b'bytes']"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'elem' in [1u, 'str', 2, b'bytes']")]
      (is (helper/error? res)))))

(deftest
  comparisons-in_map_literal-key_not_in_empty_map-test
  (testing
   "Set membership tests using map literals and the 'in' operator\n'empty' in {}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'empty' in {}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-in_map_literal-key_in_map-test
  (testing
   "Set membership tests using map literals and the 'in' operator\n'key' in {'key':'1', 'other':'2'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'key' in {'key':'1', 'other':'2'}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-in_map_literal-key_not_in_map-test
  (testing
   "Set membership tests using map literals and the 'in' operator\n'key' in {'lock':1, 'gate':2}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'key' in {'lock':1, 'gate':2}")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-in_map_literal-key_in_mixed_key_type_map-test
  (testing
   "Set membership tests using map literals and the 'in' operator\n'key' in {3:3.0, 'key':2u}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'key' in {3:3.0, 'key':2u}")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-in_map_literal-key_in_mixed_key_type_map_error-test
  (testing
   "Set membership tests using map literals and the 'in' operator\n'key' in {1u:'str', 2:b'bytes'}"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "'key' in {1u:'str', 2:b'bytes'}")]
      (is (helper/error? res)))))

(deftest
  comparisons-bound-bytes_gt_left_false-test
  (testing
   "Comparing bound variables with literals or other variables\nx > b' '"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:bytesValue "AA=="}}}),
        :translate-result? false}
       "x > b' '")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-bound-int_lte_right_true-test
  (testing
   "Comparing bound variables with literals or other variables\n123 <= x"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:int64Value "124"}}}),
        :translate-result? false}
       "123 <= x")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-bound-bool_lt_right_true-test
  (testing
   "Comparing bound variables with literals or other variables\nfalse < x"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:boolValue true}}}),
        :translate-result? false}
       "false < x")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-bound-double_ne_left_false-test
  (testing
   "Comparing bound variables with literals or other variables\nx != 9.8"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:doubleValue 9.8}}}),
        :translate-result? false}
       "x != 9.8")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-bound-map_ne_right_false-test
  (testing
   "Comparing bound variables with literals or other variables\n{'a':'b','c':'d'} != x"
    (let
     [res
      (parser/parse-eval
       {:bindings
        (helper/bindings
         {:x
          {:value
           {:mapValue
            {:entries
             [{:key {:stringValue "c"}, :value {:stringValue "d"}}
              {:key {:stringValue "a"}, :value {:stringValue "b"}}]}}}}),
        :translate-result? false}
       "{'a':'b','c':'d'} != x")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-bound-null_eq_left_true-test
  (testing
   "Comparing bound variables with literals or other variables\nx == null"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:nullValue nil}}}),
        :translate-result? false}
       "x == null")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-bound-list_eq_right_false-test
  (testing
   "Comparing bound variables with literals or other variables\n[1, 2] == x"
    (let
     [res
      (parser/parse-eval
       {:bindings
        (helper/bindings
         {:x
          {:value
           {:listValue
            {:values [{:int64Value "2"} {:int64Value "1"}]}}}}),
        :translate-result? false}
       "[1, 2] == x")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-bound-string_gte_right_true-test
  (testing
   "Comparing bound variables with literals or other variables\n'abcd' >= x"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:stringValue "abc"}}}),
        :translate-result? false}
       "'abcd' >= x")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  comparisons-bound-uint_eq_right_false-test
  (testing
   "Comparing bound variables with literals or other variables\n999u == x"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:uint64Value "1000"}}}),
        :translate-result? false}
       "999u == x")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  comparisons-bound-null_lt_right_no_such_overload-test
  (testing
   "Comparing bound variables with literals or other variables\nnull < x"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings {:x {:value {:nullValue nil}}}),
        :translate-result? false}
       "null < x")]
      (is (helper/error? res)))))
