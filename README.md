cel-parser: A Clojure parser for the CEL language
=================================================

This library provides a parser and evaluation engine for the language
specified in https://github.com/google/cel-spec/.

## Usage

The simplest way to run through a CEL expression is to use
`exoscale.cel.parser/parse-eval` which parses an expression into
a program and runs it against the provided input. Input is provided
in the form of bindings: a map of symbol to install in the program's
namespace to its value.

``` clojure
(parse-eval "true")
(parse-eval {:bindings {:x "world"}} "'hello, ' + x + '!'")
```

When repeatedly running the same expression, the action can be
decomposed in two phases, first building the program with
`exoscale.cel.parser/make-program`, and then running it with
`exoscale.cel.parser/eval-for`:

``` clojure
(def my-program (make-program "'hello, ' + x + '!'"))

(eval-for my-program {:x "world"})
```

When execution results in an error, an exception is returned, not
thrown by default. To throw upon error returns, `throw-on-error?`
may be added to the input map. Invalid expressions throw when
building the program.

### Values

Bindings are converted to CEL values: keywords and map keys become
unqualified strings, collections become lists, `nil` becomes `null`,
`java.util.Date` becomes a timestamp. `java.time.Instant` and
`java.time.Duration` map to timestamps and durations.

Results are converted back to Clojure values: lists become vectors,
maps become maps (keys are left as returned by CEL), `uint` values
become longs, bytes become byte arrays, timestamps are
`java.time.Instant` and durations `java.time.Duration`.

### Optionals

[CEL optionals](https://github.com/google/cel-spec/wiki/proposal-246)
are supported. Optional results are unwrapped to their value, or `nil`
when empty.

``` clojure
(parse-eval {:bindings {:m {}}} "m.?a.orValue(3)")  ;; => 3
(parse-eval "optional.none()")                      ;; => nil
```

### Custom functions

Custom functions are declared in an environment, built with
`exoscale.cel.parser/env` and used to build programs. Functions receive
and return Clojure values and can be called both as global `f(x, y)`
and member `x.f(y)` functions. Several definitions with the same name
and different argument types are overloads. A definition with the
signature of a standard function overrides it; ambiguous definitions
make `env` throw.

``` clojure
(def my-env
  (env {:functions [{:name "inIpRange"
                     :args [:string :string]
                     :result :bool
                     :fn in-ip-range}]}))

(eval-for (make-program my-env "ip.inIpRange('10.0.0.0/8')") {:ip "10.1.2.3"})
(parse-eval {:env my-env :bindings {:ip "10.1.2.3"}} "inIpRange(ip, '10.0.0.0/8')")
```

Supported types are `:bool`, `:int`, `:uint`, `:double`, `:string`,
`:bytes`, `:timestamp`, `:duration`, `:list`, `:map` and `:dyn`.

## Limitations

The following is currently not supported:

- Namespaces
- Namespaced keys in maps; Resulting CEL expressions will get unqualified string keys
- Protobuf object creation; For now, objects are expected to be supplied as maps

## Language support

Parsing, type checking and evaluation are provided by
[cel-java](https://github.com/cel-expr/cel-java), the reference Java
implementation of CEL. Variables are declared with the `dyn` type.
