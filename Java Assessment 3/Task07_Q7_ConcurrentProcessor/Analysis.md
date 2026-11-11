# Q7 – Concurrent, fault-tolerant result processor

## Requirements and design

Each score file can be processed independently. Shared output is a `ConcurrentLinkedQueue<ProcessingResult>`, which permits concurrent result insertion. A fixed `ExecutorService` limits the number of active worker threads. Each `ScoreFileTask` implements `Callable<ProcessingResult>` and returns a result; `Future.get()` retrieves it. A missing, corrupt or out-of-range file is converted into a failure result inside that task, so one bad file does not cancel the full batch.

The main program demonstrates two valid scores, one corrupt file and one missing file. Expected small-set counts: 2 successes and 2 failures. It prints the exact failure reason for each failed file.

## Pseudocode

```text
create fixed thread pool with p workers
submit one Callable per score file
each Callable:
    read file
    validate score
    return success result OR failure result
wait for all Futures
collect each result in concurrent collection
count successes and failures
print summary
shutdown executor
```

## Measurement

`PoolBenchmark.java` creates 10,000 score files, including corrupt inputs, then measures pool sizes 1, 2, 4 and 8. Run on your own machine and record the output. Do not assume more threads always improve performance: file I/O, storage throughput, context switching, JVM overhead and CPU count can limit gains. For a small sample set, hand-check success/failure counts before relying on the large run.

## Alternatives and complexity

- ExecutorService: bounded number p of worker threads; task submission and result collection overhead; O(n) task/result storage.
- One thread per file: up to n thread stacks and scheduling overhead; often wasteful for 10,000 files.
- Parallel streams: concise, generally uses the common ForkJoinPool and gives less direct control over pool sizing and per-task failure handling.

If n files each require average work w and p workers are effective, idealised processing time is approximately O(nw/p), plus scheduling and I/O overhead. Memory includes O(n) task/future/result references and O(p × stack-size) worker stacks for a fixed pool. A single failure is captured as a failure result; unexpected exceptions from a Future should be handled when calling get().
