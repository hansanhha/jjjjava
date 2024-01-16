package hsh.demo.jjjjava.feature;

import hsh.demo.jjjjava.dummy.Apple;
import hsh.demo.jjjjava.dummy.Box;
import hsh.demo.jjjjava.dummy.Fruit;
import hsh.demo.jjjjava.dummy.Orange;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

class TerminalStreamAPI {

    static Box<Apple> busanAppleBox;
    static Box<Apple> seoulAppleBox;

    static Box<Orange> jejuOrangeBox;

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

        LocalDateTime jejuOrangeExpiredDate = LocalDateTime.of(2024, 5, 26, 0, 0);
        int jejuOrangePrice = 1000;
        int jejuOrangeVitaminC = 100;
        int jejuOrangeVitaminD = 100;

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

        jejuOrangeBox = new Box<>(Arrays.asList(
                new Orange(jejuOrangePrice, jejuOrangeExpiredDate, jejuOrangeVitaminC, jejuOrangeVitaminD),
                new Orange(jejuOrangePrice, jejuOrangeExpiredDate, jejuOrangeVitaminC, jejuOrangeVitaminD),
                new Orange(jejuOrangePrice, jejuOrangeExpiredDate, jejuOrangeVitaminC, jejuOrangeVitaminD),
                new Orange(jejuOrangePrice, jejuOrangeExpiredDate, jejuOrangeVitaminC, jejuOrangeVitaminD),
                new Orange(jejuOrangePrice, jejuOrangeExpiredDate, jejuOrangeVitaminC, jejuOrangeVitaminD)
        ));
    }

    public static void main(String[] args) {
        System.out.println("1.8 - Stream");

        printAppleType();

        System.out.println("===busan apple box 유통기한===");
        printFruitExpireDate(busanAppleBox);

        System.out.println("===seoul apple box 유통기한===");
        printFruitExpireDate(seoulAppleBox);

        System.out.println("===seoul apple box 최대 유통기한===");
        printMaxExpiredDate(seoulAppleBox);

        System.out.println("===seoul apple box 최소 유통기한===");
        printMinExpiredDate(seoulAppleBox);

        int busanAppleBoxPrice = getBoxPrice(busanAppleBox);
        int seoulAppleBoxPrice = getBoxPrice(seoulAppleBox);
        print("===busan apple box 가격===", busanAppleBoxPrice);
        print("===seoul apple box 가격===", seoulAppleBoxPrice);

        System.out.println("===과일 별 가장 비싼 가격===");
        printMaxPriceBoxByGroup(busanAppleBox, seoulAppleBox, jejuOrangeBox);

        System.out.println("===과일 별 유통기한===");
        printExpiredDateBoxByGroup(busanAppleBox, seoulAppleBox, jejuOrangeBox);

        System.out.println("===2000원 이하의 가격을 가진 과일 개수===");
        printFruitCountByGroupAndPrice(2000, busanAppleBox, seoulAppleBox, jejuOrangeBox);
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
        System.out.println();
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
        System.out.println();
    }

    /*
        Collectors.maxBy()
     */
    private static <T extends Fruit> void printMinExpiredDate(Box<T> box) {
        List<T> item = box.getItem();

        Optional<LocalDateTime> maxExpiredDate = item.stream()
                .map(Fruit::getExpiredDate)
                .collect(Collectors.maxBy(LocalDateTime::compareTo));

        System.out.println(maxExpiredDate.get());
        System.out.println();
    }

    /*
        Collectors.groupingBy()
        특정 기준으로 그룹화, 두 번째 매개변수로 추가 연산 가능(Collector)
        과일 종류별로 가장 비싼 가격 그룹화 (사과 : 2000원, 오렌지 : 1000원 등)
     */
    @SafeVarargs
    private static void printMaxPriceBoxByGroup(Box<? extends Fruit> ... boxes) {
        Map<Fruit.Type, ? extends Optional<? extends Fruit>> result = Arrays.stream(boxes)
                .flatMap(box -> box.getItem().stream())
                .collect(Collectors.groupingBy(Fruit::getType,
                        Collectors.maxBy(Comparator.comparingInt(Fruit::getPrice))));

        for (Fruit.Type type : result.keySet()) {
            System.out.println("가장 비싼 " + type.getName() + "의 가격");
            System.out.println(result.get(type).get().getPrice() + "원");
            System.out.println();
        }
    }

    /*
        Collectors.groupingBy()
        다수준의 그룹화 가능
        과일 종류별로 유통기한 그룹화 (사과, 2024-05-26(일) : 5개 등)
     */
    @SafeVarargs
    private static void printExpiredDateBoxByGroup(Box<? extends Fruit> ... boxes) {
        Map<Fruit.Type, Map<LocalDateTime, Long>> result = Arrays.stream(boxes)
                .flatMap(box -> box.getItem().stream())
                .collect(Collectors.groupingBy(Fruit::getType,
                        Collectors.groupingBy(Fruit::getExpiredDate, Collectors.counting())));

        for (Fruit.Type type : result.keySet()) {
            System.out.println(type.getName());

            Map<LocalDateTime, Long> expiredMap = result.get(type);

            for (LocalDateTime expiredDate : expiredMap.keySet()) {
                Long count = expiredMap.get(expiredDate);
                String formatted = expiredDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E)").withLocale(Locale.KOREA));

                System.out.println(formatted + " : " + count + "개");
            }
            System.out.println();
        }
    }

    /*
        Collectors.partitioningBy()
        특정 조건으로 분할(무조건 2개)
        과일 종류별로 조건 이하의 가격을 가진 과일 개수 구하기
     */
    @SafeVarargs
    private static void printFruitCountByGroupAndPrice(int price, Box<? extends Fruit> ... boxes) {
        Map<Fruit.Type, Map<Boolean, Long>> result = Arrays.stream(boxes)
                .flatMap(box -> box.getItem().stream())
                .collect(Collectors.groupingBy(Fruit::getType,
                        Collectors.partitioningBy(item -> item.getPrice() <= price, Collectors.counting())));

        for (Fruit.Type type : result.keySet()) {
            Map<Boolean, Long> lowerPriceMap = result.get(type);

            Long count = lowerPriceMap.get(true);

            System.out.println(type.getName() + " : " + count + "개");
        }
    }

    /*
        Collectors.joining()
        문자열 합치기 연산(delimiter, prefix, suffix 사용 가능)
     */
    private static void printAppleType() {
        List<String> appleTypes = Arrays.asList("busan apple", "seoul apple");

        String result = appleTypes.stream()
                .collect(Collectors.joining(", "));

        print("===apple type===", result);
    }

    private static <T> void print(String message, T value) {
        System.out.println(message);
        System.out.println(value);
        System.out.println();
    }

}
