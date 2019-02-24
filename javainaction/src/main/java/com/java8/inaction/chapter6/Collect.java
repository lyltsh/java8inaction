package com.java8.inaction.chapter6;

import com.java8.inaction.chapter4.Dish;
import lombok.Getter;
import org.w3c.dom.DOMImplementationSource;

import java.lang.reflect.Member;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;


/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/178:47 PM
 */
public class Collect {
    public static void main(String[] args) {
        long dishesNum = Dish.menu.stream()
                .collect(Collectors.counting());

        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> maxCaloriesDish = Dish.menu.stream()
                .collect(Collectors.maxBy(dishCaloriesComparator));
        maxCaloriesDish.ifPresent(System.out::println);

        int totalCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(totalCalories);
        System.out.println("==========");
        int totalCaloriesCollect = Dish.menu.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCaloriesCollect);
        System.out.println("==========");


        double averageCalories = Dish.menu.stream()
                .collect(Collectors.averagingInt(Dish::getCalories));
        System.out.println(averageCalories);

        System.out.println("==========");
        //汇总
        IntSummaryStatistics menuStatistics = Dish.menu.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);

        System.out.println("==========");
        //字符串连接
        String shortMenu = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println(shortMenu);

        String shortMenus = Dish.menu.stream()
                .map(Dish::getName)
                .reduce("", String::concat);
        System.out.println(shortMenus);

        //使用reduce
        //计算卡路里数量
        int totalCaloriesReduce = Dish.menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        int totalCaloriesReduce2 = Dish.menu.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("==========");
        System.out.println(totalCaloriesReduce);


        //分组
        Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        dishesByType.forEach((type, dishList) -> System.out.println(type + ":" + dishList));

        System.out.println("==========");
        //根据卡路里数量，分类
        Map<CaloricLevel, List<Dish>> caloricLevelListMap = Dish.menu.stream().collect(
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        caloricLevelListMap.
                forEach((caloricLevel, dishList) ->
                        System.out.println(caloricLevel + ":" + dishList));

        System.out.println("==========");
        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })));
        dishesByTypeCaloricLevel.forEach(((type, caloricLevelListMap1) -> {
            System.out.print(type + "{");
            caloricLevelListMap1.forEach(((caloricLevel, dishList) -> {
                System.out.print(caloricLevel + ":" + dishList);
            }));
            System.out.println("}");
        }));

        System.out.println("==========");
        Map<Dish.Type, Long> typeLongMap = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        typeLongMap.forEach(((type, aLong) -> System.out.println(type + ":" + aLong)));

        System.out.println("==========");
        //获取热量高的dish
        Map<Dish.Type, Optional<Dish>> typeOptionalMap = Dish.menu.stream()
                .collect(Collectors
                        .groupingBy(Dish::getType,
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        typeOptionalMap.forEach((type, dish) -> System.out.println(type + ":" + dish.get()));

        System.out.println("==========");
        //获取热量高的dish，并去掉空的
        Map<Dish.Type, Dish> typeDishMap = Dish.menu.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        typeDishMap.forEach((type, dish) -> System.out.println(type + ":" + dish));

        //6.4 分区
        Map<Boolean, List<Dish>> partionedDish = Dish.menu.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        partionedDish.forEach(((aBoolean, dishes) -> System.out.println(aBoolean + ":" + dishes)));
    }

    @Getter
    public enum CaloricLevel {
        DIET, NORMAL, FAT
    }
}
