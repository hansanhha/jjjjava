package hsh.demo.jjjjava.feature;

import hsh.demo.jjjjava.dummy.Apple;
import hsh.demo.jjjjava.dummy.Box;
import hsh.demo.jjjjava.dummy.Fruit;
import hsh.demo.jjjjava.dummy.Orange;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;

class FunctionalInterface {

    static Comparator<Box<? extends Fruit>> boxSizeComparator = (Box<? extends Fruit> b1, Box<? extends Fruit> b2) -> b1.getSize() - b2.getSize();

    static BiConsumer<Box<? extends Fruit>, Integer> boxSizeResultReader = (box, result) -> {
        if (result > 0) {
            System.out.println(box + " is bigger");
        } else if (result < 0) {
            System.out.println(box + " is smaller");
        } else {
            System.out.println("Boxes is same");
        }
    };

    public static void main(String[] args) {
        System.out.println("1.8 - Functional Interface, using Comparator, BiConsumer");

        Box<Orange> orangeBox = getOrangeBox();
        Box<Apple> appleBox = getAppleBox();

        int result = boxSizeComparator.compare(orangeBox, appleBox);

        boxSizeResultReader.accept(orangeBox, result);
    }

    private static Box<Apple> getAppleBox() {
        LocalDateTime appleExpiredDate = LocalDateTime.of(2024, 5, 12, 0, 0);
        int applePrice = 1000;
        int appleVitaminA = 100;
        int appleVitaminB = 100;

        return new Box<>(Arrays.asList(
                new Apple(applePrice, appleExpiredDate, appleVitaminA, appleVitaminB),
                new Apple(applePrice, appleExpiredDate, appleVitaminA, appleVitaminB),
                new Apple(applePrice, appleExpiredDate, appleVitaminA, appleVitaminB),
                new Apple(applePrice, appleExpiredDate, appleVitaminA, appleVitaminB),
                new Apple(applePrice, appleExpiredDate, appleVitaminA, appleVitaminB)
        ));
    }

    private static Box<Orange> getOrangeBox() {
        LocalDateTime orangeExpiredDate = LocalDateTime.of(2024, 5, 12, 0, 0);
        int orangePrice = 1000;
        int orangeVitaminC = 100;
        int orangeVitaminD = 100;

        return new Box<>(Arrays.asList(
                new Orange(orangePrice, orangeExpiredDate, orangeVitaminC, orangeVitaminD),
                new Orange(orangePrice, orangeExpiredDate, orangeVitaminC, orangeVitaminD),
                new Orange(orangePrice, orangeExpiredDate, orangeVitaminC, orangeVitaminD)
        ));
    }

}
