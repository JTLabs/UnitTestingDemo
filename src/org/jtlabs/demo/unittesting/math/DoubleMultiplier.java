package org.jtlabs.demo.unittesting.math;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class DoubleMultiplier implements Multiplier<Double> {

    @Override
    public Double multiply(final Double... numbers) {
        return Arrays.stream(numbers)
                     .reduce(1.0, (a, b) -> a * b);
    }
}
