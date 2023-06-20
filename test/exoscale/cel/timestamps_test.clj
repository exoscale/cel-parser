(ns exoscale.cel.timestamps-test
  (:require
   [clojure.test :refer [deftest testing is]]
   [exoscale.cel.parser :as parser]
   [exoscale.cel.test-helper :as helper]))

(deftest timestamp-fn-test
  (let [timestamp-fn (fn [f datetime]
                       (:x (parser/parse-eval
                            {:bindings (helper/bindings nil), :translate-result? false}
                            (str "timestamp('" datetime "')." f "()"))))
        get-day-of-week (partial timestamp-fn "getDayOfWeek")
        get-day-of-month (partial timestamp-fn "getDayOfMonth")
        get-day-of-year (partial timestamp-fn "getDayOfYear")
        get-month (partial timestamp-fn "getMonth")
        get-full-year (partial timestamp-fn "getFullYear")
        get-hours (partial timestamp-fn "getHours")
        get-minutes (partial timestamp-fn "getMinutes")
        get-seconds (partial timestamp-fn "getSeconds")
        get-date (partial timestamp-fn "getDate")]

    (testing "getDayOfWeek: get day of week from the date in UTC, zero-based, zero for Sunday"
      (is (= 0 (get-day-of-week "2023-06-18T23:31:30Z")))
      (is (= 1 (get-day-of-week "2023-06-19T23:31:30Z")))
      (is (= 2 (get-day-of-week "2023-06-20T23:31:30Z"))))

    (testing "getDayOfMonth: get day of month from the date in UTC, zero-based indexing"
      (is (= 0 (get-day-of-month "2023-06-01T23:31:30Z"))))

    (testing "getDate: get day of month from the date with timezone, one-based indexing"
      (is (= 1 (get-date "2023-06-01T23:00:00Z")))
      (is (= 2 (get-date "2023-06-02T23:00:01Z"))))

    (testing "getDayOfYear: get day of year from the date in UTC, zero-based indexing"
      (is (= 0 (get-day-of-year "2023-01-01T23:31:30Z"))))

    (testing "getMonth: get month from the date in UTC, 0-11"
      (is (= 0 (get-month "2023-01-01T23:31:30Z")))
      (is (= 7 (get-month "2023-08-01T23:31:30Z")))
      (is (= 11 (get-month "2023-12-31T23:31:30Z"))))

    (testing "getFullYear:  get year from the date in UTC"
      (is (= 2023 (get-full-year "2023-01-01T23:31:30Z")))
      (is (= 1979 (get-full-year "1979-03-08T00:31:30Z"))))

    (testing "getHours: get hours from the date in UTC, 0-23"
      (is (= 23 (get-hours "2023-06-20T23:31:30Z")))
      (is (= 17 (get-hours "2023-06-20T17:00:30Z")))
      (is (= 0 (get-hours "2023-06-20T00:00:00Z"))))

    (testing "getMinutes: get minutes from the date in UTC, 0-59"
      (is (= 31 (get-minutes "2023-06-20T23:31:30Z")))
      (is (= 0 (get-minutes "2023-06-20T23:00:30Z")))
      (is (= 59 (get-minutes "2023-06-20T23:59:30Z"))))

    (testing "getSeconds: get seconds from the date in UTC, 0-59"
      (is (= 0 (get-seconds "2023-06-20T23:00:00Z")))
      (is (= 1 (get-seconds "2023-06-20T23:00:01Z")))
      (is (= 33 (get-seconds "2023-06-20T23:00:33Z")))
      (is (= 59 (get-seconds "2023-06-20T23:00:59Z"))))))

