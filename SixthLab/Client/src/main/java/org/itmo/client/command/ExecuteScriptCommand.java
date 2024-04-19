package org.itmo.client.command;

import com.opencsv.exceptions.CsvException;
import org.itmo.client.output.InfoPrinter;

import java.io.*;

public class ExecuteScriptCommand extends Command {

    public ExecuteScriptCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 1) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            try {
//                Runner runner = new Runner(new CommandPrinter(new PrintStream("nul")));
//                InputStream inputStream = new FileInputStream(parameters[0]);
//                runner.runMethods(inputStream, true);
//            } catch (CsvException | IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
