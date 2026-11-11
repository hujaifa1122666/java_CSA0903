import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PoolBenchmark {
    static class ScoreTask implements Callable<Boolean> {
        private final Path file;
        ScoreTask(Path file) { this.file = file; }
        public Boolean call() {
            try {
                String value = Files.readString(file).trim();
                return value.matches("\\d{1,3}") && Integer.parseInt(value) <= 100;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private static void run(int poolSize, List<Path> files) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        long start = System.nanoTime();
        int success = 0, failure = 0;
        try {
            List<Future<Boolean>> futures = new ArrayList<>();
            for (Path file : files) futures.add(pool.submit(new ScoreTask(file)));
            for (Future<Boolean> future : futures) {
                if (future.get()) success++; else failure++;
            }
        } finally {
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
        }
        long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
        System.out.printf("pool=%d files=%d success=%d failure=%d time=%d ms%n",
                poolSize, files.size(), success, failure, elapsedMs);
    }

    public static void main(String[] args) throws Exception {
        Path dir = Path.of("benchmark-scores");
        Files.createDirectories(dir);
        List<Path> files = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            Path p = dir.resolve("score" + i + ".txt");
            if (i % 1000 == 999) Files.writeString(p, "bad");
            else Files.writeString(p, Integer.toString(i % 101));
            files.add(p);
        }
        for (int size : new int[]{1, 2, 4, 8}) run(size, files);
    }
}
