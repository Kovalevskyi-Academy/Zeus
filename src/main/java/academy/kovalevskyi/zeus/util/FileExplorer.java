package academy.kovalevskyi.zeus.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FileExplorer {

  public static final String M2_HOME;
  public static final String JAVA_CLASSPATH;
  public static final String JAVA_SOURCES;
  public static final String WORKING_DIRECTORY;
  public static final String OUTPUT_DIRECTORY;

  static {
    WORKING_DIRECTORY = System.getProperty("user.dir");
    M2_HOME = System.getenv("M2_HOME");
    JAVA_CLASSPATH = System.getProperty("java.class.path");
    JAVA_SOURCES = String.format("%s%2$ssrc", WORKING_DIRECTORY, File.separator);
    OUTPUT_DIRECTORY = String.format("%s%2$starget", WORKING_DIRECTORY, File.separator);
  }

  public static boolean match(final String fileName, final FileType type) {
    return fileName.toLowerCase().endsWith(type.extension);
  }

  public static boolean isClasspathNotEmpty() {
    return Arrays
        .stream(JAVA_CLASSPATH.split(File.pathSeparator))
        .filter(file -> match(file, FileType.JAR))
        .distinct()
        .count() > 1;
  }

  public static List<File> getFiles(
      final File directory,
      final boolean deepSearch,
      final FileType type) {
    return getFiles(directory.getAbsolutePath(), deepSearch, type);
  }

  public static List<File> getFiles(
      final String directory,
      final boolean deepSearch,
      final FileType type) {
    return getFiles(directory, deepSearch)
        .stream()
        .filter(file -> match(file.getName(), type))
        .collect(Collectors.toUnmodifiableList());
  }

  public static List<File> getFiles(final File directory, final boolean deepSearch) {
    return getFiles(directory.getAbsolutePath(), deepSearch);
  }

  public static List<File> getFiles(final String directory, final boolean deepSearch) {
    final var folder = new File(directory);
    if (!folder.exists()) {
      throw new IllegalArgumentException(String.format("Wrong path '%s'", directory));
    }
    final var files = new ArrayList<File>();
    if (folder.isDirectory()) {
      getFile(files, folder, deepSearch);
    } else {
      files.add(folder);
    }
    return Collections.unmodifiableList(files);
  }

  private static void getFile(final List<File> files, final File folder, final boolean deepSearch) {
    final var list = folder.listFiles();
    if (list != null) {
      for (var file : list) {
        if (file.isDirectory()) {
          if (deepSearch) {
            getFile(files, file, true);
          }
        } else {
          files.add(file);
        }
      }
    }
  }
}
