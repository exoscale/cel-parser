(ns
 exoscale.cel.generated.conversions-test
 "Generated test - Tests for type conversions."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 conversions-bytes-string_empty-test
 (testing
  "Conversions to bytes.\nbytes('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bytes('')")]
   (is (helper/equal? (helper/translate {:bytesValue ""}) res)))))

(deftest
 conversions-bytes-string-test
 (testing
  "Conversions to bytes.\nbytes('abc')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bytes('abc')")]
   (is (helper/equal? (helper/translate {:bytesValue "YWJj"}) res)))))

(deftest
 conversions-bytes-string_unicode-test
 (testing
  "Conversions to bytes.\nbytes('ÿ')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bytes('ÿ')")]
   (is (helper/equal? (helper/translate {:bytesValue "w78="}) res)))))

(deftest
 conversions-bytes-string_unicode_vs_literal-test
 (testing
  "Conversions to bytes.\nbytes('\\377') == b'\\377'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bytes('\\377') == b'\\377'")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 conversions-double-int_zero-test
 (testing
  "Conversions to double.\ndouble(0)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(0)")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 conversions-double-int_pos-test
 (testing
  "Conversions to double.\ndouble(1000000000000)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(1000000000000)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue 1000000000000})
     res)))))

(deftest
 conversions-double-int_neg-test
 (testing
  "Conversions to double.\ndouble(-1000000000000000)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(-1000000000000000)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue -1000000000000000})
     res)))))

(deftest
 conversions-double-int_min_exact-test
 (testing
  "Conversions to double.\ndouble(-9007199254740992)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(-9007199254740992)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue -9007199254740992})
     res)))))

(deftest
 conversions-double-int_max_exact-test
 (testing
  "Conversions to double.\ndouble(9007199254740992)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(9007199254740992)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue 9007199254740992})
     res)))))

(deftest
 conversions-double-int_range-test
 (testing
  "Conversions to double.\ndouble(9223372036854775807)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(9223372036854775807)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue 9223372036854776000})
     res)))))

(deftest
 conversions-double-uint_zero-test
 (testing
  "Conversions to double.\ndouble(0u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(0u)")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 conversions-double-uint_pos-test
 (testing
  "Conversions to double.\ndouble(123u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(123u)")]
   (is (helper/equal? (helper/translate {:doubleValue 123}) res)))))

(deftest
 conversions-double-uint_max_exact-test
 (testing
  "Conversions to double.\ndouble(9007199254740992u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(9007199254740992u)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue 9007199254740992})
     res)))))

(deftest
 conversions-double-uint_range-test
 (testing
  "Conversions to double.\ndouble(18446744073709551615u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double(18446744073709551615u)")]
   (is
    (helper/equal?
     (helper/translate {:doubleValue 18446744073709552000})
     res)))))

(deftest
 conversions-double-string_zero-test
 (testing
  "Conversions to double.\ndouble('0')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('0')")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 conversions-double-string_zero_dec-test
 (testing
  "Conversions to double.\ndouble('0.0')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('0.0')")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 conversions-double-string_neg_zero-test
 (testing
  "Conversions to double.\ndouble('-0.0')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('-0.0')")]
   (is (helper/equal? (helper/translate {:doubleValue 0}) res)))))

(deftest
 conversions-double-string_no_dec-test
 (testing
  "Conversions to double.\ndouble('123')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('123')")]
   (is (helper/equal? (helper/translate {:doubleValue 123}) res)))))

(deftest
 conversions-double-string_pos-test
 (testing
  "Conversions to double.\ndouble('123.456')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('123.456')")]
   (is (helper/equal? (helper/translate {:doubleValue 123.456}) res)))))

(deftest
 conversions-double-string_neg-test
 (testing
  "Conversions to double.\ndouble('-987.654')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('-987.654')")]
   (is
    (helper/equal? (helper/translate {:doubleValue -987.654}) res)))))

(deftest
 conversions-double-string_exp_pos_pos-test
 (testing
  "Conversions to double.\ndouble('6.02214e23')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('6.02214e23')")]
   (is
    (helper/equal? (helper/translate {:doubleValue 6.02214E23}) res)))))

(deftest
 conversions-double-string_exp_pos_neg-test
 (testing
  "Conversions to double.\ndouble('1.38e-23')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('1.38e-23')")]
   (is
    (helper/equal? (helper/translate {:doubleValue 1.38E-23}) res)))))

(deftest
 conversions-double-string_exp_neg_pos-test
 (testing
  "Conversions to double.\ndouble('-84.32e7')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('-84.32e7')")]
   (is
    (helper/equal? (helper/translate {:doubleValue -843200000}) res)))))

(deftest
 conversions-double-string_exp_neg_neg-test
 (testing
  "Conversions to double.\ndouble('-5.43e-21')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double('-5.43e-21')")]
   (is
    (helper/equal? (helper/translate {:doubleValue -5.43E-21}) res)))))

(deftest
 conversions-dyn-dyn_heterogeneous_list-test
 (testing
  "Tests for dyn annotation.\ntype(dyn([1, 'one']))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(dyn([1, 'one']))")]
   (is (helper/equal? (helper/translate {:typeValue "list"}) res)))))

(deftest
 conversions-int-uint-test
 (testing
  "Conversions to int.\nint(42u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(42u)")]
   (is (helper/equal? (helper/translate {:int64Value "42"}) res)))))

(deftest
 conversions-int-uint_zero-test
 (testing
  "Conversions to int.\nint(0u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(0u)")]
   (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
 conversions-int-uint_max_exact-test
 (testing
  "Conversions to int.\nint(9223372036854775807u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(9223372036854775807u)")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "9223372036854775807"})
     res)))))

(deftest
 conversions-int-uint_range-test
 (testing
  "Conversions to int.\nint(18446744073709551615u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(18446744073709551615u)")]
   (is (helper/error? res)))))

(deftest
 conversions-int-double_round_neg-test
 (testing
  "Conversions to int.\nint(-123.456)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(-123.456)")]
   (is (helper/equal? (helper/translate {:int64Value "-123"}) res)))))

(deftest
 conversions-int-double_truncate-test
 (testing
  "Conversions to int.\nint(1.9)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(1.9)")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 conversions-int-double_truncate_neg-test
 (testing
  "Conversions to int.\nint(-7.9)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(-7.9)")]
   (is (helper/equal? (helper/translate {:int64Value "-7"}) res)))))

(deftest
 conversions-int-double_half_pos-test
 (testing
  "Conversions to int.\nint(11.5)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(11.5)")]
   (is (helper/equal? (helper/translate {:int64Value "11"}) res)))))

(deftest
 conversions-int-double_half_neg-test
 (testing
  "Conversions to int.\nint(-3.5)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(-3.5)")]
   (is (helper/equal? (helper/translate {:int64Value "-3"}) res)))))

(deftest
 conversions-int-double_big_exact-test
 (testing
  "Conversions to int.\nint(double(36028797018963968))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(double(36028797018963968))")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "36028797018963968"})
     res)))))

(deftest
 conversions-int-double_big_precision-test
 (testing
  "Conversions to int.\nint(double(36028797018963969))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(double(36028797018963969))")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "36028797018963968"})
     res)))))

(deftest
 conversions-int-double_range-test
 (testing
  "Conversions to int.\nint(1e99)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(1e99)")]
   (is (helper/error? res)))))

(deftest
 conversions-int-string-test
 (testing
  "Conversions to int.\nint('987')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int('987')")]
   (is (helper/equal? (helper/translate {:int64Value "987"}) res)))))

(deftest
 conversions-int-timestamp-test
 (testing
  "Conversions to int.\nint(timestamp('2004-09-16T23:59:59Z'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(timestamp('2004-09-16T23:59:59Z'))")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "1095379199"})
     res)))))

(deftest
 conversions-string-int-test
 (testing
  "Conversions to string.\nstring(123)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(123)")]
   (is (helper/equal? (helper/translate {:stringValue "123"}) res)))))

(deftest
 conversions-string-int_neg-test
 (testing
  "Conversions to string.\nstring(-456)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(-456)")]
   (is (helper/equal? (helper/translate {:stringValue "-456"}) res)))))

(deftest
 conversions-string-uint-test
 (testing
  "Conversions to string.\nstring(9876u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(9876u)")]
   (is (helper/equal? (helper/translate {:stringValue "9876"}) res)))))

(deftest
 conversions-string-double-test
 (testing
  "Conversions to string.\nstring(123.456)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(123.456)")]
   (is
    (helper/equal? (helper/translate {:stringValue "123.456"}) res)))))

(deftest
 conversions-string-double_hard-test
 (testing
  "Conversions to string.\nstring(-4.5e-3)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(-4.5e-3)")]
   (is
    (helper/equal? (helper/translate {:stringValue "-0.0045"}) res)))))

(deftest
 conversions-string-bytes-test
 (testing
  "Conversions to string.\nstring(b'abc')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(b'abc')")]
   (is (helper/equal? (helper/translate {:stringValue "abc"}) res)))))

(deftest
 conversions-string-bytes_unicode-test
 (testing
  "Conversions to string.\nstring(b'\\303\\277')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(b'\\303\\277')")]
   (is (helper/equal? (helper/translate {:stringValue "ÿ"}) res)))))

(deftest
 conversions-string-bytes_invalid-test
 (testing
  "Conversions to string.\nstring(b'\\000\\xff')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(b'\\000\\xff')")]
   (is (helper/error? res)))))

(deftest
 conversions-type-bool-test
 (testing
  "Type reflection tests.\ntype(true)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(true)")]
   (is (helper/equal? (helper/translate {:typeValue "bool"}) res)))))

(deftest
 conversions-type-bool_denotation-test
 (testing
  "Type reflection tests.\nbool"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bool")]
   (is (helper/equal? (helper/translate {:typeValue "bool"}) res)))))

(deftest
 conversions-type-dyn_no_denotation-test
 (testing
  "Type reflection tests.\ndyn"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "dyn")]
   (is (helper/error? res)))))

(deftest
 conversions-type-int-test
 (testing
  "Type reflection tests.\ntype(0)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(0)")]
   (is (helper/equal? (helper/translate {:typeValue "int"}) res)))))

(deftest
 conversions-type-int_denotation-test
 (testing
  "Type reflection tests.\nint"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int")]
   (is (helper/equal? (helper/translate {:typeValue "int"}) res)))))

(deftest
 conversions-type-eq_same-test
 (testing
  "Type reflection tests.\ntype(true) == type(false)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(true) == type(false)")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 conversions-type-uint-test
 (testing
  "Type reflection tests.\ntype(64u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(64u)")]
   (is (helper/equal? (helper/translate {:typeValue "uint"}) res)))))

(deftest
 conversions-type-uint_denotation-test
 (testing
  "Type reflection tests.\nuint"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint")]
   (is (helper/equal? (helper/translate {:typeValue "uint"}) res)))))

(deftest
 conversions-type-double-test
 (testing
  "Type reflection tests.\ntype(3.14)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(3.14)")]
   (is (helper/equal? (helper/translate {:typeValue "double"}) res)))))

(deftest
 conversions-type-double_denotation-test
 (testing
  "Type reflection tests.\ndouble"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "double")]
   (is (helper/equal? (helper/translate {:typeValue "double"}) res)))))

(deftest
 conversions-type-null_type-test
 (testing
  "Type reflection tests.\ntype(null)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(null)")]
   (is
    (helper/equal? (helper/translate {:typeValue "null_type"}) res)))))

(deftest
 conversions-type-null_type_denotation-test
 (testing
  "Type reflection tests.\nnull_type"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "null_type")]
   (is
    (helper/equal? (helper/translate {:typeValue "null_type"}) res)))))

(deftest
 conversions-type-string-test
 (testing
  "Type reflection tests.\ntype('foo')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type('foo')")]
   (is (helper/equal? (helper/translate {:typeValue "string"}) res)))))

(deftest
 conversions-type-string_denotation-test
 (testing
  "Type reflection tests.\nstring"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string")]
   (is (helper/equal? (helper/translate {:typeValue "string"}) res)))))

(deftest
 conversions-type-bytes-test
 (testing
  "Type reflection tests.\ntype(b'\\xff')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(b'\\xff')")]
   (is (helper/equal? (helper/translate {:typeValue "bytes"}) res)))))

(deftest
 conversions-type-bytes_denotation-test
 (testing
  "Type reflection tests.\nbytes"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "bytes")]
   (is (helper/equal? (helper/translate {:typeValue "bytes"}) res)))))

(deftest
 conversions-type-list-test
 (testing
  "Type reflection tests.\ntype([1, 2, 3])"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type([1, 2, 3])")]
   (is (helper/equal? (helper/translate {:typeValue "list"}) res)))))

(deftest
 conversions-type-list_denotation-test
 (testing
  "Type reflection tests.\nlist"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "list")]
   (is (helper/equal? (helper/translate {:typeValue "list"}) res)))))

(deftest
 conversions-type-lists_monomorphic-test
 (testing
  "Type reflection tests.\ntype([1, 2, 3]) == type(['one', 'two', 'three'])"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type([1, 2, 3]) == type(['one', 'two', 'three'])")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 conversions-type-map-test
 (testing
  "Type reflection tests.\ntype({4: 16})"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type({4: 16})")]
   (is (helper/equal? (helper/translate {:typeValue "map"}) res)))))

(deftest
 conversions-type-map_denotation-test
 (testing
  "Type reflection tests.\nmap"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "map")]
   (is (helper/equal? (helper/translate {:typeValue "map"}) res)))))

(deftest
 conversions-type-map_monomorphic-test
 (testing
  "Type reflection tests.\ntype({'one': 1}) == type({1: 'one'})"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type({'one': 1}) == type({1: 'one'})")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 conversions-type-eq_diff-test
 (testing
  "Type reflection tests.\ntype(7) == type(7u)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(7) == type(7u)")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 conversions-type-neq_same-test
 (testing
  "Type reflection tests.\ntype(0.0) != type(-0.0)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(0.0) != type(-0.0)")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 conversions-type-neq_diff-test
 (testing
  "Type reflection tests.\ntype(0.0) != type(0)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(0.0) != type(0)")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 conversions-type-meta-test
 (testing
  "Type reflection tests.\ntype(type(7)) == type(type(7u))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(type(7)) == type(type(7u))")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 conversions-type-type-test
 (testing
  "Type reflection tests.\ntype(int)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(int)")]
   (is (helper/equal? (helper/translate {:typeValue "type"}) res)))))

(deftest
 conversions-type-type_denotation-test
 (testing
  "Type reflection tests.\ntype"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type")]
   (is (helper/equal? (helper/translate {:typeValue "type"}) res)))))

(deftest
 conversions-type-type_type-test
 (testing
  "Type reflection tests.\ntype(type)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(type)")]
   (is (helper/equal? (helper/translate {:typeValue "type"}) res)))))

(deftest
 conversions-uint-int-test
 (testing
  "Conversions to uint.\nuint(1729)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(1729)")]
   (is (helper/equal? (helper/translate {:uint64Value "1729"}) res)))))

(deftest
 conversions-uint-int_max-test
 (testing
  "Conversions to uint.\nuint(9223372036854775807)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(9223372036854775807)")]
   (is
    (helper/equal?
     (helper/translate {:uint64Value "9223372036854775807"})
     res)))))

(deftest
 conversions-uint-int_neg-test
 (testing
  "Conversions to uint.\nuint(-1)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(-1)")]
   (is (helper/error? res)))))

(deftest
 conversions-uint-double-test
 (testing
  "Conversions to uint.\nuint(3.14159265)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(3.14159265)")]
   (is (helper/equal? (helper/translate {:uint64Value "3"}) res)))))

(deftest
 conversions-uint-double_truncate-test
 (testing
  "Conversions to uint.\nuint(1.9)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(1.9)")]
   (is (helper/equal? (helper/translate {:uint64Value "1"}) res)))))

(deftest
 conversions-uint-double_half-test
 (testing
  "Conversions to uint.\nuint(25.5)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(25.5)")]
   (is (helper/equal? (helper/translate {:uint64Value "25"}) res)))))

(deftest
 conversions-uint-double_big_exact-test
 (testing
  "Conversions to uint.\nuint(double(36028797018963968u))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(double(36028797018963968u))")]
   (is
    (helper/equal?
     (helper/translate {:uint64Value "36028797018963968"})
     res)))))

(deftest
 conversions-uint-double_big_precision-test
 (testing
  "Conversions to uint.\nuint(double(36028797018963969u))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(double(36028797018963969u))")]
   (is
    (helper/equal?
     (helper/translate {:uint64Value "36028797018963968"})
     res)))))

(deftest
 conversions-uint-double_uint_max_range-test
 (testing
  "Conversions to uint.\nint(18446744073709551615.0)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(18446744073709551615.0)")]
   (is (helper/error? res)))))

(deftest
 conversions-uint-double_range_beyond_uint-test
 (testing
  "Conversions to uint.\nuint(6.022e23)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint(6.022e23)")]
   (is (helper/error? res)))))

(deftest
 conversions-uint-string-test
 (testing
  "Conversions to uint.\nuint('300')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "uint('300')")]
   (is (helper/equal? (helper/translate {:uint64Value "300"}) res)))))
