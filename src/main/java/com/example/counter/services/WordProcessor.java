package com.example.counter.services;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@UtilityClass
class WordProcessor {

    static Map<String, Long> stringSplitRegexWithCollector(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = text.split("\\W+");
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

    static Map<String, Long> stringSplitWithCollector(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = text.split(" ");
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

    static Map<String, Long> stringUtilsSplitWithCollector(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = StringUtils.split(text, (" "));
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

    static Map<String, Long> stringTokenizerWithMerge(String text) {
        Map<String, Long> counters = new HashMap<>();

        StringTokenizer tokenizer = new StringTokenizer(text, " ");
        while(tokenizer.hasMoreElements()) {
            counters.merge(tokenizer.nextToken(), 1L, (k, v) -> v+1);
        }
        return counters;
    }
}
