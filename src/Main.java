public class Main {
    public static void main(String[] args) {
        VehicleType[] vehicleTypes = {
                new VehicleType("Bus", 1.2d)
                , new VehicleType("Car", 1d)
                , new VehicleType("Rink", 1.5d)
                , new VehicleType("Tractor", 1.2d)
        };

        double max = vehicleTypes[0].taxCoefficient;
        double sum = 0.0d;
        int typesCount = 0;
        double average = 0.0d;

        for (VehicleType vehicleType:
                vehicleTypes) {
            vehicleType.display();

            if (vehicleType.taxCoefficient > max) {
                max = vehicleType.taxCoefficient;
            }

            typesCount++;
            sum += vehicleType.taxCoefficient;
        }

        average = sum / typesCount;

        System.out.println (max);
        System.out.println(average);

        vehicleTypes[vehicleTypes.length - 1].setTaxCoefficient(1.3d);

        System.out.println (vehicleTypes[vehicleTypes.length - 1].taxCoefficient);
    }
}
