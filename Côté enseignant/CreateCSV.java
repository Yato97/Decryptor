import java.io.File;
import java.io.PrintWriter;

public class CreateCSV {
    public static void createCSV(String path) {
        try {
            PrintWriter printWriter = new PrintWriter(new File(path + ".csv"));
            StringBuilder stringBuilder = new StringBuilder();

            String total = WindowsM.zoneOutput.getText();
            System.out.println(total);
            String split[] = total.split(",", 0);
            // Initialisation 1er ligne du tableau
            stringBuilder.append("Numero Etudiant");
            stringBuilder.append(";");
            stringBuilder.append("Nom");
            stringBuilder.append(";");
            stringBuilder.append("Prenom");
            stringBuilder.append(";");
            stringBuilder.append("Date naissance");
            stringBuilder.append(";");
            stringBuilder.append("Note");
            stringBuilder.append(";");
            stringBuilder.append("Date");
            stringBuilder.append(";");
            stringBuilder.append("Heure");
            stringBuilder.append("\r\n"); // Passage a la 2éme ligne
            for (String s : split) { // Ajoute un retour a la ligne a la fin de chaque ligne
                System.out.println(s);
                stringBuilder.append(s);
                stringBuilder.append(";"); // Regex ';' format csv separator ->
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
