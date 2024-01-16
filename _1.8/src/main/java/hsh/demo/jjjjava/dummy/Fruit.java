package hsh.demo.jjjjava.dummy;

import java.time.LocalDateTime;

public class Fruit {

    protected Integer price;
    protected LocalDateTime expiredDate;
    protected Type type;

    public enum Type {
        APPLE("사과"), ORANGE("오렌지");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    protected Fruit(int price, LocalDateTime expiredDate, Type type) {
        this.price = price;
        this.expiredDate = expiredDate;
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public Type getType() {
        return type;
    }

    public static int compareByPrice(Fruit a, Fruit b) {
        return a.price.compareTo(b.price);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\n" +
                "- price : " + price + "\n" +
                "- expiredDate : " + expiredDate + "\n";
    }
}
