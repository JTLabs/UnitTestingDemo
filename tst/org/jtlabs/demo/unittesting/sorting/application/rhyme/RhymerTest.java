package org.jtlabs.demo.unittesting.sorting.application.rhyme;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests {@link Rhymer}
 *
 * @author jssingla
 */
class RhymerTest {

    @Test
    void test() {
        final Rhymer rhymer = new Rhymer();

        List<String> rhymedWords = rhymer.rhyme(Arrays.asList("nice",
                                                              "ace",
                                                              "craw",
                                                              "ice",
                                                              "blah",
                                                              "dumbfound",
                                                              "dice",
                                                              "grace",
                                                              "aah",
                                                              "spice",
                                                              "profound",
                                                              "splice",
                                                              "astound"));

        // get indexes for all "*ound" words
        final List<Integer> oundIndices = getIndicesOfAllWordsEndingIn("ound", rhymedWords);
        Collections.sort(oundIndices);

        assertEquals(3, oundIndices.size());
        assertEquals(2, oundIndices.get(2) - oundIndices.get(0));

        // get indexes for all "*ice" words
        final List<Integer> iceIndices = getIndicesOfAllWordsEndingIn("ice", rhymedWords);
        Collections.sort(iceIndices);

        assertEquals(5, iceIndices.size());
        assertEquals(4, iceIndices.get(4) - iceIndices.get(0));

        // get indexes for all "*ace" words
        final List<Integer> aceIndices = getIndicesOfAllWordsEndingIn("ace", rhymedWords);
        Collections.sort(aceIndices);

        assertEquals(2, aceIndices.size());
        assertEquals(1, aceIndices.get(1) - aceIndices.get(0));
    }

    private List<Integer> getIndicesOfAllWordsEndingIn(String suffix, List<String> words) {
        final List<Integer> indices = new ArrayList<>();

        for (int idx = 0; idx < words.size(); idx++) {
            if (words.get(idx)
                     .endsWith(suffix)) {
                indices.add(idx);
            }
        }
        return indices;
    }

}