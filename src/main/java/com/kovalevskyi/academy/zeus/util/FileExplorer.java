package com.kovalevskyi.academy.zeus.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileExplorer {

  public static List<File> getFiles(final String directory) {
    final var filesList = new ArrayList<File>();
    for (var path : directory.split(File.pathSeparator)) {
      var file = new File(path);
      if (file.isDirectory()) {
        getFile(filesList, file);
      } else {
        filesList.add(file);
      }
    }
    return filesList;
  }

  private static void getFile(final List<File> filesList, final File f) {
    var list = f.listFiles();
    if (Objects.nonNull(list)) {
      for (var file : list) {
        if (file.isDirectory()) {
          getFile(filesList, file);
        } else {
          filesList.add(file);
        }
      }
    }
  }
}
