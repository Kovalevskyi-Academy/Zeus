# Zeus the Mighty

## Features

- Packaging students projects into .jar archive
- Checking a style of code with Checkstyle
- Testing students solutions with jUnit

## Importantly

- Zeus jar archive should be in the root folder of the student's project
- Use java 15 with preview mode

## How to use

**_Zeus jar archive should be in the root folder of the student's project!_**

**_Launch the command line from the root folder of your project and input the code below_**

`<ZEUS>` — Zeus jar file, example **Zeus-7.jar**

`<HOMEWORK>` — the name of your jar file, example **Homework.jar**

`<DELIMITER>` — classpath delimiter:

- `:` - unix-like

- `;` - windows

`<KEY>` — any available key, example **-h**

`<COMMAND>` — any available command, example **test**

- all commands have own help menu, use key **-h** to get more information!

##### run Zeus

`java --enable-preview -jar <ZEUS>`

##### run Zeus with key

`java --enable-preview -jar <ZEUS> <KEY>`

##### run Zeus with keys

`java --enable-preview -jar <ZEUS> <KEY> <KEY>...`

##### run Zeus command

`java --enable-preview -jar <ZEUS> <COMMAND>`

##### run Zeus command with key

`java --enable-preview -jar <ZEUS> <COMMAND> <KEY>`

##### run Zeus with key and command with key

`java --enable-preview -jar <ZEUS> <KEY> <COMMAND> <KEY>`

##### run Zeus with another jar file with key

`java --enable-preview -cp "<HOMEWORK><DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY>`

##### run Zeus with another jars with key using wildcards (get all jars from 'target' folder)

`java --enable-preview -cp "./target/*<DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY>`

##### run Zeus command with key with another jar file

`java --enable-preview -cp "<HOMEWORK><DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <COMMAND> <KEY>`

## Examples of usage

##### show Zeus help

`java --enable-preview -jar Zeus-7.jar -h`

##### show Zeus checkstyle help

`java --enable-preview -jar Zeus-7.jar style -h`

##### run tests

`java --enable-preview -cp "./target/Homework.jar:Zeus-7.jar" academy.kovalevskyi.zeus.TheMighty test jcb -w0 -d3`

##### run tests (wildcards version, same that above)

`java --enable-preview -cp "./target/*:Zeus-7.jar" academy.kovalevskyi.zeus.TheMighty test jcb -w0 -d3`

##### run tests with debug mode

`java --enable-preview -cp "./target/Homework.jar:Zeus-7.jar" academy.kovalevskyi.zeus.TheMighty -d test jcb -w0 -d3`
