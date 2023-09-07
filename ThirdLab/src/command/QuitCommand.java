package command;

import reader.ReadConsoleCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuitCommand implements Command {
    private final Receiver receiver;

    public QuitCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        ReadConsoleCommand readConsoleCommand = new ReadConsoleCommand();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            readConsoleCommand.stopStream(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
