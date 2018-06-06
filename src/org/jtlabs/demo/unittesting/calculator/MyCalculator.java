package org.jtlabs.demo.unittesting.calculator;

/**
 * @author jssingla
 */
public interface MyCalculator<T> {

    T add(T... numbers);

    T multiply(T... numbers);

    T subtract(T a, T b);
}
