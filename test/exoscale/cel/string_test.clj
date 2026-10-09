(ns exoscale.cel.string-test
  (:require [clojure.test :refer [deftest is testing]]
            [exoscale.cel.expr :as expr]
            [exoscale.cel.parser :as parser]))

(def ^:private ext-overloads
  "Standard overloads with the opt-in strings extension merged in."
  (expr/merge-overloads expr/strings-extension-overloads))

(defn- eval-with-ext
  "Evaluate with the strings extension enabled; throws on error, returns values."
  [input]
  (parser/parse-eval {:overloads         ext-overloads
                      :throw-on-error?   true
                      :translate-result? true}
                     input))

(defn- eval-default
  "Evaluate with the default overloads; returns the raw result (no throw)."
  [input]
  (parser/parse-eval {:throw-on-error?   false
                      :translate-result? false}
                     input))

;; Same cases as cel-go's ext/strings TestStrings.
(deftest test-lower-ascii-with-extension
  (testing "ascii letters are lower-cased"
    (is (true? (eval-with-ext "'TacoCat'.lowerAscii() == 'tacocat'"))))
  (testing "non-ascii codepoints are left unchanged"
    (is (true? (eval-with-ext "'TacoCÆt'.lowerAscii() == 'tacocÆt'"))))
  (testing "spaces and mixed content"
    (is (true? (eval-with-ext "'TacoCÆt Xii'.lowerAscii() == 'tacocÆt xii'")))))

(deftest test-upper-ascii-with-extension
  (testing "ascii letters are upper-cased"
    (is (true? (eval-with-ext "'tacoCat'.upperAscii() == 'TACOCAT'"))))
  (testing "non-ascii codepoints are left unchanged"
    (is (true? (eval-with-ext "'tacoCαt'.upperAscii() == 'TACOCαT'")))))

(deftest test-not-in-default-overloads
  (testing "lowerAscii() is opt-in: absent from the default (spec-compliant) set"
    (let [res (eval-default "'ABC'.lowerAscii()")]
      (is (expr/error? res))
      (is (= "no such overload" (:msg res)))))
  (testing "upperAscii() is opt-in: absent from the default (spec-compliant) set"
    (let [res (eval-default "'abc'.upperAscii()")]
      (is (expr/error? res))
      (is (= "no such overload" (:msg res))))))
