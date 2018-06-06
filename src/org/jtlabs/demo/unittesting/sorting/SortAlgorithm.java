package org.jtlabs.demo.unittesting.sorting;

import java.util.List;

/**
 * Provides a contract for sorting the given sequence.
 *
 * @author jssingla
 */
public interface SortAlgorithm<T extends Comparable<? super T>> {

    /**
     * Sorts the sequence and returns. Ascending or descending sorting choice is left to implementation.
     *
     * @param numbersToSort sequence to be sorted.
     *
     * @return returns sorted sequence.
     */
    List<T> sort(List<T> numbersToSort);
}
