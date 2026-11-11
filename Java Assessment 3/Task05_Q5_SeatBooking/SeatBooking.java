import java.util.concurrent.CountDownLatch;

public class SeatBooking {
    private final boolean[] booked;
    private int bookingsMade = 0;

    SeatBooking(int seats) {
        booked = new boolean[seats];
    }

    // TODO 1 completed: synchronized makes check-and-book atomic for this screen object.
    public synchronized boolean book(int seat) {
        if (seat < 0 || seat >= booked.length) throw new IllegalArgumentException("Invalid seat");
        if (booked[seat]) return false;
        booked[seat] = true;
        bookingsMade++;
        return true;
    }

    public int getBookingsMade() {
        synchronized (this) {
            return bookingsMade;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SeatBooking screen = new SeatBooking(10);
        Thread[] users = new Thread[100];
        for (int i = 0; i < users.length; i++) {
            final int seat = i % 10;
            users[i] = new Thread(() -> screen.book(seat));
            users[i].start();
        }
        for (Thread t : users) t.join();
        System.out.println("Seats sold: " + screen.getBookingsMade());
    }
}
