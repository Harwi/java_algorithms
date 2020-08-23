package leetcode.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

    final LRUMap<Integer, Integer> lruMap;

    public LRUCache(int capacity) {
        lruMap = new LRUMap(capacity);
    }

    public int get(int key) {
        return lruMap.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        lruMap.put(key, value);
    }

    public static class LRUMap<K, V> extends LinkedHashMap<K, V> {

        private final int capacity;

        public LRUMap(int initialCapacity) {
            super(initialCapacity, 1f, true);
            this.capacity = initialCapacity;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > this.capacity;
        }
    }
}