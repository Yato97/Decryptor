import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalCrypto extends JFrame {
    JPanel contenue;

    JButton encrypter = new JButton("Crypter");
    JButton exporter = new JButton("Exporter");

    JTextField zoneInputNum = new JTextField();
    JTextField zoneInputNom = new JTextField();
    JTextField zoneInputPrenom = new JTextField();
    JTextField zoneInputDate = new JTextField();
    static JTextArea zoneOutput = new JTextArea();

    JLabel t1 = new JLabel("Numéro étudiant : ");
    JLabel t4 = new JLabel("Nom : ");
    JLabel t3 = new JLabel("Prénom : ");
    JLabel t5 = new JLabel("Date de naissance (JJ/MM/AAAA) : ");
    JLabel t2 = new JLabel("Résultat : ");

    Dimension dim = new Dimension(600, 440);

    Border EtchedBorderRaised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    static int NOTE = 20;

    public LocalCrypto() {
        super("Encryptor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(dim);
        this.setMinimumSize(dim);
        this.setLocationRelativeTo(null);
        try {
            Image image = new ImageIcon("C:/Users/Roulland/Desktop/Decryptor/Côté enseignant/img/fav.png").getImage();
            this.setIconImage(image);
        } catch (Exception e) {
            System.out.println("Application icon not found");
        }
        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("fav.png"));
            this.setIconImage(icon.getImage());
        } catch (Exception e) {
            // TODO: handle exception
        }

        this.setResizable(false);

        zoneOutput.setEditable(false); // On empéche l'edition du résultat
        exporter.setVisible(false);

        encrypter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = null;
                String heure = null;
                Date aujourdhui = new Date();

                date = new SimpleDateFormat("dd/MM/yyyy").format(aujourdhui);
                heure = new SimpleDateFormat("hh:mm:ss").format(aujourdhui);

                // Retire tout les espaces vides, les tabulations, les retour a la ligne des
                // informations saisie par l'utilisateur
                String num = zoneInputNum.getText().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r", "")
                        .replaceAll("\n", "");
                String nom = zoneInputNom.getText().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r", "")
                        .replaceAll("\n", "");
                String prenom = zoneInputPrenom.getText().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r", "")
                        .replaceAll("\n", "");
                String dateN = zoneInputDate.getText().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r", "")
                        .replaceAll("\n", "");
                String crypted = num + "," + nom + "," + prenom + "," + dateN + "," + NOTE + "," + date + "," + heure;

                System.out.println(crypted);
                String encode = Decrypt.encrypt(crypted);
                zoneOutput.setText(encode);
                exporter.setVisible(true);
            }
        });

        contenue = (JPanel) this.getContentPane();
        JPanel p1 = north();
        JPanel p2 = south();

        contenue.add(p1, BorderLayout.NORTH);
        contenue.add(p2, BorderLayout.SOUTH);
    }

    JPanel north() {
        JPanel north = new JPanel();
        JPanel temp = new JPanel();

        north.setPreferredSize(new Dimension(600, 260));
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        t1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        encrypter.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        temp.add(t1);
        temp.add(zoneInputNum);
        zoneInputNum.setPreferredSize(new Dimension(550, 25));
        temp.add(t4);
        temp.add(zoneInputNom);
        zoneInputNom.setPreferredSize(new Dimension(550, 25));
        temp.add(t3);
        temp.add(zoneInputPrenom);
        zoneInputPrenom.setPreferredSize(new Dimension(550, 25));
        temp.add(t5);
        temp.add(zoneInputDate);
        zoneInputDate.setPreferredSize(new Dimension(550, 25));

        temp.add(encrypter);

        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Clé", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));

        north.add(temp);

        return north;
    }

    JPanel south() {
        JPanel south = new JPanel();
        JPanel temp = new JPanel();

        south.setPreferredSize(new Dimension(600, 120));
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        t2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        exporter.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        temp.add(t2);
        temp.add(zoneOutput);
        zoneOutput.setPreferredSize(new Dimension(550, 20));
        temp.add(exporter);
        exporter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser dialogue = new JFileChooser();
                dialogue.showSaveDialog(null);
                String pathsrc = dialogue.getSelectedFile().getAbsolutePath();
                CreateTxt.createTXT(pathsrc);
            }
        });

        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Déverrouillage", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));
        south.add(temp);

        return south;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        LocalCrypto windows = new LocalCrypto();
        windows.setVisible(true);
    }
}
