(ns exoscale.cel.timestamps-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.parser :as parser]
   [exoscale.cel.test-helper :as helper]))

(deftest ensure-datetime-conversion-24
  (testing
   "Ensure that getHours returns 24 formated hours"
    (is (-> (parser/parse-eval
             {:bindings (helper/bindings nil), :translate-result? false}
             "timestamp('2009-02-13T23:31:30Z').getHours()")
            :x
            (= 23)))))

