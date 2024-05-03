package org.itmo.server.runner;

import org.itmo.server.collection.Receiver;
import org.itmo.server.command.*;
import org.itmo.server.database.DatabaseReceiver;
import org.itmo.server.output.InfoPrinter;
import org.itmo.server.reader.ConsoleReader;
import org.itmo.server.thread.ReadThread;
import org.itmo.server.util.ConnectionManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.itmo.server.network.Network.*;

public class Runner {
    private int port;
    private Selector selector;
    private Map<String, Command> commandMap;
    private ServerSocketChannel server;
    private Receiver receiver;
    private DatabaseReceiver databaseReceiver;
    private final InfoPrinter infoPrinter;

    private final Map<SocketChannel, ReentrantLock> locksMap = new HashMap<>();

    public Runner(InfoPrinter infoPrinter) {
        this.infoPrinter = infoPrinter;
    }

    private boolean init() {
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

        Command infoCommand = new InfoCommand(receiver, "Информация о коллекции: \"info\"", printer);
        Command showCommand = new ShowCommand(receiver, "Вывести коллекцию: \"show\"", printer);
        Command addCommand = new AddCommand(receiver, "Добавить элемент в коллекцию: \"add\"", printer, databaseReceiver);
        Command loginCommand = new LoginCommand(receiver, "Войти в аккаунт: \"login\"", printer, databaseReceiver);
        Command registerCommand = new RegisterCommand(receiver, "Зарегистрировать аккаунт: \"register\"", printer, databaseReceiver);
        Command updateCommand = new UpdateCommand(receiver, "Заменить элемент в колекции по id: \"update + (id)\"", printer);
        Command removeByIdCommand = new RemoveByIdCommand(receiver, "Удалить Route по id из коллекции: \"remove_by_id + (id)\"", printer);
        Command clearCommand = new ClearCommand(receiver, "Очистить коллекцию: \"clear\"", printer);
        Command addMinCommand = new AddMinCommand(receiver, "Добавить элемент в коллекцию, если он минимальный: \"add_if_min\"", printer);
        Command removeLowerCommand = new RemoveLowerCommand(receiver, "Удалить все элементы, меньше данного: \"remove_lower + (distance)\"", printer);
        Command historyCommand = new HistoryCommand(receiver, "Вывести историю: \"history\"", printer);
        Command minByFromCommand = new MinByFromCommand(receiver, "Вывести Route с минимальным полем LocationFrom: \"min_by_from\"", printer);
        Command countRoutesLessDistanceCommand = new CountRoutesLessDistanceCommand(receiver, "Вывести количество Route с меньшим полем distance: \"count_less_than_distance + (distance)\"", printer);
        Command filterRoutesLessDistanceCommand = new FilterRoutesLessDistance(receiver, "Вывести Route с меньшим полем distance: \"filter_less_than_distance + (distance)\"", printer);

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", infoCommand);
        commandMap.put("show", showCommand);
        commandMap.put("add", addCommand);
        commandMap.put("login", loginCommand);
        commandMap.put("register", registerCommand);
        commandMap.put("update", updateCommand);
        commandMap.put("remove_by_id", removeByIdCommand);
        commandMap.put("clear", clearCommand);
        commandMap.put("add_if_min", addMinCommand);
        commandMap.put("remove_lower", removeLowerCommand);
        commandMap.put("history", historyCommand);
        commandMap.put("min_by_from", minByFromCommand);
        commandMap.put("count_less_than_distance", countRoutesLessDistanceCommand);
        commandMap.put("filter_less_than_distance", filterRoutesLessDistanceCommand);

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

    public ServerSocketChannel choosePort() {
        Scanner scanner = new Scanner(System.in);
        ServerSocketChannel serverSocketChannel = null;

        while (true) {
            infoPrinter.printLine("Введите номер порта: ");
            try {
                int port = Integer.parseInt(scanner.nextLine());
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.socket().bind(new InetSocketAddress(port));
                this.port = port;
                break; // Если порт доступен, выходим из цикла
            } catch (NumberFormatException e) {
                infoPrinter.printLine("Неверный формат порта. Пожалуйста, введите целое число.");
            } catch (IOException e) {
                infoPrinter.printLine("Порт занят. Пожалуйста, выберите другой порт.");
            }
        }

        return serverSocketChannel;
    }

    private void listen() throws IOException {
            while (true) {
                selector.selectNow();
                Set<SelectionKey> keys = selector.selectedKeys();

                for (var iter = keys.iterator(); iter.hasNext(); ) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            var newKey = accept(key);
                            locksMap.put((SocketChannel) newKey.channel(), new ReentrantLock());
                        }
                        if (key.isReadable()) {
                            var attachment = key.attachment();
                            key.channel().register(selector, 0).attach(attachment);
                            ReadThread readThread = new ReadThread(key, locksMap.get(key.channel()), commandMap);
                            Thread thread = new Thread(readThread);
                            thread.start();
                        }

                    }
                }
            }

    }


    public void exit() throws IOException {
        selector.wakeup();
        server.close();
        ConnectionManager.closePool();
        System.exit(0);
    }


    public void start() throws IOException {
        server = choosePort();

        this.databaseReceiver = new DatabaseReceiver();
        this.receiver = new Receiver(databaseReceiver.fillRouteStorage());

        commandMap = fillCommandMap(receiver, infoPrinter);
        Thread consoleReaderThread = new Thread(new ConsoleReader(receiver, this));
        consoleReaderThread.start();
        if (init()) {
            listen();
        } else {
            infoPrinter.printLine("Ошибка инициализации сервера!");
        }

    }

}
