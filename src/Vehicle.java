import java.util.Objects;

public class Vehicle implements Comparable <Vehicle>{
    private VehicleType vehicleType;
    private String model;
    private String stateNumber;
    private double weight;
    private int year;
    private int mileage;
    private Color color;

    public Vehicle() {
    }

    public Vehicle(VehicleType vehicleType, String model, String stateNumber, double weight, int year, int mileage, Color color) {
        this.vehicleType = vehicleType;
        this.model = model;
        this.stateNumber = stateNumber;
        this.weight = weight;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getCalcTaxPerMonth() {
        double taxPerMonth = (this.weight * 0.0013) + (this.vehicleType.getTaxCoefficient() * 30) + 5;

        return taxPerMonth;
    }

    @Override
    public String toString() {
        return vehicleType.getString() +
                ", " + model +
                ", " + stateNumber +
                ", " + weight +
                ", " + year +
                ", " + mileage +
                ", " + color +
                ", " + this.getCalcTaxPerMonth()
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(model, vehicle.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, model);
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.year != o.year) {
            return this.year - o.year;
        }
        else {
            return this.mileage - o.mileage;
        }
    }
}