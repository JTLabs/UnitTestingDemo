package org.jtlabs.demo.unittesting.math;

/**
 * @author jssingla
 */
public class DoubleMultiplier implements Multiplier<Double> {

    @Override
    public Double multiply(final Double one, final Double two) {

        if (one > Double.MAX_VALUE / two) {
            throw new IllegalArgumentException("Result out of range");
        }

        return one * two;
    }
}
