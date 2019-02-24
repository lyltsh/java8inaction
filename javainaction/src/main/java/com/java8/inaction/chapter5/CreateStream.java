package com.java8.inaction.chapter5;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: leiyulin
 * @description: 创建流
 * @date:2019/1/174:02 PM
 */
public class CreateStream {
    public static void main(String[] args) {
        //又值创建流
        Stream<String> stringStream = Stream.of("Java", "8", "in", "action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("============");
        //由数组创建流
        String[] strings = {"hello", "java", "in", "action"};
        Stream<String> stringStream1 = Arrays.stream(strings);
        stringStream1.map(String::toUpperCase).forEach(System.out::println);

        System.out.println("============");
//        System.out.println(this.getClass().getResource("/cookbook.sql"));
        //由文件生成流
        //查看这个文件有多少个不同的单词
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/leiyl/Documents/code/github/java8inaction/javainaction/src/main/resources/cookbook.sql"), Charset.defaultCharset())) {
            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(uniqueWords);


        System.out.println("============");
        //由函数生成无限流
        Stream.iterate(0, n -> (n + 2))
                //返回前一个元素+2
                .limit(5)
                .forEach(System.out::println);

        System.out.println("============");
        //斐波拉契数字, iterate
        Stream.iterate(new int[]{0, 1}, (a) -> new int[]{a[1], (a[0] + a[1])})
                .limit(10)
                .forEach(a -> System.out.println(a[0] + "," + a[1]));

        System.out.println("============");
        //generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("============");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }


}
