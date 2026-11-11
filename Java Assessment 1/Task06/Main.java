
public class Vehicle {

    protected String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    // Parent class method
    public void calculateParkingFee() {
        System.out.println("General vehicle parking fee: Rs. 50");
    }

    public void displayDetails() {
        System.out.println("Vehicle Number: " + vehicleNumber);
    }
}
