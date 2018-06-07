package org.jtlabs.demo.unittesting.math;

/**
 * @author jssingla
 */
public class DoubleAdder implements Adder<Double> {

    @Override
    public Double add(final Double one, final Double two) {
        if (one > Double.MAX_VALUE - two) {
            throw new IllegalArgumentException("Sum out of range...");
        }

        return one + two;
    }
}
