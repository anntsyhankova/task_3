package ann.tsyhankova;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileService {

    private static FileService instance;
    private String filename;
    private FileService(String filename){
        this.filename = filename;
    }
    public static FileService getInstance(String filename){
        if (instance == null){
            instance = new FileService(filename);
        }
        return instance;
    }
    public String getTextFromFile() throws IOException {
        String text;
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        }
        return text;
    }
}
