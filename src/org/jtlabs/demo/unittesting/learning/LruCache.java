package org.jtlabs.demo.unittesting.learning;

import java.util.HashMap;

public class LruCache {

    private final Integer capacity;
    private final LinkedList linkedList;
    private final HashMap<Integer, CachedNode> mappedCachedNodes;

    public LruCache(int capacity) {
        this.capacity = capacity;
        linkedList = new LinkedList();
        mappedCachedNodes = new HashMap<>();
    }

    public int get(int key) {
        final CachedNode cachedNode = mappedCachedNodes.get(key);

        if (cachedNode == null) {
            return -1;
        }

        // update order in linked list
        linkedList.remove(cachedNode.node);
        linkedList.addFront(cachedNode.node);

        return cachedNode.value;
    }

    public void set(int key, int value) {
        final CachedNode node = mappedCachedNodes.get(key);
        if (node != null) {
            // given value already exists
            mappedCachedNodes.remove(key);
            linkedList.remove(node.node);
        } else if (mappedCachedNodes.size() == capacity) {
            // cache is full, get least recently used node from LL
            final LinkedListNode lastNode = linkedList.getLast();
            mappedCachedNodes.remove(lastNode.key);
            linkedList.remove(lastNode);
        }

        final LinkedListNode newNode = new LinkedListNode(key);
        mappedCachedNodes.put(key, new CachedNode(newNode, value));

        linkedList.addFront(newNode);
    }

    private class CachedNode {

        private final LinkedListNode node;
        private final int value;

        private CachedNode(final LinkedListNode node, final int value) {
            this.node = node;
            this.value = value;
        }
    }

    private class LinkedListNode {

        private int key;
        private LinkedListNode prev;
        private LinkedListNode next;

        LinkedListNode(int key) {
            this.prev = null;
            this.next = null;
            this.key = key;
        }
    }

    private class LinkedList {

        private LinkedListNode head;

        LinkedList() {
            head = new LinkedListNode(1); // create a dummy head node
            head.prev = new LinkedListNode(-1); // create a dummy tail node
            head.prev.next = head;
        }

        private void addFront(final LinkedListNode node) {
            final LinkedListNode first = head.next;
            if (first == null) {
                // empty linked list
                head.next = node;
                node.prev = head;
                // add tail as next to node.
                node.next = head.prev;
                head.prev.prev = node;
            } else {
                head.next = node;
                node.next = first;
                first.prev = node;
                node.prev = head;
            }
        }

        private void remove(final LinkedListNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

        private LinkedListNode getLast() {
            return head.prev.prev;
        }
    }
}
