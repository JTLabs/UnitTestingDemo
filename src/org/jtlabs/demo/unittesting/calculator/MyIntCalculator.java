package org.jtlabs.demo.unittesting.calculator;

import org.jtlabs.demo.unittesting.math.IntAdder;
import org.jtlabs.demo.unittesting.math.IntMultiplier;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class MyIntCalculator implements MyCalculator<Integer> {

    private final IntAdder adder;
    private final IntMultiplier multiplier;

    public MyIntCalculator(final IntAdder adder, final IntMultiplier multiplier) {
        this.adder = adder;
        this.multiplier = multiplier;
    }

    @Override
    public Integer add(final Integer... numbers) {
        return Arrays.stream(numbers)
                     .reduce(0, adder::add);
    }

    @Override
    public Integer multiply(final Integer... numbers) {
        return Arrays.stream(numbers)
                     .reduce(1, multiplier::multiply);
    }

    @Override
    public Integer subtract(final Integer a, final Integer b) {
        return adder.add(a, -b);
    }
}
