package org.itmo.server.runner;

import org.itmo.dto.reply.Reply;
import org.itmo.dto.request.Request;
import org.itmo.entity.Route;
import org.itmo.parser.ParseCSV;
import org.itmo.server.collection.Receiver;
import org.itmo.server.collection.RouteStorage;
import org.itmo.server.command.*;
import org.itmo.server.output.InfoPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.*;

import static org.itmo.server.network.Network.*;

public class Runner {
    private final int port;
    private Selector selector;
    private Map<String, Command> commandMap;
    private ServerSocketChannel server;
    private RouteStorage routeStorage;
    private Receiver receiver;
    private InfoPrinter infoPrinter;

    public Runner(int port) {
        this.port = port;
    }

    private boolean init() {
        infoPrinter = new InfoPrinter(new PrintStream(System.out));
        commandMap = fillCommandMap(receiver, infoPrinter);
        try {
            selector = Selector.open();
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Map<String, Command> fillCommandMap(Receiver receiver, InfoPrinter printer) {
        PrintStream printStream = new PrintStream(System.out);

        Command infoCommand = new InfoCommand(receiver, "Информация о коллекции: \"info\"", printer);
        Command showCommand = new ShowCommand(receiver, "Вывести коллекцию: \"show\"", printer);
        Command addCommand = new AddCommand(receiver, "Добавить элемент в коллекцию: \"add\"", printer);
        Command updateCommand = new UpdateCommand(receiver, "Заменить элемент в колекции по id: \"update + (id)\"", printer);
        Command removeByIdCommand = new RemoveByIdCommand(receiver, "Удалить Route по id из коллекции: \"remove_by_id + (id)\"", printer);
        Command clearCommand = new ClearCommand(receiver, "Очистить коллекцию: \"clear\"", printer);
        Command saveCommand = new SaveCommand(receiver, "Сохранить коллекцию в файл \"save\"", printer);
        Command addMinCommand = new AddMinCommand(receiver, "Добавить элемент в коллекцию, если он минимальный: \"add_if_min\"", printer);
        Command removeLowerCommand = new RemoveLowerCommand(receiver, "Удалить все элементы, меньше данного: \"remove_lower + (distance)\"", printer);
        Command historyCommand = new HistoryCommand(receiver, "Вывести историю: \"history\"", printer);
        Command minByFromCommand = new MinByFromCommand(receiver, "Вывести Route с минимальным полем LocationFrom: \"min_by_from\"", printer);
        Command countRoutesLessDistanceCommand = new CountRoutesLessDistanceCommand(receiver, "Вывести количество Route с меньшим полем distance: \"count_less_than_distance + (distance)\"", printer);
        Command filterRoutesLessDistanceCommand = new FilterRoutesLessDistance(receiver, "Вывести Route с меньшим полем distance: \"filter_less_than_distance + (distance)\"", printer);
        Command executeScript = new ExecuteScriptCommand(receiver, "Чтение комманд со скрипта: \"execute_script + (filename)\"", printer);

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", infoCommand);
        commandMap.put("show", showCommand);
        commandMap.put("add", addCommand);
        commandMap.put("update", updateCommand);
        commandMap.put("remove_by_id", removeByIdCommand);
        commandMap.put("clear", clearCommand);
        commandMap.put("save", saveCommand);
        commandMap.put("add_if_min", addMinCommand);
        commandMap.put("remove_lower", removeLowerCommand);
        commandMap.put("history", historyCommand);
        commandMap.put("min_by_from", minByFromCommand);
        commandMap.put("count_less_than_distance", countRoutesLessDistanceCommand);
        commandMap.put("filter_less_than_distance", filterRoutesLessDistanceCommand);
        commandMap.put("execute_script", executeScript);

        Command help = new HelpCommand(receiver, "Вывести список команд: \"help\"", printer, createDescriptionList(commandMap));
        commandMap.put("help", help);

        return commandMap;
    }
    private List<String> createDescriptionList(Map<String, Command> commandMap) {
        List<String> descriptionList = new ArrayList<>();
        for (String key : commandMap.keySet()) {
            descriptionList.add(commandMap.get(key).getDescription());
        }
        return descriptionList;
    }

    private void listen() throws IOException {
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (var iter = keys.iterator(); iter.hasNext(); ) {
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    }
                    if (key.isReadable()) {
                        Request req = read(key, (HashMap<String, Command>) commandMap);
                        if (req != null) {
                            Reply reply = commandMap.get(req.name).process(req);
                            key.interestOps(SelectionKey.OP_WRITE);
                            key.attach(reply);
                        }
                    }
                    try {
                        if (key.isWritable()) {
                            write(key);
                            ByteBuffer buf = ByteBuffer.allocate(4096);
                            key.interestOps(SelectionKey.OP_READ);
                            key.attach(buf);
                        }
                    } catch (CancelledKeyException | IOException e) {
                        key.channel().close();
                        key.cancel();
                    }
                }
            }
        }
    }


    public void exit() throws IOException {
        selector.close();
        server.close();
        System.exit(0);
    }


    public void start(String fileName) throws IOException {

        this.routeStorage = new RouteStorage((TreeSet<Route>) ParseCSV.getRouteSet(), ParseCSV.getInitTimeSet());
        try {
            // Создаем BufferedReader для чтения ввода с консоли
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите команду (для выхода введите exit):");

            // Бесконечный цикл для чтения ввода до тех пор, пока не будет введено "exit"
            String input;
            while (true) {
                if ((reader.readLine()).equals("exit")) {
                    reader.close();
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения ввода из stdin");
        }


        if (init()) {
            listen();
        } else {
            System.out.println("Ошибка инициализации сервера!");
        }
    }

}
