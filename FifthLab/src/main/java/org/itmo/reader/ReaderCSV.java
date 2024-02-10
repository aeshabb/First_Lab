package org.itmo.reader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReaderCSV {
    private static String[] headers;
    public Map<String, List<String>> readCSV(String path) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(path)).build();

        Map<String, List<String>> stringListMap = new HashMap<>();
        String[] headers = reader.readNext();
        ReaderCSV.headers = headers;
        for (String cur : headers)
            stringListMap.put(cur, new ArrayList<String>());

        for (String[] row : reader.readAll()) {
            for (int i = 0; i < row.length; i++) {
                stringListMap.get(headers[i]).add(row[i]);
            }

        }
        return stringListMap;
    }
    public static String[] getHeaders() {
        return headers;
    }
}
