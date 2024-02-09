package org.itmo;

import com.opencsv.exceptions.CsvException;
import org.apache.commons.lang3.StringUtils;
import org.itmo.runner.Runner;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        System.setProperty("path", "C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\FifthLab\\src\\main\\resources\\db.csv");
        Runner runner = new Runner();
        runner.runMethods();
    }
}