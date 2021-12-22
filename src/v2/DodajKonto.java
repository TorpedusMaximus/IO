package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DodajKonto extends JPanel {
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

    JTextField wpiszPesel = new JTextField(30);
    JLabel etykietaPesel = new JLabel("Pesel:");
    JSplitPane pesel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaPesel,wpiszPesel);

    JTextField wpiszDataUrodzenia = new JTextField(30);
    JLabel etykietaDataUrodzenia = new JLabel("Data urodzenia:");
    JSplitPane dataUrodzenia = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,etykietaDataUrodzenia,wpiszDataUrodzenia);



    JButton dodaj = new JButton("Dodaj konto");
    JButton wroc = new JButton("Wróć");

    public void addActionListener(ActionListener listener){
        dodaj.addActionListener(listener);
        wroc.addActionListener(listener);
    }

    private void komendy() {
        dodaj.setActionCommand("dodajKonto");
        wroc.setActionCommand("wroc");
    }

    public DodajKonto(){
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
        uklad.gridx=0;
        uklad.gridy=4;
        add(pesel,uklad);
        uklad.gridx=0;
        uklad.gridy=5;
        add(dataUrodzenia,uklad);

        uklad.gridwidth=1;
        uklad.gridx=0;
        uklad.gridy=6;
        add(dodaj,uklad);
        uklad.gridx=1;
        uklad.gridy=6;
        add(wroc,uklad);



    }

    public void clear() {
        wpiszDataUrodzenia.setText("");
        wpiszDowod.setText("");
        wpiszHaslo.setText("");
        wpiszImie.setText("");
        wpiszNazwisko.setText("");
        wpiszPesel.setText("");
    }

    public KontoKlienta getKonto() throws ParseException {
        int pesel = Integer.parseInt(wpiszPesel.getText());
        String imie = wpiszImie.getText();
        String nazwisko = wpiszNazwisko.getText();
        String haslo = Arrays.toString(wpiszHaslo.getPassword());
        String nrDowodu = wpiszDowod.getText();

        String data = wpiszDataUrodzenia.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date dataUrodzenia = formatter.parse(data);

        return new KontoKlienta(pesel,haslo,imie,nazwisko,nrDowodu,dataUrodzenia,-1);
    }

}
