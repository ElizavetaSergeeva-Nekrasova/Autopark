public class ElectricalEngine extends AbstractEngine{
    private double batteryCharge;
    private double electricityConsumptionPerKilometer;

    public ElectricalEngine(double batteryCharge, double electricityConsumptionPerKilometer) {
        super("Electrical", 0.1);
        this.batteryCharge = batteryCharge;
        this.electricityConsumptionPerKilometer = electricityConsumptionPerKilometer;
    }

    public double getBatteryCharge() {
        return batteryCharge;
    }

    public void setBatteryCharge(double batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public double getElectricityConsumptionPerKilometer() {
        return electricityConsumptionPerKilometer;
    }

    public void setElectricityConsumptionPerKilometer(double electricityConsumptionPerKilometer) {
        this.electricityConsumptionPerKilometer = electricityConsumptionPerKilometer;
    }

    @Override
    public double getTaxPerMonth() {
        return getTaxByEngineType();
    }

    @Override
    public double getMaxKilometers() {
        return batteryCharge / electricityConsumptionPerKilometer;
    }
}