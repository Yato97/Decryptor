import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class WindowsP extends JFrame {
    JPanel contenue;
    Border EtchedBorderRaised = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    JButton multi = new JButton("Par liste");
    JButton mono = new JButton("Par étudiant");
    JLabel label = new JLabel("Mode de décryptage : ");
    static Dimension dim = new Dimension(400, 125);

    public WindowsP() {
        super("Décryptor");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setMinimumSize(dim);
        this.setMaximumSize(dim);

        try {
            ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("fav.png"));
            this.setIconImage(icon.getImage());
        } catch (Exception e) {
            //TODO: handle exception
        }

        this.setLocationRelativeTo(null);

        this.setResizable(false);

        contenue = (JPanel) this.getContentPane();
        contenue.setLayout(new BorderLayout());

        contenue.add(centerPanel(), BorderLayout.CENTER);

        mono.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Windows windows;
                windows = new Windows();
                windows.setVisible(true);
            }
        });

        multi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowsM windows;
                windows = new WindowsM();
                windows.setVisible(true);
            }
        });

    }

    JPanel centerPanel() {
        JPanel pane = new JPanel();
        JPanel temp = new JPanel();
        temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
        label.setAlignmentX(CENTER_ALIGNMENT);
        temp.add(label);
        pane.add(mono);
        pane.add(multi);
        temp.add(pane);

        temp.setBorder(BorderFactory.createTitledBorder(EtchedBorderRaised, "Sélection", TitledBorder.LEADING,
                TitledBorder.TOP, new Font("Arial", Font.PLAIN, 12), Color.gray));

        return temp;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WindowsP fenetre = new WindowsP();
        fenetre.setVisible(true);
    }
}