(ns generator
  (:require [cheshire.core   :as json]
            [clojure.java.io :as io]
            [clojure.walk    :as walk]
            [clojure.pprint  :as pprint]))

(defn- parse-data
  [data-name]
  (-> (format "testdata/%s.json" (name data-name))
      (io/reader)
      (json/parse-stream true)))

(defn stringify-type
  [m]
  (let [f (fn [[k v]] (if (= (keyword "@type") k) [(name k) v] [k v]))]
    ;; only apply to maps
    (walk/postwalk (fn [x] (if (map? x) (into {} (map f x)) x)) m)))

(defn generate
  [{:keys [data-name]}]
  (let [{:keys [description section] :as input} (parse-data data-name)]
    (binding [*out* (io/writer (format "%s_test.clj" (:name input)))]
      (pprint/pprint
       (list 'ns
             (symbol (format "exoscale.cel.generated.%s-test" (name data-name)))
             (str "Generated test - " description)
             '(:require [clojure.test :refer [deftest testing is]]
                        [exoscale.cel.test-helper :as helper]
                        [exoscale.cel.parser :as parser])))

      (println)

      (doseq [{:keys [description test] :as section} section]
        (doseq [{:keys [expr value bindings name evalError]} test]
          (println)
          (pprint/pprint
           (list
            'deftest
            (symbol (format "%s-%s-%s-test"
                            (:name input)
                            (:name section)
                            name))
            (list 'testing
                  (str description "\n" expr)
                  (list 'let ['res (list
                                    'parser/parse-eval
                                    {:bindings          (list
                                                         'helper/bindings
                                                         (stringify-type bindings))
                                     :translate-result? false}
                                    expr)]
                        (cond
                          (some? value)
                          (list 'is (list 'helper/equal?
                                          (list 'helper/translate (stringify-type value))
                                          'res))

                          (some? evalError)
                          '(is (helper/error? res))

                          :else
                          (throw
                           (ex-info "unknown test structure" {}))))))))))))
