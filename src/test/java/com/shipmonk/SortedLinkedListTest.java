package com.shipmonk;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SortedLinkedListTest {

    @Test
    void constructorCreatesInstance() {
        var list = new SortedLinkedList();
        Assertions.assertNotNull(list);
        Assertions.assertInstanceOf(SortedLinkedList.class, list);
    }
}
