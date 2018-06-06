package org.jtlabs.demo.unittesting.math;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class IntAdder implements Adder<Integer> {

    @Override
    public Integer add(final Integer... numbers) {
        return Arrays.stream(numbers)
                     .reduce(0, Integer::sum);
    }
}
