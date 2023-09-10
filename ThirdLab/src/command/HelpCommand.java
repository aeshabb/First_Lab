package command;

public class HelpCommand implements Command {
    private final Receiver receiver;

    public HelpCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        System.out.println("Список команд:");
        System.out.println("Удалить абитуриента по id: \"delete + (id абитуриента)\"");
        System.out.println("Изменить балл ЕГЭ абитуриента: \"updateScore + (id абитуриента) + (название предмета) + (балл предмета)\"");
        System.out.println("Изменить название направления: \"changeDirName + (старое название) + > + (новое название)\"");
        System.out.println("Сравнить баллы двух абитуриентов: \"compare + (id первого) + (id второго) + (название предмета)\"");
        System.out.println("Вывести список абитуриентов: \"showAllEnrollees\"");
        System.out.println("Вывести список направлений: \"showAllDirections\"");
        System.out.println("Вывести количество абитуриентов с оригиналами на направлении: \"showOriginals + (название направления)\"");
        System.out.println("Завершить работу: \"quit\"");

    }
}
