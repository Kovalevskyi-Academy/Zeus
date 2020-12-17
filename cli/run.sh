#!/bin/zsh

java -cp "../CodingBootcamp/target/CodingBootcamp-1.0.jar:cli/Zeus.jar" com.kovalevskyi.academy.codingbootcamp.suite.Zeus --maven /Users/vkovalevskyi/dev/apache-maven-3.6.3 --week 0 --day 0
java -cp "Zeus.jar" com.kovalevskyi.academy.codingbootcamp.suite.Zeus --maven /Users/vkovalevskyi/dev/apache-maven-3.6.3 --week 0 --day 0


# windows
java -cp "CodingBootcamp-1.0.jar;Zeus.jar" com.kovalevskyi.academy.codingbootcamp.suite.Zeus -w0 -d1

# unix-like
java -cp "CodingBootcamp-1.0.jar:Zeus.jar" com.kovalevskyi.academy.codingbootcamp.suite.Zeus -w0 -d1