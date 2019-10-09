package com.example.counter.services;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@UtilityClass
class WordProcessor {

    static Map<String, Long> processText(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = text.split("\\W+");
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

}
