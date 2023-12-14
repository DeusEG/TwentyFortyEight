package com.deus.key;

import java.util.Objects;


public class Key {

    private final int i;
    private final int j;

    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }
    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return getI() == key.getI() && getJ() == key.getJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getI(), getJ());
    }

    @Override
    public String toString() {
        return "Key{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
