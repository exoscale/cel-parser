(ns exoscale.cel.errors-test
  (:require [clojure.test :refer [deftest is testing]]
            [exoscale.cel.parser :as parser]))

(deftest test-errors
  (testing "invalid syntax"
    (try (parser/parse-eval {} "can't parse this")
         (catch Exception e
           (is (-> e ex-data :errors first :msg
                   (= "token recognition error at: ''t parse this'")))))))
