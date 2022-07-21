import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromFile {
    public static List<String> readInfo(String inFile) {
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
}