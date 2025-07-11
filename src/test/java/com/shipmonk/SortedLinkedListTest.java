package com.shipmonk;

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
}
