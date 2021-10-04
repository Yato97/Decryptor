import java.io.File;
import java.io.PrintWriter;

public class CreateTxt {
    public static void createTXT(String path) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(path + ".txt"));
            StringBuilder stringBuilder = new StringBuilder();

            String result = LocalCrypto.zoneOutput.getText();
            stringBuilder.append(result);

            printWriter.write(stringBuilder.toString());
            printWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
