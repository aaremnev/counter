package com.example.counter.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CounterService {

    private final Map<String, Long> counters = new HashMap<>();

    public void process(String name, String text) {
        log.info("Processing file: {}...", name);

        long count = process(text);
        counters.put(name, count);

        log.info("Processed file {}: {}", name, count);
    }

    public Long getCounter(String name) {
        return counters.get(name);
    }

    public Collection<String> listCounters() {
        return counters.keySet();
    }

    private long process(String text) {
        return text.length();
    }

}
