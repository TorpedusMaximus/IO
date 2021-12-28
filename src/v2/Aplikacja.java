package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Aplikacja extends JFrame {
    static Aplikacja frame;
    final CardLayout layout = new CardLayout();
    OknoGlowne oknoGlowne = new OknoGlowne();
    ZmienDane zmienDane = new ZmienDane();
    DodajKonto dodajKonto = new DodajKonto();
    private KontoKlienta aktualneKonto = null;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();
    long klient=0;

    public Aplikacja() {
        Odczyt odczyt = new Odczyt();
        listaKlientow = odczyt.klienci();
        listaPracownikow = odczyt.pracownicy();

        setTitle("");
        inicjalizacja();
        komendyOknoGlowne();
        komendyZmienDane();
        komendyDodajKonto();
        layout.show(getContentPane(), "oknoGlowne");
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new Aplikacja();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void inicjalizacja() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(layout);
        add(oknoGlowne, "oknoGlowne");
        add(zmienDane, "zmienDane");
        add(dodajKonto, "dodajKonto");
    }

    private void komendyOknoGlowne() {
        oknoGlowne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                String nrKarty;
                Bezpieczenstwo bezpieczenstwo;
                boolean flaga = false;



                switch (akcja) {
                    case "wybor":
                        klient = Long.parseLong(JOptionPane.showInputDialog(frame, "Podaj ID klienta", "", JOptionPane.PLAIN_MESSAGE));
                        wybierzKonto(klient);
                        break;
                    case "wyjdz":
                        Zapis zapis = new Zapis(listaKlientow, listaPracownikow);
                        zapis.zapisz();
                        dispose();
                        break;
                    case "odblokujKonto":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if (aktualneKonto.getStan()) {
                            JOptionPane.showMessageDialog(frame, "Konto jest już odblokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.setStan(true);
                        bezpieczenstwo = new Bezpieczenstwo("odblokujKonto", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "zablokujKonto":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if (!aktualneKonto.getStan()) {
                            JOptionPane.showMessageDialog(frame, "Konto jest już zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.setStan(false);
                        bezpieczenstwo = new Bezpieczenstwo("zablokujKonto", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "dodajKonto":
                        layout.show(getContentPane(), "dodajKonto");
                        break;
                    case "zmienDane":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        layout.show(getContentPane(), "zmienDane");
                        break;
                    case "usunKonto":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        bezpieczenstwo = new Bezpieczenstwo("usunKonto", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        bezpieczenstwo.usunKonto();
                        listaKlientow = bezpieczenstwo.wyniki();
                        aktualneKonto = null;
                        setTitle("");
                        break;
                    case "wyplata":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        long wyplac = Long.parseLong(JOptionPane.showInputDialog(frame, "Podaj kwotę wypłacaną.\nAktualny stan konta: " + aktualneKonto.getPieniadze(), "", JOptionPane.PLAIN_MESSAGE));
                        if (wyplac > aktualneKonto.getPieniadze()) {
                            JOptionPane.showMessageDialog(frame, "Brak środków na koncie", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        if (wyplac <=0) {
                            JOptionPane.showMessageDialog(frame, "Błędna kwota", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.dodajPieniadze(-1 * wyplac);
                        bezpieczenstwo = new Bezpieczenstwo("wyplac", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "wplac":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        long wplata = Long.parseLong((String) JOptionPane.showInputDialog(frame, "Podaj kwotę wpłaty", "", JOptionPane.PLAIN_MESSAGE));
                        if (wplata <=0) {
                            JOptionPane.showMessageDialog(frame, "Błędna kwota", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.dodajPieniadze(wplata);
                        bezpieczenstwo = new Bezpieczenstwo("wplac", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "przelew":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        long przelew = Long.parseLong(JOptionPane.showInputDialog(frame, "Podaj kwotę przelewu\nAktualny stan konta: " + aktualneKonto.getPieniadze(), "", JOptionPane.PLAIN_MESSAGE));
                        if (przelew > aktualneKonto.getPieniadze()) {
                            JOptionPane.showMessageDialog(frame, "Brak środków na koncie", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        if (przelew <=0) {
                            JOptionPane.showMessageDialog(frame, "Błędna kwota", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                        long nrKonta = Long.parseLong(JOptionPane.showInputDialog(frame, "Podaj numer konta", "", JOptionPane.PLAIN_MESSAGE));
                        aktualneKonto.dodajPieniadze(-1 * przelew);
                        bezpieczenstwo = new Bezpieczenstwo("przelew", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        bezpieczenstwo.przelej(przelew, nrKonta);
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "dodajKarte":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.dodajKarte(losujNrKarty(), frame);
                        bezpieczenstwo = new Bezpieczenstwo("dodajKarte", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "zablokujKarte":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        System.out.println(aktualneKonto.getKarty().size());
                        nrKarty = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        for (Karta karta : aktualneKonto.getKarty()) {
                            if (Objects.equals(karta.getNrKarty(), nrKarty)) {
                                flaga = true;
                            }
                        }
                        if (!flaga) {
                            JOptionPane.showMessageDialog(frame, "Wskazana karta nie istnieje", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                        for (Karta karta : aktualneKonto.getKarty()) {
                            if (Objects.equals(karta.getNrKarty(), nrKarty)) {
                                if (!karta.getStan()){
                                    JOptionPane.showMessageDialog(frame, "Karta już jest zablokowana", "", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                        }

                        aktualneKonto.zablokujKarte(nrKarty);
                        bezpieczenstwo = new Bezpieczenstwo("zablokujKarte", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "odblokujKarte":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        nrKarty = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        for (Karta karta : aktualneKonto.getKarty()) {
                            if (Objects.equals(karta.getNrKarty(), nrKarty)) {
                                flaga = true;
                            }
                        }
                        if (!flaga) {
                            JOptionPane.showMessageDialog(frame, "Wskazana karta nie istnieje", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        for (Karta karta : aktualneKonto.getKarty()) {
                            if (Objects.equals(karta.getNrKarty(), nrKarty)) {
                                if (karta.getStan()){
                                    JOptionPane.showMessageDialog(frame, "Karta już jest odblokowana", "", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                        }

                        aktualneKonto.odblokujKarte(nrKarty);
                        bezpieczenstwo = new Bezpieczenstwo("odblokujKarte", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                    case "usunKarte":
                        if (!kontoWybrane()) {
                            break;
                        }
                        if(!aktualneKonto.getStan()){
                            JOptionPane.showMessageDialog(frame, "Akcja niemożliwa. Konto zablokowane.", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        nrKarty = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        for (Karta karta : aktualneKonto.getKarty()) {
                            if (Objects.equals(karta.getNrKarty(), nrKarty)) {
                                flaga = true;
                            }
                        }
                        if (!flaga) {
                            JOptionPane.showMessageDialog(frame, "Wskazana karta nie istnieje", "", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        aktualneKonto.usunKarte(nrKarty);
                        bezpieczenstwo = new Bezpieczenstwo("usunKarte", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        break;
                }

                if (!akcja.equals("usunKonto") && !akcja.equals("wyjdz") && !akcja.equals("dodajKonto")) {
                    wybierzKonto(klient);
                }

            }
        });
    }

    private boolean kontoWybrane() {
        if (aktualneKonto != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Brak wybranego konta!", "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private void wybierzKonto(Long nrKonta) {
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                aktualneKonto = new KontoKlienta(kontoKlienta);
                setTitle("Nr Konta " + nrKonta);
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Brak klienta w systemie", "", JOptionPane.PLAIN_MESSAGE);
    }

    private void komendyZmienDane() {
        zmienDane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "wybor":
                        aktualneKonto = zmienDane.zmienDane(aktualneKonto);
                        zmienDane.clear();
                        Bezpieczenstwo bezpieczenstwo = new Bezpieczenstwo("zmienDane", frame, aktualneKonto, listaKlientow, listaPracownikow);
                        bezpieczenstwo.proceduraBezpieczenstwa();
                        listaKlientow = bezpieczenstwo.wyniki();
                        layout.show(getContentPane(), "oknoGlowne");
                        break;
                    case "wyjdz":
                        zmienDane.clear();
                        layout.show(getContentPane(), "oknoGlowne");
                        break;
                }
            }
        });
    }

    private void komendyDodajKonto() {
        dodajKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "dodajKonto":
                        try {
                            KontoKlienta kontoKlienta = dodajKonto.getKonto();
                            kontoKlienta.setNrKonta(losujNrKonta());
                            JOptionPane.showMessageDialog(frame, "Numer konta:" + kontoKlienta.getNrKonta(), "", JOptionPane.PLAIN_MESSAGE);
                            System.out.println(kontoKlienta.getNrKonta());
                            listaKlientow.add(kontoKlienta);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        layout.show(getContentPane(), "oknoGlowne");
                        dodajKonto.clear();
                        break;
                    case "wroc":
                        dodajKonto.clear();
                        layout.show(getContentPane(), "oknoGlowne");
                        break;
                }
            }
        });
    }

    private long losujNrKonta() {
        long nrKonta = (long) (Math.random() * 1000000000000L);
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                return losujNrKonta();
            }
        }
        return nrKonta;
    }

    public String losujNrKarty() {
        StringBuilder nrKarty = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            String temp = "";
            int kawalek = (int) (Math.random() * 9999);
            if (kawalek < 1000) {
                temp += "0";
            }
            if (kawalek < 100) {
                temp += "0";
            }
            if (kawalek < 10) {
                temp += "0";
            }
            temp += Integer.toString(kawalek);

            nrKarty.append(temp);
        }

        for (KontoKlienta kontoKlienta : listaKlientow) {
            for (int ii = 0; ii < kontoKlienta.getKarty().size(); ii++) {
                if (Objects.equals(kontoKlienta.getKarty().get(ii).getNrKarty(), nrKarty.toString())) {
                    nrKarty = Optional.ofNullable(losujNrKarty()).map(StringBuilder::new).orElse(null);
                    return nrKarty == null ? null : nrKarty.toString();
                }
            }
        }

        return nrKarty.toString();
    }
}
