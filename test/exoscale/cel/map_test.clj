;; Tests for map operations on bound maps: membership with `in`, `has()`
;; and equality of values, including keys containing dashes.
(ns exoscale.cel.map-test
  (:require [clojure.test :refer [deftest is testing]]
            [exoscale.cel.parser :as parser]))

(def abc->cde
  {:headers {"abc" "cde"
             "with-dashes" "cde"}})

(deftest test-operator-in
  (testing "non-dashed value"
    (testing "value does not exist"
      (is (not (parser/parse-eval {:bindings abc->cde
                                   :throw-on-error? true}
                                  "('xxx' in headers)"))))

    (testing "value exists"
      (is (parser/parse-eval {:bindings abc->cde
                              :throw-on-error? true}
                             "('abc' in headers)"))))

  (testing "value with dashes"
    (testing "value does not exist"
      (is (not (parser/parse-eval {:bindings abc->cde
                                   :throw-on-error? true}
                                  "('some-header' in headers)"))))

    (testing "value exists"
      (is (parser/parse-eval {:bindings abc->cde
                              :throw-on-error? true}
                             "('with-dashes' in headers)")))))

(deftest test-map-has
  (testing "value exists"
    (is (parser/parse-eval {:bindings abc->cde
                            :throw-on-error? true}
                           "has(headers.abc)"))))

(deftest test-map-equality
  (testing "value matches"
    (is (parser/parse-eval {:bindings abc->cde
                            :throw-on-error? true}
                           "(headers.abc) == 'cde'")))

  (testing "value with dashes matches"
    (is (parser/parse-eval {:bindings abc->cde
                            :throw-on-error? true}
                           "headers['with-dashes'] == 'cde'"))))
