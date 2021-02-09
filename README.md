# Zeus the Mighty

## Features

- Packaging students projects into .jar archive
- Checking a style of code with Checkstyle
- Testing students solutions with jUnit

## Importantly

- Zeus jar archive should be in the root folder of the student's project
- Use java 13 or above

## How to use

**_Zeus jar archive should be in the root folder of the student's project!_**

**_Launch the command line from the root folder of your project and input the code below_**

`<ZEUS>` — Zeus jar file, example **Zeus-2.6.jar**

`<HOMEWORK>` — the name of your jar file, example **Homework.jar**

`<DELIMITER>` — classpath delimiter:

- `:` - unix-like

- `;` - windows

`<KEY>` — any available key, example **-h**

`<COMMAND>` — any available command, example **test**

- all commands have own help menu, use it to get more information!

##### run Zeus

`java -jar <ZEUS>`

##### run Zeus with key

`java -jar <ZEUS> <KEY>`

##### run Zeus with keys

`java -jar <ZEUS> <KEY> <KEY>...`

##### run Zeus command

`java -jar <ZEUS> <COMMAND>`

##### run Zeus command with key

`java -jar <ZEUS> <COMMAND> <KEY>`

##### run Zeus with key and command with key

`java -jar <ZEUS> <KEY> <COMMAND> <KEY>`

##### run Zeus with another jar file with key

`java -cp "<HOMEWORK><DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY>`

##### run Zeus with another jars with key using wildcards (get all jars from 'target' folder)

`java -cp "./target/*<DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <KEY>`

##### run Zeus command with key with another jar file

`java -cp "<HOMEWORK><DELIMITER><ZEUS>" academy.kovalevskyi.zeus.TheMighty <COMMAND> <KEY>`

## Examples of usage

##### show Zeus help

`java -jar Zeus-2.6.jar -h`

##### show Zeus checkstyle help

`java -jar Zeus-2.6.jar checkstyle -h`

##### run tests

`java -cp "./target/Homework.jar:Zeus-2.6.jar" academy.kovalevskyi.zeus.TheMighty test -c0 -w0 -d0`

##### run tests (wildcards version, same that above)

`java -cp "./target/*:Zeus-2.6.jar" academy.kovalevskyi.zeus.TheMighty test -c0 -w0 -d0`

##### run tests with debug mode

`java -cp "./target/Homework.jar:Zeus-2.6.jar" academy.kovalevskyi.zeus.TheMighty -d test -c0 -w0 -d0`
