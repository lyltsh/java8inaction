package com.java8.inaction.chapter1;

import java.io.File;
import java.util.Arrays;

/**
 * @author: leiyulin
 * @description:
 * @date:2018/11/2610:35 PM
 */
public class FileHidden {
    public static void main(String[] args){
        File[] files = new File(".").listFiles(File::isHidden);
        Arrays.stream(files).forEach(System.out::println);
    }
}
