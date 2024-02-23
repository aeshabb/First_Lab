package org.itmo.command;

import com.opencsv.exceptions.CsvException;
import org.itmo.database.RouteStorage;
import org.itmo.entity.Route;
import org.itmo.output.CommandPrinter;
import org.itmo.reader.ReaderCSV;
import org.itmo.runner.Runner;

import java.io.*;
import java.util.List;
import java.util.TreeSet;

public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand(Receiver receiver, String description, CommandPrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            printer.printLine("Неверный ввод аргументов");
        } else {

            try {
                RouteStorage routeStorage = new RouteStorage((TreeSet<Route>) ReaderCSV.getRouteSet(), ReaderCSV.getInitTimeSet());
                Runner runner = new Runner(routeStorage, new CommandPrinter(new PrintStream("nul")));
                InputStream inputStream = new FileInputStream("C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\FifthLab\\src\\main\\resources\\" + parameters[0]);
                runner.runMethods(inputStream, true);
            } catch (CsvException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
