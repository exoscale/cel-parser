(ns exoscale.cel.map-test
  (:require [clojure.test :refer [deftest is testing]]
            [exoscale.cel.parser :as parser]
            [exoscale.cel.test-helper :as helper]))

(deftest test-operator-in
  (let [abc->cde (helper/bindings {:headers {:value {:mapValue {:entries [{:key   {:stringValue "abc"}
                                                                           :value {:stringValue "cde"}}

                                                                          {:key   {:stringValue "with-dashes"}
                                                                           :value {:stringValue "cde"}}]}}}})]
    (testing "non-dashed value"
      (testing "value does not exist"
        (is (not (parser/parse-eval {:bindings          abc->cde
                                     :throw-on-error?   true
                                     :translate-result? true}
                                    "('xxx' in headers)"))))

      (testing "value exists"
        (is (parser/parse-eval {:bindings          abc->cde
                                :throw-on-error?   true
                                :translate-result? true}
                               "('abc' in headers)"))))

    (testing "value with dashes"
      (testing "value does not exist"
        (is (not (parser/parse-eval {:bindings          abc->cde
                                     :throw-on-error?   true
                                     :translate-result? true}
                                    "('some-header' in headers)"))))

      (testing "value exists"
        (is (parser/parse-eval {:bindings          abc->cde
                                :throw-on-error?   true
                                :translate-result? true}
                               "('with-dashes' in headers)"))))))

(deftest test-map-has
  (let [abc->cde (helper/bindings {:headers {:value {:mapValue {:entries [{:key   {:stringValue "abc"}
                                                                           :value {:stringValue "cde"}}

                                                                          {:key   {:stringValue "with-dashes"}
                                                                           :value {:stringValue "cde"}}]}}}})]
    (testing "value exists"
      (is (parser/parse-eval {:bindings          abc->cde
                              :throw-on-error?   true
                              :translate-result? true}
                             "has(headers.abc)")))))

(deftest test-map-equality
  (let [abc->cde (helper/bindings {:headers {:value {:mapValue {:entries [{:key   {:stringValue "abc"}
                                                                           :value {:stringValue "cde"}}

                                                                          {:key   {:stringValue "with-dashes"}
                                                                           :value {:stringValue "cde"}}]}}}})]
    (testing "value matches"
      (is (parser/parse-eval {:bindings          abc->cde
                              :throw-on-error?   true
                              :translate-result? true}
                             "(headers.abc) == 'cde'")))

    (testing "value with dashes matches"
      (is (parser/parse-eval {:bindings          abc->cde
                              :throw-on-error?   true
                              :translate-result? true}
                             "headers['with-dashes'] == 'cde'")))))
