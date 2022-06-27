public class Main {
    static double max;
    static double sum;
    static double average;
    static int vehicleTypesCount;

    public static void main(String[] args) {
        VehicleType[] vehicleTypes = createArray();

        max = vehicleTypes[0].taxCoefficient;
        sum = 0.0d;
        vehicleTypesCount = 0;
        average = 0.0d;

        for (VehicleType vehicleType:
                vehicleTypes) {
            vehicleType.display();

            if (vehicleType.taxCoefficient > max) {
                max = vehicleType.taxCoefficient;
            }

            vehicleTypesCount++;

            sum += vehicleType.taxCoefficient;
        }

        average = countAverage();

        showMax();
        showAverage();

        vehicleTypes[vehicleTypes.length - 1].setTaxCoefficient(1.3d);
    }

    private static VehicleType[] createArray() {
        VehicleType[] vehicleTypes = {
                new VehicleType("Bus", 1.2d)
                , new VehicleType("Car", 1d)
                , new VehicleType("Rink", 1.5d)
                , new VehicleType("Tractor", 1.2d)
        };

        return vehicleTypes;
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
