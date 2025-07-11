package com.shipmonk;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SortedLinkedListTest {

    @Test
    void constructorCreatesInstance() {
        var list = new SortedLinkedList<Integer>();
        Assertions.assertNotNull(list);
        Assertions.assertInstanceOf(SortedLinkedList.class, list);
    }

    @Test
    void newEmptyListHasZeroSize() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        Assertions.assertEquals(0, list.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 11, Integer.MAX_VALUE, -1, -111, Integer.MIN_VALUE})
    void addOneIntegerToEmptyListUpdatesSizeAndGetReturnsIt(int value) {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(value);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(value, list.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "b", "c", "abc", "A", "test text"})
    void addOneStringToEmptyListUpdatesSizeAndGetReturnsIt(String value) {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add(value);
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(value, list.get(0));
    }

    @Test
    void addWithNullValueThrowsException() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.add(null));
    }

    @Test
    void addSeveralIntegerElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        final List<Integer> elementsToAdd = getSampleListOfIntegerElements();
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
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        final List<Integer> elementsToAdd = getSampleListOfIntegerElements();
        elementsToAdd.forEach(list::add);
        List<Integer> resultElements = list.toList();
        Assertions.assertEquals(elementsToAdd.size(), resultElements.size(),
            "Size of elements is expected %d".formatted(elementsToAdd.size()));
        elementsToAdd.forEach(addedElement -> Assertions.assertTrue(resultElements.contains(addedElement),
            "The result elements should contain '%s'.".formatted(addedElement)));
        resultElements.forEach(resultElement -> Assertions.assertTrue(elementsToAdd.contains(resultElement),
            "The result elements contains unexpected '%s'.".formatted(resultElement)));
    }

    @Test
    void toListIsImmutable() {
        SortedLinkedList<Integer> sortedList = createSampleSortedIntegerList();
        List<Integer> listBeforeAdd = sortedList.toList();
        final int newElement = 999;
        listBeforeAdd.add(newElement);
        List<Integer> listAfterAdd = sortedList.toList();
        Assertions.assertFalse(listAfterAdd.contains(newElement), ("The original sorted list shouldn't contain element "
            + "'%s' added to the List produced by toList() method.").formatted(newElement));
    }

    @Test
    void addIntegerKeepsListSorted() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(3);
        assertListContent(List.of(1, 3), list);
        list.add(2);
        assertListContent(List.of(1, 2, 3), list);
        list.add(-1);
        assertListContent(List.of(-1, 1, 2, 3), list);
        list.add(Integer.MAX_VALUE);
        assertListContent(List.of(-1, 1, 2, 3, Integer.MAX_VALUE), list);
        list.add(2);
        assertListContent(List.of(-1, 1, 2, 2, 3, Integer.MAX_VALUE), list);
    }

    @Test
    void addStringKeepsListSorted() {
        SortedLinkedList<String> list = new SortedLinkedList<>();
        list.add("");
        list.add("a");
        assertListContent(List.of("", "a"), list);
        list.add("c");
        assertListContent(List.of("", "a", "c"), list);
        list.add("b");
        assertListContent(List.of("", "a", "b", "c"), list);
        list.add("some longer text");
        assertListContent(List.of("", "a", "b", "c", "some longer text"), list);
        list.add("  text prefixed with space");
        assertListContent(List.of("", "  text prefixed with space", "a", "b", "c", "some longer text"), list);
    }

    @Test
    void removeTheOnlyElement() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        assertListContent(List.of(1), list);
        Integer removed = list.remove(0);
        Assertions.assertEquals(1, removed);
        assertListContent(List.of(), list);
    }

    @Test
    void removeOneUniqueOfMultipleElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertListContent(List.of(1, 2, 3), list);
        Integer removed = list.remove(1);
        Assertions.assertEquals(2, removed);
        assertListContent(List.of(1, 3), list);
    }

    @Test
    void removeOneDuplicateOfMultipleElements() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(3);
        assertListContent(List.of(1, 2, 2, 2, 3), list);
        Integer removed = list.remove(2);
        Assertions.assertEquals(2, removed);
        assertListContent(List.of(1, 2, 2, 3), list);
    }

    private static <T extends Comparable<T>> void assertListContent(List<T> expectedElements, SortedLinkedList<T> list) {
        Assertions.assertEquals(expectedElements.size(), list.size(), "List size doesn't match.");
        for (int i = 0; i < list.size(); i++) {
            final T actualElement = list.get(i);
            final T expectedElement = expectedElements.get(i);
            Assertions.assertEquals(
                expectedElement,
                actualElement,
                "List element '%s' on position %d doesn't match the expected element '%s'."
                    .formatted(actualElement, i, expectedElement));
        }
    }

    private static SortedLinkedList<Integer> createSampleSortedIntegerList() {
        SortedLinkedList<Integer> sortedList = new SortedLinkedList<>();
        getSampleListOfIntegerElements().forEach(sortedList::add);
        return sortedList;
    }

    private static List<Integer> getSampleListOfIntegerElements() {
        return List.of(0, 1, 11, Integer.MAX_VALUE, -1, -111, Integer.MIN_VALUE);
    }
}
