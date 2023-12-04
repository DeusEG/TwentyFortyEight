package com.deus.board;

import com.deus.key.Key;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SquareBoard extends Board{

    int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<Integer> list) {
        int indexOfList = 0;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Key key = new Key(i, j);
                Integer value = list.get(indexOfList);
                indexOfList++;
                board.put(key, value);
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> keysWithNullValues = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Key key = getKey(i, j);
                Integer value = board.get(key);
                if (value == null) {
                    keysWithNullValues.add(getKey(i, j));
                }
            }
        }
        return keysWithNullValues;
    }

    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (Map.Entry<Key, Integer> entry : board.entrySet()) {
            Key key = new Key(i, j);
            if (entry.getKey().equals(key)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public List<Key> getRow(int i) {
        return null;
    }

    @Override
    public List<Key> getColumn(int j) {
        return null;
    }

    @Override
    public boolean hasValue(Integer value) {
        return false;
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        return null;
    }
}
