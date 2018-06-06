package org.jtlabs.demo.unittesting.search;

import java.util.List;

/**
 * This is a contract for search algorithm. Provides a contract to search an element in list of given elements and returns index of the
 * element in the list.
 *
 * @author jssingla
 */
public interface SearchAlgorithm<T extends Comparable<? super T>> {

    /**
     * Seeks the element in the list and if found returns the index of the element. If not found, it throws {@link
     * ElementNotFoundException}.
     *
     * @param element element to be searched.
     * @param elements all the elements in which this is to be searched.
     *
     * @return returns the index of the element.
     *
     * @throws ElementNotFoundException when element does not exist in the list.
     */
    int search(T element, List<T> elements) throws ElementNotFoundException;
}
