
import java.util.Objects;

abstract class Vehicle {

    private final String regNo;

    protected Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    // Each subclass provides its own hourly rate
    protected abstract double ratePerHour();

    // Calculate parking fee
    public double calculateFee(int minutes) {

        if (minutes < 0) {
            throw new IllegalArgumentException(
                "Parking duration cannot be negative"
            );
        }

        int hours = (minutes + 59) / 60;

        return hours * ratePerHour();
    }

    // Override toString()
    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "[Registration Number=" + regNo + "]";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Vehicle)) {
            return false;
        }

        Vehicle other = (Vehicle) obj;

        return regNo.equals(other.regNo);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(regNo);
    }
}

class TwoWheeler extends Vehicle {

    public TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 10;
    }
}

class Car extends Vehicle {

    public Car(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

class ElectricCar extends Car {

    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    protected double ratePerHour() {
        return 30;
    }
}

class OverrideDemo {

    public static void main(String[] args) {

        Vehicle bike = new TwoWheeler("TN01A1");
        Vehicle car = new Car("TN02B2");
        Vehicle electricCar = new ElectricCar("TN03C3");

        System.out.println("TASK 7 - METHOD OVERRIDING");
        System.out.println("--------------------------------");

        System.out.println(bike);
        System.out.println("Parking Fee: Rs. " +
                bike.calculateFee(130));

        System.out.println();

        System.out.println(car);
        System.out.println("Parking Fee: Rs. " +
                car.calculateFee(130));

        System.out.println();

        System.out.println(electricCar);
        System.out.println("Parking Fee: Rs. " +
                electricCar.calculateFee(130));

        System.out.println("\nEQUALS METHOD DEMONSTRATION");

        Vehicle car1 = new Car("TN02B2");
        Vehicle car2 = new ElectricCar("TN02B2");

        System.out.println("Are vehicles equal? "
                + car1.equals(car2));

        System.out.println("Same hash code? "
                + (car1.hashCode() == car2.hashCode()));
    }
}
