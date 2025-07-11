package com.shipmonk;

import java.util.LinkedList;
import java.util.List;

/**
 * Linked list implementation which keeps the elements always sorted.
 */
public class SortedLinkedList {

    private final List<Integer> list = new LinkedList<>();

    /**
     * @return number of elements in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * @param newElement element which is to be added to the list
     */
    public void add(final int newElement) {
        list.add(newElement);
    }

    /**
     * @param index of the element in the list to be returned (starting by 0)
     * @return element on the specified position (index) in the list
     */
    public int get(final int index) {
        return list.get(index);
    }
}
