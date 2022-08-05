package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = true;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int idx = getIndex(key);
        if (table[idx] == null) {
            table[idx] = new MapEntry<>(key, value);
            count++;
            modCount++;
        } else {
            rsl = false;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private int getIndex(K key) {
        int hashCode = key == null ? 0 : key.hashCode();
        return indexFor(hash(hashCode));
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity << 1];
        capacity = capacity << 1;
        for (MapEntry<K, V> entry: oldTable) {
            if (entry != null) {
                table[getIndex(entry.key)] = entry;
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        int idx = getIndex(key);
        MapEntry<K, V> entry = table[idx];
        if (entry != null
                && ((key != null && entry.key.hashCode() == key.hashCode() && entry.key.equals(key))
                || (key == null && entry.key == null))
        ) {
            value = entry.value;
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int idx = getIndex(key);
        MapEntry<K, V> entry = table[idx];
        if (entry != null
                && ((key != null && entry.key.hashCode() == key.hashCode() && entry.key.equals(key))
                || (key == null && entry.key == null))
        ) {
            table[idx] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;

            private int count = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size() > count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                K key = null;
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                        key = table[i].key;
                        cursor = i + 1;
                        count++;
                        break;
                    }
                }
                return key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}