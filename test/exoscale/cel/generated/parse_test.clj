(ns
 exoscale.cel.generated.parse-test
  "Generated test - End-to-end parsing tests."
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.test-helper :as helper]
   [exoscale.cel.parser :as parser]))

(deftest
  parse-nest-list_index-test
  (testing
   "Deep parse trees which all implementations must support.\na[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[0]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]"
    (let
     [res
      (parser/parse-eval
       {:bindings
        (helper/bindings
         {:a {:value {:listValue {:values [{:int64Value "0"}]}}}}),
        :translate-result? false}
       "a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[a[0]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]")]
      (is (helper/equal? (helper/translate {:int64Value "0"}) res)))))

(deftest
  parse-nest-funcall-test
  (testing
   "Deep parse trees which all implementations must support.\nint(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(7))))))))))))))))))))))))))))))))"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(int(uint(7))))))))))))))))))))))))))))))))")]
      (is (helper/equal? (helper/translate {:int64Value "7"}) res)))))

(deftest
  parse-nest-parens-test
  (testing
   "Deep parse trees which all implementations must support.\n((((((((((((((((((((((((((((((((7))))))))))))))))))))))))))))))))"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "((((((((((((((((((((((((((((((((7))))))))))))))))))))))))))))))))")]
      (is (helper/equal? (helper/translate {:int64Value "7"}) res)))))

(deftest
  parse-nest-list_literal-test
  (testing
   "Deep parse trees which all implementations must support.\nsize([[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[0]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]])"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size([[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[0]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]])")]
      (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
  parse-nest-map_literal-test
  (testing
   "Deep parse trees which all implementations must support.\nsize({0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: 'foo'}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}})"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "size({0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: {0: 'foo'}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}})")]
      (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
  parse-repeat-conditional-test
  (testing
   "Repetitive parse trees which all implementations must support.\ntrue ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : true ? true : false")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  parse-repeat-or-test
  (testing
   "Repetitive parse trees which all implementations must support.\nfalse || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || false || true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  parse-repeat-and-test
  (testing
   "Repetitive parse trees which all implementations must support.\ntrue && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && false"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && true && false")]
      (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
  parse-repeat-add_sub-test
  (testing
   "Repetitive parse trees which all implementations must support.\n3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3 - 3 + 3")]
      (is (helper/equal? (helper/translate {:int64Value "3"}) res)))))

(deftest
  parse-repeat-mul_div-test
  (testing
   "Repetitive parse trees which all implementations must support.\n4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4 * 4 / 4")]
      (is (helper/equal? (helper/translate {:int64Value "4"}) res)))))

(deftest
  parse-repeat-not-test
  (testing
   "Repetitive parse trees which all implementations must support.\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!true"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!true")]
      (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
  parse-repeat-unary_neg-test
  (testing
   "Repetitive parse trees which all implementations must support.\n--------------------------------19"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "--------------------------------19")]
      (is (helper/equal? (helper/translate {:int64Value "19"}) res)))))

(deftest
  parse-repeat-index-test
  (testing
   "Repetitive parse trees which all implementations must support.\n[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[['foo']]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[['foo']]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0][0]")]
      (is (helper/equal? (helper/translate {:stringValue "foo"}) res)))))

(deftest
  parse-repeat-list_literal-test
  (testing
   "Repetitive parse trees which all implementations must support.\n[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31][17]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31][17]")]
      (is (helper/equal? (helper/translate {:int64Value "17"}) res)))))

(deftest
  parse-repeat-map_literal-test
  (testing
   "Repetitive parse trees which all implementations must support.\n{0: 'zero', 1: 'one', 2: 'two', 3: 'three', 4: 'four', 5: 'five', 6: 'six', 7: 'seven', 8: 'eight', 9: 'nine', 10: 'ten', 11: 'eleven', 12: 'twelve', 13: 'thirteen', 14: 'fourteen', 15: 'fifteen', 16: 'sixteen', 17: 'seventeen', 18: 'eighteen', 19: 'nineteen', 20: 'twenty', 21: 'twenty-one', 22: 'twenty-two', 23: 'twenty-three', 24: 'twenty-four', 25: 'twenty-five', 26: 'twenty-six', 27: 'twenty-seven', 28: 'twenty-eight', 29: 'twenty-nine', 30: 'thirty', 31: 'thirty-one'}[17]"
    (let
     [res
      (parser/parse-eval
       {:bindings (helper/bindings nil), :translate-result? false}
       "{0: 'zero', 1: 'one', 2: 'two', 3: 'three', 4: 'four', 5: 'five', 6: 'six', 7: 'seven', 8: 'eight', 9: 'nine', 10: 'ten', 11: 'eleven', 12: 'twelve', 13: 'thirteen', 14: 'fourteen', 15: 'fifteen', 16: 'sixteen', 17: 'seventeen', 18: 'eighteen', 19: 'nineteen', 20: 'twenty', 21: 'twenty-one', 22: 'twenty-two', 23: 'twenty-three', 24: 'twenty-four', 25: 'twenty-five', 26: 'twenty-six', 27: 'twenty-seven', 28: 'twenty-eight', 29: 'twenty-nine', 30: 'thirty', 31: 'thirty-one'}[17]")]
      (is
       (helper/equal?
        (helper/translate {:stringValue "seventeen"})
        res)))))
