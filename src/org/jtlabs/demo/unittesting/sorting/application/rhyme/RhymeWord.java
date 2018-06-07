package org.jtlabs.demo.unittesting.sorting.application.rhyme;

/**
 * @author jssingla
 */
public class RhymeWord implements Comparable<RhymeWord> {

    private final String value;

    public RhymeWord(final String value) {
        this.value = value;
    }

    @Override
    public int compareTo(final RhymeWord o) {
        // reverse the words as nice rhymes with dice, and ecin is close to ecid as per ..
        final String oReverse = reverse(o.value);
        final String thisReverse = reverse(this.value);

        return thisReverse.compareTo(oReverse);
    }

    @Override
    public String toString() {
        return value;
    }

    private String reverse(String str) {
        char ch[] = str.toCharArray();
        final StringBuilder rev = new StringBuilder();

        for (int i = ch.length - 1; i >= 0; i--) {
            rev.append(ch[i]);
        }

        return rev.toString();
    }
}
