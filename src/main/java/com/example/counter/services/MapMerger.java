package com.example.counter.services;

import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.toMap;

@UtilityClass
class MapMerger {

    static Map<String, Long> iterators(Map<String, Map<String, Long>> mapMap) {
        Map<String, Long> totalCounters = new HashMap<>();
        for(Map<String, Long> fileCounters: mapMap.values()) {
            for(Entry<String, Long> entry: fileCounters.entrySet()) {
                Long value = totalCounters.getOrDefault(entry.getKey(), 0L);
                totalCounters.put(entry.getKey(), value + entry.getValue());
            }
        }
        return totalCounters;
    }

    static Map<String, Long> iteratorsAndMerge(Map<String, Map<String, Long>> mapMap) {
        Map<String, Long> totalCounters = new HashMap<>();
        for(Map<String, Long> nameCounters: mapMap.values()) {
            for(Entry<String, Long> entry: nameCounters.entrySet()) {
                totalCounters.merge(entry.getKey(), entry.getValue(), Long::sum);
            }
        }
        return totalCounters;
    }

    static Map<String, Long> streams(Map<String, Map<String, Long>> mapMap) {
        return mapMap.values().stream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(toMap(Entry::getKey, Entry::getValue, Long::sum));
    }

    static Map<String, Long> parallelStreams(Map<String, Map<String, Long>> mapMap) {
        return mapMap.values().parallelStream()
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(toMap(Entry::getKey, Entry::getValue, Long::sum));
    }

}
