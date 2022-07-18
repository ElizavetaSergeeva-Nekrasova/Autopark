import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer  {
    private static final String[] DETAILS = {"Фильтр", "Втулка", "Вал", "Ось", "Свеча",  "Масло", "ГРМ", "ШРУС"};
    private static final int NUMBER_OF_DETAILS = 8;
    private static final int MAX_NUMBER_OF_BROKEN_DETAILS = 5;
    private static final int MAX_NUMBER_OF_BREAKS = 5;

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Map<String, Integer> map = new HashMap<>();
        int numberOfBrokenDetails = getRandomNumberOfBrokenDetails();

        int i = 0;
        while (i < numberOfBrokenDetails) {
            String detail = DETAILS[getRandomArrayElement()];
            int numberOfBreaks = getRandomNumberOfBreaks();
            map.put(detail, numberOfBreaks);
            i++;
        }

        if (!map.isEmpty()) {
            writeToFile(vehicle, map);
        }

        return map;
    }

    @Override
    public void repair(Vehicle vehicle) {
        List<String> list = readInfo();
        String regex = vehicle.getId() + ".*";

        list.removeIf(i -> i.matches(regex));
        reWriteToFile(list);
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        List<String> list = readInfo();
        String regex = vehicle.getId() + ".*";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(regex)) {
                return true;
            }
        }

        return false;
    }

    private static int getRandomNumberOfBrokenDetails() {
        return (int) (Math.random() * MAX_NUMBER_OF_BROKEN_DETAILS);
    }

    private static int getRandomArrayElement() {
        return (int) (Math.random() * NUMBER_OF_DETAILS);
    }

    private static int getRandomNumberOfBreaks() {
        return (int) ((Math.random() * MAX_NUMBER_OF_BREAKS) + 1);
    }

    private static void writeToFile(Vehicle vehicle, Map<String, Integer> map) {
        String line = String.valueOf(vehicle.getId());

        for (Map.Entry<String, Integer> entry:
             map.entrySet()) {
            line = line + ", " + entry.getKey() + ", " + entry.getValue();
        }

        line = line + "\n";

        try {
            Files.write(Paths.get("orders.csv"), line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readInfo() {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("orders.csv"))) {
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

    private static void reWriteToFile(List<String> list) {
        try {
            Files.write(Paths.get("orders.csv"), list, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}