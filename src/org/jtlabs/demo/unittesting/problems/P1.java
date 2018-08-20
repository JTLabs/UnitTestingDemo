package org.jtlabs.demo.unittesting.problems;

import javafx.util.Pair;
import org.jtlabs.demo.unittesting.search.SearchUtility;
import org.jtlabs.demo.unittesting.sorting.SortAlgorithm;

import java.util.List;

/**
 * @author jssingla
 */
public class P1 {

    private final SortAlgorithm<Integer> sortAlgorithm;

    public P1(final SortAlgorithm<Integer> sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    public Pair<Integer, Integer> getTwoValuesWithSumAsTarget(List<Integer> values, Integer targetValue) {
        final List<Integer> sortedValues = sortAlgorithm.sort(values);

        Integer overallMinFound = Integer.MAX_VALUE;
        Pair<Integer, Integer> bestPair = null;

        for (int i = 0; i < sortedValues.size(); i++) {
            Integer currentValue = sortedValues.get(i);

            Integer valueRequired = targetValue - currentValue;
            Integer currentMatchFound = SearchUtility.findClosestToKey(sortedValues, valueRequired);
            Integer currentMinFound = Math.abs(currentMatchFound + currentValue - targetValue);

            // ignore current value, if there are no same values in the array.
            boolean repeatingValue = isThisRepeatingValue(sortedValues, i);
            boolean toConsiderThisValue = !currentValue.equals(currentMatchFound) || repeatingValue;

            if (toConsiderThisValue && currentMinFound < overallMinFound) {
                overallMinFound = currentMinFound;
                bestPair = new Pair<>(currentMatchFound, currentValue);
            }
        }
        return bestPair;
    }

    private boolean isThisRepeatingValue(final List<Integer> sortedValues, final int i) {
        return ((i > 0) && sortedValues.get(i - 1)
                                       .equals(sortedValues.get(i)) || (i + 1 < sortedValues.size()) && sortedValues.get(i + 1)
                                                                                                                    .equals(sortedValues
                                                                                                                                .get(
                                                                                                                        i)));
    }
}
