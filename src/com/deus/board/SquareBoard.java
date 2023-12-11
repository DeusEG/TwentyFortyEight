package com.deus.board;

import com.deus.key.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SquareBoard<V> extends Board<Key, V> {

    private int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<V> list) {
        if (list.size() > getWidth() * getHeight()){
            throw new RuntimeException(String.format("Board size is smaller than incoming list"));
        }
        board.clear();
        int indexOfList = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Key key = new Key(i, j);
                var value = list.get(indexOfList);
                indexOfList++;
                addItem(key, value);
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> keysWithNullValues = new ArrayList<>();
        for (Map.Entry<Key, V> entry : board.entrySet()) {
            if (entry.getValue() == null) {
                keysWithNullValues.add(entry.getKey());
            }
        }
        return keysWithNullValues;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (Map.Entry<Key, V> entry : board.entrySet()) {
            Key key = new Key(i, j);
            if (entry.getKey().equals(key)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> rowKey = new ArrayList<>();
        for (Map.Entry<Key, V> entry : board.entrySet()) {
            if (entry.getKey().getI() == i) {
                rowKey.add(entry.getKey());
            }
        }
        return rowKey;
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> columnKey = new ArrayList<>();
        for (Map.Entry<Key, V> entry : board.entrySet()) {
            if (entry.getKey().getJ() == j) {
                columnKey.add(entry.getKey());
            }
        }
        return columnKey;
    }

    @Override
    public boolean hasValue(V value) {
        for (Map.Entry<Key, V> entry : board.entrySet()) {
            if (Objects.equals(entry.getValue(), value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> valuesList = new ArrayList<>();
        for (Key key: keys) {
            for (Map.Entry<Key, V> entry : board.entrySet()) {
                if (entry.getKey() == key) {
                    valuesList.add(entry.getValue());
                }
            }
        }
        return valuesList;
    }
}
