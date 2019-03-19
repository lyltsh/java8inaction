package com.java8.functionprogram.examples.chapter3;

import com.java8.functionprogram.examples.chapter1.Artist;

import java.util.List;

/**
 * Created by leiyulin on 2019-03-10
 */
public class Question2 {
    public int totalNum(List<Artist> artistList) {
        return artistList.stream()
            .map(artist -> artist.getMembers().count())
            .reduce(0L, Long::sum)
            .intValue();
    }
}
