package org.jtlabs.demo.unittesting.search;

import java.util.List;

/**
 * @author jssingla
 */
public class LinearSearch<T extends Comparable<? super T>> implements SearchAlgorithm<T> {

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
    @Override
    public int search(final T element, final List<T> elements) throws ElementNotFoundException {
        if (elements == null) {
            throw new IllegalArgumentException("Elements can't be null.");
        }

        for (int idx = 0; idx < elements.size(); idx++) {
            if (areElementsSame(element, elements.get(idx))) {
                return idx;
            }
        }

        throw new ElementNotFoundException("Given element - " + element + " doesn't exist in given sequence - " + elements);
    }

    private boolean areElementsSame(final T one, final T two) {
        return one.compareTo(two) == 0;
    }
}
