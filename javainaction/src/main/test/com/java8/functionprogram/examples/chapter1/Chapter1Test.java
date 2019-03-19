package com.java8.functionprogram.examples.chapter1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by leiyulin on 2019-03-03
 */
public class Chapter1Test {

    @Test
    public void collectToList() {
        // BEGIN collect_to_list_1
        List<String> collected = Stream.of("a", "b", "c") // <1>
            .collect(Collectors.toList()); // <2>

        assertEquals(Arrays.asList("a", "b", "c"), collected); // <3>
        // END collect_to_list_1
    }

    @Test
    public void UpperCaseTest() {
        List<String> collected = Stream.of("a", "b", "hello")
            .map(String::toUpperCase).collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "HELLO"), collected);
    }

    @Test
    public void flatMapTest() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
            .flatMap(Collection::stream).collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 2, 3, 4), together);
    }

    @Test
    public void max() {
        List<Track> tracks = Arrays.asList(new Track("Bakai", 524),
            new Track("Violets for Your Furs", 378),
            new Track("Time Was", 451));
        Optional<Track> shortest = tracks.stream().min(Comparator.comparing(Track::getLength));
        assertEquals(tracks.get(1), shortest.get());
    }

    @Test
    public void sum() {
        //for test，实际上不能使用这个方法求和
        int sum = Stream.of(1, 2, 3).reduce(0, (a, b) -> (a + b));
        assertEquals(6, sum);
    }

    @Test
    public void findLongTracks() {
        List<Album> albums = new ArrayList<>();
        Set<String> trackNames = albums.stream()
            .flatMap(Album::getTracks)
            .filter(track -> track.getLength() > 60)
            .map(Track::getName)
            .collect(Collectors.toSet());
    }

    @Test
    public void testMap(){
        Map<String, String> stringMap = new HashMap<>(0);

    }
}