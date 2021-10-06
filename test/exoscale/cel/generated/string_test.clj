(ns
 exoscale.cel.generated.string-test
 "Generated test - Tests for string and bytes operations."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 string-size-empty-test
 (testing
  "Tests for the size() function.\nsize('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size('')")]
   (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
 string-size-one_ascii-test
 (testing
  "Tests for the size() function.\nsize('A')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size('A')")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 string-size-one_unicode-test
 (testing
  "Tests for the size() function.\nsize('ÿ')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size('ÿ')")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 string-size-ascii-test
 (testing
  "Tests for the size() function.\nsize('four')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size('four')")]
   (is (helper/equal? (helper/translate {:int64Value "4"}) res)))))

(deftest
 string-size-unicode-test
 (testing
  "Tests for the size() function.\nsize('πέντε')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size('πέντε')")]
   (is (helper/equal? (helper/translate {:int64Value "5"}) res)))))

(deftest
 string-size-bytes_empty-test
 (testing
  "Tests for the size() function.\nsize(b'')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size(b'')")]
   (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
 string-size-bytes-test
 (testing
  "Tests for the size() function.\nsize(b'abc')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "size(b'abc')")]
   (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))

(deftest
 string-starts_with-basic_true-test
 (testing
  "Tests for the startsWith() function.\n'foobar'.startsWith('foo')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.startsWith('foo')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-starts_with-basic_false-test
 (testing
  "Tests for the startsWith() function.\n'foobar'.startsWith('bar')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.startsWith('bar')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-starts_with-empty_target-test
 (testing
  "Tests for the startsWith() function.\n''.startsWith('foo')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.startsWith('foo')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-starts_with-empty_arg-test
 (testing
  "Tests for the startsWith() function.\n'foobar'.startsWith('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.startsWith('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-starts_with-empty_empty-test
 (testing
  "Tests for the startsWith() function.\n''.startsWith('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.startsWith('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-starts_with-unicode-test
 (testing
  "Tests for the startsWith() function.\n'завтра'.startsWith('за')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'завтра'.startsWith('за')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-ends_with-basic_true-test
 (testing
  "Tests for the endsWith() function.\n'foobar'.endsWith('bar')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.endsWith('bar')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-ends_with-basic_false-test
 (testing
  "Tests for the endsWith() function.\n'foobar'.endsWith('foo')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.endsWith('foo')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-ends_with-empty_target-test
 (testing
  "Tests for the endsWith() function.\n''.endsWith('foo')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.endsWith('foo')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-ends_with-empty_arg-test
 (testing
  "Tests for the endsWith() function.\n'foobar'.endsWith('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'foobar'.endsWith('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-ends_with-empty_empty-test
 (testing
  "Tests for the endsWith() function.\n''.endsWith('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.endsWith('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-ends_with-unicode-test
 (testing
  "Tests for the endsWith() function.\n'forté'.endsWith('té')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'forté'.endsWith('té')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-basic-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'hubba'.matches('ubb')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'hubba'.matches('ubb')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-empty_target-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n''.matches('foo|bar')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.matches('foo|bar')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-matches-empty_arg-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'cows'.matches('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'cows'.matches('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-empty_empty-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n''.matches('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.matches('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-re_concat-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'abcd'.matches('bc')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'abcd'.matches('bc')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-re_alt-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'grey'.matches('gr(a|e)y')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'grey'.matches('gr(a|e)y')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-re_rep-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'banana'.matches('ba(na)*')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'banana'.matches('ba(na)*')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-matches-unicode-test
 (testing
  "Tests for regexp matching.  For now, we will only test the subset of regular languages.\n'mañana'.matches('a+ñ+a+')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'mañana'.matches('a+ñ+a+')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-concatentation-concat_true-test
 (testing
  "Tests for string concatenation.\n'he' + 'llo'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'he' + 'llo'")]
   (is (helper/equal? (helper/translate {:stringValue "hello"}) res)))))

(deftest
 string-concatentation-concat_with_spaces-test
 (testing
  "Tests for string concatenation.\n'hello' + ' ' == 'hello'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'hello' + ' ' == 'hello'")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-concatentation-concat_empty_string_beginning-test
 (testing
  "Tests for string concatenation.\n'' + 'abc'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'' + 'abc'")]
   (is (helper/equal? (helper/translate {:stringValue "abc"}) res)))))

(deftest
 string-concatentation-concat_empty_string_end-test
 (testing
  "Tests for string concatenation.\n'abc' + ''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'abc' + ''")]
   (is (helper/equal? (helper/translate {:stringValue "abc"}) res)))))

(deftest
 string-concatentation-concat_empty_with_empty-test
 (testing
  "Tests for string concatenation.\n'' + ''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'' + ''")]
   (is (helper/equal? (helper/translate {:stringValue ""}) res)))))

(deftest
 string-concatentation-unicode_unicode-test
 (testing
  "Tests for string concatenation.\n'¢' + 'ÿ' + 'Ȁ'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'¢' + 'ÿ' + 'Ȁ'")]
   (is (helper/equal? (helper/translate {:stringValue "¢ÿȀ"}) res)))))

(deftest
 string-concatentation-ascii_unicode-test
 (testing
  "Tests for string concatenation.\n'r' + 'ô' + 'le'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'r' + 'ô' + 'le'")]
   (is (helper/equal? (helper/translate {:stringValue "rôle"}) res)))))

(deftest
 string-concatentation-empty_unicode-test
 (testing
  "Tests for string concatenation.\n'' + 'Ω' + ''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'' + 'Ω' + ''")]
   (is (helper/equal? (helper/translate {:stringValue "Ω"}) res)))))

(deftest
 string-contains-contains_true-test
 (testing
  "Tests for contains.\n'hello'.contains('he')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'hello'.contains('he')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-contains-contains_empty-test
 (testing
  "Tests for contains.\n'hello'.contains('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'hello'.contains('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-contains-contains_false-test
 (testing
  "Tests for contains.\n'hello'.contains('ol')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'hello'.contains('ol')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-contains-contains_multiple-test
 (testing
  "Tests for contains.\n'abababc'.contains('ababc')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'abababc'.contains('ababc')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-contains-contains_unicode-test
 (testing
  "Tests for contains.\n'Straße'.contains('aß')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'Straße'.contains('aß')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-contains-empty_contains-test
 (testing
  "Tests for contains.\n''.contains('something')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.contains('something')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 string-contains-empty_empty-test
 (testing
  "Tests for contains.\n''.contains('')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "''.contains('')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 string-bytes_concat-concat-test
 (testing
  "Tests for bytes concatenation.\nb'abc' + b'def'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'abc' + b'def'")]
   (is
    (helper/equal? (helper/translate {:bytesValue "YWJjZGVm"}) res)))))

(deftest
 string-bytes_concat-left_unit-test
 (testing
  "Tests for bytes concatenation.\nb'' + b'\\xffoo'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'' + b'\\xffoo'")]
   (is (helper/equal? (helper/translate {:bytesValue "/29v"}) res)))))

(deftest
 string-bytes_concat-right_unit-test
 (testing
  "Tests for bytes concatenation.\nb'zxy' + b''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'zxy' + b''")]
   (is (helper/equal? (helper/translate {:bytesValue "enh5"}) res)))))

(deftest
 string-bytes_concat-empty_empty-test
 (testing
  "Tests for bytes concatenation.\nb'' + b''"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "b'' + b''")]
   (is (helper/equal? (helper/translate {:bytesValue ""}) res)))))
