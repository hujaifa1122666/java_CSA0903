import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Wallet {
    final String name;
    int balance;
    final ReentrantLock lock = new ReentrantLock();

    Wallet(String name, int balance) { this.name = name; this.balance = balance; }

    static boolean transferWithOrderedLocks(Wallet from, Wallet to, int amount)
            throws InterruptedException {
        Wallet first = from.name.compareTo(to.name) < 0 ? from : to;
        Wallet second = first == from ? to : from;
        first.lock.lockInterruptibly();
        try {
            second.lock.lockInterruptibly();
            try {
                if (from.balance < amount) return false;
                from.balance -= amount;
                to.balance += amount;
                return true;
            } finally {
                second.lock.unlock();
            }
        } finally {
            first.lock.unlock();
        }
    }
}

class TokenCounter {
    private final int[] buffer = new int[5];
    private int count = 0, in = 0, out = 0;

    public synchronized void put(int token) throws InterruptedException {
        // TODO 1 completed: loop while full, insert, then wake waiting consumers.
        while (count == buffer.length) wait();
        buffer[in] = token;
        in = (in + 1) % buffer.length;
        count++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        // TODO 2 completed: loop while empty, remove, wake waiting producers.
        while (count == 0) wait();
        int token = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        notifyAll();
        return token;
    }

    synchronized int size() { return count; }
}

public class DeadlockAndTokenDemo {
    public static void main(String[] args) throws InterruptedException {
        Wallet a = new Wallet("A", 1000);
        Wallet b = new Wallet("B", 1000);
        Thread transfer1 = new Thread(() -> {
            try { Wallet.transferWithOrderedLocks(a, b, 100); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread transfer2 = new Thread(() -> {
            try { Wallet.transferWithOrderedLocks(b, a, 50); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        transfer1.start(); transfer2.start();
        transfer1.join(); transfer2.join();
        System.out.println("Wallet total: " + (a.balance + b.balance));

        TokenCounter counter = new TokenCounter();
        int[] collected = new int[2];
        Thread cook = new Thread(() -> {
            try {
                for (int t = 1; t <= 20; t++) counter.put(t);
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread[] students = new Thread[2];
        for (int s = 0; s < 2; s++) {
            final int id = s;
            students[s] = new Thread(() -> {
                try {
                    for (int k = 0; k < 10; k++) collected[id] += counter.take();
                } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
        }
        cook.start();
        for (Thread t : students) t.start();
        cook.join();
        for (Thread t : students) t.join();
        System.out.println("Sum of tokens collected: " + (collected[0] + collected[1])
                + " (expected 210)");
        System.out.println("Counter size at end: " + counter.size());
    }
}
