package hsh.demo.jjjjava.feature;

import hsh.demo.jjjjava.dummy.Apple;
import hsh.demo.jjjjava.dummy.Box;
import hsh.demo.jjjjava.dummy.Fruit;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class TerminalStreamAPI {

    static Box<Apple> busanAppleBox;
    static Box<Apple> seoulAppleBox;

    static {
        LocalDateTime busanAppleExpiredDate = LocalDateTime.of(2024, 5, 26, 0, 0);
        int busanApplePrice = 1000;
        int busanAppleVitaminC = 100;
        int busanAppleVitaminB = 100;

        LocalDateTime seoulAppleExpiredDate1 = LocalDateTime.of(2024, 12, 3, 0, 0);
        LocalDateTime seoulAppleExpiredDate2 = LocalDateTime.of(2024, 4, 9, 0, 0);
        int seoulApplePrice = 2000;
        int seoulAppleVitaminA = 100;
        int seoulAppleVitaminB = 100;

        busanAppleBox = new Box<>(Arrays.asList(
                new Apple(busanApplePrice, busanAppleExpiredDate, busanAppleVitaminC, busanAppleVitaminB),
                new Apple(busanApplePrice, busanAppleExpiredDate, busanAppleVitaminC, busanAppleVitaminB),
                new Apple(busanApplePrice, busanAppleExpiredDate, busanAppleVitaminC, busanAppleVitaminB),
                new Apple(busanApplePrice, busanAppleExpiredDate, busanAppleVitaminC, busanAppleVitaminB),
                new Apple(busanApplePrice, busanAppleExpiredDate, busanAppleVitaminC, busanAppleVitaminB)
        ));

        seoulAppleBox = new Box<>(Arrays.asList(
                new Apple(seoulApplePrice, seoulAppleExpiredDate1, seoulAppleVitaminA, seoulAppleVitaminB),
                new Apple(seoulApplePrice, seoulAppleExpiredDate1, seoulAppleVitaminA, seoulAppleVitaminB),
                new Apple(seoulApplePrice, seoulAppleExpiredDate1, seoulAppleVitaminA, seoulAppleVitaminB),
                new Apple(seoulApplePrice, seoulAppleExpiredDate2, seoulAppleVitaminA, seoulAppleVitaminB),
                new Apple(seoulApplePrice, seoulAppleExpiredDate2, seoulAppleVitaminA, seoulAppleVitaminB),
                new Apple(seoulApplePrice, seoulAppleExpiredDate2, seoulAppleVitaminA, seoulAppleVitaminB)
        ));
    }

    public static void main(String[] args) {
        System.out.println("1.8 - Stream");

        System.out.println("===busan apple box===");
        printFruitExpireDate(busanAppleBox);

        System.out.println("===seoul apple box===");
        printFruitExpireDate(seoulAppleBox);

        int busanAppleBoxPrice = getBoxPrice(busanAppleBox);
        int seoulAppleBoxPrice = getBoxPrice(seoulAppleBox);

        print("===busan apple box 가격===", busanAppleBoxPrice);
        print("===seoul apple box 가격===", seoulAppleBoxPrice);

        System.out.println("===seoul apple box 최대 유통기한===");
        printMaxExpiredDate(seoulAppleBox);

        System.out.println("===seoul apple box 최소 유통기한===");
        printMinExpiredDate(seoulAppleBox);
    }

    /*
        void forEach(Consumer<? super T> action)
        스트림 API는 내부 반복자 패턴(internal iterator pattern) 사용
     */
    private static <T extends Fruit> void printFruitExpireDate(Box<T> box) {
        List<T> item = box.getItem();

        item.stream()
            .map(Fruit::getExpiredDate)
            .forEach(System.out::println);
    }

    /*
        <R, A> R collect(Collector<? super T, A, R> collector)

        collect()의 매개변수 Collector는 mutable reduction operation을 수행하는 역할

        reduction(fold) operation : 스트림 요소들을 하나의 결과로 결합하는 연산(불변성) - reduce()
        mutable reduction operation : 누적기(accumulator)를 사용하여 reduction operation 수행(누적기 가변성) - collect()

        Collectors : Collector를 구현한 유틸리티 클래스

        accumulator : 누적기, 스트림 요소를 처리하고 누적기에 저장하는 역할
            -> reduce()의 경우 identity를 통해 accumulator의 결과를 저장(불변성)
            -> collect()의 경우 supplier를 통해 임시 저장 컨테이너(Collection 등)를 생성하고 accumulator의 결과를 저장(가변성)
        combiner : 병렬 스트림에서 각 스레드의 부분 결과를 합하는 용도로 사용
     */
    private static <T extends Fruit> int getBoxPrice(Box<T> box) {
        List<T> item = box.getItem();

        return item.stream()
                    .collect(Collectors.summingInt(Fruit::getPrice));
    }

    /*
        Optional<T> reduce(BinaryOperator<T> accumulator)
        Optional<T> reduce(T identity, BinaryOperator<T> accumulator)
        <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)

        collect()와 마찬가지로 스트림 요소들을 하나의 결과로 결합하는 연산
        불변성을 가지는 reduction operation
     */
    private static <T extends Fruit> void printMaxExpiredDate(Box<T> box) {
        List<T> item = box.getItem();

        T result = item.stream()
                .reduce((a, b) -> a.getExpiredDate().isAfter(b.getExpiredDate()) ? a : b)
                .get();

        System.out.println(result.getExpiredDate());
    }

    private static <T extends Fruit> void printMinExpiredDate(Box<T> box) {
        List<T> item = box.getItem();

        T result = item.stream()
                .reduce((a, b) -> a.getExpiredDate().isBefore(b.getExpiredDate()) ? a : b)
                .get();

        System.out.println(result.getExpiredDate());
    }

    private static <T> void print(String message, T value) {
        System.out.println(message);
        System.out.println(value);
    }

}
