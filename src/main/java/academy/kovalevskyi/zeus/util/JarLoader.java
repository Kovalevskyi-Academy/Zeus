package academy.kovalevskyi.zeus.util;

import academy.kovalevskyi.zeus.cli.command.Zeus;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
import net.bytebuddy.agent.ByteBuddyAgent;

public final class JarLoader {

  public static boolean isManuallyLoaded() {
    return Arrays
        .stream(FileExplorer.JAVA_CLASSPATH.split(File.pathSeparator))
        .filter(file -> FileExplorer.match(file, FileType.JAR))
        .distinct()
        .count() > 1;
  }

  public static boolean isDynamicallyLoaded() {
    var jars = initializeList();
    if (!jars.isEmpty()) {
      try {
        initializeJavaAgent(jars);
      } catch (Exception e) {
        if (Zeus.isDev()) {
          e.printStackTrace();
        }
        return false;
      }
      return true;
    }
    return false;
  }

  private static void initializeJavaAgent(final List<File> jars) throws IOException {
    if (pathContainsIllegalSymbols()) {
      var message = "Zeus cannot load your JAR(s), UTF-8 symbols is not supported in path";
      throw new UnsupportedEncodingException(message);
    }
    final var instrumentation = ByteBuddyAgent.install();
    for (var jar : jars) {
      instrumentation.appendToSystemClassLoaderSearch(new JarFile(jar));
    }
  }

  private static List<File> initializeList() {
    try {
      return FileExplorer.getFiles(FileExplorer.OUTPUT_DIRECTORY, false, FileType.JAR);
    } catch (IllegalArgumentException e) {
      return Collections.emptyList();
    }
  }

  private static boolean pathContainsIllegalSymbols() {
    try {
      var workDir = FileExplorer.WORKING_DIRECTORY.toString();
      StandardCharsets.US_ASCII.newDecoder()
          .onMalformedInput(CodingErrorAction.REPORT)
          .onUnmappableCharacter(CodingErrorAction.REPORT)
          .decode(ByteBuffer.wrap(workDir.getBytes(StandardCharsets.UTF_8)));
    } catch (CharacterCodingException e) {
      return true;
    }
    return false;
  }
}
