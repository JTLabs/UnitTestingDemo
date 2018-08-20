package org.jtlabs.demo.unittesting.problems;

import org.jtlabs.demo.unittesting.sorting.SortAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jssingla
 */
public class P2 {

    private final Random random;
    private final SortAlgorithm sortAlgorithm;

    public P2(final Random random, final SortAlgorithm sortAlgorithm) {
        this.random = random;
        this.sortAlgorithm = sortAlgorithm;
    }

    public int solve(List<Integer> values) {
        List<Integer> sortedValues = sortAlgorithm.sort(values);

        int rightShifts = random.nextInt(values.size());

        List<Integer> rotatedValues = rotate(sortedValues, rightShifts);

        return findRotations(rotatedValues, 0, sortedValues.size() - 1);
    }

    private List<Integer> rotate(List<Integer> values, int shifts) {
        if (values.size() == 0) {
            return values;
        }

        ArrayList<Integer> integerValues = new ArrayList<>(values);

        // take a modulo to avoid unnecessary shifts.
        shifts %= integerValues.size();

        for (int i = 0; i < shifts; i++) {
            // remove last element, add it to front of the ArrayList
            Integer value = integerValues.remove(values.size() - 1);
            integerValues.add(0, value);
        }

        return integerValues;
    }

    private int findRotations(final List<Integer> rotatedValues, final int st, final int en) {
        if (st == en) {
            return st; // as many rotations as index of this min element.
        }

        if (en - st == 1) {
            // only two values
            return rotatedValues.get(st) < rotatedValues.get(en) ? st : en;
        }

        int mid = (st + en) / 2;

        // if mid or mid + 1 are smallest.
        if (rotatedValues.get(mid) < rotatedValues.get(mid - 1)) {
            return mid;
        }

        if (rotatedValues.get(mid + 1) < rotatedValues.get(mid)) {
            return mid + 1;
        }

        // otherwise, find which half
        if (rotatedValues.get(mid) > rotatedValues.get(en)) {
            // go for right half
            return findRotations(rotatedValues, mid + 1, en);
        } else {
            // go for left half
            return findRotations(rotatedValues, st, mid - 1);
        }

    }
}
