package com.deus.board;

import com.deus.key.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K, V> {

    private int width;
    private int height;
    protected Map<K, V> board = new LinkedHashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public abstract void fillBoard(List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem(K key, V value);
    public abstract K getKey(int i, int j);
    public abstract V getValue(K key);
    public abstract List<K> getRow(int i);
    public abstract List<K> getColumn(int j);
    public abstract boolean hasValue(V value);
    public abstract List<V> getValues(List<K> keys);

}
