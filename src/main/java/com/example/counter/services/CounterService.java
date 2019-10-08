package com.example.counter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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

    public Map<String, Long> getFileCounters(String name) {
        return fileCounters.get(name);
    }

    public Map<String, Map<String, Long>> listAllCounters() {
        return fileCounters;
    }

    private Map<String, Long> processInternal(String text) {
        Map<String, Long> counters = new HashMap<>();

        String[] words = text.split("\\W+");
        Stream.of(words).collect(groupingBy(k -> k, () -> counters, counting()));

        return counters;
    }

}
