package org.itmo.reader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.itmo.entity.Coordinates;
import org.itmo.entity.LocationFrom;
import org.itmo.entity.LocationTo;
import org.itmo.entity.Route;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ReaderCSV {
    private static String[] headers;
    private static LocalDateTime initTimeSet;
    private static Set<Route> routeSet;
    public void readCSV(String path) throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(path)).build();

        Map<String, List<String>> stringListMap = new HashMap<>();
        String[] headers = reader.readNext();
        ReaderCSV.headers = headers;
        for (String cur : headers)
            stringListMap.put(cur, new ArrayList<>());

        for (String[] row : reader.readAll()) {
            for (int i = 0; i < row.length; i++) {
                stringListMap.get(headers[i]).add(row[i]);
            }

        }
        routeSet = fillRouteSet(stringListMap);
    }
    public static String[] getHeaders() {
        return headers;
    }

    private Set<Route> fillRouteSet(Map<String, List<String>> stringListMap) {
        Set<Route> routeSet = new TreeSet<>();
        int size = stringListMap.get("name").size();
        for (int i = 0; i < size; i++) {
            String name = stringListMap.get("name").get(i);
            Coordinates coordinates = new Coordinates(Double.parseDouble(stringListMap.get("xC").get(i)), Integer.parseInt(stringListMap.get("yC").get(i)));
            LocationFrom locationFrom = new LocationFrom(Long.parseLong(stringListMap.get("xLF").get(i)), Double.parseDouble(stringListMap.get("yLF").get(i)), Float.parseFloat(stringListMap.get("zLF").get(i)), stringListMap.get("nameLF").get(i));
            LocalDateTime date = LocalDateTime.now();
            LocationTo locationTo = new LocationTo(Double.parseDouble(stringListMap.get("xLT").get(i)), Long.parseLong(stringListMap.get("yLT").get(i)), Integer.parseInt(stringListMap.get("zLT").get(i)));
            Integer distance = Integer.parseInt(stringListMap.get("distance").get(i));
            routeSet.add(new Route(i + 1, name, coordinates, date, locationFrom, locationTo, distance));

        }
        this.initTimeSet = LocalDateTime.now();

        return routeSet;
    }

    public static LocalDateTime getInitTimeSet() {
        return initTimeSet;
    }

    public static Set<Route> getRouteSet() {
        return routeSet;
    }
}
