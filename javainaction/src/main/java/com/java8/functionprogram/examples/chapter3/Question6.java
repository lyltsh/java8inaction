package com.java8.functionprogram.examples.chapter3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by leiyulin on 2019-03-10
 */
public class Question6 {
    public int lowerCaseNumber(String str) {
        return (int) str.chars().filter(Character::isLowerCase).count();
    }

    public Optional<String> mostLowerCaseString(List<String> strings){
        return strings.stream().max(Comparator.comparing(this::lowerCaseNumber));
    }
}
