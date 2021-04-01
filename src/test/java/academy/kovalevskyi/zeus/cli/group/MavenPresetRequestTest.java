package academy.kovalevskyi.zeus.cli.group;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import academy.kovalevskyi.zeus.engine.maven.Lifecycle;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.Option;

public class MavenPresetRequestTest {

  private final MavenPresetRequest presetRequest = new MavenPresetRequest();

  @Test
  public void testCleanField() throws NoSuchFieldException {
    var isCleanField = MavenPresetRequest.class.getDeclaredField("isClean");
    assertTrue(Modifier.isPrivate(isCleanField.getModifiers()));
    var isCleanFieldOption = isCleanField.getAnnotation(Option.class);
    assertNotNull(isCleanFieldOption);
    assertFalse(isCleanFieldOption.hidden());
    assertArrayEquals(new String[]{"-c", "--clean"}, isCleanFieldOption.names());
    assertEquals("false", isCleanFieldOption.defaultValue());
    assertEquals(1, isCleanFieldOption.description().length);
    assertEquals("Clean a project", isCleanFieldOption.description()[0]);
  }

  @Test
  public void testCompileField() throws NoSuchFieldException {
    var isCompileField = MavenPresetRequest.class.getDeclaredField("isCompile");
    assertTrue(Modifier.isPrivate(isCompileField.getModifiers()));
    var isCompileFieldOption = isCompileField.getAnnotation(Option.class);
    assertNotNull(isCompileFieldOption);
    assertFalse(isCompileFieldOption.hidden());
    assertArrayEquals(new String[]{"-C", "--compile"}, isCompileFieldOption.names());
    assertEquals("false", isCompileFieldOption.defaultValue());
    assertEquals(1, isCompileFieldOption.description().length);
    assertEquals("Compile a project", isCompileFieldOption.description()[0]);
  }

  @Test
  public void testTestField() throws NoSuchFieldException {
    var isTestField = MavenPresetRequest.class.getDeclaredField("isTest");
    assertTrue(Modifier.isPrivate(isTestField.getModifiers()));
    var isTestFieldOption = isTestField.getAnnotation(Option.class);
    assertNotNull(isTestFieldOption);
    assertFalse(isTestFieldOption.hidden());
    assertArrayEquals(new String[]{"-t", "--test"}, isTestFieldOption.names());
    assertEquals("false", isTestFieldOption.defaultValue());
    assertEquals(1, isTestFieldOption.description().length);
    assertEquals("Test a project", isTestFieldOption.description()[0]);
  }

  @Test
  public void testBuildField() throws NoSuchFieldException {
    var isBuildField = MavenPresetRequest.class.getDeclaredField("isBuild");
    assertTrue(Modifier.isPrivate(isBuildField.getModifiers()));
    var isBuildFieldOption = isBuildField.getAnnotation(Option.class);
    assertNotNull(isBuildFieldOption);
    assertFalse(isBuildFieldOption.hidden());
    assertArrayEquals(new String[]{"-b", "--build"}, isBuildFieldOption.names());
    assertEquals("false", isBuildFieldOption.defaultValue());
    assertEquals(1, isBuildFieldOption.description().length);
    assertEquals("Package a project", isBuildFieldOption.description()[0]);
  }

  @Test
  public void testCleanRequest() throws NoSuchFieldException, IllegalAccessException {
    var clean = MavenPresetRequest.class.getDeclaredField("isClean");
    clean.setAccessible(true);
    clean.set(presetRequest, true);
    var request = presetRequest.getRequest().getCommands();
    assertEquals(1, request.size());
    assertEquals(Lifecycle.CLEAN.command, request.get(0));
  }

  @Test
  public void testCompileRequest() throws NoSuchFieldException, IllegalAccessException {
    var compile = MavenPresetRequest.class.getDeclaredField("isCompile");
    compile.setAccessible(true);
    compile.set(presetRequest, true);
    var request = presetRequest.getRequest().getCommands();
    assertEquals(2, request.size());
    assertEquals(Lifecycle.CLEAN.command, request.get(0));
    assertEquals(Lifecycle.COMPILE.command, request.get(1));
  }

  @Test
  public void testTestRequest() throws NoSuchFieldException, IllegalAccessException {
    var test = MavenPresetRequest.class.getDeclaredField("isTest");
    test.setAccessible(true);
    test.set(presetRequest, true);
    var request = presetRequest.getRequest().getCommands();
    assertEquals(2, request.size());
    assertEquals(Lifecycle.CLEAN.command, request.get(0));
    assertEquals(Lifecycle.TEST.command, request.get(1));
  }

  @Test
  public void testBuildRequest() throws NoSuchFieldException, IllegalAccessException {
    var build = MavenPresetRequest.class.getDeclaredField("isBuild");
    build.setAccessible(true);
    build.set(presetRequest, true);
    var request = presetRequest.getRequest().getCommands();
    assertEquals(2, request.size());
    assertEquals(Lifecycle.CLEAN.command, request.get(0));
    assertEquals(Lifecycle.PACKAGE.command, request.get(1));
  }
}