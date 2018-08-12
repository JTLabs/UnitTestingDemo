package org.jtlabs.demo.unittesting.prepare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TrieMain {

    public static void main(final String[] args) {
        final Trie trie = new Trie();
        trie.addWord("jatin");
        trie.addWord("jack");
        trie.addWord("jatun");
        trie.addWord("john");
        trie.addWord("area");
        trie.addWord("arrear");
        trie.addWord("ariel");
        trie.addWord("awesome");

        System.out.println(trie.autoSuggest("ar"));
        // [area, ariel, arrear]
    }

    public static class TrieNode {

        private final Character value;
        private final TrieNode[] children = new TrieNode[26];
        private Integer count;

        TrieNode(final Character value) {
            this.value = value;
            this.count = 1;
        }

        public void addChild(final Character child) {
            final int childIndex = child - 'a';
            if (this.children[childIndex] == null) {
                this.children[childIndex] = new TrieNode(child);
            } else {
                this.children[childIndex].count++;
            }
        }
    }

    public static class Trie {

        private final TrieNode root = new TrieNode(null);

        void addWord(final String word) {
            TrieNode prevNode = root;
            final String word_ = word.toLowerCase();

            for (int i = 0; i < word_.length(); i++) {
                prevNode.addChild(word_.charAt(i));
                prevNode = prevNode.children[word_.charAt(i) - 'a'];
            }
        }

        List<String> getSuffixesBelowNode(final TrieNode node) {
            if (node == null) {
                return new ArrayList<>();
            }

            final List<String> suffixes = new ArrayList<>();
            for (int i = 0; i < node.children.length; i++) {
                final TrieNode childNode = node.children[i];
                if (childNode != null) {
                    final List<String> childSuffixes = getSuffixesBelowNode(childNode);

                    if (childSuffixes.size() == 0) {
                        // leaf node
                        suffixes.add(childNode.value.toString());
                    }

                    suffixes.addAll(childSuffixes.stream()
                                                 .map(suffix -> childNode.value + suffix)
                                                 .collect(Collectors.toList()));
                }
            }

            return suffixes;
        }

        List<String> autoSuggest(final String partialString) {
            TrieNode curNode = root;

            final String partialString_ = partialString.toLowerCase();
            for (int i = 0; i < partialString_.length(); i++) {
                curNode = curNode.children[partialString_.charAt(i) - 'a'];
            }

            return getSuffixesBelowNode(curNode).stream()
                                                .map(suffix -> partialString + suffix)
                                                .collect(Collectors.toList());
        }
    }
}
