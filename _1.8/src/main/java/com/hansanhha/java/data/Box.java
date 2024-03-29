package com.hansanhha.java.data;

import java.util.List;
import java.util.Objects;

public class Box<T extends Fruit> {

    private final List<T> item;

    public Box(List<T> item) {
        Objects.requireNonNull(item);
        this.item = item;
    }

    public int getSize() {
        return item.size();
    }

    public List<T> getItem() {
        return item;
    }

    @Override
    public String toString() {
        return item.get(0).getClass().getSimpleName() + "Box";
    }
}
