package org.itmo.server;

import org.itmo.server.runner.Runner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("CSVPATH", "C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\FifthLab\\src\\main\\resources\\db.csv");
        Runner runner = new Runner(5252);
        runner.start(System.getProperty("CSVPATH"));
    }
}