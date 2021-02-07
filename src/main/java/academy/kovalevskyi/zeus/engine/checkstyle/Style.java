package academy.kovalevskyi.zeus.engine.checkstyle;

public enum Style {

  GOOGLE("google_checks.xml"),
  SUN("sun_checks.xml");

  public final String config;

  Style(String config) {
    this.config = config;
  }
}
