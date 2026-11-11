class ScannerStation extends Thread {
    private final int stationId;
    private final int scripts;

    ScannerStation(int stationId, int scripts) {
        super("ThreadStation-" + stationId);
        this.stationId = stationId;
        this.scripts = scripts;
    }

    @Override
    public void run() {
        process();
    }

    private void process() {
        try {
            Thread.sleep(100L + stationId * 70L);
            System.out.println(getName() + " scanned " + scripts + " scripts.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(getName() + " was interrupted.");
        }
    }
}

class RunnableStation implements Runnable {
    private final int stationId;
    private final int scripts;

    RunnableStation(int stationId, int scripts) {
        this.stationId = stationId;
        this.scripts = scripts;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(120L + stationId * 60L);
            System.out.println("RunnableStation-" + stationId + " scanned " + scripts + " scripts.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ThreadLifecycleDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread[] stations = {
                new ScannerStation(1, 100),
                new ScannerStation(2, 120),
                new ScannerStation(3, 90),
                new Thread(new RunnableStation(4, 110), "RunnableStation-4")
        };

        for (Thread station : stations) {
            System.out.println(station.getName() + " before start: " + station.getState());
            station.start();
        }

        for (Thread station : stations) {
            System.out.println(station.getName() + " alive before join: " + station.isAlive());
            station.join();
        }
        System.out.println("All stations completed. Total scripts = 420");
    }
}
