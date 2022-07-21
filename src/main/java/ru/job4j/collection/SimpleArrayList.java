package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T old = get(index);
        container[index] = newValue;
        return old;
    }

    @Override
    public T remove(int index) {
        T old = get(index);
        System.arraycopy(container, index + 1, container, index, size - 1 - index);
        container[size - 1] = null;
        size--;
        modCount++;
        return old;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int cursor = 0;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size() > cursor;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T next = container[cursor];
                    cursor++;
                    return next;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    private void grow() {
        container = container.length == 0 ? (T[]) new Object[10] : Arrays.copyOf(container, container.length  * 2);
    }
}