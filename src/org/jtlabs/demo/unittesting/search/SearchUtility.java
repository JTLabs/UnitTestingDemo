package org.jtlabs.demo.unittesting.search;

import java.util.List;

import static java.lang.Math.abs;

/**
 * @author jssingla
 */
public class SearchUtility {

    /**
     * Finds and returns the closest value to key in sorted values. O(LogN)
     * If values are not sorted, then result is undefined.
     *
     * @param values values in which the key is to be searched.
     * @param key key to be searched
     *
     * @return a value from given values such that (value ~ key) is minimum.
     */
    public static Integer findClosestToKey(final List<Integer> values, Integer key) {
        return b_s(values, 0, values.size() - 1, key);
    }

    private static Integer b_s(final List<Integer> values, final int searchStartRange, final int searchEndRange, final Integer key) {
        if (key <= values.get(searchStartRange)) {
            return values.get(searchStartRange);
        }

        if (key >= values.get(searchEndRange)) {
            return values.get(searchEndRange);
        }

        int mid = (searchStartRange + searchEndRange) / 2;

        if (key > values.get(mid) && key < values.get(mid + 1)) {
            return (abs(values.get(mid) - key) < abs(values.get(mid + 1) - key)) ? values.get(mid) : values.get(mid + 1);
        }

        if (key <= values.get(mid)) {
            return b_s(values, searchStartRange, mid, key);
        } else {
            return b_s(values, mid + 1, searchEndRange, key);
        }
    }
}
