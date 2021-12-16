package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ZmienDane extends JPanel {
    JTextField wpiszImie = new JTextField(30);
    JLabel etykietaImie = new JLabel("Imię:");
    JSplitPane imie = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaImie,wpiszImie);

    JTextField wpiszDowod = new JTextField(30);
    JLabel etykietaDowod = new JLabel("Numer Dowodu:");
    JSplitPane dowod = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaDowod,wpiszDowod);

    JTextField wpiszNazwisko = new JTextField(30);
    JLabel etykietaNazwisko = new JLabel("Nazwisko:");
    JSplitPane nazwisko = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaNazwisko,wpiszNazwisko);

    JPasswordField wpiszHaslo = new JPasswordField(30);
    JLabel etykietaHaslo = new JLabel("Hasło:");
    JSplitPane haslo = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaHaslo,wpiszHaslo);

    JButton wprowadz = new JButton("Zmień dane");
    JButton wroc = new JButton("Wróć");

    public void addActionListener(ActionListener listener){
        wprowadz.addActionListener(listener);
        wroc.addActionListener(listener);
    }

    private void komendy() {
        wprowadz.setActionCommand("wybor");
        wroc.setActionCommand("wyjdz");
    }

    public ZmienDane() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 500));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        komendy();

        uklad.gridwidth=2;
        uklad.gridx=0;
        uklad.gridy=0;
        add(imie,uklad);
        uklad.gridx=0;
        uklad.gridy=1;
        add(nazwisko,uklad);
        uklad.gridx=0;
        uklad.gridy=2;
        add(dowod,uklad);
        uklad.gridx=0;
        uklad.gridy=3;
        add(haslo,uklad);

        uklad.gridwidth=1;
        uklad.gridx=0;
        uklad.gridy=4;
        add(wprowadz,uklad);
        uklad.gridx=1;
        uklad.gridy=4;
        add(wroc,uklad);
    }

    public void clear() {
        wpiszDowod.setText("");
        wpiszHaslo.setText("");
        wpiszImie.setText("");
        wpiszNazwisko.setText("");
    }
}
