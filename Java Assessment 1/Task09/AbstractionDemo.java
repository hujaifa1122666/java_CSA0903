
public class AbstractionDemo {

    public static void main(String[] args) {

        System.out.println("CAMPUS SMART PARKING SYSTEM");
        System.out.println("--------------------------------");

        // Abstract class reference
        Vehicle car = new Car("TN01AB1234");

        System.out.println("\nNORMAL CAR DETAILS");
        car.displayDetails();

        System.out.println("Parking Fee: Rs. "
                + car.calculateFee(3));

        // Electric car using abstraction and interface
        ElectricCar electricCar =
                new ElectricCar("TN02EV5678");

        System.out.println("\nELECTRIC CAR DETAILS");
        electricCar.displayDetails();

        System.out.println("Parking Fee: Rs. "
                + electricCar.calculateFee(3));

        System.out.println("Charging Fee: Rs. "
                + electricCar.chargingFee(10));

        // Default interface method
        electricCar.displayChargingStatus();

        // Static interface method
        Chargeable.displayChargingInformation();

        System.out.println("\nAbstraction demonstrated successfully.");
    }
}
