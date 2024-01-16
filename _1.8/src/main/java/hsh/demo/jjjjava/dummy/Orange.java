package hsh.demo.jjjjava.dummy;

import java.time.LocalDateTime;

public class Orange extends Fruit {

    int vitaminC;
    int vitaminD;

    public Orange(int price, LocalDateTime expiredDate, int vitaminC, int vitaminD) {
        super(price, expiredDate, Type.ORANGE);
        this.vitaminC = vitaminC;
        this.vitaminD = vitaminD;
    }

    public int getVitaminC() {
        return vitaminC;
    }

    public int getVitaminD() {
        return vitaminD;
    }

}
