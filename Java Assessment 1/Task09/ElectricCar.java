
public class ElectricCar extends Car implements Chargeable {

    public ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double ratePerHour() {
        return 30;
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
