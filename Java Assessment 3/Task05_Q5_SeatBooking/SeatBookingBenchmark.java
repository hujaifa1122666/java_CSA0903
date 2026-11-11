import java.util.concurrent.CountDownLatch;

public class SeatBookingBenchmark {
    static class Booking {
        final boolean[] seats;
        int sold;
        Booking(int n) { seats = new boolean[n]; }

        boolean bookUnsafe(int seat) {
            if (seats[seat]) return false;
            // Widen the race window for the experiment.
            Thread.yield();
            seats[seat] = true;
            sold++;
            return true;
        }

        synchronized boolean bookSynchronized(int seat) {
            if (seats[seat]) return false;
            seats[seat] = true;
            sold++;
            return true;
        }

        boolean bookWithBlock(int seat) {
            synchronized (this) {
                if (seats[seat]) return false;
                seats[seat] = true;
                sold++;
                return true;
            }
        }
    }

    private static void run(String label, int users, int seatCount, int mode)
            throws InterruptedException {
        Booking booking = new Booking(seatCount);
        CountDownLatch ready = new CountDownLatch(users);
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[users];
        long begin = System.nanoTime();
        for (int i = 0; i < users; i++) {
            final int seat = i % seatCount;
            threads[i] = new Thread(() -> {
                ready.countDown();
                try { start.await(); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); return;
                }
                if (mode == 0) booking.bookUnsafe(seat);
                else if (mode == 1) booking.bookSynchronized(seat);
                else booking.bookWithBlock(seat);
            });
            threads[i].start();
        }
        ready.await();
        start.countDown();
        for (Thread thread : threads) thread.join();
        long elapsed = System.nanoTime() - begin;
        System.out.printf("%s: users=%d seats=%d sold=%d elapsed=%d ns%n",
                label, users, seatCount, booking.sold, elapsed);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int r = 1; r <= 10; r++) {
            System.out.println("Run " + r);
            run("UNSAFE", 100, 10, 0);
            run("SYNCHRONIZED_METHOD", 100, 10, 1);
            run("SYNCHRONIZED_BLOCK", 100, 10, 2);
        }
    }
}
