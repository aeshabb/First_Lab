package handlers;

import entity.Division;
import entity.Enrollee;
import entity.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDataHandlerImpl implements EnrolleeDataHandler {
    @Override
    public List<Enrollee> handle(List<String> list) throws IOException {
        List<Enrollee> enrollees = new ArrayList<>();
        for (String line : list) {
            String[] infoAboutEnrollees = line.split(" \\| ");

            int enrolleeId = Integer.parseInt(infoAboutEnrollees[0].substring(1, 10));
            String[] subjectsInfo = infoAboutEnrollees[1].split(", ");
            Subject[] subjects = new Subject[3];

            int count = 0;
            for (String subject : subjectsInfo) {
                String[] subjectInfo = subject.split(" ");
                if (subjectInfo.length == 2) {
                    String name = subjectInfo[0];
                    int score = Integer.parseInt(subjectInfo[1]);
                    subjects[count] = new Subject(name, score);
                } else {
                    String name = subjectInfo[0] + " " + subjectInfo[1];
                    int score = Integer.parseInt(subjectInfo[2]);
                    subjects[count] = new Subject(name, score);
                }
                count++;
            }
            String[] divisionsInfo = infoAboutEnrollees[2].split(" ");
            Division[] divisions = new Division[divisionsInfo.length];

            int i = 0;
            for (String divisionName : divisionsInfo) {
                divisions[i] = new Division(divisionName);
                i++;
            }

            boolean privilages;
            if (infoAboutEnrollees[3].equals("-")) {
                privilages = false;
            } else {
                privilages = true;
            }

            boolean target;
            if (infoAboutEnrollees[4].equals("-")) {
                target = false;
            } else {
                target = true;
            }

            Division originalsToDivision;
            if (infoAboutEnrollees[5].equals("-")) {
                originalsToDivision = null;
            } else {
                originalsToDivision = new Division(infoAboutEnrollees[5]);
            }

            enrollees.add(new Enrollee(enrolleeId, subjects, divisions, privilages, target, originalsToDivision));
        }
        return enrollees;
    }
}
