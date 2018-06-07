package org.jtlabs.demo.unittesting.math;

/**
 * @author jssingla
 */
public class IntAdder implements Adder<Integer> {

    @Override
    public Integer add(final Integer one, final Integer two) {
        if (one > Integer.MAX_VALUE - two) {
            throw new IllegalArgumentException("Sum out of range...");
        }

        return one + two;
    }
}
