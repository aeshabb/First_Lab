package reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataReader implements DatabaseReader {

    public List<String> readFromDatabase(String path) throws IOException {
        List<String> fileInfo = new ArrayList<>();
        String line;

        File file = new File(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

        while ((line = br.readLine()) != null) {
            fileInfo.add(line);
        }
        br.close();
        return fileInfo;
    }
}
