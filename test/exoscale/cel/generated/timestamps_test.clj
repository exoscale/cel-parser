(ns
 exoscale.cel.generated.timestamps-test
 "Generated test - Timestamp and duration tests."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 timestamps-timestamp_conversions-toInt_timestamp-test
 (testing
  "Conversions of timestamps to other types.\nint(timestamp('2009-02-13T23:31:30Z'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "int(timestamp('2009-02-13T23:31:30Z'))")]
   (is
    (helper/equal?
     (helper/translate {:int64Value "1234567890"})
     res)))))

(deftest
 timestamps-timestamp_conversions-toString_timestamp-test
 (testing
  "Conversions of timestamps to other types.\nstring(timestamp('2009-02-13T23:31:30Z'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(timestamp('2009-02-13T23:31:30Z'))")]
   (is
    (helper/equal?
     (helper/translate {:stringValue "2009-02-13T23:31:30Z"})
     res)))))

(deftest
 timestamps-timestamp_conversions-toString_timestamp_nanos-test
 (testing
  "Conversions of timestamps to other types.\nstring(timestamp('9999-12-31T23:59:59.999999999Z'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(timestamp('9999-12-31T23:59:59.999999999Z'))")]
   (is
    (helper/equal?
     (helper/translate {:stringValue "9999-12-31T23:59:59.999999999Z"})
     res)))))

(deftest
 timestamps-timestamp_conversions-toType_timestamp-test
 (testing
  "Conversions of timestamps to other types.\ntype(timestamp('2009-02-13T23:31:30Z'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(timestamp('2009-02-13T23:31:30Z'))")]
   (is
    (helper/equal?
     (helper/translate {:typeValue "google.protobuf.Timestamp"})
     res)))))

(deftest
 timestamps-duration_conversions-toString_duration-test
 (testing
  "Conversions of durations to other types.\nstring(duration('1000000s'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "string(duration('1000000s'))")]
   (is
    (helper/equal? (helper/translate {:stringValue "1000000s"}) res)))))

(deftest
 timestamps-duration_conversions-toType_duration-test
 (testing
  "Conversions of durations to other types.\ntype(duration('1000000s'))"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "type(duration('1000000s'))")]
   (is
    (helper/equal?
     (helper/translate {:typeValue "google.protobuf.Duration"})
     res)))))

(deftest
 timestamps-timestamp_selectors-getDate-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getDate()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDate()")]
   (is (helper/equal? (helper/translate {:int64Value "13"}) res)))))

(deftest
 timestamps-timestamp_selectors-getDayOfMonth-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfMonth()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfMonth()")]
   (is (helper/equal? (helper/translate {:int64Value "12"}) res)))))

(deftest
 timestamps-timestamp_selectors-getDayOfWeek-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfWeek()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfWeek()")]
   (is (helper/equal? (helper/translate {:int64Value "5"}) res)))))

(deftest
 timestamps-timestamp_selectors-getDayOfYear-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfYear()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfYear()")]
   (is (helper/equal? (helper/translate {:int64Value "43"}) res)))))

(deftest
 timestamps-timestamp_selectors-getFullYear-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getFullYear()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getFullYear()")]
   (is (helper/equal? (helper/translate {:int64Value "2009"}) res)))))

(deftest
 timestamps-timestamp_selectors-getHours-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getHours()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getHours()")]
   (is (helper/equal? (helper/translate {:int64Value "23"}) res)))))

(deftest
 timestamps-timestamp_selectors-getMilliseconds-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:20.123456789Z').getMilliseconds()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:20.123456789Z').getMilliseconds()")]
   (is (helper/equal? (helper/translate {:int64Value "123"}) res)))))

(deftest
 timestamps-timestamp_selectors-getMinutes-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getMinutes()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getMinutes()")]
   (is (helper/equal? (helper/translate {:int64Value "31"}) res)))))

(deftest
 timestamps-timestamp_selectors-getMonth-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getMonth()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getMonth()")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 timestamps-timestamp_selectors-getSeconds-test
 (testing
  "Timestamp selection operators without timezones\ntimestamp('2009-02-13T23:31:30Z').getSeconds()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getSeconds()")]
   (is (helper/equal? (helper/translate {:int64Value "30"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDate-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getDate('Australia/Sydney')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDate('Australia/Sydney')")]
   (is (helper/equal? (helper/translate {:int64Value "14"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfMonth_name_pos-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfMonth('US/Central')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfMonth('US/Central')")]
   (is (helper/equal? (helper/translate {:int64Value "12"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfMonth_numerical_pos-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfMonth('+11:00')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfMonth('+11:00')")]
   (is (helper/equal? (helper/translate {:int64Value "13"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfMonth_numerical_neg-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T02:00:00Z').getDayOfMonth('-02:30')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T02:00:00Z').getDayOfMonth('-02:30')")]
   (is (helper/equal? (helper/translate {:int64Value "11"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfMonth_name_neg-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T02:00:00Z').getDayOfMonth('America/St_Johns')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T02:00:00Z').getDayOfMonth('America/St_Johns')")]
   (is (helper/equal? (helper/translate {:int64Value "11"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfWeek-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfWeek('UTC')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfWeek('UTC')")]
   (is (helper/equal? (helper/translate {:int64Value "5"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getDayOfYear-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getDayOfYear('US/Central')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getDayOfYear('US/Central')")]
   (is (helper/equal? (helper/translate {:int64Value "43"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getFullYear-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getFullYear('-09:30')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getFullYear('-09:30')")]
   (is (helper/equal? (helper/translate {:int64Value "2009"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getHours-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getHours('02:00')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getHours('02:00')")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getMinutes-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getMinutes('Asia/Kathmandu')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getMinutes('Asia/Kathmandu')")]
   (is (helper/equal? (helper/translate {:int64Value "16"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getMonth-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getMonth('UTC')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getMonth('UTC')")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 timestamps-timestamp_selectors_tz-getSeconds-test
 (testing
  "Timestamp selection operators with timezones\ntimestamp('2009-02-13T23:31:30Z').getSeconds('-00:00')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z').getSeconds('-00:00')")]
   (is (helper/equal? (helper/translate {:int64Value "30"}) res)))))

(deftest
 timestamps-timestamp_equality-eq_same-test
 (testing
  "Equality operations on timestamps.\ntimestamp('2009-02-13T23:31:30Z') == timestamp('2009-02-13T23:31:30Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:30Z') == timestamp('2009-02-13T23:31:30Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_equality-eq_diff-test
 (testing
  "Equality operations on timestamps.\ntimestamp('2009-02-13T23:31:29Z') == timestamp('2009-02-13T23:31:30Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:29Z') == timestamp('2009-02-13T23:31:30Z')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-timestamp_equality-neq_same-test
 (testing
  "Equality operations on timestamps.\ntimestamp('1945-05-07T02:41:00Z') != timestamp('1945-05-07T02:41:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('1945-05-07T02:41:00Z') != timestamp('1945-05-07T02:41:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-timestamp_equality-neq_diff-test
 (testing
  "Equality operations on timestamps.\ntimestamp('2000-01-01T00:00:00Z') != timestamp('2001-01-01T00:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2000-01-01T00:00:00Z') != timestamp('2001-01-01T00:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-duration_equality-eq_same-test
 (testing
  "Equality tests for durations.\nduration('123s') == duration('123s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('123s') == duration('123s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-duration_equality-eq_diff-test
 (testing
  "Equality tests for durations.\nduration('60s') == duration('3600s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('60s') == duration('3600s')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-duration_equality-neq_same-test
 (testing
  "Equality tests for durations.\nduration('604800s') != duration('604800s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('604800s') != duration('604800s')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-duration_equality-neq_diff-test
 (testing
  "Equality tests for durations.\nduration('86400s') != duration('86164s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('86400s') != duration('86164s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-add_duration_to_time-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\ntimestamp('2009-02-13T23:00:00Z') + duration('240s') == timestamp('2009-02-13T23:04:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:00:00Z') + duration('240s') == timestamp('2009-02-13T23:04:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-add_time_to_duration-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\nduration('120s') + timestamp('2009-02-13T23:01:00Z') == timestamp('2009-02-13T23:03:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('120s') + timestamp('2009-02-13T23:01:00Z') == timestamp('2009-02-13T23:03:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-add_duration_to_duration-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\nduration('600s') + duration('50s') == duration('650s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('600s') + duration('50s') == duration('650s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-add_time_to_duration_nanos_negative-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\ntimestamp('0001-01-01T00:00:01.000000001Z') + duration('-999999999ns') == timestamp('0001-01-01T00:00:00.000000002Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0001-01-01T00:00:01.000000001Z') + duration('-999999999ns') == timestamp('0001-01-01T00:00:00.000000002Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-add_time_to_duration_nanos_positive-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\ntimestamp('0001-01-01T00:00:01.999999999Z') + duration('999999999ns') == timestamp('0001-01-01T00:00:02.999999998Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0001-01-01T00:00:01.999999999Z') + duration('999999999ns') == timestamp('0001-01-01T00:00:02.999999998Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-subtract_duration_from_time-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\ntimestamp('2009-02-13T23:10:00Z') - duration('600s') == timestamp('2009-02-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:10:00Z') - duration('600s') == timestamp('2009-02-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-subtract_time_from_time-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\ntimestamp('2009-02-13T23:31:00Z') - timestamp('2009-02-13T23:29:00Z') == duration('120s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:31:00Z') - timestamp('2009-02-13T23:29:00Z') == duration('120s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-timestamp_arithmetic-subtract_duration_from_duration-test
 (testing
  "Arithmetic operations on timestamps and/or durations.\nduration('900s') - duration('42s') == duration('858s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('900s') - duration('42s') == duration('858s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-leq_timestamp_true-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T23:00:00Z') <= timestamp('2009-02-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:00:00Z') <= timestamp('2009-02-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-leq_timestamp_false-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T23:00:00Z') <= timestamp('2009-02-13T22:59:59Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:00:00Z') <= timestamp('2009-02-13T22:59:59Z')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-comparisons-leq_duration_true-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('200s') <= duration('200s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('200s') <= duration('200s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-leq_duration_false-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('300s') <= duration('200s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('300s') <= duration('200s')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-comparisons-less_timestamp_true-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T23:00:00Z') < timestamp('2009-03-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:00:00Z') < timestamp('2009-03-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-less_duration_true-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('200s') < duration('300s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('200s') < duration('300s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-geq_timestamp_true-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T23:00:00Z') >= timestamp('2009-02-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:00:00Z') >= timestamp('2009-02-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-geq_timestamp_false-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T22:58:00Z') >= timestamp('2009-02-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T22:58:00Z') >= timestamp('2009-02-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-comparisons-geq_duration_true-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('200s') >= duration('200s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('200s') >= duration('200s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-geq_duration_false-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('120s') >= duration('200s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('120s') >= duration('200s')")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 timestamps-comparisons-greater_timestamp_true-test
 (testing
  "Comparisons on timestamps and/or durations.\ntimestamp('2009-02-13T23:59:00Z') > timestamp('2009-02-13T23:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('2009-02-13T23:59:00Z') > timestamp('2009-02-13T23:00:00Z')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-comparisons-greater_duration_true-test
 (testing
  "Comparisons on timestamps and/or durations.\nduration('300s') > duration('200s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('300s') > duration('200s')")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 timestamps-duration_converters-get_hours-test
 (testing
  "Conversion functions on durations. Unlike timestamps, we don't, e.g. select the 'minutes' field - we convert the duration to integer minutes.\nduration('10000s').getHours()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('10000s').getHours()")]
   (is (helper/equal? (helper/translate {:int64Value "2"}) res)))))

(deftest
 timestamps-duration_converters-get_milliseconds-test
 (testing
  "Conversion functions on durations. Unlike timestamps, we don't, e.g. select the 'minutes' field - we convert the duration to integer minutes.\nx.getMilliseconds()"
  (let
   [res
    (parser/parse-eval
     {:bindings
      (helper/bindings
       {:x
        {:value
         {:objectValue
          {"@type" "type.googleapis.com/google.protobuf.Duration",
           :value "123.123456789s"}}}}),
      :translate-result? false}
     "x.getMilliseconds()")]
   (is (helper/equal? (helper/translate {:int64Value "123123"}) res)))))

(deftest
 timestamps-duration_converters-get_minutes-test
 (testing
  "Conversion functions on durations. Unlike timestamps, we don't, e.g. select the 'minutes' field - we convert the duration to integer minutes.\nduration('3730s').getMinutes()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('3730s').getMinutes()")]
   (is (helper/equal? (helper/translate {:int64Value "62"}) res)))))

(deftest
 timestamps-duration_converters-get_seconds-test
 (testing
  "Conversion functions on durations. Unlike timestamps, we don't, e.g. select the 'minutes' field - we convert the duration to integer minutes.\nduration('3730s').getSeconds()"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('3730s').getSeconds()")]
   (is (helper/equal? (helper/translate {:int64Value "3730"}) res)))))

(deftest
 timestamps-timestamp_range-from_string_under-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('0000-01-01T00:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0000-01-01T00:00:00Z')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-from_string_over-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('10000-01-01T00:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('10000-01-01T00:00:00Z')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-add_duration_under-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('0001-01-01T00:00:00Z') + duration('-1s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0001-01-01T00:00:00Z') + duration('-1s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-add_duration_over-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('9999-12-31T23:59:59Z') + duration('1s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('9999-12-31T23:59:59Z') + duration('1s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-add_duration_nanos_over-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('9999-12-31T23:59:59.999999999Z') + duration('1ns')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('9999-12-31T23:59:59.999999999Z') + duration('1ns')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-add_duration_nanos_under-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('0001-01-01T00:00:00Z') + duration('-1ns')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0001-01-01T00:00:00Z') + duration('-1ns')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-sub_time_duration_over-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('9999-12-31T23:59:59Z') - timestamp('0001-01-01T00:00:00Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('9999-12-31T23:59:59Z') - timestamp('0001-01-01T00:00:00Z')")]
   (is (helper/error? res)))))

(deftest
 timestamps-timestamp_range-sub_time_duration_under-test
 (testing
  "Tests for out-of-range operations on timestamps.\ntimestamp('0001-01-01T00:00:00Z') - timestamp('9999-12-31T23:59:59Z')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "timestamp('0001-01-01T00:00:00Z') - timestamp('9999-12-31T23:59:59Z')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-from_string_under-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('-320000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('-320000000000s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-from_string_over-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('320000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('320000000000s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-add_under-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('-200000000000s') + duration('-200000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('-200000000000s') + duration('-200000000000s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-add_over-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('200000000000s') + duration('200000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('200000000000s') + duration('200000000000s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-sub_under-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('-200000000000s') - duration('200000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('-200000000000s') - duration('200000000000s')")]
   (is (helper/error? res)))))

(deftest
 timestamps-duration_range-sub_over-test
 (testing
  "Tests for out-of-range operations on durations.\nduration('200000000000s') - duration('-200000000000s')"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "duration('200000000000s') - duration('-200000000000s')")]
   (is (helper/error? res)))))
