import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Parse {
    public static void parse(File folder) throws FileNotFoundException {
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) {
                System.out.println(file.getAbsolutePath());
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    WindowsM.zoneOutput.append(Decrypt.decrypt(sc.nextLine()));
                    WindowsM.zoneOutput.append("\n");
                }
            } else {
                parse(file);
            }
        }
    }
}
