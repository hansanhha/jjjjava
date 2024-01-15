package hsh.demo.jjjjava.dummy;

import java.time.LocalDateTime;

public class Fruit {

    protected int price;
    protected LocalDateTime expiredDate;

    protected Fruit(int price, LocalDateTime expiredDate) {
        this.price = price;
        this.expiredDate = expiredDate;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\n" +
                "- price : " + price + "\n" +
                "- expiredDate : " + expiredDate + "\n";
    }
}
