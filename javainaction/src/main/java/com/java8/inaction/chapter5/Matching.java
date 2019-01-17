package com.java8.inaction.chapter5;

import com.java8.inaction.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/115:58 PM
 */
public class Matching {
    public static void main(String[] args) {

        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is some isVegetarian");
        }
        Dish.menu.stream().allMatch(dish -> dish.getCalories() < 1000);

        //查找任意元素
        Optional<Dish> dish = Dish.menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println(dish.isPresent() ? dish.get().getName() : "");

        Dish.menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(dish1 -> System.out.println(dish1.getName()));

        //第一个平方能被3整除的元素
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        Optional<Double> dividedBy3Number = integerList.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .map(Math::sqrt)
                .findFirst();
        dividedBy3Number.ifPresent(System.out::println);

        Integer sum = integerList.stream().reduce(0, Integer::sum);
        System.out.println(sum);

        long integer = Dish.menu.stream().map(Dish::getName).distinct().count();
        System.out.println(integer);
    }
}
