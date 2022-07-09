import java.util.Objects;

public class Vehicle implements Comparable<Vehicle> {
    private VehicleType vehicleType;
    private String model;
    private String stateNumber;
    private double weight;
    private int year;
    private int mileage;
    private Color color;
    private Startable engine;

    public Vehicle() {
    }

    public Vehicle(VehicleType vehicleType, String model, String stateNumber,
                   double weight, int year, int mileage, Color color, Startable engine) {
        setVehicleType(vehicleType);
        setModel(model);
        setStateNumber(stateNumber);
        setWeight(weight);
        setYear(year);
        setMileage(mileage);
        setColor(color);
        setEngine(engine);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        if (TechnicalSpecialist.validateVehicleType(vehicleType)) {
            this.vehicleType = vehicleType;
        } else {
            this.vehicleType = getDefaultVehicleType();
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (TechnicalSpecialist.validateModelName(model)) {
            this.model = model;
        } else {
            this.model = "unknown";
        }
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        if (TechnicalSpecialist.validateRegistrationNumber(stateNumber)) {
            this.stateNumber = stateNumber;
        } else {
            this.stateNumber = "unknown";
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (TechnicalSpecialist.validateWeight(weight)) {
            this.weight = weight;
        } else {
            this.weight = 0.0d;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (TechnicalSpecialist.validateManufactureYear(year)) {
            this.year = year;
        } else {
            this.year = 9999;
        }
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (TechnicalSpecialist.validateMileage(mileage)) {
            this.mileage = mileage;
        } else {
            this.mileage = 0;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (TechnicalSpecialist.validateColor(color)) {
            this.color = color;
        } else {
            this.color = Color.White;
        }
    }

    public Startable getEngine() {
        return engine;
    }

    public void setEngine(Startable engine) {
        this.engine = engine;
    }

    public double getCalcTaxPerMonth() {
        double taxPerMonth = (this.weight * 0.0013) + (this.vehicleType.getTaxCoefficient() * this.engine.getTaxPerMonth() * 30) + 5;

        return taxPerMonth;
    }

    @Override
    public String toString() {
        return vehicleType.getString() + ", "
                + model + ", "
                + stateNumber + ", "
                + weight + ", "
                + year + ", "
                + mileage + ", "
                + color + ", "
                + this.getCalcTaxPerMonth() + ", "
                + engine.toString();
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
        } else {
            return this.mileage - o.mileage;
        }
    }

    private static VehicleType getDefaultVehicleType() {
        return new VehicleType("Unknown", 1d);
    }
}