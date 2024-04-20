package org.itmo.client.command;

import org.itmo.client.network.Network;
import org.itmo.client.output.InfoPrinter;
import org.itmo.dto.reply.HelpReply;
import org.itmo.dto.request.HelpRequest;

import java.io.InputStreamReader;

public class HelpCommand extends Command {

    public HelpCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);

    }

    @Override
    public void execute(String[] parameters) {
        HelpRequest request = new HelpRequest();
        HelpReply helpReply = (HelpReply) Network.sendAndReceive(receiver.getSocket(), request);
        if (helpReply != null && helpReply.isSuccess())
            printer.printLine(helpReply.getResult());
        else
            printer.printLine("Не удалось получить справочную информацию");
    }
    }


