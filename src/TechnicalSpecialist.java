public class TechnicalSpecialist {
    public static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;

    static public boolean validateManufactureYear(int year) {
        if (year >= LOWER_LIMIT_MANUFACTURE_YEAR
                && year < 9999) {
            return true;
        }

        return false;
    }

    static public boolean validateMileage(int mileage) {
        if (mileage >= 0) {
            return true;
        }

        return false;
    }

    static public boolean validateWeight(double weight) {
        if (weight >= 0) {
            return true;
        }

        return false;
    }

    static public boolean validateColor(Color color) {
        if (color != null) {
            return true;
        }

        return false;
    }

    static public boolean validateVehicleType(VehicleType vehicleType) {
        if (vehicleType != null
                && vehicleType.getTaxCoefficient() >= 0.0d
                && vehicleType.getTypeName() != ""
                && vehicleType.getTypeName() != null) {
            return true;
        }

        return false;
    }

    static public boolean validateRegistrationNumber(String number) {
        if (number != null) {
            String regex = "\\d{4}\\s{1}[A-Z]{2}(-)\\d{1}";
            if (number.matches(regex)) {
                return true;
            }
        }

        return false;
    }

    static public boolean validateModelName(String name) {
        if (name != "") {
            return true;
        }

        return false;
    }
}