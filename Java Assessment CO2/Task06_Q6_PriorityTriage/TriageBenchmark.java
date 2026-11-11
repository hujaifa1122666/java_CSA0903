import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class TriageBenchmark {
    static class Entry {
        final int severity;
        final long arrival;
        Entry(int severity, long arrival) { this.severity = severity; this.arrival = arrival; }
    }

    private static long run(int n) {
        PriorityQueue<Entry> queue = new PriorityQueue<>(
                Comparator.comparingInt((Entry e) -> e.severity)
                        .thenComparingLong(e -> e.arrival));
        Random random = new Random(42);
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) queue.add(new Entry(1 + random.nextInt(5), i));
        while (!queue.isEmpty()) queue.poll();
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        for (int n : new int[]{100, 1_000, 10_000}) {
            long total = 0;
            for (int r = 0; r < 5; r++) total += run(n);
            System.out.println("n=" + n + " average add-all + remove-all ns=" + total / 5);
        }
    }
}
