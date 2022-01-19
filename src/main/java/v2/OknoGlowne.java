package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OknoGlowne extends JPanel {
    private final JButton wybor = new JButton("Wybór klienta");
    private final JButton wyjdz = new JButton("Wyjdź");

    private final JButton odblokujKonto = new JButton("Odblokuj konto");
    private final JButton zablokujKonto = new JButton("Zablokuj konto");
    private final JButton dodajKonto = new JButton("Dodaj konto");
    private final JButton zmienDane = new JButton("Zmień dane");
    private final JButton usunKonto = new JButton("Usuń konto");

    private final JButton wyplata = new JButton("Wypłać pieniądze");
    private final JButton wplac = new JButton("Wpłać pieniądze");
    private final JButton przelew = new JButton("Przelew");

    private final JButton dodajKarte = new JButton("Dodaj kartę");
    private final JButton usunKarte = new JButton("Usuń kartę");
    private final JButton zablokujKarte = new JButton("Zablokuj kartę");
    private final JButton odblokujKarte = new JButton("Odblokuj kartę");

    private final JLabel pieniadzeLabel = new JLabel("Operacje na pieniądzach");
    private final JLabel kontoLabel = new JLabel("Operacje na kontach");
    private final JLabel kartyLabel = new JLabel("Operacje na kartach");

    public void addActionListener(ActionListener listener) {
        wybor.addActionListener(listener);
        wyjdz.addActionListener(listener);
        odblokujKonto.addActionListener(listener);
        zablokujKonto.addActionListener(listener);
        dodajKonto.addActionListener(listener);
        zmienDane.addActionListener(listener);
        usunKonto.addActionListener(listener);
        wyplata.addActionListener(listener);
        wplac.addActionListener(listener);
        przelew.addActionListener(listener);
        dodajKarte.addActionListener(listener);
        zablokujKarte.addActionListener(listener);
        odblokujKarte.addActionListener(listener);
        usunKarte.addActionListener(listener);
    }

    public void komendy() {
        wybor.setActionCommand("wybor");
        wyjdz.setActionCommand("wyjdz");
        odblokujKonto.setActionCommand("odblokujKonto");
        zablokujKonto.setActionCommand("zablokujKonto");
        dodajKonto.setActionCommand("dodajKonto");
        zmienDane.setActionCommand("zmienDane");
        usunKonto.setActionCommand("usunKonto");
        wyplata.setActionCommand("wyplata");
        wplac.setActionCommand("wplac");
        przelew.setActionCommand("przelew");
        dodajKarte.setActionCommand("dodajKarte");
        zablokujKarte.setActionCommand("zablokujKarte");
        odblokujKarte.setActionCommand("odblokujKarte");
        usunKarte.setActionCommand("usunKarte");
    }

    public OknoGlowne() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 500));
        GridBagConstraints uklad = new GridBagConstraints();
        uklad.gridwidth = 1;
        uklad.weightx = 1;
        uklad.insets = new Insets(10, 10, 10, 10);
        komendy();

        uklad.gridx = 0;
        uklad.gridy = 0;
        add(wybor, uklad);
        uklad.gridx = 2;
        uklad.gridy = 0;
        add(dodajKonto, uklad);

        uklad.gridx = 0;
        uklad.gridy = 1;
        add(kontoLabel, uklad);
        uklad.gridx = 1;
        uklad.gridy = 1;
        add(kartyLabel, uklad);
        uklad.gridx = 2;
        uklad.gridy = 1;
        add(pieniadzeLabel, uklad);

        uklad.gridx = 1;
        uklad.gridy = 2;
        add(dodajKarte, uklad);
        uklad.gridx = 1;
        uklad.gridy = 3;
        add(usunKarte, uklad);
        uklad.gridx = 1;
        uklad.gridy = 4;
        add(zablokujKarte, uklad);
        uklad.gridx = 1;
        uklad.gridy = 5;
        add(odblokujKarte, uklad);

        uklad.gridx = 0;
        uklad.gridy = 2;
        add(usunKonto, uklad);
        uklad.gridx = 0;
        uklad.gridy = 3;
        add(zablokujKonto, uklad);
        uklad.gridx = 0;
        uklad.gridy = 4;
        add(odblokujKonto, uklad);
        uklad.gridx = 0;
        uklad.gridy = 5;
        add(zmienDane, uklad);
        uklad.gridx = 1;
        uklad.gridy = 6;
        add(wyjdz, uklad);

        uklad.gridx = 2;
        uklad.gridy = 2;
        add(wplac, uklad);
        uklad.gridx = 2;
        uklad.gridy = 3;
        add(wyplata, uklad);
        uklad.gridx = 2;
        uklad.gridy = 4;
        add(przelew, uklad);
    }
}
