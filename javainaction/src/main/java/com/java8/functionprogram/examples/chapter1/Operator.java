package com.java8.functionprogram.examples.chapter1;

import java.util.function.BinaryOperator;

/**
 * Created by leiyulin on 2019-03-02
 */
public class Operator {
    public static void main(String[] args) {
        BinaryOperator<Long> sum = (x, y) -> (x + y);

    }
}
