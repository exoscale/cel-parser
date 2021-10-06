#!bin/sh

target=../test/exoscale/cel/generated/
for i in basic logic plumbing comparisons lists parse fields timestamps unknowns macros fp_math conversions string integer_math; do
    clojure -X generator/generate :data-name :${i} \
        && mv ${i}_test.clj $target \
        && echo $i
done
