
public abstract class Vehicle {

    private final String regNo;

    public Vehicle(String regNo) {
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    // Abstract method
    public abstract double ratePerHour();

    // Concrete method
    public double calculateFee(int hours) {

        if (hours < 0) {
            throw new IllegalArgumentException(
                "Hours cannot be negative"
            );
        }

        return hours * ratePerHour();
    }

    public void displayDetails() {
        System.out.println("Registration Number: " + regNo);
    }
}
