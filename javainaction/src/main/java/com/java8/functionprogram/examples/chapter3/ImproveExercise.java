package com.java8.functionprogram.examples.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by leiyulin on 2019-03-10
 */
public class ImproveExercise {
    public static <I, O> List<O> map(Stream<I> stream, Function<I, O> mapper) {
        return stream.reduce(new ArrayList<O>(0), (acc, x)->{
            List<O> newResult = new ArrayList<>(acc);
            newResult.add(mapper.apply((I) acc));
            return newResult;
        }, (List<O> left, List<O> right) -> {
            // We are copying left to new list to avoid mutating it.
            List<O> newLeft = new ArrayList<>(left);
            newLeft.addAll(right);
            return newLeft;
        });
    }
}
