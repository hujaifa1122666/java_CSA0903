import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class SalesReportDemo {
    private static List<String> readStoreFiles(List<Path> inputs) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Path file : inputs) {
            System.out.println("Reading: " + file);
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                while ((line = reader.readLine()) != null) lines.add(line);
            }
        }
        return lines;
    }

    // Deliberately unsafe version for debugger investigation:
    // if readLine throws after opening, execution skips reader.close().
    private static List<String> readManually(List<Path> inputs) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Path file : inputs) {
            BufferedReader reader = Files.newBufferedReader(file);
            System.out.println("Manual read: " + file);
            String line;
            while ((line = reader.readLine()) != null) lines.add(line);
            reader.close(); // skipped if readLine throws
        }
        return lines;
    }

    private static void writeReport(Path output, List<String> lines) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(output,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of("sample-data");
        Files.createDirectories(dir);
        Path store1 = dir.resolve("store1.txt");
        Path store2 = dir.resolve("store2.txt");
        Files.writeString(store1, "Store A,1200\nStore A,800\n");
        Files.writeString(store2, "Store B,950\nStore B,650\n");
        List<Path> inputs = List.of(store1, store2);

        List<String> manual = readManually(inputs);
        List<String> safe = readStoreFiles(inputs);
        Path manualOutput = dir.resolve("manual-report.txt");
        Path safeOutput = dir.resolve("safe-report.txt");
        writeReport(manualOutput, manual);
        writeReport(safeOutput, safe);

        System.out.println("Manual report lines: " + manual.size());
        System.out.println("Try-with-resources report lines: " + safe.size());
        System.out.println("Reports equal: " + manual.equals(safe));
        System.out.println("Output files: " + manualOutput + ", " + safeOutput);
    }
}
