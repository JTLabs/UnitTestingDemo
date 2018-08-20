package org.jtlabs.demo.unittesting.problems;

import org.jtlabs.demo.unittesting.sorting.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author jssingla
 */
class P2Test {

    private P2 p2 = new P2(new RandomStub(), new MergeSort(true));

    @Test
    public void test() {
        System.out.println(p2.solve(Arrays.asList(6, 7, 2, 3, -3, 834, 542, 43, 64)));
    }

    private class RandomStub extends Random {

        @Override
        public int nextInt(int bounds) {
            return 7;
        }
    }
}