
class Vehicle {
    String vehicleNumber;
    String vehicleType;

    // 1. Default constructor
    Vehicle() {
        this("UNKNOWN", "Car");
        System.out.println("Default constructor called.");
    }

    // 2. Parameterized constructor
    Vehicle(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        System.out.println("Parameterized constructor called.");
    }

    // 3. Overloaded constructor
    Vehicle(String vehicleNumber) {
        this(vehicleNumber, "Two-Wheeler");
        System.out.println("Overloaded constructor called.");
    }

    // Display vehicle details
    void display() {
        System.out.println("Vehicle Number: " + this.vehicleNumber);
        System.out.println("Vehicle Type: " + this.vehicleType);
        System.out.println();
    }
}

public class ConstructorsDemo {

    public static void main(String[] args) {

        System.out.println("===== Default Constructor =====");
        Vehicle v1 = new Vehicle();
        v1.display();

        System.out.println("===== Parameterized Constructor =====");
        Vehicle v2 = new Vehicle("TN01AB1234", "Car");
        v2.display();

        System.out.println("===== Overloaded Constructor =====");
        Vehicle v3 = new Vehicle("TN01CD5678");
        v3.display();

        // Demonstrating object references
        System.out.println("===== Object Reference Demonstration =====");

        Vehicle v4 = v2;

        System.out.println("Before modifying through v4:");
        v2.display();

        v4.vehicleType = "Electric Car";

        System.out.println("After modifying through v4:");
        v2.display();

        System.out.println("v2 and v4 refer to the same object.");
    }
}
