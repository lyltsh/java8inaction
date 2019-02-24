package com.java8.inaction.chapter5;

import com.java8.inaction.chapter4.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/171:10 PM
 */
public class NumStream {
    public static void main(String[] args) {
        int calories = Dish.menu.stream()
                //数值流
                .mapToInt(Dish::getCalories)
                //转换为对象流
//                .boxed()
                .sum();
        System.out.println(calories);
        System.out.println("==========");

        OptionalInt maxCalories = Dish.menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max = maxCalories.orElse(1);

        IntStream evenNums = IntStream
                .rangeClosed(1, 100)
                //range(1,100)
                .filter(i -> i % 2 == 0);
        System.out.println(evenNums.count());
        System.out.println("==========");

        //找出勾股数
        Stream<int[]> pythagoreanTriple = IntStream.rangeClosed(1, 100)
                //通过使用boxed，将IntStream转成Stream<Integer>
                .boxed()
                .flatMap(operand ->
                        IntStream.rangeClosed(operand, 100)
                                .filter(b -> Math.sqrt(operand * operand + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{operand, b, (int) Math.sqrt(operand * operand + b * b)})
                );
        pythagoreanTriple.forEach(pythagorean -> System.out.println(pythagorean[0] + "," + pythagorean[1] + "," + pythagorean[2]));
    }
}
