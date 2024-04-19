package org.itmo.server.command;

import org.itmo.dto.reply.Reply;
import org.itmo.dto.request.Request;
import org.itmo.server.collection.Receiver;
import org.itmo.server.output.InfoPrinter;

public class RemoveLowerCommand extends Command {

    public RemoveLowerCommand(Receiver receiver, String description, InfoPrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public Reply process(Request request) {
        return null;
    }
}
