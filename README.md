# Zeus the Mighty

## Features

- controlling maven of your project
- checking a style of code with Checkstyle
- testing students solutions with JUnit
- downloading the latest releases of Zeus from repo

## Importantly

- Zeus should lay in the root folder of the student's project **([EXAMPLE](./detailManuals/projectFolder.md))**
- use Java 16 and above

## Course keys
* jcb - Java Coding Bootcamp
* jdd - Java Deep Dive

## How to use
- while writing the solution:
    - run code style checker separately
    - run tests separately for each day or even a separate test (the latter is optional)
- when submitting a job, you must use `pass`.
  The task can be completed in parts - it is not necessary to turn in at once all day.
  You can even turn in only one point from your scoring card of the current day!

`<ZEUS>` — Zeus JAR file, example **Zeus-27.jar**

`<KEY>` — any available key, example: **-h**, **-v**, **-d** or course key. Different keys apply to Zeus itself and to individual Zeus commands.

`<COMMAND>` — any available command, example **test**

### local run Zeus

`java -jar <ZEUS> <KEY>... <COMMAND> <KEY>...`

### Google claud run Zeus
An alias for Zeus is registered in Google Cloud. Therefore, the command will be like this:
`zeus <KEY>... <COMMAND> <KEY>...`

## Zeus keys

* `-d`,`--dev` - developer mode
* `-h`,`--help` - show help
* `-V`,`--version` - show version

## Zeus commands

* `show` - show available courses or containers (in developing)
* `style` - run checkstyle for all or selected sources, you cannot pick a specific day to check the code style.
* `test` - run test containers. You can choose a specific day for testing.
* `pass` - run exam: run `style` and `test` in sequence
* `maven` - run maven presets or custom commands
* `update` - download the latest Zeus release

> all commands have own help menu, use key **-h** or **--help** to get help
> example: `java -jar Zeus-27.jar pass -h`

## Detail about each command

- [show](./detailManuals/show.md)
- [style](./detailManuals/style.md)
- [test](./detailManuals/test.md)
- [pass](./detailManuals/pass.md)
- [maven](./detailManuals/maven.md)

- `update` - download the latest release of Zeus

## Examples of usage

### show Zeus help

`java -jar Zeus-27.jar -h`Zeus

### show Zeus checkstyle help

`java -jar Zeus-27.jar style -h`

### run checkstyle
`java -jar Zeus-27.jar style` — local run
`zeus style` — claud run

### run tests

`java -jar Zeus-27.jar test jcb -w0 -d4` — run all test of the week0 day4
`java -jar Zeus-27.jar test jcb -w0 -d4 -i0` — run only first test class of the week0 day4
`java -jar Zeus-27.jar test jcb -w0 -d4 -i1` — run only second test class of the week0 day4

> `jcb` — tells Zeus which course to run tests from.

`java -jar Zeus-27.jar test jcb -w0 -d4 -i1 -v` — run only second test class of the week0 day4 **END** show extra long error messages

`zeus test jcb -w0 -d4 -i1 -v` — the same, but in google claud

> `-v` — special kay for `test` command, more verbose

### run pass
`java -jar Zeus-27.jar pass jcb` - starts a code style check for the entire project, and then starts testing the entire project

`java -jar Zeus-27.jar pass jcb -w0 -d4 -i0` - starts first checking the code style of the entire project, and then runs the tests specified: week0, day4, test class1

`zeus pass jcb -w0 -d4 -i0` - the same, but in google claud