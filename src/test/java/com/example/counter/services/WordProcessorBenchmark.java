package com.example.counter.services;

import org.openjdk.jmh.annotations.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

@BenchmarkMode(Mode.Throughput)
@Fork(value = 1, warmups = 1)
@Warmup(iterations = 2)
public class WordProcessorBenchmark {

    private static String text = readFile();

    private static String readFile() {
        try {
            return  new String(readAllBytes(Paths.get(new ClassPathResource("data/lipsum2.txt").getURI())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String [] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void stringSplitWithCollector() {
        WordProcessor.stringSplitWithCollector(text);
    }

    @Benchmark
    public void stringSplitRegexWithCollector() {
        WordProcessor.stringSplitRegexWithCollector(text);
    }

    @Benchmark
    public void stringUtilsSplitWithCollector() {
        WordProcessor.stringUtilsSplitWithCollector(text);
    }

    @Benchmark
    public void stringTokenizerWithMerge() {
        WordProcessor.stringTokenizerWithMerge(text);
    }

}
