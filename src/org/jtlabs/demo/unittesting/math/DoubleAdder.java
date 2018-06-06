package org.jtlabs.demo.unittesting.math;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class DoubleAdder implements Adder<Double> {

    @Override
    public Double add(final Double... numbers) {
        return Arrays.stream(numbers)
                     .reduce(0.0, Double::sum);
    }
}
