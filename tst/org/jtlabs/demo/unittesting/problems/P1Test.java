package org.jtlabs.demo.unittesting.problems;

import org.jtlabs.demo.unittesting.sorting.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jssingla
 */
class P1Test {

    @Test
    public void test1() {
        P1 p1 = new P1(new MergeSort<>(true));

        List<Integer> values = Arrays.asList(-3, -5, 10, 2, -15);

        System.out.println(p1.getTwoValuesWithSumAsTarget(values, 0));
    }

    @Test
    public void test_null() {
        P1 p1 = new P1(new MergeSort<>(true));

        List<Integer> values = Arrays.asList(-3);

        System.out.println(p1.getTwoValuesWithSumAsTarget(values, 0));
    }

    @Test
    public void test_2() {
        P1 p1 = new P1(new MergeSort<>(true));

        List<Integer> values = Arrays.asList(-3, -5, 10, 2, -15);

        System.out.println(p1.getTwoValuesWithSumAsTarget(values, 9));
    }

    @Test
    public void test_3() {
        P1 p1 = new P1(new MergeSort<>(true));

        List<Integer> values = Arrays.asList(Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE + 4);

        System.out.println(p1.getTwoValuesWithSumAsTarget(values, 4));
    }

    @Test
    public void test_4() {
        P1 p1 = new P1(new MergeSort<>(true));

        List<Integer> values = Arrays.asList(3, 5, 5);

        System.out.println(p1.getTwoValuesWithSumAsTarget(values, 6));
    }

}