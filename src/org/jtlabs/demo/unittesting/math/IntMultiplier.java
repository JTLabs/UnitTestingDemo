package org.jtlabs.demo.unittesting.math;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class IntMultiplier implements Multiplier<Integer> {

    @Override
    public Integer multiply(final Integer... numbers) {
        return Arrays.stream(numbers)
                     .reduce(1, (a, b) -> a * b);
    }
}
