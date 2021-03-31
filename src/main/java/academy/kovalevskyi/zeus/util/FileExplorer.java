package academy.kovalevskyi.zeus.util;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FileExplorer {

  public static final String JAVA_CLASSPATH = System.getProperty("java.class.path");
  public static final Path M2_HOME = Path.of(System.getenv("M2_HOME"));
  public static final Path WORKING_DIRECTORY = Path.of(System.getProperty("user.dir"));
  public static final Path TMP_DIRECTORY = Path.of(System.getProperty("java.io.tmpdir"));
  public static final Path JAVA_SOURCES = Path.of(WORKING_DIRECTORY.toString(), "src");
  public static final Path OUTPUT_DIRECTORY = Path.of(WORKING_DIRECTORY.toString(), "target");

  public static boolean match(final String fileName, final String extension) {
    if (fileName == null || extension == null) {
      return false;
    }
    var dotIndex = fileName.lastIndexOf(46);
    if (dotIndex == -1 || dotIndex + 1 >= fileName.length()) {
      return false;
    }
    var fileExtension = extension.replaceFirst("\\.", "");
    return fileName.substring(dotIndex + 1).equalsIgnoreCase(fileExtension);
  }

  public static boolean match(final String fileName, final FileType type) {
    return match(fileName, type.extension);
  }

  public static List<File> getFiles(
      final Path directory,
      final boolean deepSearch,
      final FileType type) {
    return getFiles(directory, deepSearch)
        .stream()
        .filter(file -> match(file.getName(), type))
        .collect(Collectors.toUnmodifiableList());
  }

  public static List<File> getFiles(final Path directory, final boolean deepSearch) {
    final var folder = directory.toFile();
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
