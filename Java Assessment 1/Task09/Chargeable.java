
public interface Chargeable {

    // Abstract method
    double chargingFee(int kWh);

    // Default method
    default void displayChargingStatus() {
        System.out.println("Vehicle supports battery charging.");
    }

    // Static method
    static void displayChargingInformation() {
        System.out.println("Charging rate: Rs. 12 per kWh");
    }
}
