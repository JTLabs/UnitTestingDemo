package org.jtlabs.demo.unittesting.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jssingla
 */
public class MergeSort<T extends Comparable<? super T>> implements SortAlgorithm<T> {

    private final boolean ascendingOrder;

    public MergeSort(final boolean ascendingOrder) {
        this.ascendingOrder = ascendingOrder;
    }

    @Override
    public List<T> sort(final List<T> numbersToSort) {
        if (numbersToSort.size() < 2) {
            return numbersToSort;
        }

        // divide into two lists
        final int mid = numbersToSort.size() / 2;
        final List<T> left = new ArrayList<>(numbersToSort.subList(0, mid));

        final List<T> right = new ArrayList<>(numbersToSort.subList(mid, numbersToSort.size()));

        sort(left);
        sort(right);
        merge(left, right, numbersToSort);
        return numbersToSort;
    }

    private void merge(final List<T> left, final List<T> right, List<T> listToSort) {
        int leftIndex = 0;
        int rightIndex = 0;
        int listIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            // if left element is smaller.
            if (ascendingOrder
                == left.get(leftIndex)
                       .compareTo(right.get(rightIndex)) < 0) {
                listToSort.set(listIndex++, left.get(leftIndex++));
            } else {
                listToSort.set(listIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            listToSort.set(listIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            listToSort.set(listIndex++, right.get(rightIndex++));
        }
    }
}
