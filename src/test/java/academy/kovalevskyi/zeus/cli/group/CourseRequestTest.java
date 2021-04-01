package academy.kovalevskyi.zeus.cli.group;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import academy.kovalevskyi.testing.service.ContainerRequest;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class CourseRequestTest {

  private final CourseRequest courseRequest = new CourseRequest();

  @Test
  public void testKeyField() throws NoSuchFieldException {
    var keyField = CourseRequest.class.getDeclaredField("key");
    assertTrue(Modifier.isPrivate(keyField.getModifiers()));
    var keyFieldParameters = keyField.getAnnotation(Parameters.class);
    assertNotNull(keyFieldParameters);
    assertFalse(keyFieldParameters.hidden());
    assertEquals("1", keyFieldParameters.arity());
    assertEquals(1, keyFieldParameters.description().length);
    assertEquals("Course key", keyFieldParameters.description()[0]);
  }

  @Test
  public void testWeekField() throws NoSuchFieldException {
    var weekField = CourseRequest.class.getDeclaredField("week");
    assertTrue(Modifier.isPrivate(weekField.getModifiers()));
    var weekFieldOption = weekField.getAnnotation(Option.class);
    assertNotNull(weekFieldOption);
    assertFalse(weekFieldOption.hidden());
    assertArrayEquals(new String[]{"-w", "--week"}, weekFieldOption.names());
    assertEquals("-1", weekFieldOption.defaultValue());
    assertEquals(1, weekFieldOption.description().length);
    assertEquals("Week number", weekFieldOption.description()[0]);
  }

  @Test
  public void testDayField() throws NoSuchFieldException {
    var dayField = CourseRequest.class.getDeclaredField("day");
    assertTrue(Modifier.isPrivate(dayField.getModifiers()));
    var dayFieldOption = dayField.getAnnotation(Option.class);
    assertNotNull(dayFieldOption);
    assertFalse(dayFieldOption.hidden());
    assertArrayEquals(new String[]{"-d", "--day"}, dayFieldOption.names());
    assertEquals("-1", dayFieldOption.defaultValue());
    assertEquals(1, dayFieldOption.description().length);
    assertEquals("Day number", dayFieldOption.description()[0]);
  }

  @Test
  public void testIdField() throws NoSuchFieldException {
    var idField = CourseRequest.class.getDeclaredField("id");
    assertTrue(Modifier.isPrivate(idField.getModifiers()));
    var idFieldOption = idField.getAnnotation(Option.class);
    assertNotNull(idFieldOption);
    assertFalse(idFieldOption.hidden());
    assertArrayEquals(new String[]{"-i", "--id"}, idFieldOption.names());
    assertEquals("-1", idFieldOption.defaultValue());
    assertEquals(1, idFieldOption.description().length);
    assertEquals("Container id", idFieldOption.description()[0]);
  }

  @Test
  public void testKeyRequest() throws NoSuchFieldException, IllegalAccessException {
    var key = CourseRequest.class.getDeclaredField("key");
    key.setAccessible(true);
    key.set(courseRequest, "R2D2");
    var week = CourseRequest.class.getDeclaredField("week");
    week.setAccessible(true);
    week.set(courseRequest, 0);
    var day = CourseRequest.class.getDeclaredField("day");
    day.setAccessible(true);
    day.set(courseRequest, 1);
    var id = CourseRequest.class.getDeclaredField("id");
    id.setAccessible(true);
    id.set(courseRequest, 2);
    var expected = ContainerRequest.builder().course("R2D2").week(0).day(1).container(2).build();
    var actual = courseRequest.prepareRequest();
    assertEquals(expected.toString(), actual.toString());
  }
}