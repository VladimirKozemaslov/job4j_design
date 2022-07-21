package ru.job4j.collection;

import ru.job4j.generics.Node;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E>[] container;

    private Node<E> first;

    private Node<E> last;

    private int size;

    private int modCount;

    public SimpleLinkedList() {
        this.container = (Node<E>[]) new Object[10];
    }

    public SimpleLinkedList(int capacity) {
        this.container = (Node<E>[]) new Object[capacity];
    }

    @Override
    public void add(E value) {
        if (size == container.length) {
            grow();
        }
        if (size == 0) {
            container[size] = new Node<E>(null, value, null);
            first = container[size];
        } else {
            container[size] = new Node<E>(container[size - 1], value, null);
            container[size - 1].next = container[size];
        }
        last = container[size];
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index].item;
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
                if (hasNext()) {
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
                } else {
                    throw new NoSuchElementException();
                }
            }

        };
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length  * 2);
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