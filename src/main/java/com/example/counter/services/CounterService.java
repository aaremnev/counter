package com.example.counter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounterService {

    private final Map<String, Map<String, Long>> fileCounters = new HashMap<>();

    public void processFile(String name, String text) {
        log.info("Processing file: {}...", name);

        fileCounters.put(name, processInternal(text));

        log.info("Processed file {}: {}", name);
    }

    public String[] getCounterNames() {
        return fileCounters.keySet().toArray(new String[0]);
    }

    public Map<String, Long> getFileCounters(String name) {
        return fileCounters.get(name);
    }

    public Map<String, Long> getTotalCounters() {
        log.info("Merging {} counters", fileCounters.size());

        return fileCounters.values().stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(toMap(Entry::getKey, Entry::getValue, Long::sum));

//        Map<String, Long> totalCounters = new HashMap<>();
//        for(Map<String, Long> nameCounters: fileCounters.values()) {
//            for(Entry<String, Long> entry: nameCounters.entrySet()) {
//                totalCounters.merge(entry.getKey(), entry.getValue(), Long::sum);
//            }
//        }
//
//        return totalCounters;
    }

    private Map<String, Long> processInternal(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = text.split("\\W+");
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

}
