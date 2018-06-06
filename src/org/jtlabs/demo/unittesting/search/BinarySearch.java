package org.jtlabs.demo.unittesting.search;

import java.util.List;

/**
 * @author jssingla
 */
public class BinarySearch<T extends Comparable<? super T>> implements SearchAlgorithm<T> {

    /**
     * This expects input sequence to be sorted in ascending order. Seeks the element in the list and if found returns the index of the
     * element. If not found, it throws {@link
     * ElementNotFoundException}.
     *
     * @param element element to be searched.
     * @param sortedElements all the elements in which this is to be searched.
     *
     * @return returns the index of the element.
     *
     * @throws ElementNotFoundException when element does not exist in the list.
     */
    @Override
    public int search(final T element, final List<T> sortedElements) throws ElementNotFoundException {
        return binarySearch(element, sortedElements, 0, sortedElements.size() - 1);
    }

    private int binarySearch(final T element, final List<T> sortedElements, int searchRangeStart, int searchRangeEnd) {
        if (searchRangeEnd < searchRangeStart) {
            throw new ElementNotFoundException("Given element - " + element + " doesn't exist in given sequence.");
        }

        if (searchRangeEnd == searchRangeStart) {
            if (sortedElements.get(searchRangeEnd)
                              .compareTo(element) == 0) {
                return searchRangeEnd;
            }

            throw new ElementNotFoundException("Given element - " + element + " doesn't exist in given sequence.");
        }

        final int rangeMid = (searchRangeStart + searchRangeEnd) / 2;

        if (element.compareTo(sortedElements.get(rangeMid)) == 0) {
            // element found
            return rangeMid;
        }

        // element is less than mid, search in left half
        if (element.compareTo(sortedElements.get(rangeMid)) < 0) {
            return binarySearch(element, sortedElements, searchRangeStart, rangeMid - 1);
        } else {
            return binarySearch(element, sortedElements, rangeMid + 1, searchRangeEnd);
        }
    }
}
