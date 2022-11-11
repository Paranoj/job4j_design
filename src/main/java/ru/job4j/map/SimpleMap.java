package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int bucket(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (((float) count / capacity) >= LOAD_FACTOR) {
            expand();
        }
        if (table[bucket(key)] == null) {
            table[bucket(key)] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity *= 2;
            MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                newTable[bucket(map.key)] = map;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int bucketIndex = bucket(key);
        if (table[bucket(key)] != null && hash(bucket(table[bucketIndex].key))
                == hash(bucket(key)) && Objects.equals(table[bucketIndex].key, key)) {
            rsl = table[bucketIndex].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (get(key) != null) {
            table[bucket(key)] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
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
