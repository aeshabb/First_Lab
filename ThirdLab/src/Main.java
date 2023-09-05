import entity.Subject;
import runner.Runner;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Runner run = new Runner();
        run.fillStorages();
        run.runCommands();
    }
}