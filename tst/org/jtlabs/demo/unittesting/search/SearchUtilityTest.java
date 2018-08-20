package org.jtlabs.demo.unittesting.search;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jssingla
 */
class SearchUtilityTest {

    @Test
    public void test() {
        List<Integer> integers = Arrays.asList(-1, 4, 10, 100);
        System.out.println(SearchUtility.findClosestToKey(integers, 91));
    }
}