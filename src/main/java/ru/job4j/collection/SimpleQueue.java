package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        T elem = null;
        if (!out.isEmpty()) {
            elem = out.pop();
            size--;
        }
        return elem;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}