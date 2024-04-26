package org.itmo.server;

import org.itmo.parser.ParseCSV;
import org.itmo.server.output.InfoPrinter;
import org.itmo.server.runner.Runner;

import java.io.IOException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("CSVPATH", "C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\SixthLab\\Server\\src\\main\\resources\\db.csv");
        ParseCSV.readCSV(System.getProperty("CSVPATH"));
        Runner runner = new Runner(new InfoPrinter(new PrintStream(System.out)));
        runner.start();
    }
}