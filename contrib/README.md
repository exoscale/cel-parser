# Test generator

Generator for tests provided by https://github.com/google/cel-spec

## proto translate

The source for tests should be the data files available at:
https://github.com/google/cel-spec/tree/master/tests/simple/testdata

These are stored in the textual version of protobuf. To make things
simpler on the Clojure side, JSON is expected. To generate the JSON files
run the following:

To create the files run as follows, from within this directory:

``` shell
mkdir -p testdata
git clone https://github.com/google/cel-spec /tmp/cel-spec
go run translate.go /tmp/cel-spec/tests/simple/testdata testdata
```

## test generator

Once the above has run, tests can be generated:

``` shell
sh build-tests.sh
```
