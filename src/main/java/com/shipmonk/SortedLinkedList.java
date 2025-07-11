package com.shipmonk;

import java.util.LinkedList;
import java.util.List;

/**
 * Linked list implementation which keeps the elements always sorted.
 * <p>
 * The elements must implement the {@link Comparable} interface because the sorting is achieved by that.
 * @param <E> the type of objects that this list may contain
 */
public class SortedLinkedList<E extends Comparable<E>> {

    private final List<E> list = new LinkedList<>();

    /**
     * @return number of elements in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds a new element to the list while keeping it sorted.
     * <p>
     * The order is decided by the standard (ascending) comparison using {@link Comparable#compareTo(Object)},
     * so for instance adding '2' to the existing list of { '1', '3'} results in list {'1', '2', '3'}.
     *
     * @param newElement element which is to be added to the list
     * @throws IllegalArgumentException when the element being added is {@code null}
     */
    public void add(final E newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("Element being added to the list must be NOT null.");
        }
        int index = 0;
        while (index < list.size()) {
            if (newElement.compareTo(list.get(index)) < 0) {
                break;
            }
            index++;
        }
        list.add(index, newElement);
    }

    /**
     * @param index of the element in the list to be returned (starting by 0)
     * @return element on the specified position (index) in the list
     */
    public E get(final int index) {
        return list.get(index);
    }

    /**
     * @return new List instance containing all elements
     */
    public List<E> toList() {
        return new LinkedList<>(list);
    }
}
