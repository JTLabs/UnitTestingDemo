package org.jtlabs.demo.unittesting.math;

/**
 * @author jssingla
 */
public class IntMultiplier implements Multiplier<Integer> {

    @Override
    public Integer multiply(final Integer one, final Integer two) {
        if (one > Integer.MAX_VALUE / two) {
            throw new IllegalArgumentException("Result out of range...");
        }

        return one * two;
    }
}
