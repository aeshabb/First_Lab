import java.io.IOException;
import java.util.List;

public class EnrolleeDataHandler {
    private List<Enrollee> handledDataAboutEnrollee;
    public List<Enrollee> dataHandler() throws IOException {
    EnrolleeDataReader inf = new EnrolleeDataReader();
    List<String> list  = inf.readFromDatabase();
    for (String s : list) {
        String[] infoAboutEnrollee = s.split(" //| ");
        infoAboutEnrollee[0] = infoAboutEnrollee[0].substring(1, 10);
        Enrollee enrollee = new Enrollee(Integer.parseInt(infoAboutEnrollee[0]), infoAboutEnrollee[1], infoAboutEnrollee[2], infoAboutEnrollee[3], infoAboutEnrollee[4], infoAboutEnrollee[5]);
        handledDataAboutEnrollee.add(enrollee);
    }
    return handledDataAboutEnrollee;
}
}