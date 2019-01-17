package com.java8.inaction.chapter5;

import com.java8.inaction.chapter4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/114:49 PM
 */
public class Mapping {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Hello World", "java8inaction");
        //返回字符的长度
        words.stream()
                .map(String::length)
                .collect(toList())
                .forEach(System.out::println);
        System.out.println("-----");
        //每个菜肴名字的长度
        Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList()).forEach(System.out::println);

        //返回字符串中不同的字母列表
        List<String> wordsList = words.stream()
                .map(word -> word.split(""))
                //将每个生成流扁平化为单个流，把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println("-----");
        //返回一个数组的平方，列表
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> squareList = integerList.stream()
                .map(integer -> integer * integer)
                .collect(toList());
        System.out.println(squareList);
        System.out.println("-----");
        //两对数组，返回数对
        List<Integer> integerList1 = Arrays.asList(1, 2, 3);
        List<Integer> integerList2 = Arrays.asList(4, 5);
        List<Integer[]> integers = integerList1.stream()
                .flatMap(integer ->
                        integerList2.stream()
                                .filter(integer1 -> (integer + integer1) % 3 == 0)
                                .map(integer1 -> new Integer[]{integer, integer1})
                )
                .collect(toList());
        integers.forEach(integers1 -> System.out.println(integers1[0] + "," + integers1[1]));
    }
}
