package ru.job4j.collection;

import ru.job4j.generics.Node;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    private int modCount;

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> elem = first;
        E rsl;
        for (int i = 0; i < index; i++) {
            elem = elem.next;
        }
        return elem.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int cursor = 0;

            private SimpleLinkedList.Node<E> elem;

            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > cursor;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E next;
                if (cursor == 0) {
                    next = first.item;
                    elem = first.next;
                } else if (cursor == size - 1) {
                    next = last.item;
                } else {
                    next = elem.item;
                    elem = elem.next;
                }
                cursor++;
                return next;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}