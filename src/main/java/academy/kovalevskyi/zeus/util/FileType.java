package academy.kovalevskyi.zeus.util;

public enum FileType {

  JAR("jar"),
  JAVA("java");

  public final String extension;

  FileType(String extension) {
    this.extension = extension;
  }
}
