package org.jtlabs.demo.unittesting.sorting.application.rhyme;

import org.jtlabs.demo.unittesting.sorting.MergeSort;
import org.jtlabs.demo.unittesting.sorting.SortAlgorithm;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sorts the given sequence of words such that all rhyming words are adjacent to each other.
 *
 * @author jssingla
 */
public class Rhymer {

    private final SortAlgorithm<RhymeWord> algorithm;

    public Rhymer() {
        algorithm = new MergeSort<>(true);
    }

    public List<String> rhyme(List<String> words) {
        List<RhymeWord> wordsToRhyme = words.stream()
                                            .map(RhymeWord::new)
                                            .collect(Collectors.toList());
        return algorithm.sort(wordsToRhyme)
                        .stream()
                        .map(RhymeWord::toString)
                        .collect(Collectors.toList());
    }
}
