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
     * @throws IllegalArgumentException when the element being added is {@code null}
     */
    public void add(final Integer newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("Element being added to the list must be NOT null.");
        }
        list.add(newElement);
    }

    /**
     * @param index of the element in the list to be returned (starting by 0)
     * @return element on the specified position (index) in the list
     */
    public Integer get(final int index) {
        return list.get(index);
    }

    /**
     * @return new List instance containing all elements
     */
    public List<Integer> toList() {
        return new LinkedList<>(list);
    }
}
