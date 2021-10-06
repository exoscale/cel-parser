(defproject com.exoscale/cel-parser "0.1.0-SNAPSHOT"

  :description "Common Expression Language parser"
  :url         "https://github.com/exoscale/cel-parser"
  :license     {:name "MIT/ISC"}

  :deploy-repositories [["snapshots" :clojars]
                        ["releases"  :clojars]]

  :pedantic? :warn
  :dependencies [[org.clojure/clojure      "1.10.3"]
                 [org.antlr/antlr4-runtime "4.9.2"]
                 [com.exoscale/antlr-cel   "0.1.1"]]

  :profiles {:test {:plugins [[lein-cljfmt "0.8.0"]]}})
