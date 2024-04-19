package org.itmo.client.command;

import org.itmo.client.network.Network;
import org.itmo.client.output.InfoPrinter;
import org.itmo.dto.reply.AddReply;
import org.itmo.dto.request.AddRequest;
import org.itmo.entity.Route;

import java.io.InputStreamReader;

public class AddCommand extends Command {

    public AddCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);

    }

    public void execute(String[] args) {
        RouteParser routeParser = new RouteParser(receiver, printer);
        Route route = routeParser.parseRouteFromConsole(inputStreamReader);
        AddRequest addRequest = new AddRequest(route);
        AddReply addReply = (AddReply) Network.sendAndReceive(receiver.getSocket(), addRequest);
        if (addReply != null && addReply.isSuccess())
            printer.printLine(addReply.getMessage());
        else
            printer.printLine("Не удалось добавить элемент в коллекцию");
    }
}

