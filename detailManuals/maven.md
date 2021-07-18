## maven

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

### examples

`java -jar Zeus-27.jar maven -c` — clean your project

`zeus maven -c` — the same, but in google claud

`java -jar Zeus-27.jar maven -b` — build your project

`zeus maven -b` — the same, but in google claud

`java -jar Zeus-27.jar maven -t` — runs your tests that you yourself added or wrote in the project

`zeus maven -t` — the same, but in google claud
