package command;

import entity.Enrollee;

public class CompareScoreCommand implements Command {
    private final Receiver receiver;

    public CompareScoreCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        String[] info = line.split(" ");
        Enrollee enrollee1 = receiver.getEnrolleeById(Integer.parseInt(info[0]));
        Enrollee enrollee2 = receiver.getEnrolleeById(Integer.parseInt(info[1]));
        int enrolleeScore1 = receiver.getSubjectScore(enrollee1, info[2]);
        int enrolleeScore2 = receiver.getSubjectScore(enrollee2, info[2]);
        System.out.println("У первого абитуриента: " + enrolleeScore1 + " баллов. По предмету: " + info[2]);
        System.out.println("У второго абитуриента: " + enrolleeScore2 + " баллов. По предмету: " + info[2]);
    }
}
