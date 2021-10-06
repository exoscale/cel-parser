(ns
 exoscale.cel.generated.basic-test
 "Generated test - Basic conformance tests that all implementations should pass."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 basic-self_eval_zeroish-self_eval_int_zero-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n0"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0")]
   (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_uint_zero-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n0u"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0u")]
   (is (helper/equal? (helper/translate {:uint64Value "0"}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_float_zero-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n0.0"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0.0")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_float_zerowithexp-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n0e+0"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0e+0")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_string_empty-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_string_empty_quotes-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n\"\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "\"\"")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_string_raw_prefix-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nr\"\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "r\"\"")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_bytes_empty-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nb\"\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b\"\"")]
   (is (helper/equal? (helper/translate {:bytesValue ""}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_bool_false-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nfalse"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "false")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_null-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nnull"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "null")]
   (is (helper/equal? (helper/translate {:nullValue nil}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_empty_list-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n[]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "[]")]
   (is (helper/equal? (helper/translate {:listValue {}}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_empty_map-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\n{}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{}")]
   (is (helper/equal? (helper/translate {:mapValue {}}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_string_raw_prefix_triple_double-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nr\"\"\"\"\"\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "r\"\"\"\"\"\"")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 basic-self_eval_zeroish-self_eval_string_raw_prefix_triple_single-test
 (testing
  "Simple self-evaluating forms to zero-ish values.\nr''''''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "r''''''")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_int_nonzero-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n42"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "42")]
   (is (helper/equal? (helper/translate {:int64Value "42"}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_uint_nonzero-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n123456789u"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "123456789u")]
   (is
    (helper/equal?
     (helper/translate {:uint64Value "123456789"})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_int_negative_min-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n-9223372036854775808"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "-9223372036854775808")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "-9223372036854775808"})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_float_negative_exp-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n-2.3e+1"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "-2.3e+1")]
   (is (helper/equal? (helper/translate {:doubleValue -23}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_string_excl-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n\"!\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "\"!\"")]
   (is (helper/equal? (helper/translate {:stringValue "!"}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_string_escape-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n'\\''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'\\''")]
   (is (helper/equal? (helper/translate {:stringValue "'"}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_bytes_escape-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\nb'ÿ'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'ÿ'")]
   (is (helper/equal? (helper/translate {:bytesValue "w78="}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_bytes_invalid_utf8-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\nb'\\000\\xff'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'\\000\\xff'")]
   (is (helper/equal? (helper/translate {:bytesValue "AP8="}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_list_singleitem-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n[-1]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "[-1]")]
   (is
    (helper/equal?
     (helper/translate {:listValue {:values [{:int64Value "-1"}]}})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_map_singleitem-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n{\"k\":\"v\"}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{\"k\":\"v\"}")]
   (is
    (helper/equal?
     (helper/translate
      {:mapValue
       {:entries
        [{:key {:stringValue "k"}, :value {:stringValue "v"}}]}})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_bool_true-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\ntrue"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "true")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_int_hex-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n0x55555555"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0x55555555")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "1431655765"})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_int_hex_negative-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n-0x55555555"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "-0x55555555")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "-1431655765"})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_uint_hex-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n0x55555555u"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "0x55555555u")]
   (is
    (helper/equal?
     (helper/translate {:uint64Value "1431655765"})
     res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_unicode_escape_four-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n\"\\u270c\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "\"\\u270c\"")]
   (is (helper/equal? (helper/translate {:stringValue "✌"}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_unicode_escape_eight-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n\"\\U0001f431\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "\"\\U0001f431\"")]
   (is (helper/equal? (helper/translate {:stringValue "🐱"}) res)))))

(deftest
 basic-self_eval_nonzeroish-self_eval_ascii_escape_seq-test
 (testing
  "Simple self-evaluating forms to non-zero-ish values.\n\"\\a\\b\\f\\n\\r\\t\\v\\\"\\'\\\\\""
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "\"\\a\\b\\f\\n\\r\\t\\v\\\"\\'\\\\\"")]
   (is
    (helper/equal?
     (helper/translate {:stringValue "\b\f\n\r\t\"'\\"})
     res)))))

(deftest
 basic-variables-self_eval_bound_lookup-test
 (testing
  "Variable lookups.\nx"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:x {:value {:int64Value "123"}}}),
      :translate-result? false}
     "x")]
   (is (helper/equal? (helper/translate {:int64Value "123"}) res)))))

(deftest
 basic-variables-self_eval_unbound_lookup-test
 (testing
  "Variable lookups.\nx"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "x")]
   (is (helper/error? res)))))

(deftest
 basic-variables-unbound_is_runtime_error-test
 (testing
  "Variable lookups.\nx || true"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "x || true")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 basic-functions-binop-test
 (testing
  "Basic mechanisms for function calls.\n1 + 1"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "1 + 1")]
   (is (helper/equal? (helper/translate {:int64Value "2"}) res)))))

(deftest
 basic-functions-unbound-test
 (testing
  "Basic mechanisms for function calls.\nf_unknown(17)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "f_unknown(17)")]
   (is (helper/error? res)))))

(deftest
 basic-functions-unbound_is_runtime_error-test
 (testing
  "Basic mechanisms for function calls.\nf_unknown(17) || true"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "f_unknown(17) || true")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 basic-reserved_const-false-test
 (testing
  "Named constants should never be shadowed by identifiers.\nfalse"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:false {:value {:boolValue true}}}),
      :translate-result? false}
     "false")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 basic-reserved_const-true-test
 (testing
  "Named constants should never be shadowed by identifiers.\ntrue"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:true {:value {:boolValue false}}}),
      :translate-result? false}
     "true")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 basic-reserved_const-null-test
 (testing
  "Named constants should never be shadowed by identifiers.\nnull"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:null {:value {:boolValue true}}}),
      :translate-result? false}
     "null")]
   (is (helper/equal? (helper/translate {:nullValue nil}) res)))))
