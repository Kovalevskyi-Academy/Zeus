### How to build:
just `mvn clean package`

_Я не уверен, что я все в этой жизни сделал правильно, но очень хотелось ))_


### Howe to run:
0. Examples of dev usage:

`<NAME>` = `CodingBootcamp-1.0-SNAPSHOT` or `CodingBootcamp-1.0-SNAPSHOT-tests-only`.

Test for week 0 day 1

`java -cp "target/<NAME>.jar:Zeus-1.0.jar" com.kovalevskyi.academy.zeus.TheMighty -w0 -d0`

Test for week 0 day 1 with no prints (only error prints)

`java -cp "target/<NAME>.jar:Zeus-1.0.jar" com.kovalevskyi.academy.zeus.TheMighty -e -w0 -d0`

1. Do not forget make a separate branch for your fixes.
2. Remember - tests should be speaking.