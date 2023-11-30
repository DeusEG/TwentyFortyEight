package com.deus.board;

import com.deus.key.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board {

    int width;
    int height;
    Map<Key, Integer> board = new HashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    abstract void fillBoard(List<Integer> list);

    abstract List<Key> availableSpace();

    abstract void addItem(Key key, Integer value);

    abstract Key getKey(int i, int j);

    abstract List<Key> getRow(int i);

    abstract List<Key> getColumn(int j);

    abstract boolean hasValue(Integer value);

    abstract List<Integer> getValues(List<Key> keys);
}
