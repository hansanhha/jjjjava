package com.hansanhha.java.feature;

import com.hansanhha.java.data.Apple;
import com.hansanhha.java.data.Box;
import com.hansanhha.java.data.Fruit;

import java.time.LocalDateTime;
import java.util.*;

/*
    참고하기 유용한 문서
    https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 */
public class MethodReference {

    static Box<Apple> appleBox;

    static {
        LocalDateTime appleExpiredDate = LocalDateTime.of(2024, 5, 26, 0, 0);
        int appleVitaminC = 100;
        int appleVitaminD = 100;

        appleBox = new Box<>(Arrays.asList(
                new Apple(5000, appleExpiredDate, appleVitaminC, appleVitaminD),
                new Apple(1000, appleExpiredDate, appleVitaminC, appleVitaminD),
                new Apple(2000, appleExpiredDate, appleVitaminC, appleVitaminD),
                new Apple(500, appleExpiredDate, appleVitaminC, appleVitaminD),
                new Apple(2500, appleExpiredDate, appleVitaminC, appleVitaminD)
        ));
    }

    public static void main(String[] args) {
        normalObject();
        reverseOrder();

        lambdaExpression();
        reverseOrder();

        lambdaExpressionExistMethod();
        reverseOrder();

        methodReference();
        reverseOrder();
    }

    /*
        객체를 매개변수로 전달하는 방식
     */
    public static void normalObject() {
        System.out.println("===객체를 매개변수로 전달하는 방식===");

        List<Apple> item = appleBox.getItem();

        class FruitComparator implements Comparator<Fruit> {

            @Override
            public int compare(Fruit a, Fruit b) {
                return a.getPrice().compareTo(b.getPrice());
            }
        }

        FruitComparator fruitComparator = new FruitComparator();

        item.sort(fruitComparator);

        for (Fruit fruit : item) {
            System.out.println(fruit.getPrice());
        }
    }

    /*
        람다식을 사용하는 방식(Comparator는 함수형 인터페이스)
     */
    public static void lambdaExpression() {
        System.out.println("===람다식을 사용하는 방식===");

        List<Apple> item = appleBox.getItem();

        item.sort((a, b) -> a.getPrice().compareTo(b.getPrice()));

        for (Fruit fruit : item) {
            System.out.println(fruit.getPrice());
        }
    }

    /*
        람다식으로 기존의 메서드를 호출하는 방식
     */
    public static void lambdaExpressionExistMethod() {
        System.out.println("===람다식으로 기존의 메서드를 호출하는 방식===");

        List<Apple> item = appleBox.getItem();

        item.sort((a, b) -> Fruit.compareByPrice(a,b));

        for (Fruit fruit : item) {
            System.out.println(fruit.getPrice());
        }
    }

    /*
        람다식이 함수를 하나의 식으로 취급하여 사용하는 방식
        메서드 참조는 기존의 메서드를 람다식으로 재사용하는 방식
        따라서 함수형 인터페이스를 사용하는 곳에 메서드 참조 사용 가능

        메서드 참조를 사용하는 방식

        (a, b) -> Fruit.compareByPrice(a,b)와 Fruit::compareByPrice는 동일한 의미

        메서드 참조 : 이미 존재하는 메서드를 람다식으로 활용하는 방식
         - 매개변수 타입 일치 : Comparator.compare(Fruit, Fruit)와 Fruit.compareByPrice(Fruit,Fruit)
         - 메서드 바디 : Fruit.compareByPrice 호출

        메서드 참조 분류
         - static 메서드 참조 Class::staticMethodName (Fruit::compareByPrice)
         - instance 메서드 참조 instance::instanceMethodName (apple::getPrice)
         - 특정 타입의 메서드 참조 Type::methodName (Integer::sum)
         - 생성자 참조 ClassName::new (HashSet::new)
     */
    public static void methodReference() {
        System.out.println("===메서드 참조를 사용하는 방식===");

        List<Apple> item = appleBox.getItem();

        item.sort(Fruit::compareByPrice);

        for (Fruit fruit : item) {
            System.out.println(fruit.getPrice());
        }
    }

    private static void reverseOrder() {
        List<Apple> item = appleBox.getItem();

        item.sort((a, b) -> b.getPrice().compareTo(a.getPrice()));
    }

}
