package com.java8.inaction.chapter4;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/112:37 PM
 */
public class StreamBasic {
    public static void main(String... args) {
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);


        Map<Dish.Type, List<Dish>> typeListMap = Dish.menu.stream().collect(groupingBy(Dish::getType));

        System.out.println("---");
        //获取高卡路里的三个菜肴名字
        List<String> threeHighCaloricDishName = Dish.menu.stream()
                .filter(d -> {
                    System.out.println("filter:" + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("map:" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(toList());
        threeHighCaloricDishName.stream().forEach(System.out::println);
    }

    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d : dishes) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
