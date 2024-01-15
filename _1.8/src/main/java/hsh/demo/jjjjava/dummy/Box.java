package hsh.demo.jjjjava.dummy;

import java.util.List;
import java.util.Objects;

public class Box<T extends Fruit> {

    private final List<T> item;

    public Box(List<T> item) {
        Objects.requireNonNull(item);
        System.out.println("Fruit Box Size : " + item.size());
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
