import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Deque;

public class ListBenchmark {
    private static volatile long sink;

    private static long measure(Runnable action, int repeats) {
        for (int i = 0; i < 2; i++) action.run();
        long start = System.nanoTime();
        for (int i = 0; i < repeats; i++) action.run();
        return (System.nanoTime() - start) / repeats;
    }

    private static void testSize(int n) {
        List<Integer> array = new ArrayList<>();
        List<Integer> linked = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            array.add(i); linked.add(i); deque.addLast(i);
        }

        long arrayAppend = measure(() -> array.add(-1), 2_000);
        long linkedAppend = measure(() -> linked.add(-1), 2_000);
        long dequeAppend = measure(() -> deque.addLast(-1), 2_000);

        long arrayFront = measure(() -> { array.add(0, -1); array.remove(0); }, 500);
        long linkedFront = measure(() -> { linked.add(0, -1); linked.remove(0); }, 500);
        long dequeFront = measure(() -> { deque.addFirst(-1); deque.removeFirst(); }, 500);

        int index = n / 2;
        long arrayGet = measure(() -> sink = array.get(index), 20_000);
        long linkedGet = measure(() -> sink = linked.get(index), 2_000);

        long arrayIter = measure(() -> {
            long sum = 0; for (int value : array) sum += value; sink = sum;
        }, 100);
        long linkedIter = measure(() -> {
            long sum = 0; for (int value : linked) sum += value; sink = sum;
        }, 100);
        long dequeIter = measure(() -> {
            long sum = 0; for (int value : deque) sum += value; sink = sum;
        }, 100);

        System.out.printf("N=%d%n", n);
        System.out.printf("append ns/op: ArrayList=%d LinkedList=%d ArrayDeque=%d%n",
                arrayAppend, linkedAppend, dequeAppend);
        System.out.printf("front add+remove ns/op: ArrayList=%d LinkedList=%d ArrayDeque=%d%n",
                arrayFront, linkedFront, dequeFront);
        System.out.printf("middle get ns/op: ArrayList=%d LinkedList=%d%n", arrayGet, linkedGet);
        System.out.printf("full iteration ns/op: ArrayList=%d LinkedList=%d ArrayDeque=%d%n",
                arrayIter, linkedIter, dequeIter);
    }

    public static void main(String[] args) {
        System.out.println("Q2: List benchmark. Values are machine/JVM dependent.");
        testSize(1_000);
        testSize(10_000);
        testSize(50_000);
    }
}
