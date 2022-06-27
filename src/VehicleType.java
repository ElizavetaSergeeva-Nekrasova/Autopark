public class VehicleType {
    String typeName;
    double taxCoefficient;

    public VehicleType (String typeName, double taxCoefficient) {
        this.typeName = typeName;
        this.taxCoefficient = taxCoefficient;
    }

    public VehicleType() {
    }

    public String getTypeName() {
        return typeName;
    }

    public double getTaxCoefficient() {
        return taxCoefficient;
    }

    public void setTypeName (String typeName) {
        this.typeName = typeName;
    }

    public void setTaxCoefficient (double taxCoefficient) {
        this.taxCoefficient = taxCoefficient;
    }

    public void display() {
        System.out.println("typeName = " + this.typeName + "; taxCoefficient = " + this.taxCoefficient);
    }

    public String getString() {
        String result = this.typeName + ", " + this.taxCoefficient;

        return result;
    }
}
