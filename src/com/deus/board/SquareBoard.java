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
    public Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> rowOfKeys = new ArrayList<>();
        for (Map.Entry<Key, Integer> entry : board.entrySet()) {
            if (entry.getKey().getI() == i) {
                rowOfKeys.add(entry.getKey());
            }
        }
        return rowOfKeys;
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> rowOfKeys = new ArrayList<>();
        for (Map.Entry<Key, Integer> entry : board.entrySet()) {
            if (entry.getKey().getJ() == j) {
                rowOfKeys.add(entry.getKey());
            }
        }
        return rowOfKeys;
    }

    @Override
    public boolean hasValue(Integer value) {
        for (Map.Entry<Key, Integer> entry : board.entrySet()) {
            if (entry.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Integer> getValues(List<Key> keys) {
        List<Integer> valuesList = new ArrayList<>();
        for (Key key: keys) {
            for (Map.Entry<Key, Integer> entry : board.entrySet()) {
                if (entry.getKey() == key) {
                    valuesList.add(entry.getValue());
                }
            }
        }
        return valuesList;
    }
}
