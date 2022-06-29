public class Main {
    private static double max;
    private static double sum;
    private static double average;
    private static int vehicleTypesCount;
    private static VehicleType[] vehicleTypes;

    public static void main(String[] args) {
        vehicleTypes = createArray();

        displayAndCountMaxAndCountAverage();
        showMax();
        showAverage();

        vehicleTypes[vehicleTypes.length - 1].setTaxCoefficient(1.3d);
    }

    private static VehicleType[] createArray() {
        VehicleType[] vehicleTypes = {
                new VehicleType("Bus", 1.2d)
                , new VehicleType("Car", 1.0d)
                , new VehicleType("Rink", 1.5d)
                , new VehicleType("Tractor", 1.2d)
        };

        return vehicleTypes;
    }

    private static void displayAndCountMaxAndCountAverage() {
        max = vehicleTypes[0].getTaxCoefficient();

        for (VehicleType vehicleType:
                vehicleTypes) {
            vehicleType.display();

            if (vehicleType.getTaxCoefficient() > max) {
                max = vehicleType.getTaxCoefficient();
            }

            vehicleTypesCount++;

            sum += vehicleType.getTaxCoefficient();
        }

        average = countAverage();
    }

    private static void showMax() {
        System.out.println(max);
    }

    private static void showAverage() {
        System.out.println(average);
    }

    private static double countAverage () {
        double average = sum / vehicleTypesCount;

        return average;
     }
 }
