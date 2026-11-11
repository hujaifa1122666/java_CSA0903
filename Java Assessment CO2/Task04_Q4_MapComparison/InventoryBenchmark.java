import java.util.*;

public class InventoryBenchmark {
    private static volatile int sink;

    private static Map<String, Integer> create(String kind, int n) {
        Map<String, Integer> map;
        switch (kind) {
            case "TreeMap": map = new TreeMap<>(); break;
            case "LinkedHashMap": map = new LinkedHashMap<>(); break;
            default: map = new HashMap<>();
        }
        for (int i = n - 1; i >= 0; i--) map.put(String.format("MED%07d", i), i);
        return map;
    }

    private static long lookupTime(Map<String, Integer> map, int n) {
        String[] keys = new String[100_000];
        for (int i = 0; i < keys.length; i++)
            keys[i] = String.format("MED%07d", i % n);
        long start = System.nanoTime();
        int sum = 0;
        for (String key : keys) sum += map.get(key);
        sink = sum;
        return System.nanoTime() - start;
    }

    private static long reportTime(Map<String, Integer> map, boolean sortByCode) {
        long start = System.nanoTime();
        List<String> keys = new ArrayList<>(map.keySet());
        if (sortByCode) Collections.sort(keys);
        int sum = 0;
        for (String key : keys) sum += map.get(key);
        sink = sum;
        return System.nanoTime() - start;
    }

    private static void run(int n) {
        System.out.println("\nMedicines = " + n);
        for (String kind : List.of("HashMap", "TreeMap", "LinkedHashMap")) {
            Map<String, Integer> map = create(kind, n);
            long lookups = lookupTime(map, n);
            long codeReport = reportTime(map, true);
            long insertionReport = reportTime(map, false);
            System.out.printf("%s | 100,000 lookups=%d ns | code-order report=%d ns"
                    + " | insertion-order traversal=%d ns%n",
                    kind, lookups, codeReport, insertionReport);
            System.out.println("First 3 keys in native iteration: "
                    + map.keySet().stream().limit(3).toList());
        }
    }

    public static void main(String[] args) {
        System.out.println("Q4: Map comparison. Timings are dependent on machine and JVM.");
        run(1_000);
        run(10_000);
        run(50_000);
    }
}
