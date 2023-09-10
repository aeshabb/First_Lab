package reader;

import java.io.BufferedReader;
import java.io.IOException;

public class InputStream {
    private final BufferedReader br;

    public InputStream(BufferedReader br) {
        this.br = br;
    }

    public String readConsoleString() throws IOException {
        return br.readLine();
    }

    public void stopStream() throws IOException {
        br.close();
    }
}
