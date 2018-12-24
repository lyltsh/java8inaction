package com.java8.inaction.chapter2;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: leiyulin
 * @description:
 * @date:2018/11/2610:56 PM
 */
public class FilteringApples {
    public static void main(String[] args) {
        List<Apple> appleList = Arrays.asList(
                new Apple("red", 100),
                new Apple("red", 140),
                new Apple("green", 120),
                new Apple("green", 130),
                new Apple("yellow", 90));

        List<Apple> greenApples = filterApplesNew(appleList, (Apple apple) -> apple.color.equals("green"));
        System.out.println(greenApples);

        List<Apple> weightApples = filter(appleList, (Apple apple) -> apple.weight > 110);
        //排序方法1
//        weightApples.sort(new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getWeight().compareTo(o2.getWeight());
//            }
//        });

        //排序方法2
//        weightApples.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));
        //排序方法3
        weightApples.sort(Comparator.comparing(Apple::getWeight));

        weightApples.stream().forEach(System.out::println);
    }

    private static List<Apple> filterApplesNew(List<Apple> inventory, ApplePredict ap) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (ap.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Data
    public static class Apple {
        public Apple(String color, double weight) {
            this.color = color;
            this.weight = weight;
        }

        private String color;
        private Double weight;
    }


    public interface ApplePredict {
        boolean test(Apple apple);
    }

    /**
     * 使用泛型的方式进行filter
     *
     * @param list
     * @param p
     * @param <T>
     * @return
     */
    private static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public interface Predict<T> {
        boolean test(T t);
    }
}
