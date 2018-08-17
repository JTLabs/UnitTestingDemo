package org.jtlabs.demo.unittesting.prepare;

import org.junit.jupiter.api.Test;

/**
 * @author jssingla
 */
class LruCacheTest {

    @Test
    public void test() {
        final LruCache cache = new LruCache(10);

        cache.set(5, 10);
        cache.set(6, 12);
        cache.set(1, 2);
        cache.set(12, 24);
        cache.set(7, 14);
        cache.set(4, 8);
        cache.set(17, 34);

        System.out.println(cache.get(5));
        System.out.println(cache.get(6));

        cache.set(14, 28);
        cache.set(15, 30);
        cache.set(16, 32);
        cache.set(27, 54);
        cache.set(0, 0);

        System.out.println(cache.get(7));
        System.out.println(cache.get(4));
    }
}