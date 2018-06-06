package org.jtlabs.demo.unittesting.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests {@link MergeSort}
 *
 * @author jssingla
 */
class MergeSortTest {// prepare

    @Test
    void descendingSort_positiveIntegers() {
        // prepare
        final SortAlgorithm<Integer> sorter = new MergeSort<>(false);

        // act
        final List<Integer> sortedList = sorter.sort(Arrays.asList(10, 18, 3, 1, 90));

        //assert
        assertEquals(sortedList, Arrays.asList(90, 18, 10, 3, 1));
    }

    @Test
    void descendingSort_negativeIntegers() {
        // prepare
        final SortAlgorithm<Integer> sorter = new MergeSort<>(false);

        // act
        final List<Integer> sortedList = sorter.sort(Arrays.asList(-10, -18, -3, -1, -90));

        //assert
        assertEquals(sortedList, Arrays.asList(-1, -3, -10, -18, -90));//assert
    }

    @Test
    void ascendingSort_positiveDoubles() {
        // prepare
        final SortAlgorithm<Double> sorter = new MergeSort<>(true);

        // act
        final List<Double> sortedList = sorter.sort(Arrays.asList(1.1, 1.111, 0.3, 0.0001, 0.90));

        // assert
        assertEquals(sortedList, Arrays.asList(0.0001, 0.3, 0.90, 1.1, 1.111));
    }

    @Test
    void ascendingSort_negativeDoubles() {
        // prepare
        final SortAlgorithm<Double> sorter = new MergeSort<>(true);

        // act
        final List<Double> sortedList = sorter.sort(Arrays.asList(-1.1, -1.111, -0.3, -0.0001, -0.90));

        //assert
        assertEquals(sortedList, Arrays.asList(-1.111, -1.1, -0.90, -0.3, -0.0001));
    }

    @Test
    void descendingSort_positiveDoubles() {
        // prepare
        final SortAlgorithm<Double> sorter = new MergeSort<>(false);

        // act
        final List<Double> sortedList = sorter.sort(Arrays.asList(1.1, 1.111, 0.3, 0.0001, 0.90));

        //assert
        assertEquals(sortedList, Arrays.asList(1.111, 1.1, 0.90, 0.3, 0.0001));
    }

    @Test
    void descendingSort_negativeDoubles() {
        // prepare
        final SortAlgorithm<Double> sorter = new MergeSort<>(false);

        // act
        final List<Double> sortedList = sorter.sort(Arrays.asList(-1.1, -1.111, -0.3, -0.0001, -0.90));

        //assert
        assertEquals(sortedList, Arrays.asList(-0.0001, -0.3, -0.90, -1.1, -1.111));
    }

    @Test
    void ascendingSort_positiveIntegers() {
        // prepare
        final SortAlgorithm<Integer> sorter = new MergeSort<>(true);

        // act
        final List<Integer> sortedList = sorter.sort(Arrays.asList(10, 18, 3, 1, 90));

        //assert
        assertEquals(sortedList, Arrays.asList(1, 3, 10, 18, 90));//assert
    }

    @Test
    void ascendingSort_negativeIntegers() {
        // prepare
        final SortAlgorithm<Integer> sorter = new MergeSort<>(true);

        // act
        final List<Integer> sortedList = sorter.sort(Arrays.asList(-10, -18, -3, -1, -90));

        //assert
        assertEquals(sortedList, Arrays.asList(-90, -18, -10, -3, -1));
    }
}