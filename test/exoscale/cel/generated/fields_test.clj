(ns
 exoscale.cel.generated.fields-test
 "Generated test - Tests for field access in maps."
 (:require
  [clojure.test :refer [deftest testing is]]
  [exoscale.cel.test-helper :as helper]
  [exoscale.cel.parser :as parser]))


(deftest
 fields-map_fields-map_key_int64-test
 (testing
  "select an element in a map\n{0:1,2:2,5:true}[5]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0:1,2:2,5:true}[5]")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 fields-map_fields-map_key_uint64-test
 (testing
  "select an element in a map\n{0u:1u,2u:'happy',5u:3u}[2u]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0u:1u,2u:'happy',5u:3u}[2u]")]
   (is (helper/equal? (helper/translate {:stringValue "happy"}) res)))))

(deftest
 fields-map_fields-map_key_string-test
 (testing
  "select an element in a map\n{'name':100u}['name']"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{'name':100u}['name']")]
   (is (helper/equal? (helper/translate {:uint64Value "100"}) res)))))

(deftest
 fields-map_fields-map_key_bool-test
 (testing
  "select an element in a map\n{true:5}[true]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{true:5}[true]")]
   (is (helper/equal? (helper/translate {:int64Value "5"}) res)))))

(deftest
 fields-map_fields-map_key_mix_type-test
 (testing
  "select an element in a map\n{true:1,2:2,5u:3}[true]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{true:1,2:2,5u:3}[true]")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 fields-map_fields-map_field_access-test
 (testing
  "select an element in a map\nx.name"
  (let
   [res
    (parser/parse-eval
     {:bindings
      (helper/bindings
       {:x
        {:value
         {:mapValue
          {:entries
           [{:key {:stringValue "name"},
             :value {:int64Value "1024"}}]}}}}),
      :translate-result? false}
     "x.name")]
   (is (helper/equal? (helper/translate {:int64Value "1024"}) res)))))

(deftest
 fields-map_fields-map_no_such_key-test
 (testing
  "select an element in a map\n{0:1,2:2,5:3}[1]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0:1,2:2,5:3}[1]")]
   (is (helper/error? res)))))

(deftest
 fields-map_fields-map_field_select_no_such_key-test
 (testing
  "select an element in a map\nx.name"
  (let
   [res
    (parser/parse-eval
     {:bindings
      (helper/bindings
       {:x
        {:value
         {:mapValue
          {:entries
           [{:key {:stringValue "holiday"},
             :value {:stringValue "field"}}]}}}}),
      :translate-result? false}
     "x.name")]
   (is (helper/error? res)))))

(deftest
 fields-map_fields-map_value_null-test
 (testing
  "select an element in a map\n{true:null}[true]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{true:null}[true]")]
   (is (helper/equal? (helper/translate {:nullValue nil}) res)))))

(deftest
 fields-map_fields-map_value_bool-test
 (testing
  "select an element in a map\n{27:false}[27]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{27:false}[27]")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 fields-map_fields-map_value_string-test
 (testing
  "select an element in a map\n{'n':'x'}['n']"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{'n':'x'}['n']")]
   (is (helper/equal? (helper/translate {:stringValue "x"}) res)))))

(deftest
 fields-map_fields-map_value_float-test
 (testing
  "select an element in a map\n{3:15.15}[3]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{3:15.15}[3]")]
   (is (helper/equal? (helper/translate {:doubleValue 15.15}) res)))))

(deftest
 fields-map_fields-map_value_uint64-test
 (testing
  "select an element in a map\n{0u:1u,2u:2u,5u:3u}[0u]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0u:1u,2u:2u,5u:3u}[0u]")]
   (is (helper/equal? (helper/translate {:uint64Value "1"}) res)))))

(deftest
 fields-map_fields-map_value_int64-test
 (testing
  "select an element in a map\n{true:1,false:2}[true]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{true:1,false:2}[true]")]
   (is (helper/equal? (helper/translate {:int64Value "1"}) res)))))

(deftest
 fields-map_fields-map_value_bytes-test
 (testing
  "select an element in a map\n{0:b\"\"}[0]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0:b\"\"}[0]")]
   (is (helper/equal? (helper/translate {:bytesValue ""}) res)))))

(deftest
 fields-map_fields-map_value_list-test
 (testing
  "select an element in a map\n{0u:[1]}[0u]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{0u:[1]}[0u]")]
   (is
    (helper/equal?
     (helper/translate {:listValue {:values [{:int64Value "1"}]}})
     res)))))

(deftest
 fields-map_fields-map_value_map-test
 (testing
  "select an element in a map\n{\"map\": {\"k\": \"v\"}}[\"map\"]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{\"map\": {\"k\": \"v\"}}[\"map\"]")]
   (is
    (helper/equal?
     (helper/translate
      {:mapValue
       {:entries
        [{:key {:stringValue "k"}, :value {:stringValue "v"}}]}})
     res)))))

(deftest
 fields-map_fields-map_value_mix_type-test
 (testing
  "select an element in a map\n{\"map\": {\"k\": \"v\"}, \"list\": [1]}[\"map\"]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{\"map\": {\"k\": \"v\"}, \"list\": [1]}[\"map\"]")]
   (is
    (helper/equal?
     (helper/translate
      {:mapValue
       {:entries
        [{:key {:stringValue "k"}, :value {:stringValue "v"}}]}})
     res)))))

(deftest
 fields-map_has-has-test
 (testing
  "Has macro for map entries.\nhas({'a': 1, 'b': 2}.a)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "has({'a': 1, 'b': 2}.a)")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 fields-map_has-has_not-test
 (testing
  "Has macro for map entries.\nhas({'a': 1, 'b': 2}.c)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "has({'a': 1, 'b': 2}.c)")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 fields-map_has-has_empty-test
 (testing
  "Has macro for map entries.\nhas({}.a)"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "has({}.a)")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 fields-qualified_identifier_resolution-list_field_select_unsupported-test
 (testing
  "Tests for qualified identifier resolution.\na.b.pancakes"
  (let
   [res
    (parser/parse-eval
     {:bindings
      (helper/bindings
       {:a.b
        {:value {:listValue {:values [{:stringValue "pancakes"}]}}}}),
      :translate-result? false}
     "a.b.pancakes")]
   (is (helper/error? res)))))

(deftest
 fields-qualified_identifier_resolution-int64_field_select_unsupported-test
 (testing
  "Tests for qualified identifier resolution.\na.pancakes"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings {:a {:value {:int64Value "15"}}}),
      :translate-result? false}
     "a.pancakes")]
   (is (helper/error? res)))))

(deftest
 fields-qualified_identifier_resolution-map_key_float-test
 (testing
  "Tests for qualified identifier resolution.\n{3.3:15.15, 1.0: 5}[1.0]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{3.3:15.15, 1.0: 5}[1.0]")]
   (is (helper/error? res)))))

(deftest
 fields-qualified_identifier_resolution-map_key_null-test
 (testing
  "Tests for qualified identifier resolution.\n{null:false}[null]"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "{null:false}[null]")]
   (is (helper/error? res)))))

(deftest
 fields-in-empty-test
 (testing
  "Tests for 'in' operator for maps.\n7 in {}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "7 in {}")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))

(deftest
 fields-in-singleton-test
 (testing
  "Tests for 'in' operator for maps.\ntrue in {true: 1}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "true in {true: 1}")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 fields-in-present-test
 (testing
  "Tests for 'in' operator for maps.\n'George' in {'John': 'smart', 'Paul': 'cute', 'George': 'quiet', 'Ringo': 'funny'}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'George' in {'John': 'smart', 'Paul': 'cute', 'George': 'quiet', 'Ringo': 'funny'}")]
   (is (helper/equal? (helper/translate {:boolValue true}) res)))))

(deftest
 fields-in-absent-test
 (testing
  "Tests for 'in' operator for maps.\n'spider' in {'ant': 6, 'fly': 6, 'centipede': 100}"
  (let
   [res
    (parser/parse-eval
     {:bindings (helper/bindings nil), :translate-result? false}
     "'spider' in {'ant': 6, 'fly': 6, 'centipede': 100}")]
   (is (helper/equal? (helper/translate {:boolValue false}) res)))))
