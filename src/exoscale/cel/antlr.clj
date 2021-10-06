(ns ^:no-doc exoscale.cel.antlr
  "Antlr interop"
  (:import exoscale.cel.CELParser
           exoscale.cel.CELLexer
           org.antlr.v4.runtime.CharStreams
           org.antlr.v4.runtime.CharStream
           org.antlr.v4.runtime.CommonTokenStream
           org.antlr.v4.runtime.BaseErrorListener))

(defn- error-listener
  "Error listener for the parser"
  [state]
  (proxy [BaseErrorListener] []
    (syntaxError [recognizer offending-symbol line char msg ex]
      (vswap! state conj {:line line :char char :msg msg}))))

(defn make-program
  "Build a program from the given input string. Throws for invalid
   expressions."
  [input]
  (let [cs     ^CharStream (CharStreams/fromString (str input))
        state  (volatile! [])
        lexer  (CELLexer. cs)
        ts     (CommonTokenStream. lexer)
        parser (doto (CELParser. ts)
                  (.addErrorListener (error-listener state)))
        tree   (.start parser)]
     (when (seq @state)
       (throw (ex-info "unable to parse CEL expression" {:errors @state})))
     tree))
