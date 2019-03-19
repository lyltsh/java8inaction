package com.java8.functionprogram.examples.chapter3;

import com.java8.functionprogram.examples.chapter1.Album;
import com.java8.functionprogram.examples.chapter1.Artist;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by leiyulin on 2019-03-10
 */
public class Question1 {

    /**
     * 计算流中所有元素之和
     *
     * @param numbers
     * @return
     */
    public int addUpp(Stream<Integer> numbers) {
        return numbers.reduce(0, (a, b) -> (a + b));
    }

    public static List<String> getNamesAndOrigins(List<Artist> artists) {
        return artists.stream()
            .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))
            .collect(toList());
    }

    public List<Album> threeSong(List<Album> albums) {
        return albums.stream()
            .filter(album -> album.getTrackList().size() <= 3)
            .collect(toList());
    }
}
