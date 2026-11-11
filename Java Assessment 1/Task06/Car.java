
public class Car extends Vehicle {

    protected int numberOfSeats;

    public Car(String vehicleNumber, int numberOfSeats) {
        super(vehicleNumber, "Car");
        this.numberOfSeats = numberOfSeats;
    }

    public void displayCarDetails() {
        displayVehicleDetails();
        System.out.println("Number of Seats: " + numberOfSeats);
    }
}
