
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {
    private static VehicleType[] vehicleTypes;
    private static Vehicle[] vehicles;

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection("types", "vehicles", "rents");
        List<Vehicle> vehicleList = vehicleCollection.getVehicleList();
        MechanicService mechanicService = new MechanicService();

        fixVehicles(vehicleList, mechanicService);

        mechanicService.detectBreaking(vehicleList.get(0));
        mechanicService.repair(vehicleList.get(0));

        Rent rent = null;
        try {
            rent = rentVehicle(vehicleList.get(0), 100, mechanicService);
        } catch (DefectedVehicleException e) {
            e.printStackTrace();
        }

        getRentInfo(rent);
    }

    private static class VehicleUtils {
        private static void showVehicleArray() {
            for (Vehicle vehicle :
                    vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private static Rent rentVehicle(Vehicle vehicle, double cost,  MechanicService mechanicService)
            throws DefectedVehicleException {
        if (!mechanicService.isBroken(vehicle)) {
            return new Rent(vehicle.getId(), new Date(), cost);
        }

        throw new DefectedVehicleException("Vehicle is defected");
    }

    private static void getRentInfo(Rent rent) {
        if (rent != null) {
            System.out.println(rent);
        } else {
            System.out.println("Rent is not possible");
        }
    }

    private static void fixVehicles(List<Vehicle> vehicleList, MechanicService mechanicService) {
        int max = 0;
        Vehicle vehicleWithMaxBreaks = null;

        for (int i = 0; i < vehicleList.size(); i++) {
            Vehicle vehicle = vehicleList.get(i);
            Map<String, Integer> map = mechanicService.detectBreaking(vehicle);
            int breaksCount = getBreaksCount(map);

            if (breaksCount == 0) {
                System.out.println("Vehicle without breaks: " + vehicle);
            }

            if (breaksCount > max) {
                max = breaksCount;
                vehicleWithMaxBreaks = vehicle;
            }

            mechanicService.repair(vehicle);
        }

        System.out.println("Car with the maximum number of breaks: " + vehicleWithMaxBreaks);
    }

    private static int getBreaksCount(Map<String, Integer> map) {
        int numberOfBreaks = 0;

        if (map.isEmpty()) {
            return 0;
        }

        for (Map.Entry<String, Integer> entry:
                map.entrySet()) {
            numberOfBreaks += entry.getValue();
        }

        return numberOfBreaks;
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

        for (Vehicle vehicle :
                vehicles) {
            if (vehicle.getEngine().getMaxKilometers() > vehicleWithMaxKilometers.getEngine().getMaxKilometers()) {
                vehicleWithMaxKilometers = vehicle;
            }
        }

        System.out.println("Vehicle with max kilometers: " + vehicleWithMaxKilometers);
    }
}