import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VehicleCollection {
    private static List<VehicleType> vehicleTypeList;
    private static List<Vehicle> vehicleList;
    private static List<Rent> rentList;

    private static final int ZERO_ARRAY_ELEMENT = 0;
    private static final int FIRST_ARRAY_ELEMENT = 1;
    private static final int SECOND_ARRAY_ELEMENT = 2;
    private static final int THIRD_ARRAY_ELEMENT = 3;
    private static final int FOURTH_ARRAY_ELEMENT = 4;
    private static final int FIFTH_ARRAY_ELEMENT = 5;
    private static final int SIXTH_ARRAY_ELEMENT = 6;
    private static final int SEVENTH_ARRAY_ELEMENT = 7;
    private static final int EIGHTH_ARRAY_ELEMENT = 8;
    private static final int NINTH_ARRAY_ELEMENT = 9;
    private static final int TENTH_ARRAY_ELEMENT = 10;
    private static final int ELEVENTH_ARRAY_ELEMENT = 11;
    private static final int NUMBER_OF_PARAMETERS_FOR_COMBUSTION_ENGINES = 12;



    public VehicleCollection(String types, String vehicles, String rents) {
        String typesFile = types + ".csv";
        String vehiclesFile = vehicles + ".csv";
        String rentsFile = rents + ".csv";

        vehicleTypeList = loadTypes(typesFile);
        vehicleList = loadVehicles(vehiclesFile);
        rentList = loadRents(rentsFile);
    }

    List<VehicleType> loadTypes(String typesFile) {
        List<String> list = readInfo(typesFile);
        List<VehicleType> typesList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            typesList.add(createType(list.get(i)));
        }

        return typesList;
    }

    List<Vehicle> loadVehicles(String vehiclesFile) {
        List<String> list = readInfo(vehiclesFile);
        List<Vehicle> vehiclesList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            vehiclesList.add(createVehicle(list.get(i)));
        }

        return vehiclesList;
    }

    List<Rent> loadRents(String rentsFile) {
        List<String> list = readInfo(rentsFile);
        List<Rent> rentsList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            rentsList.add(createRent(list.get(i)));
        }

        return rentsList;
    }

    VehicleType createType(String csvString) {
        String[] fields = getLineFields(csvString);

        VehicleType vehicleType = new VehicleType(
                Integer.parseInt(fields[ZERO_ARRAY_ELEMENT]),
                fields[FIRST_ARRAY_ELEMENT],
                Double.parseDouble(fields[SECOND_ARRAY_ELEMENT]));

        return vehicleType;
    }

    Vehicle createVehicle(String csvString) {
        String[] fields = getLineFields(csvString);

        Vehicle vehicle = new Vehicle(
                Integer.parseInt(fields[ZERO_ARRAY_ELEMENT]),
                getVehicleTypeById(Integer.parseInt(fields[FIRST_ARRAY_ELEMENT])),
                fields[SECOND_ARRAY_ELEMENT],
                fields[THIRD_ARRAY_ELEMENT],
                Double.parseDouble(fields[FOURTH_ARRAY_ELEMENT]),
                Integer.parseInt(fields[FIFTH_ARRAY_ELEMENT]),
                Integer.parseInt(fields[SIXTH_ARRAY_ELEMENT]),
                Color.valueOf(fields[SEVENTH_ARRAY_ELEMENT]),
                createEngine(fields));

        return vehicle;
    }

    Rent createRent(String csvString) {
        String[] fields = getLineFields(csvString);

        Rent rent = new Rent(
                Integer.parseInt(fields[ZERO_ARRAY_ELEMENT]),
                Date.valueOf(fields[FIRST_ARRAY_ELEMENT]),
                Double.parseDouble(fields[SECOND_ARRAY_ELEMENT]));

        return rent;
    }

    private Startable createEngine(String[] fields) {
        if (fields.length == NUMBER_OF_PARAMETERS_FOR_COMBUSTION_ENGINES) {
            if (fields[EIGHTH_ARRAY_ELEMENT].equals("Gasoline")) {
                return createGasolineEngine(fields);
            }

            return createDieselEngine(fields);
        }

        return createElectricalEngine(fields);
    }

    private GasolineEngine createGasolineEngine(String[] fields) {
        return new GasolineEngine(
                Double.parseDouble(fields[NINTH_ARRAY_ELEMENT]),
                Double.parseDouble(fields[TENTH_ARRAY_ELEMENT]),
                Double.parseDouble(fields[ELEVENTH_ARRAY_ELEMENT]));
    }

    private DieselEngine createDieselEngine(String[] fields) {
        return new DieselEngine(
                Double.parseDouble(fields[NINTH_ARRAY_ELEMENT]),
                Double.parseDouble(fields[TENTH_ARRAY_ELEMENT]),
                Double.parseDouble(fields[ELEVENTH_ARRAY_ELEMENT]));
    }

    private ElectricalEngine createElectricalEngine(String[] fields) {
        return new ElectricalEngine(
                Double.parseDouble(fields[NINTH_ARRAY_ELEMENT]),
                Double.parseDouble(fields[TENTH_ARRAY_ELEMENT]));
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

    private static VehicleType getVehicleTypeById(int id) {
        return vehicleTypeList.get(id - 1);
    }
}