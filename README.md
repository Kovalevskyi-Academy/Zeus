# Zeus the Mighty

## Features

- Packaging students projects into .jar archive
- Checking a style of code with Checkstyle
- Testing students solutions with jUnit

## Importantly

- Zeus jar archive should be in the root folder of the student's project
- Use java 13 or above

## How to use

Launch the command line from the root folder of your project and input the code below

##### run Zeus only

`java -jar Zeus-1.4.jar <parameters>`

##### build your project

`java -jar Zeus-1.4.jar -b`

##### run check-style 

`java -jar Zeus-1.4.jar -c` (for all source files in your project)

##### run tests

###### Unix-like

`java -cp "./target/<NAME>.jar:Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

or

`java -cp "./target/*:Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

###### Windows

`java -cp "./target/<NAME>.jar;Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

or

`java -cp "./target/*;Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty <parameters>`

**`<NAME>`** — the name of your jar file.

**If your `<NAME>.jar` location is different, you should write a path to this file from the
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

**Zeus jar archive should be in the root folder of the student's project!**

Check all source files in the project

`java -jar Zeus-1.4.jar -c`

Build your project to `*.jar` archive

`java -jar Zeus-1.4.jar -b`

Run test for week 0 day 0

`java -cp "./target/*:Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty -w0 -d0`

Test for week 0 day 0 with no prints (only error prints)

`java -cp "./target/*:Zeus-1.4.jar" com.kovalevskyi.academy.zeus.TheMighty -e -w0 -d0`

________________________________________________________

## To devs

See [this instructions](./toDevs/building.md)!