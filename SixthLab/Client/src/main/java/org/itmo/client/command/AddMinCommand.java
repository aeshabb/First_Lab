package org.itmo.client.command;

import org.itmo.client.network.Network;
import org.itmo.client.output.InfoPrinter;
import org.itmo.dto.reply.AddMinReply;
import org.itmo.dto.request.AddMinRequest;
import org.itmo.entity.Route;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class AddMinCommand extends Command {

    public AddMinCommand(Socket socket, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(socket, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] args) {
        Route route;
        RouteParser routeParser = new RouteParser(printer);
        route = routeParser.parseRouteFromConsole(inputStreamReader);

        AddMinRequest addMinRequest = new AddMinRequest(route);
        AddMinReply addIfMinReply = (AddMinReply) Network.sendAndReceive(socket, addMinRequest);
        if (addIfMinReply != null && addIfMinReply.isSuccess())
            printer.printLine(addIfMinReply.getMessage());
        else
            printer.printLine("Не удалось добавить элемент в коллекцию");
    }
}
