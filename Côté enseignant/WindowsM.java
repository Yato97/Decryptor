import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class WindowsM extends JFrame {
    JPanel contenue;
    JButton selection = new JButton("Sélectionner");
    JButton exporter = new JButton("Exporter");
    JButton enregistrer = new JButton("Enregistrer");
    JButton decrypte = new JButton("Décrypter");

    Border EtchedBorderRaised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    JLabel zoneInput = new JLabel();
    static JTextArea zoneOutput = new JTextArea();

    JLabel t1 = new JLabel("Sélection du fichier contenant les chaines cryptées : ");
    JLabel t2 = new JLabel("Liste décryptée : ");

    Dimension dim = new Dimension(900, 700);

    JFileChooser dialogue = new JFileChooser();

    String pathsrc;
    String pathdst;
    File directory;
    // Variables de test d'existences
    File dir = new File(System.getProperty("user.dir") + File.separator + "dst");
    File csv = new File(System.getProperty("user.dir") + File.separator + "file.csv");

    public WindowsM() {
        super("Décryptor - multi export");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(dim);
        this.setMinimumSize(dim);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        ImageIcon icon = new ImageIcon(
                System.getProperty("user.dir") + File.separator + "/Côté enseignant/img/fav.png");
        this.setIconImage(icon.getImage());
        this.setResizable(false);

        zoneOutput.setEditable(false); // On empéche l'edition du résultat
        contenue = (JPanel) this.getContentPane();

        JPanel p1 = north();
        JPanel p2 = south();

        contenue.add(p1, BorderLayout.WEST);
        contenue.add(p2, BorderLayout.EAST);
        this.pack();

    }

    JPanel north() {
        JPanel north = new JPanel();
        JPanel temp = new JPanel();

        temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Paramètre", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));
        selection.setAlignmentX(CENTER_ALIGNMENT);
        t1.setAlignmentX(CENTER_ALIGNMENT);
        zoneInput.setAlignmentX(CENTER_ALIGNMENT);
        decrypte.setAlignmentX(CENTER_ALIGNMENT);

        north.add(t1);
        north.add(Box.createVerticalStrut(10));
        north.add(selection);
        north.add(Box.createVerticalStrut(10));
        north.add(zoneInput);
        north.add(Box.createVerticalStrut(10));
        north.add(decrypte);

        // north.setBackground(Color.red);
        // temp.setBackground(Color.GRAY);

        temp.setPreferredSize(new Dimension(400, 550));

        temp.add(Box.createHorizontalGlue());
        temp.add(Box.createVerticalGlue());
        temp.add(north);
        temp.add(Box.createVerticalGlue());
        temp.add(Box.createHorizontalGlue());

        decrypte.setVisible(false);

        JFileChooser dialogue = new JFileChooser();
        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (csv.exists()) {
                    csv.delete();
                }
                if (dir.exists()) {
                    try {
                        RemoveDir.delete(dir);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                dialogue.showOpenDialog(null);
                pathsrc = dialogue.getSelectedFile().getAbsolutePath();
                pathdst = System.getProperty("user.dir") + File.separator + "dst";
                zoneInput.setText("Fichier choisi : " + pathsrc);
                try {
                    UnzipFile.unZip(pathsrc, pathdst);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                decrypte.setVisible(true);
            }
        });

        decrypte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                directory = new File(pathdst);
                try {
                    Parse.parse(directory); // On récupérent les clé crypté et on les decrypte + affichage sur UI
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        temp.setBorder(new EmptyBorder(30, 10, 10, 10));
        return temp;
    }

    JPanel south() {
        JPanel south = new JPanel();
        JPanel temp = new JPanel();

        south.setPreferredSize(new Dimension(500, 700));
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        t2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        exporter.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        temp.add(Box.createHorizontalGlue());
        temp.add(Box.createVerticalGlue());
        temp.add(t2);
        temp.add(zoneOutput);
        zoneOutput.setPreferredSize(new Dimension(400, 450));
        temp.add(exporter);
        temp.add(enregistrer);
        temp.add(Box.createVerticalGlue());
        temp.add(Box.createHorizontalGlue());

        south.add(Box.createHorizontalGlue());
        south.add(Box.createVerticalGlue());
        south.add(temp);
        south.add(Box.createVerticalGlue());
        south.add(Box.createHorizontalGlue());

        exporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCSV.createCSV(System.getProperty("user.dir")); // On genere le fichier cvs
            }
        });
        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Résultat", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));
        south.setBorder(new EmptyBorder(10, 0, 0, 20));
        return south;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        WindowsM windowsM = new WindowsM();
        windowsM.setVisible(true);
    }
}
