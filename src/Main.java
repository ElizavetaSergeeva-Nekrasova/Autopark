import java.util.List;

public class Main {
    public static void main(String[] args) {
        VehicleCollection vehicleCollection = new VehicleCollection("types", "vehicles", "rents");
        List<Vehicle> vehicleList = vehicleCollection.getVehicleList();
        MechanicService mechanicService = new MechanicService();

        List<Vehicle> brokenVehicleList = vehicleCollection.getBrokenVehicles(mechanicService);
        showVehicles(brokenVehicleList);

        List<Vehicle> sortedBrokenVehicleList = VehicleCollection.sortByNumberOfBrokenDetails(brokenVehicleList);
        showVehicles(sortedBrokenVehicleList);

        repairVehicleList(brokenVehicleList, mechanicService);

    }

    private static void showVehicles(List<Vehicle> vehicleList) {
        vehicleList.stream().forEach(System.out::println);
    }

    private static void repairVehicleList(List<Vehicle> brokenVehicleList, MechanicService mechanicService) {
        brokenVehicleList.stream().forEach(mechanicService::repair);
    }

    private static void showVolkswagenVehicles(List<Vehicle> vehicleList) {

    }
}