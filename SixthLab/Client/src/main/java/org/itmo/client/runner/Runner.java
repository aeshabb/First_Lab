package org.itmo.client.runner;

import com.opencsv.exceptions.CsvException;
import org.itmo.client.command.*;
import org.itmo.client.controller.Invoker;
import org.itmo.client.output.InfoPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Runner для начала работы программы.
 */
public class Runner {
    private Socket socket;
    private Invoker invoker;
    private InfoPrinter commandPrinter;
    private final InfoPrinter infoPrinter;
    private InputStreamReader inputStreamReader;
    private BufferedReader br;

    public Runner(InfoPrinter commandPrinter, InputStreamReader inputStreamReader) {
        this.commandPrinter = commandPrinter;
        this.infoPrinter = new InfoPrinter(new PrintStream(System.out));
        this.inputStreamReader = inputStreamReader;

    }

    public Runner(InfoPrinter commandPrinter, InputStreamReader inputStreamReader, Socket socket) {
        this.commandPrinter = commandPrinter;
        this.infoPrinter = new InfoPrinter(new PrintStream(System.out));
        this.inputStreamReader = inputStreamReader;
        this.socket = socket;

    }

    public Socket connectToServer() {

        Socket socket = null;

        while (true) {
            try {
                infoPrinter.printLine("Введите адрес сервера: ");
                String host = br.readLine();
                infoPrinter.printLine("Введите порт сервера: ");
                int port = Integer.parseInt(br.readLine());
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 100);
                infoPrinter.printLine("Вы успешно подключились!");
                break; // Если удалось подключиться к серверу, выходим из цикла
            } catch (IOException | NumberFormatException e) {
                infoPrinter.printLine("Ошибка подключения к серверу. Пожалуйста, проверьте введенные данные.");
            }
        }

        return socket;
    }


    public void runMethods(boolean isScript) throws IOException, CsvException {
        br = new BufferedReader(inputStreamReader);
        if (!isScript) {
            this.socket = connectToServer();
        }

        initInvoker(commandPrinter);
        runCommands(isScript);
    }


    /**
     * @param printer
     */
    private void initInvoker(InfoPrinter printer) {
        this.commandPrinter = printer;
        Map<String, Command> commandMap = fillCommandMap(printer);
        invoker = new Invoker(commandMap);
    }


    private Map<String, Command> fillCommandMap(InfoPrinter printer) {


        Command infoCommand = new InfoCommand(socket, infoPrinter, inputStreamReader);
        Command showCommand = new ShowCommand(socket, infoPrinter, inputStreamReader);
        Command addCommand = new AddCommand(socket, printer, inputStreamReader);
        Command updateCommand = new UpdateCommand(socket, printer, inputStreamReader);
        Command removeByIdCommand = new RemoveByIdCommand(socket, printer, inputStreamReader);
        Command clearCommand = new ClearCommand(socket, infoPrinter, inputStreamReader);
        Command addMinCommand = new AddMinCommand(socket, printer, inputStreamReader);
        Command removeLowerCommand = new RemoveLowerCommand(socket, printer, inputStreamReader);
        Command historyCommand = new HistoryCommand(socket, infoPrinter, inputStreamReader);
        Command minByFromCommand = new MinByFromCommand(socket, infoPrinter, inputStreamReader);
        Command countRoutesLessDistanceCommand = new CountRoutesLessDistanceCommand(socket, infoPrinter, inputStreamReader);
        Command filterRoutesLessDistanceCommand = new FilterRoutesLessDistance(socket, infoPrinter, inputStreamReader);
        Command executeScript = new ExecuteScriptCommand(socket, infoPrinter, inputStreamReader);
        Command help = new HelpCommand(socket, infoPrinter, inputStreamReader);

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", infoCommand);
        commandMap.put("show", showCommand);
        commandMap.put("add", addCommand);
        commandMap.put("update", updateCommand);
        commandMap.put("remove_by_id", removeByIdCommand);
        commandMap.put("clear", clearCommand);
        commandMap.put("add_if_min", addMinCommand);
        commandMap.put("remove_lower", removeLowerCommand);
        commandMap.put("history", historyCommand);
        commandMap.put("min_by_from", minByFromCommand);
        commandMap.put("count_less_than_distance", countRoutesLessDistanceCommand);
        commandMap.put("filter_less_than_distance", filterRoutesLessDistanceCommand);
        commandMap.put("execute_script", executeScript);
        commandMap.put("help", help);

        return commandMap;
    }


    private void runCommands(boolean isScript) throws IOException, CsvException {

        String line;
        while (!"exit".equals(line = br.readLine())) {
//            if (socket.isBound()) {
                if (line == null) {
                    break;
                } else {
                    if (isScript) {
                        if (line.split(" ").length == 2) {
                            String[] par = line.split(" ");
                            if (par[0].equals("execute_script")) {
                                ScriptsCounter.scriptsList.add(par[1]);
                                ScriptsCounter.scriptsSet.add(par[1]);
                                if (ScriptsCounter.scriptsList.size() != ScriptsCounter.scriptsSet.size()) {
                                    infoPrinter.printLine("Рекурсия!!!");
                                    break;
                                }
                            }
                        }
                    } else {
                        if (line.split(" ").length == 2) {
                            String[] par = line.split(" ");
                            if (par[0].equals("execute_script")) {
                                ScriptsCounter.scriptsList.clear();
                                ScriptsCounter.scriptsSet.clear();
                                ScriptsCounter.scriptsSet.add(par[1]);
                                ScriptsCounter.scriptsList.add(par[1]);
                            }
                        }
                    }


                    if (!invoker.executeCommand(line)) {
                        infoPrinter.printLine("Неправильная команда");
                    }
                }
//            } else {
//                infoPrinter.printLine("Соединение потеряно, подключитесь еще раз.");
//                runMethods(isScript);
//            }
        }
        if (!ScriptsCounter.scriptsList.isEmpty()) {
            ScriptsCounter.scriptsSet.remove(ScriptsCounter.scriptsList.get(ScriptsCounter.scriptsList.size() - 1));
            ScriptsCounter.scriptsList.remove(ScriptsCounter.scriptsList.size() - 1);
        }

        br.close();
    }

}
