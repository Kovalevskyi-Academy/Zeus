package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day2;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week1.day2.List;
import java.util.function.Function;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ListTest extends AbstractTestExecutor {

  @Test
  public void createOne() {
    var value = (Integer) 1;
    var newListElement = List.createOne(value);

    assertThat(newListElement.getNext()).isNull();
    assertThat(newListElement.getPrev()).isNull();
    assertThat(newListElement.getValue()).isEqualTo(value);
  }

  @Test
  public void addToEnd() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);

    var firstNew = List.addToEnd(firstListElement, value + 1);
    var secondNew = List.addToEnd(firstListElement, value + 2);

    assertThat(firstListElement.getPrev()).isNull();
    assertThat(firstNew).isEqualTo(firstListElement.getNext());
    assertThat(secondNew).isEqualTo(firstListElement.getNext().getNext());
    assertThat(firstListElement).isEqualTo(firstListElement.getNext().getPrev());
    assertThat(firstNew).isEqualTo(firstListElement.getNext().getNext().getPrev());
    assertThat(firstListElement.getNext().getNext().getNext()).isNull();

    assertThat(firstListElement.getValue()).isEqualTo(value);
    assertThat(firstListElement.getNext().getValue()).isEqualTo(value + 1);
    assertThat(firstListElement.getNext().getNext().getValue()).isEqualTo(value + 2);
  }

  @Test
  public void addToStart() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);

    var firstNew = List.addToStart(firstListElement, value + 1);
    var secondNew = List.addToStart(firstListElement, value + 2);

    assertThat(secondNew.getPrev()).isNull();
    assertThat(firstNew).isEqualTo(secondNew.getNext());
    assertThat(firstListElement).isEqualTo(secondNew.getNext().getNext());
    assertThat(secondNew).isEqualTo(secondNew.getNext().getPrev());
    assertThat(firstNew).isEqualTo(secondNew.getNext().getNext().getPrev());
    assertThat(secondNew.getNext()).isNotNull();

    assertThat(secondNew.getValue()).isEqualTo(value + 2);
    assertThat(secondNew.getNext().getValue()).isEqualTo(value + 1);
    assertThat(secondNew.getNext().getNext().getValue()).isEqualTo(value);
  }

  @Test
  public void contains() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    assertThat(List.contains(firstListElement, 0)).isFalse();
    assertThat(List.contains(firstListElement, 5)).isTrue();
  }

  @Test
  public void map() {
    var toStr = (Function<Integer, String>) String::valueOf;

    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    var newList = List.map(firstListElement, toStr);
    assertThat(newList.length()).isEqualTo(6);
    assertThat(List.contains(newList, "1")).isTrue();
    assertThat(List.contains(newList, "2")).isTrue();
    assertThat(List.contains(newList, "3")).isTrue();
    assertThat(List.contains(newList, "4")).isTrue();
    assertThat(List.contains(newList, "5")).isTrue();
    assertThat(List.contains(newList, "6")).isTrue();
    // Just in case:)
    assertThat(List.contains(newList, "7")).isFalse();
  }

  @Test
  public void lengthToEnd() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    assertThat(firstListElement.length()).isEqualTo(6);
  }

  @Test
  public void insertAfter() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    var targetElement = List.addToEnd(firstListElement, value + 3);
    var nextElement = List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    var insertedElement = targetElement.insertAfter(15);

    assertThat(List.contains(firstListElement, 15)).isTrue();
    assertThat(firstListElement.length()).isEqualTo(7);
    assertThat(insertedElement.getNext()).isEqualTo(nextElement);
    assertThat(insertedElement.getPrev()).isEqualTo(targetElement);
    assertThat(targetElement.getNext()).isEqualTo(insertedElement);
    assertThat(nextElement.getPrev()).isEqualTo(insertedElement);
  }

  @Test
  public void insertAfterLast() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    List.addToEnd(firstListElement, value + 4);
    var lastElement = List.addToEnd(firstListElement, value + 5);

    var insertedElement = lastElement.insertAfter(15);

    assertThat(List.contains(firstListElement, 15)).isTrue();
    assertThat(firstListElement.length()).isEqualTo(7);
    assertThat(lastElement.getNext()).isEqualTo(insertedElement);
    assertThat(insertedElement.getPrev()).isEqualTo(lastElement);
    assertThat(insertedElement.getNext()).isNull();
  }

  @Test
  public void insertAfterWithArray() {
    var toAdd = new Integer[] {20, 21, 22, 23, 24, 25};

    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    var targetElement = List.addToEnd(firstListElement, value + 3);
    var nextElement = List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    targetElement.insertAfter(toAdd);
    for (var toAddElement : toAdd) {
      assertThat(List.contains(firstListElement, toAddElement)).isTrue();
    }
    assertThat(firstListElement.length()).isEqualTo(12);
  }

  @Test
  public void delete() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    var beforeDelete = List.addToEnd(firstListElement, value + 2);
    var toDelete = List.addToEnd(firstListElement, value + 3);
    var afterDelete = List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    toDelete.delete();

    assertThat(beforeDelete.getNext()).isEqualTo(afterDelete);
    assertThat(afterDelete.getPrev()).isEqualTo(beforeDelete);
    assertThat(firstListElement.length()).isEqualTo(5);
  }

  @Test
  public void deleteFirstElement() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    var secondElement = List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    List.addToEnd(firstListElement, value + 4);
    List.addToEnd(firstListElement, value + 5);

    firstListElement.delete();

    assertThat(secondElement.getPrev()).isNull();
  }

  @Test
  public void deleteLastElement() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    List.addToEnd(firstListElement, value + 1);
    List.addToEnd(firstListElement, value + 2);
    List.addToEnd(firstListElement, value + 3);
    var beforeLast = List.addToEnd(firstListElement, value + 4);
    var lastElement = List.addToEnd(firstListElement, value + 5);

    lastElement.delete();

    assertThat(beforeLast.getNext()).isNull();
  }

  @Test
  public void setPrev() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    var secondListElement = List.createOne(value);

    firstListElement.setPrev(secondListElement);

    assertThat(firstListElement.getPrev()).isEqualTo(secondListElement);
  }

  @Test
  public void setNext() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    var secondListElement = List.createOne(value);

    firstListElement.setNext(secondListElement);

    assertThat(firstListElement.getNext()).isEqualTo(secondListElement);
  }

  @Test
  public void getValue() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);

    assertThat(firstListElement.getValue()).isEqualTo(value);
  }

  @Test
  public void connect() {
    var value = (Integer) 1;
    var firstListElement = List.createOne(value);
    var secondListElement = List.createOne(value);

    firstListElement.connect(secondListElement);

    assertThat(secondListElement.getPrev()).isEqualTo(firstListElement);
    assertThat(firstListElement.getNext()).isEqualTo(secondListElement);
  }
}
