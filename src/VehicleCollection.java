import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VehicleCollection {
    private List<VehicleType> vehicleTypeList;
    private List<Vehicle> vehicleList;
    private List<Rent> rentList;

    private static final int NUMBER_OF_PARAMETERS_FOR_COMBUSTION_ENGINES = 12;

    public VehicleCollection(String types, String vehicles, String rents) {
        String typesFile = types + ".csv";
        String vehiclesFile = vehicles + ".csv";
        String rentsFile = rents + ".csv";

        vehicleTypeList = loadTypes(typesFile);
        rentList = loadRents(rentsFile);
        vehicleList = loadVehicles(vehiclesFile);
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void insert(int index, Vehicle v) {
        if(index >= vehicleList.size() || index < 0) {
            vehicleList.add(v);
        }
    }

    public int delete(int index) {
        if(index >= vehicleList.size() || index < 0) {
            return -1;
        }

        vehicleList.remove(index);

        return index;
    }

    public double sumTotalProfit() {
        double sum = 0;

        for (int i = 0; i < vehicleList.size(); i++) {
            sum += vehicleList.get(i).getTotalProfit();
        }

        return sum;
    }

    public void sort(Comparator<Vehicle> comparator) {
        Collections.sort(vehicleList, comparator);
    }

    public void display() {
        String templateForHeader = "\n%3s%10s%15s%25s%25s%15s%15s%15s%15s%12s%15s";
        System.out.printf(templateForHeader, "Id", "Type", "ModelName", "Number",
                "Weight (kg)", "Year", "Mileage", "Color", "Income", "Tax", "Profit");

        String templateForLines = "%-7d%-10s%-29s%-20s%-22.2f%-12d%-15d%-15s%-15.2f%-15.2f%-15.2f";

        int i = 0;
        while (i < vehicleList.size()) {
            Vehicle v = vehicleList.get(i);
            System.out.println();
            System.out.format(templateForLines, v.getId(), v.getVehicleType().getTypeName(), v.getModel(),
                    v.getStateNumber(), v.getWeight(), v.getYear(), v.getMileage(),
                    v.getColor(), v.getTotalIncome(), v.getCalcTaxPerMonth(), v.getTotalProfit());
            i++;
        }
        System.out.println();
        System.out.printf("%-160s%.2f", "Total", sumTotalProfit());
    }

    private List<VehicleType> loadTypes(String typesFile) {
        List<String> list = readInfo(typesFile);
        List<VehicleType> typesList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            typesList.add(createType(list.get(i)));
        }

        return typesList;
    }

    private List<Vehicle> loadVehicles(String vehiclesFile) {
        List<String> list = readInfo(vehiclesFile);
        List<Vehicle> vehiclesList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            vehiclesList.add(createVehicle(list.get(i)));
        }

        return vehiclesList;
    }

    private List<Rent> loadRents(String rentsFile) {
        List<String> list = readInfo(rentsFile);
        List<Rent> rentsList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            rentsList.add(createRent(list.get(i)));
        }

        return rentsList;
    }

    private VehicleType createType(String csvString) {
        String[] fields = getLineFields(csvString);

        VehicleType vehicleType = new VehicleType(
                Integer.parseInt(fields[0]),
                fields[1],
                Double.parseDouble(fields[2])
        );

        return vehicleType;
    }

    private Vehicle createVehicle(String csvString) {
        String[] fields = getLineFields(csvString);

        Vehicle vehicle = new Vehicle(
                rentList,
                Integer.parseInt(fields[0]),
                getVehicleTypeById(Integer.parseInt(fields[1])),
                fields[2],
                fields[3],
                Double.parseDouble(fields[4]),
                Integer.parseInt(fields[5]),
                Integer.parseInt(fields[6]),
                Color.valueOf(fields[7]),
                createEngine(fields)
        );

        return vehicle;
    }

    private Rent createRent(String csvString) {
        String[] fields = getLineFields(csvString);

        Rent rent = new Rent(
                    Integer.parseInt(fields[0]),
                    formatStringToDate(fields[1]),
                    Double.parseDouble(fields[2])
        );

        return rent;
    }

    private Startable createEngine(String[] fields) {
        if (fields.length == NUMBER_OF_PARAMETERS_FOR_COMBUSTION_ENGINES) {
            if (fields[8].equals("Gasoline")) {
                return createGasolineEngine(fields);
            }

            return createDieselEngine(fields);
        }

        return createElectricalEngine(fields);
    }

    private GasolineEngine createGasolineEngine(String[] fields) {
        return new GasolineEngine(
                Double.parseDouble(fields[9]),
                Double.parseDouble(fields[10]),
                Double.parseDouble(fields[11])
        );
    }

    private DieselEngine createDieselEngine(String[] fields) {
        return new DieselEngine(
                Double.parseDouble(fields[9]),
                Double.parseDouble(fields[10]),
                Double.parseDouble(fields[11])
        );
    }

    private ElectricalEngine createElectricalEngine(String[] fields) {
        return new ElectricalEngine(
                Double.parseDouble(fields[9]),
                Double.parseDouble(fields[10])
        );
    }

    private VehicleType getVehicleTypeById(int id) {
        return vehicleTypeList.get(id - 1);
    }

    private static List<String> readInfo(String inFile) {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile))) {
            String fileContent = null;
            while ((fileContent = bufferedReader.readLine()) != null) {
                list.add(fileContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Cannot read the file", e);
        }

        return list;
    }

    private static String formatString(String csvString) {
        String regex = "(\")(\\d+)(,)(\\d+)(\")";
        return csvString.replaceAll(regex, "$2" + "." + "$4");
    }

    private static String[] getLineFields(String csvString) {
        String formattedCsvString = formatString(csvString);
        String[] fields = formattedCsvString.split(",");

        return fields;
    }

    private static Date formatStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Wrong date format", e);
        }
    }
}