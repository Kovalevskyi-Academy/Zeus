package academy.kovalevskyi.zeus.util;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
import net.bytebuddy.agent.ByteBuddyAgent;

public final class JarLoader {

  private static final List<File> JAR_FILES;

  static {
    JAR_FILES = initializeList();
  }

  public static void agentmain(String name, Instrumentation instrumentation) throws IOException {
    for (var jar : JAR_FILES) {
      instrumentation.appendToSystemClassLoaderSearch(new JarFile(jar));
    }
  }

  public static boolean isDynamicallyLoaded() {
    if (!JAR_FILES.isEmpty()) {
      initializeJavaAgent();
      return true;
    }
    return false;
  }

  private static void initializeJavaAgent() {
    final var pid = ProcessHandle.current().toString();
    final var agent = new File(JarLoader.class
        .getProtectionDomain()
        .getCodeSource()
        .getLocation()
        .getPath());
    ByteBuddyAgent.attach(agent, pid);
  }

  private static List<File> initializeList() {
    try {
      return FileExplorer.getFiles(FileExplorer.OUTPUT_DIRECTORY, false, FileType.JAR);
    } catch (IllegalArgumentException e) {
      return Collections.emptyList();
    }
  }
}
