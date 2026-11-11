
import java.time.Duration;
import java.time.LocalTime;

// Interface for vehicles that support charging
interface Chargeable {
    double chargingFee(int kWh);
}

// Abstract parent class
abstract class Vehicle {

    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    // Overloaded method 1: Calculate fee using minutes
    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Minutes cannot be negative"
            );
        }

        // Every started hour is charged in full
        int hours = (minutes + 59) / 60;

        return hours * ratePerHour();
    }

    // Overloaded method 2: Calculate fee using entry and exit times
    public double calculateFee(LocalTime in, LocalTime out) {

        if (in == null || out == null) {
            throw new IllegalArgumentException(
                "Entry and exit times cannot be null"
            );
        }

        long minutes = Duration.between(in, out).toMinutes();

        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Exit time cannot be before entry time"
            );
        }

        return calculateFee((int) minutes);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Vehicle
                && regNo.equals(((Vehicle) o).regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

// Two-wheeler class
class TwoWheeler extends Vehicle {

    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

// Car class
class Car extends Vehicle {

    Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

// Electric car implements Chargeable
class ElectricCar extends Car implements Chargeable {

    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {

        if (kWh < 0) {
            throw new IllegalArgumentException(
                "Charging units cannot be negative"
            );
        }

        return kWh * 12;
    }
}

// Main class
public class FeeDemo {

    public static void main(String[] args) {

        // Upcasting: different objects stored in Vehicle array
        Vehicle[] parked = {
            new TwoWheeler("TN01A1"),
            new Car("TN02B2"),
            new ElectricCar("TN03C3")
        };

        System.out.println("CAMPUS SMART PARKING SYSTEM");
        System.out.println("--------------------------------");

        // Runtime polymorphism
        for (Vehicle v : parked) {

            double fee = v.calculateFee(130);

            // Interface capability check
            if (v instanceof Chargeable c) {
                fee += c.chargingFee(10);
            }

            System.out.println(
                v.getClass().getSimpleName()
                + " " + v.getRegNo()
                + " -> Rs " + fee
            );
        }

        // Demonstrating equals()
        System.out.println(
            new Car("TN02B2").equals(
                new ElectricCar("TN02B2")
            )
        );

        // Demonstrating compile-time polymorphism
        Vehicle testCar = new Car("TN04D4");

        double timeBasedFee = testCar.calculateFee(
            LocalTime.of(10, 0),
            LocalTime.of(11, 30)
        );

        System.out.println(
            "Overloaded method fee: Rs " + timeBasedFee
        );
    }
}
