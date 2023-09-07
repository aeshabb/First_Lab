package reader;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadConsoleCommand {
    public String readConsoleString(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public void stopStream(BufferedReader br) throws IOException {
        br.close();
    }
}
