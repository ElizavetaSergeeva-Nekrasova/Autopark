import java.util.List;

public class Main {
    private static VehicleType[] vehicleTypes;
    private static Vehicle[] vehicles;

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection("types", "vehicles", "rents");
        List<Vehicle> vehicleList = vehicleCollection.getVehicleList();

        MyStack<Vehicle> vehicleMyStack = new MyStack<Vehicle>();
        garageModel(vehicleMyStack, vehicleList);
    }

    private static class VehicleUtils {
        private static void showVehicleArray() {
            for (Vehicle vehicle :
                    vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private static void garageModel(MyStack<Vehicle> vehicleMyStack, List<Vehicle> vehicleList) {
        int i = 0;
        for (; i < vehicleList.size(); i++) {
            vehicleMyStack.push(vehicleList.get(i));
            System.out.println("Auto" + (i + 1) + " заехало в гараж");
        }
        while (vehicleMyStack.size() != 0) {
            vehicleMyStack.pop();
            System.out.println("Auto" + i + " выехало из гаража");
            i--;
        }
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