package com.example.counter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounterService {

    private final Map<String, Map<String, Long>> fileCounters = new HashMap<>();

    public void processFile(String name, String text) {
        log.info("Processing file: {}...", name);

        fileCounters.put(name, WordProcessor.stringSplitWithCollector(text));

        log.info("Processed file {}", name);
    }

    public String[] getCounterNames() {
        return fileCounters.keySet().toArray(new String[0]);
    }

    public Map<String, Long> getFileCounters(String name) {
        return fileCounters.get(name);
    }

    public Map<String, Long> getTotalCounters() {
        log.info("Merging {} counters", fileCounters.size());

        return MapMerger.iteratorsAndMerge(fileCounters);
    }

}
