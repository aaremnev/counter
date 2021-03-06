package com.example.counter.services;

import org.openjdk.jmh.annotations.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;

@BenchmarkMode(Mode.Throughput)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 2)
public class MapMergerBenchmark {

    private final static String [] files = {"lipsum1.txt", "lipsum2.txt"};
    private static Map<String, Map<String, Long>> maps = prepareMaps();

    public static void main(String [] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    private static Map<String, Long> readMap(String fileName) {
        try {
            String text = new String(readAllBytes(Paths.get(new ClassPathResource("data/" + fileName).getURI())));
            return WordProcessor.stringSplitWithCollector(text);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, Map<String, Long>> prepareMaps() {
        Map<String, Map<String, Long>> maps = new HashMap<>();
        for(String file: files) {
            maps.put(file, readMap(file));
        }
        return maps;
    }

    @Benchmark
    public void iterators(){
        MapMerger.iterators(maps);
    }

    @Benchmark
    public void iteratorsAndMerge(){
        MapMerger.iteratorsAndMerge(maps);
    }

    @Benchmark
    public void streams(){
        MapMerger.streams(maps);
    }

    @Benchmark
    public void parallelStreams(){
        MapMerger.streams(maps);
    }

}
