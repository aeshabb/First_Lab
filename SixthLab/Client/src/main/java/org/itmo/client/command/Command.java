package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public abstract class Command {
    protected Receiver receiver;
    protected InfoPrinter printer;
    protected InputStreamReader inputStreamReader;

    Command(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        this.receiver = receiver;
        this.printer = printer;
        this.inputStreamReader = inputStreamReader;
    }

    abstract public void execute(String[] parameters);

}
