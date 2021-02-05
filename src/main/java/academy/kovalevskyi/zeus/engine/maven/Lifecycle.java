package academy.kovalevskyi.zeus.engine.maven;

public enum Lifecycle {

  CLEAN("clean"),
  VALIDATE("validate"),
  COMPILE("compile"),
  TEST("test"),
  PACKAGE("package"),
  VERIFY("verify"),
  INSTALL("install"),
  SITE("site"),
  DEPLOY("deploy");

  public final String command;

  Lifecycle(String command) {
    this.command = command;
  }
}
