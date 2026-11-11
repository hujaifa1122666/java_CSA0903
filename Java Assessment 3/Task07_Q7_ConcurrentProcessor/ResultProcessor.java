import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ProcessingResult {
    final String file;
    final boolean success;
    final String message;

    ProcessingResult(String file, boolean success, String message) {
        this.file = file; this.success = success; this.message = message;
    }
}

class ScoreFileTask implements Callable<ProcessingResult> {
    private final Path file;

    ScoreFileTask(Path file) { this.file = file; }

    @Override
    public ProcessingResult call() {
        try {
            if (!Files.exists(file))
                throw new IOException("File is missing");
            String text = Files.readString(file).trim();
            if (text.isEmpty() || !text.matches("\\d{1,3}"))
                throw new IllegalArgumentException("Corrupt score data");
            int score = Integer.parseInt(text);
            if (score < 0 || score > 100)
                throw new IllegalArgumentException("Score outside 0..100");
            return new ProcessingResult(file.getFileName().toString(), true,
                    "Validated score " + score);
        } catch (IOException | IllegalArgumentException e) {
            return new ProcessingResult(file.getFileName().toString(), false, e.getMessage());
        }
    }
}

public class ResultProcessor {
    static void process(List<Path> files, int poolSize) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        ConcurrentLinkedQueue<ProcessingResult> results = new ConcurrentLinkedQueue<>();
        try {
            List<Callable<ProcessingResult>> tasks =
                    files.stream().<Callable<ProcessingResult>>map(ScoreFileTask::new).toList();
            List<Future<ProcessingResult>> futures = executor.invokeAll(tasks);
            for (Future<ProcessingResult> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    results.add(new ProcessingResult("unknown", false,
                            "Unexpected task failure: " + e.getMessage()));
                }
            }
        } finally {
            executor.shutdown();
        }

        long successes = results.stream().filter(r -> r.success).count();
        long failures = results.size() - successes;
        System.out.println("Pool size: " + poolSize);
        System.out.println("Files processed: " + results.size());
        System.out.println("Successes: " + successes);
        System.out.println("Failures: " + failures);
        results.stream().filter(r -> !r.success).forEach(r ->
                System.out.println("FAILED " + r.file + ": " + r.message));
    }

    public static void main(String[] args) throws Exception {
        Path input = Path.of("scores");
        Files.createDirectories(input);
        Files.writeString(input.resolve("student01.txt"), "87");
        Files.writeString(input.resolve("student02.txt"), "91");
        Files.writeString(input.resolve("student03.txt"), "corrupt");
        // student04.txt is intentionally missing.
        List<Path> files = List.of(
                input.resolve("student01.txt"),
                input.resolve("student02.txt"),
                input.resolve("student03.txt"),
                input.resolve("student04.txt"));
        process(files, 4);
    }
}
