package com.java8.inaction.chapter3;

import java.util.concurrent.Callable;

/**
 * @author: leiyulin
 * @description:
 * @date:2018/11/2710:45 PM
 */
public class RunnableDemo {
    public static void main(String[] args) {
        Runnable r1 = () -> System.out.println("Runnable 1");
        process(r1);

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable 2");
            }
        };
        process(r2);


        process(() -> System.out.println("Runnable Process"));
    }

    private static void process(Runnable runnable) {
        runnable.run();
    }


    private Callable<String> fetch() {
        return () -> "StringTest";
    }
}
