package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public class HelpCommand extends Command {

    public HelpCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);

    }

    @Override
    public void execute(String[] parameters) {

    }

}
