package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        for (int i = row, j = column; i < data.length; i++, j = 0) {
            if (data[i].length > 0 && j < data[i].length) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length) {
            return data[row][column++];
        } else if (data[row + 1].length == 0) {
            row++;
            return next();
        } else {
            column = 0;
            return data[++row][column++];
        }
    }
}