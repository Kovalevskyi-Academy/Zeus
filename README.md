# Zeus the Mighty

## Features

- Packaging students projects into .jar archive
- Checking a style of code with Checkstyle
- Testing students solutions with jUnit

## Importantly

- Zeus jar archive should be in the root folder of the student's project

## How to use

Launch the command line from the root folder of your project and input the code below

##### run Zeus only

`java -jar Zeus-1.2.jar <parameters>`

##### Build your project

`java -jar Zeus-1.2.jar -b`

##### run only check-style

`java -jar Zeus-1.2.jar -c <week/day params>`

##### run tests & check-style

###### Unix-like

`java -cp "./target/<NAME>.jar:Zeus-1.2.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

###### Windows

`java -cp "./target/<NAME>.jar;Zeus-1.2.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

**<NAME>** — the name of your jar file.

**If your <NAME>.jar location is different, you should write a path to this file from the
project root.**

## Available parameters

|Short|Full|Description|
|---|---|---|
|`-a`|`--all`|Run all tests|
|`-b`|`--build`|Build jar|
|`-c`|`--checkstyle`|Run checkstyle for week/day. Cannot be applied to `*.jar` files!|
|`-d`|`--day=<day>`|Number of the day|
|`-e`|`--error`|JUnit error mode (only error prints)|
|`-h`|`--help`|Show help message|
|`-m`|`--maven=<mavenHome>`|Set a path to the maven home|
|`-s`|`--show`|Show tests' path for week/day|
|`-t`|`--test=<test>`|Specific test to executed according path to week/day|
|`-V`|`--version`|Print version information|
|`-w`|`--week=<week>`|Number of the week|

## Examples of usage

**If your <NAME>.jar location is different, you should write a path to this file from the
project root.**

Check only a style of code for week 0 day 0

`java -jar Zeus-1.2.jar -c -w0 -d0`

Build your jar archive

`java -jar Zeus-1.2.jar -b`

Run test for week 0 day 0

`java -cp "./target/CodingBootcamp-1.1.jar:Zeus-1.2.jar" com.kovalevskyi.academy.zeus.TheMighty -w0 -d0`

Test for week 0 day 0 with no prints (only error prints)

`java -cp "./target/CodingBootcamp-1.1.jar:Zeus-1.2.jar" com.kovalevskyi.academy.zeus.TheMighty -e -w0 -d0`

Run all tests that contains in Zeus

`java -jar Zeus-1.2.jar -a`
