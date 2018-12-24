package com.java8.inaction.chapter3;

import com.java8.inaction.chapter2.FilteringApples;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author: leiyulin
 * @description:
 * @date:2018/11/2711:04 PM
 */
public class FileReadDemo {
    public static void main(String[] args) throws Exception {
//        String result = processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());
        foreach(Arrays.asList(1, 2, 3, 4), System.out::println);
        foreach(Arrays.asList(1, 2, 3, 4), (Integer i) -> System.out.println(i));

//        processFile((BufferedReader reader) -> reader.readLine());
//        processFile((BufferedReader reader) -> reader.readLine() + reader.readLine());

        Predicate<String> notEmptyPredicate = (String s) -> !s.isEmpty();
        foreachPredict(Arrays.asList("12", "we"), notEmptyPredicate);

        List<Integer> integerList = map(Arrays.asList("12", "34"), (String s) -> s.length());
        integerList.stream().forEach(System.out::println);


        int portNumber = 99;
        Runnable runnable = () -> System.out.println(portNumber);

    }

    private static String processFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return reader.readLine();
        }
    }

    private static String processFile(ProcessFileInterface processFileInterface) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            return processFileInterface.process(reader);
        }
    }

    private static <T> void foreach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    private static <T> List<T> foreachPredict(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * 通过Function做转换
     *
     * @param list
     * @param f
     * @param <T>
     * @param <R>
     * @return
     */
    private static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    public interface ProcessFileInterface {
        String process(BufferedReader reader) throws IOException;
    }

}
