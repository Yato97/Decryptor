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
            stringBuilder.append("Numero Etudiant");
            stringBuilder.append(";");
            stringBuilder.append("Nom");
            stringBuilder.append(";");
            stringBuilder.append("Prenom");
            stringBuilder.append(";");
            stringBuilder.append("Note");
            stringBuilder.append(";");
            stringBuilder.append("Date");
            stringBuilder.append(";");
            stringBuilder.append("Heure");
            stringBuilder.append("\r\n");
            for (String s : split) {
                System.out.println(s);
                stringBuilder.append(s);
                stringBuilder.append(";");
            }
            printWriter.write(stringBuilder.toString());
            printWriter.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
