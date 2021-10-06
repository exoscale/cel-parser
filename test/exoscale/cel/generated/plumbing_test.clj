(ns
 exoscale.cel.generated.plumbing-test
 "Generated test - Check that the ConformanceService server can accept all arguments and return all responses."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 plumbing-min-min_program-test
 (testing
  "Minimal programs.\n17"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "17")]
   (is (helper/equal? (helper/translate {:int64Value "17"}) res)))))

(deftest
 plumbing-eval_results-error_result-test
 (testing
  "All evaluation result kinds.\n1 / 0"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "1 / 0")]
   (is (helper/error? res)))))

(deftest
 plumbing-eval_results-eval_map_results-test
 (testing
  "All evaluation result kinds.\n{\"k1\":\"v1\",\"k\":\"v\"}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{\"k1\":\"v1\",\"k\":\"v\"}")]
   (is
    (helper/equal?
     (helper/translate
      {:mapValue
       {:entries
        [{:key {:stringValue "k"}, :value {:stringValue "v"}}
         {:key {:stringValue "k1"}, :value {:stringValue "v1"}}]}})
     res)))))

(deftest
 plumbing-check_inputs-skip_check-test
 (testing
  "All inputs to Check phase.\n[17, 'pancakes']"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "[17, 'pancakes']")]
   (is
    (helper/equal?
     (helper/translate
      {:listValue
       {:values [{:int64Value "17"} {:stringValue "pancakes"}]}})
     res)))))

(deftest
 plumbing-eval_inputs-one_ignored_value_arg-test
 (testing
  "All inputs to Eval phase.\n'foo'"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:x {:value {:int64Value "17"}}}),
      :translate-result? false}
     "'foo'")]
   (is (helper/equal? (helper/translate {:stringValue "foo"}) res)))))
