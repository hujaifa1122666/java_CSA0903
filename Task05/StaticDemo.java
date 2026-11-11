
class ParkingFacility {

    // Static variable shared by all objects
    static String facilityName = "Campus Smart Parking";

    // Static variable to count registered vehicles
    static int totalVehicles = 0;

    // Instance variables
    String vehicleNumber;
    String vehicleType;

    // Static block executes once when the class is loaded
    static {
        System.out.println("Static block executed.");
        System.out.println("Parking facility initialized.");
        System.out.println();
    }

    // Constructor
    ParkingFacility(String vehicleNumber, String vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;

        totalVehicles++;
    }

    // Static method
    static void displayFacilityDetails() {
        System.out.println("Facility Name: " + facilityName);
        System.out.println("Total Registered Vehicles: " + totalVehicles);
        System.out.println();
    }

    // Instance method
    void displayVehicleDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println();
    }
}

public class StaticDemo {

    public static void main(String[] args) {

        System.out.println("===== Parking Facility Details =====");

        // Calling static method using class name
        ParkingFacility.displayFacilityDetails();

        System.out.println("===== Registering Vehicles =====");

        ParkingFacility v1 =
                new ParkingFacility("TN01AB1234", "Car");

        ParkingFacility v2 =
                new ParkingFacility("TN01CD5678", "Two-Wheeler");

        ParkingFacility v3 =
                new ParkingFacility("TN01EF9012", "Electric Car");

        // Displaying individual vehicle details
        v1.displayVehicleDetails();
        v2.displayVehicleDetails();
        v3.displayVehicleDetails();

        System.out.println("===== Updated Facility Details =====");

        // Static members are shared by all objects
        ParkingFacility.displayFacilityDetails();
    }
}
