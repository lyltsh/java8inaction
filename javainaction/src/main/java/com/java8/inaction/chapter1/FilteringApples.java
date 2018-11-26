package com.java8.inaction.chapter1;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
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
                new Apple("green", 120),
                new Apple("green", 130));

        List<Apple> greenApples = filterApples(appleList, FilteringApples::isGreen);
        System.out.println(greenApples);


        appleList.stream().filter(FilteringApples::isGreen).forEach(System.out::println);

        appleList.stream().filter((Apple apple) -> apple.getWeight() > 120).forEach(System.out::println);

        List<Apple> heavyApples = appleList.parallelStream().filter((Apple apple) -> apple.getWeight() > 110).collect(Collectors.toList());
        heavyApples.stream().forEach(System.out::println);
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static boolean isGreen(Apple apple) {
        return "green".equals(apple.getColor());
    }

    private static boolean isBig(Apple apple) {
        return apple.getWeight() > 110;
    }

    @Data
    public static class Apple {
        public Apple(String color, double weight) {
            this.color = color;
            this.weight = weight;
        }

        private String color;
        private double weight;
    }
}
