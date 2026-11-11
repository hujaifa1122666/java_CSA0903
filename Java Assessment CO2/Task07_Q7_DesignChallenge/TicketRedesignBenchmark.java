public class TicketRedesignBenchmark {
    private static volatile int sink;

    // Original solution: immutable String concatenation inside a loop.
    private static String original(String[] fields) {
        String ticket = "";
        for (String field : fields) ticket = ticket + field + "|";
        return ticket;
    }

    // Redesigned solution: mutable builder, then one final String.
    private static String redesigned(String[] fields) {
        StringBuilder ticket = new StringBuilder();
        for (String field : fields) ticket.append(field).append('|');
        return ticket.toString();
    }

    private static long measure(boolean useBuilder, String[] fields, int tickets) {
        for (int i = 0; i < 2; i++) run(useBuilder, fields, tickets);
        long start = System.nanoTime();
        run(useBuilder, fields, tickets);
        return System.nanoTime() - start;
    }

    private static void run(boolean useBuilder, String[] fields, int tickets) {
        int checksum = 0;
        for (int i = 0; i < tickets; i++) {
            String value = useBuilder ? redesigned(fields) : original(fields);
            checksum += value.length();
        }
        sink = checksum;
    }

    public static void main(String[] args) {
        String[] fields = new String[50];
        for (int i = 0; i < fields.length; i++) fields[i] = "FIELD" + i + "_DATA";
        for (int tickets : new int[]{100, 1_000, 10_000}) {
            long oldTime = measure(false, fields, tickets);
            long newTime = measure(true, fields, tickets);
            System.out.printf("tickets=%d | original String + = %d ns | StringBuilder = %d ns%n",
                    tickets, oldTime, newTime);
        }
        System.out.println("Use repeated runs and record medians; this is a local microbenchmark.");
    }
}
