package academy.kovalevskyi.zeus.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileExplorer {

  public static final String M2_HOME;
  public static final String JAVA_CLASSPATH;
  public static final String JAVA_SOURCES;
  public static final String WORKING_DIRECTORY;

  static {
    WORKING_DIRECTORY = System.getProperty("user.dir");
    M2_HOME = System.getenv("M2_HOME");
    JAVA_CLASSPATH = System.getProperty("java.class.path");
    JAVA_SOURCES = String.format("%s%2$ssrc%2$smain%2$sjava", WORKING_DIRECTORY, File.separator);
  }

  public static boolean match(final File file, final FileType type) {
    if (!file.exists()) {
      throw new IllegalArgumentException(String.format("%s is absent", file.getAbsolutePath()));
    }
    if (!file.isFile()) {
      throw new IllegalArgumentException(String.format("%s is not a file", file.getAbsolutePath()));
    }
    return file.getName().toLowerCase().endsWith(type.extension);
  }

  public static boolean isJarAbsentInClasspath() {
    return Arrays
        .stream(JAVA_CLASSPATH.split("[;|:]"))
        .filter(path -> match(new File(path), FileType.JAR))
        .distinct()
        .count() == 1;
  }

  public static List<File> getFiles(final File directory, final FileType type) {
    return getFiles(directory.getAbsolutePath(), type);
  }

  public static List<File> getFiles(final String directory, final FileType type) {
    return getFiles(directory)
        .stream()
        .filter(file -> match(file, type))
        .collect(Collectors.toUnmodifiableList());
  }

  public static List<File> getFiles(final File directory) {
    return getFiles(directory.getAbsolutePath());
  }

  public static List<File> getFiles(final String directory) {
    var folder = new File(directory);
    if (!folder.isDirectory()) {
      throw new IllegalArgumentException(String.format("Wrong directory path '%s'", directory));
    }
    final var files = new ArrayList<File>();
    for (var path : directory.split(File.pathSeparator)) {
      var file = new File(path);
      if (file.isDirectory()) {
        getFile(files, file);
      } else {
        files.add(file);
      }
    }
    return Collections.unmodifiableList(files);
  }

  private static void getFile(final List<File> files, final File folder) {
    final var list = folder.listFiles();
    if (list != null) {
      for (var file : list) {
        if (file.isDirectory()) {
          getFile(files, file);
        } else {
          files.add(file);
        }
      }
    }
  }
}
