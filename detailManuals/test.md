## test

`test key -wX -dX -iX -e -D -v` - run test containers

`test -h` - show help of usage

> X - specify number of: week, day, test class (last — optional)

* `key` - course key **_(required)_** (jcb, jdd, ...)
* `-w`,`--week=<week>` - week number
* `-d`,`--day=<day>` - day number
* `-i`,`--id=<id>` - container id
* `-e`,`--error` - show only errors
* `-D`,`--debug` - show std out/error prints and time of each test
* `-v`,`--verbose` - show extra long error messages
* `-h`,`--help` - show help