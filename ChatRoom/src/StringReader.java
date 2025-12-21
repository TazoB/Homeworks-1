import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StringReader {
    private static InputStream is = System.in;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(is));

    public static String readString(String text) {
        if (System.in != is) {
            is = System.in;
            br = new BufferedReader(new InputStreamReader(is));
        }
        try {
            System.out.println(text);
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
