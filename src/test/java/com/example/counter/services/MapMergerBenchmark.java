package com.example.counter.services;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;

public class MapMergerBenchmark {

    private final static String [] files = {"lipsum1.txt", "lipsum2.txt"};
    private static Map<String, Map<String, Long>> maps = prepareMaps();

    public static void main(String [] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    private static Map<String, Long> readMap(String fileName) {
        try {
            String text = new String(readAllBytes(Paths.get(new ClassPathResource("data/" + fileName).getURI())));
            return WordProcessor.processText(text);
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
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    public void iterators(){
        MapMerger.iterators(maps);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    public void iteratorsAndMerge(){
        MapMerger.iteratorsAndMerge(maps);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    public void streams(){
        MapMerger.streams(maps);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 1)
    public void parallelStreams(){
        MapMerger.streams(maps);
    }

}
