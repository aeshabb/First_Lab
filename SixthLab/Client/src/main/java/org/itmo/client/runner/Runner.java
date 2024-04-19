package org.itmo.client.runner;

import com.opencsv.exceptions.CsvException;
import org.itmo.client.command.*;
import org.itmo.client.controller.Invoker;
import org.itmo.client.output.InfoPrinter;



import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

/**
 * Runner для начала работы программы.
 */
public class Runner {
    private final Socket socket;
    private final Receiver receiver;
    private Invoker invoker;
    private InfoPrinter commandPrinter;
    private InfoPrinter infoPrinter;
    private InputStreamReader inputStreamReader;

    public Runner(InfoPrinter commandPrinter, InputStreamReader inputStreamReader) {
        this.socket = connectToServer();
        this.receiver = new Receiver(socket);
        this.commandPrinter = commandPrinter;
        this.inputStreamReader = inputStreamReader;
    }

    public static Socket connectToServer() {
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;

        while (true) {
            try {
                System.out.print("Введите адрес сервера: ");
                String host = scanner.nextLine();
                System.out.print("Введите порт сервера: ");
                int port = Integer.parseInt(scanner.nextLine());
                socket = new Socket();
                socket.connect(new InetSocketAddress(host, port), 1000);
                break; // Если удалось подключиться к серверу, выходим из цикла
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка подключения к серверу. Пожалуйста, проверьте введенные данные.");
            }
        }

        return socket;
    }

    /**
     *
     * @param inputStream
     * @param isScript
     * @throws IOException
     * @throws CsvException
     */
    public void runMethods(InputStream inputStream, boolean isScript) throws IOException, CsvException {
        initInvoker(commandPrinter);
        runCommands(inputStream, isScript);
    }


    /**
     *
     * @param printer
     */
    private void initInvoker(InfoPrinter printer) {
        this.commandPrinter = printer;
        Map<String, Command> commandMap = fillCommandMap(receiver, printer);
        invoker = new Invoker(commandMap);
    }


    private Map<String, Command> fillCommandMap(Receiver receiver, InfoPrinter printer) {
        PrintStream printStream = new PrintStream(System.out);
        InfoPrinter infoPrinter = new InfoPrinter(printStream);
        this.infoPrinter = infoPrinter;

        Command infoCommand = new InfoCommand(receiver, infoPrinter, inputStreamReader);
        Command showCommand = new ShowCommand(receiver, infoPrinter, inputStreamReader);
        Command addCommand = new AddCommand(receiver,  printer, inputStreamReader);
        Command updateCommand = new UpdateCommand(receiver,  printer, inputStreamReader);
        Command removeByIdCommand = new RemoveByIdCommand(receiver, printer, inputStreamReader);
        Command clearCommand = new ClearCommand(receiver, infoPrinter, inputStreamReader);
        Command saveCommand = new SaveCommand(receiver,  infoPrinter, inputStreamReader);
        Command addMinCommand = new AddMinCommand(receiver,  printer, inputStreamReader);
        Command removeLowerCommand = new RemoveLowerCommand(receiver, printer, inputStreamReader);
        Command historyCommand = new HistoryCommand(receiver, infoPrinter, inputStreamReader);
        Command minByFromCommand = new MinByFromCommand(receiver, infoPrinter, inputStreamReader);
        Command countRoutesLessDistanceCommand = new CountRoutesLessDistanceCommand(receiver, infoPrinter, inputStreamReader);
        Command filterRoutesLessDistanceCommand = new FilterRoutesLessDistance(receiver, infoPrinter, inputStreamReader);
        Command executeScript = new ExecuteScriptCommand(receiver, infoPrinter, inputStreamReader);
        Command help = new HelpCommand(receiver, infoPrinter, inputStreamReader);

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
        commandMap.put("help", help);

        return commandMap;
    }


    private void runCommands(InputStream inputStream, boolean isScript) throws IOException {

        invoker.executeCommand("help");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while (!"exit".equals(line = br.readLine())) {
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
                    commandPrinter.printLine("Неправильная команда");
                }
            }
        }
        if (!ScriptsCounter.scriptsList.isEmpty()) {
            ScriptsCounter.scriptsSet.remove(ScriptsCounter.scriptsList.get(ScriptsCounter.scriptsList.size() - 1));
            ScriptsCounter.scriptsList.remove(ScriptsCounter.scriptsList.size() - 1);
        }

        br.close();
    }

}
