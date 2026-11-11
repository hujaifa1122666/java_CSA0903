import java.util.Locale;

/**
 * Compares three ways of constructing ticket text.
 * Run on the same machine and repeat the program for meaningful observations.
 */
public class StringBenchmark {
    private static volatile int blackHole;

    private static String buildWithPlus(String[] fields) {
        String result = "";
        for (String field : fields) {
            result = result + field + "|";
        }
        return result;
    }

    private static String buildWithStringBuilder(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) {
            result.append(field).append('|');
        }
        return result.toString();
    }

    private static String buildWithStringBuffer(String[] fields) {
        StringBuffer result = new StringBuffer();
        for (String field : fields) {
            result.append(field).append('|');
        }
        return result.toString();
    }

    private static long time(Runnable work, int repetitions) {
        // Warm-up helps the JVM compile frequently used methods.
        for (int i = 0; i < Math.max(2, repetitions / 10); i++) work.run();
        long start = System.nanoTime();
        for (int i = 0; i < repetitions; i++) work.run();
        return (System.nanoTime() - start) / repetitions;
    }

    private static void runWorkload(String label, int fieldCount, int ticketCount) {
        String[] fields = new String[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = "FIELD" + i + "_VALUE";
        }

        int rounds = 5;
        long plusTotal = 0, builderTotal = 0, bufferTotal = 0;
        for (int r = 0; r < rounds; r++) {
            plusTotal += time(() -> {
                int checksum = 0;
                for (int t = 0; t < ticketCount; t++) checksum += buildWithPlus(fields).length();
                blackHole = checksum;
            }, 1);
            builderTotal += time(() -> {
                int checksum = 0;
                for (int t = 0; t < ticketCount; t++) checksum += buildWithStringBuilder(fields).length();
                blackHole = checksum;
            }, 1);
            bufferTotal += time(() -> {
                int checksum = 0;
                for (int t = 0; t < ticketCount; t++) checksum += buildWithStringBuffer(fields).length();
                blackHole = checksum;
            }, 1);
        }
        System.out.printf(Locale.US,
                "%s | fields=%d tickets=%d | String=%d ns | StringBuilder=%d ns | StringBuffer=%d ns%n",
                label, fieldCount, ticketCount, plusTotal / rounds,
                builderTotal / rounds, bufferTotal / rounds);
    }

    public static void main(String[] args) {
        System.out.println("Q1: Ticket text construction benchmark (averages of 5 measured rounds)");
        runWorkload("SMALL", 6, 1);
        runWorkload("MEDIUM", 20, 1_000);
        runWorkload("LARGE", 50, 10_000);
        System.out.println("Note: each displayed value is ns per complete workload, not ns per ticket.");
    }
}
