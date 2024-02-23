package org.itmo;

import com.opencsv.exceptions.CsvException;
import org.itmo.database.RouteStorage;
import org.itmo.entity.Route;
import org.itmo.output.CommandPrinter;
import org.itmo.reader.ReaderCSV;
import org.itmo.runner.Runner;

import java.io.IOException;
import java.io.PrintStream;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException, CsvException {
        System.setProperty("CSVPATH", "C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\FifthLab\\src\\main\\resources\\db.csv");
        ReaderCSV.readCSV(System.getProperty("CSVPATH"));
        RouteStorage routeStorage = new RouteStorage((TreeSet<Route>) ReaderCSV.getRouteSet(), ReaderCSV.getInitTimeSet());
        Runner runner = new Runner(routeStorage, new CommandPrinter(new PrintStream(System.out)));
        runner.runMethods(System.in, false);
    }
}