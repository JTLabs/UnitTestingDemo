package org.jtlabs.demo.unittesting.sorting;

import java.util.List;

/**
 * @author jssingla
 */
public interface SortAlgorithm<T extends Comparable<? super T>> {

    List<T> sort(List<T> numbersToSort);
}
