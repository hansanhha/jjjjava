package com.hansanhha.java.data;

import java.time.LocalDateTime;

public class Apple extends Fruit {

    int vitaminA;
    int vitaminB;

    public Apple(int price, LocalDateTime expiredDate, int vitaminA, int vitaminB) {
        super(price, expiredDate, Type.APPLE);
        this.vitaminA = vitaminA;
        this.vitaminB = vitaminB;
    }

    public int getVitaminA() {
        return vitaminA;
    }

    public int getVitaminB() {
        return vitaminB;
    }
}
