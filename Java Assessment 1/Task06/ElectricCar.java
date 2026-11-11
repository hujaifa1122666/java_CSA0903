
public class ElectricCar extends Car {

    private int batteryCapacity;

    public ElectricCar(String vehicleNumber, int numberOfSeats,
                       int batteryCapacity) {

        super(vehicleNumber, numberOfSeats);
        this.vehicleType = "Electric Car";
        this.batteryCapacity = batteryCapacity;
    }

    public void displayElectricCarDetails() {
        displayCarDetails();
        System.out.println("Battery Capacity: " +
                           batteryCapacity + " kWh");
    }
}
