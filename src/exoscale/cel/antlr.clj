(ns ^:no-doc exoscale.cel.antlr
  "Antlr interop"
  (:import (exoscale.cel CELParser CELLexer)
           (org.antlr.v4.runtime Parser
                                 CharStream
                                 CharStreams
                                 CommonTokenStream
                                 BaseErrorListener
                                 RecognitionException)))

;; recognition-exception->map is shamelessly borrowed from clj-antlr
;; https://github.com/aphyr/clj-antlr
(defn recognition-exception->map
  "Converts a RecognitionException to a nice readable map."
  [^RecognitionException e]
  (let [rule (.getCtx e)
        expected (try (.getExpectedTokens e)
                      (catch IllegalArgumentException _
                                        ; I think ANTLR throws here for
                                        ; tokenizer errors.
                        nil))
        token (.getOffendingToken e)]
    (cond-> {:state (.getOffendingState e)}
      rule (assoc :rule rule)
      expected (assoc :expected expected)
      token (assoc :token token))))

(defn- error-listener
  "Error listener for the parser"
  [state]
  (proxy [BaseErrorListener] []
    (syntaxError [recognizer offending-symbol line char msg ex]
      (vswap! state
              conj
              (cond-> {:msg msg}
                (some? offending-symbol)
                (assoc :symbol offending-symbol)
                line (assoc :line line)
                char (assoc :char char)
                (instance? Parser recognizer)
                (assoc :stack (-> ^Parser recognizer .getRuleInvocationStack reverse))
                (some? ex)
                (into (recognition-exception->map ex)))))))

(defn make-program
  "Build a program from the given input string. Throws for invalid
   expressions."
  [input]
  (let [cs ^CharStream (CharStreams/fromString (str input))
        state (volatile! [])
        err-listener (error-listener state)
        lexer (doto (CELLexer. cs)
                (.addErrorListener err-listener))
        ts (CommonTokenStream. lexer)
        parser (doto (CELParser. ts) (.addErrorListener err-listener))
        tree (.start parser)]
    (when (seq @state)
      (throw (ex-info "Unable to parse CEL expression"
                      {:exoscale.ex/type :exoscale.ex/fault
                       :errors @state})))
    tree))
