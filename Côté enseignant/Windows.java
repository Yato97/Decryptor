import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Windows extends JFrame {
    JPanel contenue;

    JButton decrypter = new JButton("Décrypter");
    JButton exporter = new JButton("Exporter");

    JTextArea zoneInput = new JTextArea();;
    JTextArea zoneOutput = new JTextArea();;

    JLabel t1 = new JLabel("Entrez la chaine cryptée : ");
    JLabel t2 = new JLabel("Résultat : ");

    Dimension dim = new Dimension(600, 300);

    Border EtchedBorderRaised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    public Windows() {
        super("Décryptor - mono export");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(dim);
        this.setMinimumSize(dim);
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("fav.png"));
        this.setIconImage(icon.getImage());

        this.setResizable(false);

        zoneOutput.setEditable(false); // On empéche l'edition du résultat

        decrypter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String crypted = zoneInput.getText();
                String decode = Decrypt.decrypt(crypted);
                zoneOutput.setText(decode);
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

        north.setPreferredSize(new Dimension(600, 120));
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));

        t1.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        decrypter.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        temp.add(t1);
        temp.add(zoneInput);
        zoneInput.setPreferredSize(new Dimension(550, 25));
        temp.add(decrypter);

        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Clé", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));

        north.add(temp);

        return north;
    }

    JPanel south() {
        JPanel south = new JPanel();
        JPanel temp = new JPanel();

        south.setPreferredSize(new Dimension(600, 100));
        south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));

        t2.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        exporter.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        temp.add(t2);
        temp.add(zoneOutput);
        zoneOutput.setPreferredSize(new Dimension(550, 25));
        // temp.add(exporter);

        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Déverrouillage", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));
        south.add(temp);

        return south;
    }

}
