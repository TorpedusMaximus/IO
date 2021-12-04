import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.plaf.metal.*;
public class WyborKlienta extends JPanel{
    private JPanel panel;
    private JTextArea nrKonta;
    private JButton wprowadz;
    int numerKonta=-1;


    public WyborKlienta() {
        wprowadz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numerKonta=Integer.parseInt(nrKonta.toString());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Aplikacja");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(100, 100);
                frame.setContentPane(new WyborKlienta().panel);
                frame.setVisible(true);

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
