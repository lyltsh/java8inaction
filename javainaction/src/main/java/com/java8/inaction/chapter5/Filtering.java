package com.java8.inaction.chapter5;

import com.java8.inaction.chapter4.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/114:37 PM
 */
public class Filtering {
    public static void main(String[] args) {

        // Filtering with predicate
        List<Dish> vegetarianMenu =
                Dish.menu.stream()
                        .filter(Dish::isVegetarian)
                        .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        // Filtering unique elements
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        //skip 跳过部分数据
        List<Dish> dishes = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .skip(2)
                .collect(toList());
        dishes.stream().forEach(System.out::println);

        //筛选两个荤菜
        Dish.menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList()).stream().forEach(System.out::println);

    }
}
