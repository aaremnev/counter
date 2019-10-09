# Benchmarks 

### MapMerge algorithms

| Benchmark                               | Mode   | Cnt|       Score ± Error    | Units|
|-----------------------------------------|--------|----|------------------------|-------
| `MapMergerBenchmark.iteratorsAndMerge`  | thrpt  | 20 | 134581.973 ± 2366.642  |ops/s |
| `MapMergerBenchmark.streams`            | thrpt  | 20 | 105345.782 ± 2746.281  |ops/s |
| `MapMergerBenchmark.parallelStreams`    | thrpt  | 20 | 99886.611 ± 2657.738   |ops/s |
| `MapMergerBenchmark.iterators`          | thrpt  | 20 | 75896.606 ± 1748.498   |ops/s |

|Benchmark                                            |  Mode | Cnt |      Score   ±  Error | Units|
|-----------------------------------------------------|-------|-----|-----------------------|------|
|`WordProcessorBenchmark.stringUtilsSplitWithCollector` | thrpt | 20  |131460.799 ± 8529.961  |ops/s |
|`WordProcessorBenchmark.stringTokenizerWithMerge`      | thrpt | 20  | 19595.147 ±  252.388  |ops/s |
|`WordProcessorBenchmark.stringSplitWithCollector`      | thrpt | 20  | 18082.560 ±  704.737  |ops/s |
|`WordProcessorBenchmark.stringSplitRegexWithCollector` | thrpt | 20  |  9437.352 ±  207.702  |ops/s |
