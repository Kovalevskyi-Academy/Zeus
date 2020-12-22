package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day3;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week2.day3.List;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ListTest extends AbstractTestExecutor {

    @Test
    void createOne() {
        var value = (Integer) 1;
        var newListElement = List.createOne(value);

        assertNull(newListElement.getNext());
        assertNull(newListElement.getPrev());
        assertEquals(value, newListElement.getValue());
    }

    @Test
    void addToEnd() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);

        var firstNew = List.addToEnd(firstListElement, value + 1);
        var secondNew = List.addToEnd(firstListElement, value + 2);

        assertNull(firstListElement.getPrev());
        assertEquals(firstListElement.getNext(), firstNew);
        assertEquals(firstListElement.getNext().getNext(), secondNew);
        assertEquals(firstListElement.getNext().getPrev(), firstListElement);
        assertEquals(firstListElement.getNext().getNext().getPrev(), firstNew);
        assertNull(firstListElement.getNext().getNext().getNext());

        assertEquals(firstListElement.getValue(), value);
        assertEquals(firstListElement.getNext().getValue(), value + 1);
        assertEquals(firstListElement.getNext().getNext().getValue(), value + 2);
    }

    @Test
    void addToStart() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);

        var firstNew = List.addToStart(firstListElement, value + 1);
        var secondNew = List.addToStart(firstListElement, value + 2);

        assertNull(secondNew.getPrev());
        assertEquals(secondNew.getNext(), firstNew);
        assertEquals(secondNew.getNext().getNext(), firstListElement);
        assertEquals(secondNew.getNext().getPrev(), secondNew);
        assertEquals(secondNew.getNext().getNext().getPrev(), firstNew);
        assertNull(secondNew.getNext().getNext().getNext());

        assertEquals(secondNew.getValue(), value + 2);
        assertEquals(secondNew.getNext().getValue(), value + 1);
        assertEquals(secondNew.getNext().getNext().getValue(), value);
    }

    @Test
    void contains() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        assertFalse(List.contains(firstListElement, 0));
        assertTrue(List.contains(firstListElement, 5));
    }

    @Test
    void map() {
        var toStr = (Function<Integer, String>) String::valueOf;

        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        var newList = List.map(firstListElement, toStr);
        assertEquals(6, newList.length());
        assertTrue(List.contains(newList, "1"));
        assertTrue(List.contains(newList, "2"));
        assertTrue(List.contains(newList, "3"));
        assertTrue(List.contains(newList, "4"));
        assertTrue(List.contains(newList, "5"));
        assertTrue(List.contains(newList, "6"));
    }

    @Test
    void lengthToEnd() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        assertEquals(6, firstListElement.length());
    }

    @Test
    void insertAfter() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        var targetElement = List.addToEnd(firstListElement, value + 3);
        var nextElement = List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        var insertedElement = targetElement.insertAfter(15);

        assertTrue(List.contains(firstListElement, 15));
        assertEquals(7, firstListElement.length());
        assertEquals(nextElement, insertedElement.getNext());
        assertEquals(targetElement, insertedElement.getPrev());
        assertEquals(insertedElement, targetElement.getNext());
        assertEquals(insertedElement, nextElement.getPrev());
    }

    @Test
    void insertAfterLast() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        List.addToEnd(firstListElement, value + 4);
        var lastElement = List.addToEnd(firstListElement, value + 5);

        var insertedElement = lastElement.insertAfter(15);

        assertTrue(List.contains(firstListElement, 15));
        assertEquals(7, firstListElement.length());
        assertEquals(insertedElement, lastElement.getNext());
        assertEquals(lastElement, insertedElement.getPrev());
        assertNull(insertedElement.getNext());
    }

    @Test
    void insertAfterWithArray() {
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
            assertTrue(List.contains(firstListElement, toAddElement));
        }
        assertEquals(12, firstListElement.length());
    }

    @Test
    void delete() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        var beforeDelete = List.addToEnd(firstListElement, value + 2);
        var toDelete = List.addToEnd(firstListElement, value + 3);
        var afterDelete = List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        toDelete.delete();

        assertEquals(afterDelete, beforeDelete.getNext());
        assertEquals(beforeDelete, afterDelete.getPrev());
        assertEquals(5, firstListElement.length());
    }

    @Test
    void deleteFirstElement() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        var secondElement = List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        List.addToEnd(firstListElement, value + 4);
        List.addToEnd(firstListElement, value + 5);

        firstListElement.delete();

        assertNull(secondElement.getPrev());
    }

    @Test
    void deleteLastElement() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        List.addToEnd(firstListElement, value + 1);
        List.addToEnd(firstListElement, value + 2);
        List.addToEnd(firstListElement, value + 3);
        var beforeLast = List.addToEnd(firstListElement, value + 4);
        var lastElement = List.addToEnd(firstListElement, value + 5);

        lastElement.delete();

        assertNull(beforeLast.getNext());
    }

    @Test
    void setPrev() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        var secondListElement = List.createOne(value);

        firstListElement.setPrev(secondListElement);

        assertEquals(secondListElement, firstListElement.getPrev());
    }

    @Test
    void setNext() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        var secondListElement = List.createOne(value);

        firstListElement.setNext(secondListElement);

        assertEquals(secondListElement, firstListElement.getNext());
    }

    @Test
    void getValue() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);

        assertEquals(value, firstListElement.getValue());
    }

    @Test
    void connect() {
        var value = (Integer) 1;
        var firstListElement = List.createOne(value);
        var secondListElement = List.createOne(value);

        firstListElement.connect(secondListElement);

        assertEquals(firstListElement, secondListElement.getPrev());
        assertEquals(secondListElement, firstListElement.getNext());
    }
}