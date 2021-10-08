import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parse {
    public static void parse(File folder) throws FileNotFoundException {
        for (File file : folder.listFiles()) {
            if (!file.isDirectory()) { // Si le fichier n'est pas un repertoire alors c'est un fichier texte
                System.out.println(file.getAbsolutePath());
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) { // On récupére clé des .txt
                    WindowsM.zoneOutput.append(Decrypt.decrypt(sc.nextLine()));
                    WindowsM.zoneOutput.append("\n");
                }
                sc.close();
            } else {
                parse(file);
            }
        }
    }
}
