package org.itmo.command;

import org.itmo.output.ConsolePrinter;

import java.io.PrintWriter;

public abstract class Command {
    protected Receiver receiver;
    protected String description;
    protected ConsolePrinter printer;

    Command(Receiver receiver, String description, ConsolePrinter printer) {
        this.receiver = receiver;
        this.description = description;
        this.printer = printer;
    }
    abstract public void execute(String[] parameters);

    public String getDescription() {
        return description;
    }
}
