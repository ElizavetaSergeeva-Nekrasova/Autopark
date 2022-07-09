public class Main {
    private static VehicleType[] vehicleTypes;
    private static Vehicle[] vehicles;

    public static void main(String[] args) {
        vehicleTypes = createVehicleTypeArray();
        vehicleTypes[vehicleTypes.length - 1].setTaxCoefficient(1.3d);
        vehicles = createVehicleArray();

        VehicleUtils.showVehicleArray();

        showIdenticalVehicles();

        showVehicleWithMaxKilometers();

    }

    private static class VehicleUtils {
        private static void showVehicleArray() {
            for (Vehicle vehicle:
                    vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private static VehicleType[] createVehicleTypeArray() {
        VehicleType[] vehicleTypes = {
                new VehicleType("Bus", 1.2d)
                , new VehicleType("Car", 1d)
                , new VehicleType("Rink", 1.5d)
                , new VehicleType("Tractor", 1.2d)
        };

        return vehicleTypes;
    }

    private static Vehicle[] createVehicleArray() {
        Vehicle[] vehicles = {
                new Vehicle(vehicleTypes[0], "Volkswagen Crafter", "5427 AX-7", 2022, 2015, 376000, Color.Blue, new GasolineEngine(2, 8.1, 75))
                , new Vehicle(vehicleTypes[0], "Volkswagen Crafter", "6427 AA-7", 2500, 2014, 227010, Color.White, new GasolineEngine(2.18, 8.5, 75))
                , new Vehicle(vehicleTypes[0], "Electric Bus E321", "6785 BA-7", 12080, 2019, 20451, Color.Green, new ElectricalEngine(50, 150))
                , new Vehicle(vehicleTypes[1], "Golf 5", "8682 AX-7", 1200, 2006, 230451, Color.Gray, new DieselEngine(1.6, 7.2, 55))
                , new Vehicle(vehicleTypes[1], "Tesla Model S 70D", "0001 AA-7", 2200, 2019, 10454, Color.White, new ElectricalEngine(25, 70))
                , new Vehicle(vehicleTypes[2], "Hamm HD 12 VV", "null", 3000, 2016, 122, Color.Yellow, new DieselEngine(3.2, 25, 20))
                , new Vehicle(vehicleTypes[3], "МТЗ Беларус-1025.4", "1145 AB-7", 1200, 2020, 109, Color.Red, new DieselEngine(4.75, 20.1, 135))
        };

        return vehicles;
    }

    private static void swap(Vehicle[] vehicles, int a, int b) {
        Vehicle tmp = vehicles[a];
        vehicles[a] = vehicles[b];
        vehicles[b] = tmp;
    }

    private static void selectionSort() {
        for (int i = 0; i < vehicles.length; i++) {
            int minIndex = i;
            for (int j = i; j < vehicles.length; j++) {
                if (vehicles[j].compareTo(vehicles[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            swap(vehicles, i, minIndex);
        }
    }

    private static void showIdenticalVehicles() {
        selectionSort();

        int counter = 0;
        for (int i = 1; i < vehicles.length; i++) {
            if (vehicles[i].equals(vehicles[i - 1])) {
                counter++;
                if ((i + 1) == vehicles.length) {
                    showArrayFragment(i + 1, counter);
                    counter = 0;
                }
            } else {
                if (counter > 0) {
                    showArrayFragment(i, counter);
                    counter = 0;
                }
            }
        }
    }

    private static void showArrayFragment(int i, int counter) {
        System.out.println("Identical vehicles: ");
        for (int j = i - counter - 1; j < i; j++) {
            System.out.println(vehicles[j]);
        }
    }

    private static void showVehicleWithMaxKilometers() {
        Vehicle vehicleWithMaxKilometers = vehicles[0];

        for (Vehicle vehicle:
                vehicles) {
            if (vehicle.getEngine().getMaxKilometers() > vehicleWithMaxKilometers.getEngine().getMaxKilometers()) {
                vehicleWithMaxKilometers = vehicle;
            }
        }

        System.out.println("Vehicle with max kilometers: " + vehicleWithMaxKilometers);
    }
}