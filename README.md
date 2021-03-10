# Zeus the Mighty

## Features

- controlling maven of your project
- checking a style of code with Checkstyle
- testing students solutions with jUnit
- downloading the latest releases of Zeus from repo

## Importantly

- Zeus should lay in the root folder of the student's project
- use Java 15
  with [preview mode](https://docs.oracle.com/en/java/javase/15/language/preview-language-and-vm-features.html)

## How to use

`<ZEUS>` — Zeus JAR file, example **Zeus-7.jar**

`<HOMEWORK>` — the name of your JAR file, example **Homework.jar**

`<DELIMITER>` — classpath delimiter:

- `:` - unix-like

- `;` - windows

`<KEY>` — any available key, example **-h** or **--help**

`<COMMAND>` — any available command, example **test**

### run Zeus

`java --enable-preview -jar <ZEUS> <KEY> <COMMAND> <KEY>...`

> The two examples below demonstrate how to add your homework manually.
> But, actually, Zeus does this automatically.

### run Zeus with another JAR file

`java --enable-preview -cp "<HOMEWORK><DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY> <COMMAND> <KEY>...`

### run Zeus with another JAR files (wildcards version, same that above)

`java --enable-preview -cp "/target/*<DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY> <COMMAND> <KEY>...`

> * launch the command line from the root folder of your project
> * `/target/*` will add all JAR files to classpath from **target** folder, see [more](https://riptutorial.com/java/example/12854/adding-all-jars-in-a-directory-to-the-classpath)
> * command and keys are not required
> * `...` meaning that key can be one or more

## Zeus keys

* `-d`,`--dev` - developer mode
* `-h`,`--help` - show help
* `-V`,`--version` - show version

## Zeus commands

* `show` - show available courses or containers
* `style` - run checkstyle for all or selected sources
* `test` - run test containers
* `pass` - run exam
* `maven` - run maven presets or custom commands
* `update` - download the latest Zeus release

> all commands have own help menu, use key **-h** or **--help** to get help

## Detail about each command

### show

`show` - show available courses and its keys

`show key -wX -dX -iX` - show test containers and its ids

`show -h` - show help of usage

> X - specify week/day number or test container id

* `key` - course key **_(required)_**
* `-w`,`--week=<week>` - week number
* `-d`,`--day=<day>` - day number
* `-i`,`--id=<id>` - container id
* `-h`,`--help` - show help

### style

`style` - launch checkstyle for all source files of your project

`style ClassName1 ClassName2...` - launch checkstyle for selected source files

`style -h` - show help of usage

* `-h`,`--help` - show help

### test

`test key -wX -dX -iX -e -D -v` - run test containers

`test -h` - show help of usage

> X - specify week/day number or test container id

* `key` - course key **_(required)_**
* `-w`,`--week=<week>` - week number
* `-d`,`--day=<day>` - day number
* `-i`,`--id=<id>` - container id
* `-e`,`--error` - show only errors
* `-D`,`--debug` - show std out/error prints and time of each test
* `-v`,`--verbose` - show extra long error messages
* `-h`,`--help` - show help

### pass

`pass key -wX -dX -iX` - run checkstyle and test containers to pass an exam

`pass -h` - show help of usage

> X - specify week/day number or test container id

* `key` - course key **_(required)_**
* `-w`,`--week=<week>` - week number
* `-d`,`--day=<day>` - day number
* `-i`,`--id=<id>` - container id
* `-h`,`--help` - show help

### maven

`maven -m"dir" command1 command2...` - invoke any maven commands

`maven -m"dir" X` - invoke maven preset

`maven -h` - show help of usage

> _X_ - any key from list below

* `-b`,`--build` - package a project
* `-c`,`--clean` - clean a project
* `-C`,`--compile` - compile a project
* `-m`,`--maven=<mavenHome>` - maven home directory _**(optional, if M2_HOME absent in your system)**_
* `-t`,`--test` - test a project
* `-h`,`--help` - show help

### update

`update` - download the latest release of Zeus

## Examples of usage

### show Zeus help

`java --enable-preview -jar Zeus-7.jar -h`

### show Zeus checkstyle help

`java --enable-preview -jar Zeus-7.jar style -h`

### run tests

`java --enable-preview -jar Zeus-7.jar test jcb -w0 -d3`

### run tests (manually adding your JAR into classpath)

`java --enable-preview -cp "/target/Homework.jar:Zeus-7.jar" academy.kovalevskyi.zeus.TheMighty test jcb -w0 -d3`

### run tests (manually adding your JAR into classpath, wildcards version, same that above)

`java --enable-preview -cp "/target/*:Zeus-7.jar" academy.kovalevskyi.zeus.TheMighty test jcb -w0 -d3`