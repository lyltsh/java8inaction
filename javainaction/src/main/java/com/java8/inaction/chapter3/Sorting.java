package com.java8.inaction.chapter3;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author: leiyulin
 * @description:
 * @date:2019/1/1110:12 AM
 */
public class Sorting {
    public static void main(String[] args) {
        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        ));

        inventory.stream().forEach(System.out::println);

//        inventory.sort(new AppleComparator());
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("after sort");
        inventory.stream().forEach(System.out::println);

        List<String> stringList = Arrays.asList("a", "b", "A", "B");
        System.out.println(stringList);
//        stringList.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        stringList.sort(String::compareToIgnoreCase);
        System.out.println(stringList);

        //方法引用
        /*
         * 构造函数引用
         */
        //空构造函数
        Supplier<Apple> appleSupplier = Apple::new;
        Apple a1 = appleSupplier.get();

        //一个构造参数，使用Function
        Function<Integer, Apple> appleFunction = Apple::new;
        Apple a2 = appleFunction.apply(100);

        List<Integer> weights = Arrays.asList(9, 8, 34, 10);
        List<Apple> apples = map(weights, Apple::new);
        //逆序和链式比较
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor)
        );
        System.out.println(apples);

        //多个参数构造函数
        BiFunction<Integer, String, Apple> appleBiFunction = Apple::new;
        Apple a3 = appleBiFunction.apply(20, "green");

        /*
        谓词复合
        and, or, negate
         */
        Predicate<Apple> applePredicate;

        /*
        函数复合
        andThen, compose
         */
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> 2 * x;
        Function<Integer, Integer> function1 = f.andThen(g); //类似于 g(f(x))
        System.out.println(function1.apply(1));

        Function<Integer, Integer> function2 = f.compose(g); //类似于 f(g(x))
        System.out.println(function2.apply(1));
    }

    private static List<Apple> map(List<Integer> weights, Function<Integer, Apple> function) {
        List<Apple> apples = new ArrayList<>();
        for (Integer w : weights) {
            apples.add(function.apply(w));
        }
        return apples;
    }

    @Data
    @NoArgsConstructor
    public static class Apple {
        public Apple(Integer weight, String color) {
            this.color = color;
            this.weight = weight;
        }

        public Apple(Integer weight) {
            this.weight = weight;
        }

        private String color;
        private Integer weight;
    }


    public interface ApplePredict {
        boolean test(Apple apple);
    }

    static class AppleComparator implements Comparator<Apple> {

        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight().compareTo(o2.getWeight());
        }
    }
}
