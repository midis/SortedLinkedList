package com.shipmonk;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SortedLinkedListTest {

    @Test
    void constructorCreatesInstance() {
        var list = new SortedLinkedList();
        Assertions.assertNotNull(list);
        Assertions.assertInstanceOf(SortedLinkedList.class, list);
    }

    @Test
    void newEmptyListHasZeroSize() {
        SortedLinkedList list = new SortedLinkedList();
        Assertions.assertEquals(0, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 11, Integer.MAX_VALUE, -1, -111, Integer.MIN_VALUE})
    void addOneIntegerToEmptyListUpdatesSizeAndGetReturnsIt(int value) {
        SortedLinkedList list = new SortedLinkedList();
        list.add(value);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(value, list.get(0));
    }

    @Test
    void addWithNullValueThrowsException() {
        SortedLinkedList list = new SortedLinkedList();
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.add(null));
    }

    @Test
    void addSeveralIntegerElements() {
        SortedLinkedList list = new SortedLinkedList();
        final List<Integer> elementsToAdd = getSampleListOfElements();
        for (int i = 0; i < elementsToAdd.size(); i++) {
            final Integer newElement = elementsToAdd.get(i);
            final int expectedSize = i + 1;
            list.add(newElement);
            Assertions.assertEquals(expectedSize, list.size(),
                "After adding element %s the size is expected to be %d".formatted(newElement, expectedSize));
            List<Integer> elements = list.toList();
            Assertions.assertTrue(elements.contains(newElement),
                "After adding element %s the list (%s) should contain it.".formatted(newElement, elements));
        }
    }

    @Test
    void toListContainsAllElements() {
        SortedLinkedList list = new SortedLinkedList();
        final List<Integer> elementsToAdd = getSampleListOfElements();
        elementsToAdd.forEach(list::add);
        List<Integer> resultElements = list.toList();
        Assertions.assertEquals(elementsToAdd.size(), resultElements.size(),
            "Size of elements is expected %d".formatted(elementsToAdd.size()));
        elementsToAdd.forEach(addedElement -> Assertions.assertTrue(resultElements.contains(addedElement),
            "The result elements should contain '%s'.".formatted(addedElement)));
        resultElements.forEach(resultElement -> Assertions.assertTrue(elementsToAdd.contains(resultElement),
            "The result elements contains unexpected '%s'.".formatted(resultElement)));
    }

    // TODO: add test for adding already existing element(s)

    @Test
    void toListIsImmutable() {
        SortedLinkedList sortedList = createSampleSortedList();
        List<Integer> listBeforeAdd = sortedList.toList();
        final int newElement = 999;
        listBeforeAdd.add(newElement);
        List<Integer> listAfterAdd = sortedList.toList();
        Assertions.assertFalse(listAfterAdd.contains(newElement), ("The original sorted list shouldn't contain element "
            + "'%s' added to the List produced by toList() method.").formatted(newElement));
    }

    @Test
    void addIntegerKeepsListSorted() {
        var list = new SortedLinkedList();
        list.add(1);
        list.add(3);
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(3, list.get(1));
        list.add(2);
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
        list.add(-1);
        Assertions.assertEquals(-1, list.get(0));
        Assertions.assertEquals(1, list.get(1));
        Assertions.assertEquals(2, list.get(2));
        Assertions.assertEquals(3, list.get(3));
        list.add(Integer.MAX_VALUE);
        Assertions.assertEquals(-1, list.get(0));
        Assertions.assertEquals(1, list.get(1));
        Assertions.assertEquals(2, list.get(2));
        Assertions.assertEquals(3, list.get(3));
        Assertions.assertEquals(Integer.MAX_VALUE, list.get(4));
    }

    private static SortedLinkedList createSampleSortedList() {
        SortedLinkedList sortedList = new SortedLinkedList();
        final List<Integer> elementsToAdd = getSampleListOfElements();
        elementsToAdd.forEach(sortedList::add);
        return sortedList;
    }

    private static List<Integer> getSampleListOfElements() {
        return List.of(0, 1, 11, Integer.MAX_VALUE, -1, -111, Integer.MIN_VALUE);
    }
}
