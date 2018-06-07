package org.jtlabs.demo.unittesting.calculator;

import org.jtlabs.demo.unittesting.math.DoubleAdder;
import org.jtlabs.demo.unittesting.math.DoubleMultiplier;

import java.util.Arrays;

/**
 * @author jssingla
 */
public class MyDoubleCalculator implements MyCalculator<Double> {

    private final DoubleAdder adder;
    private final DoubleMultiplier multiplier;

    public MyDoubleCalculator(final DoubleAdder adder, final DoubleMultiplier multiplier) {
        this.adder = adder;
        this.multiplier = multiplier;
    }

    @Override
    public Double add(final Double... numbers) {
        return Arrays.stream(numbers)
                     .reduce(0.0, adder::add);
    }

    @Override
    public Double multiply(final Double... numbers) {
        return Arrays.stream(numbers)
                     .reduce(1.0, multiplier::multiply);
    }

    @Override
    public Double subtract(final Double a, final Double b) {
        return adder.add(a, -b);
    }
}
