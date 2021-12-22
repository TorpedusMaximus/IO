package v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Rdzeń extends JFrame {
    private KontoKlienta aktualneKonto = null;
    private java.util.List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();

    static Rdzeń frame;

    final CardLayout layout = new CardLayout();
    OknoGlowne oknoGlowne = new OknoGlowne();
    ZmienDane zmienDane = new ZmienDane();
    DodajKonto dodajKonto = new DodajKonto();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new Rdzeń();
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
        add(dodajKonto,"dodajKonto");
    }

    public Rdzeń() {
        inicjalizacja();
        komendyOknoGlowne();
        komendyZmienDane();
        komendyDodajKonto();
        layout.show(getContentPane(), "oknoGlowne");
        pack();
        setLocationRelativeTo(null);
    }

    private void komendyOknoGlowne() {
        oknoGlowne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                String karta;
                switch (akcja) {
                    case "wybor":
                        String klient = (String) JOptionPane.showInputDialog(frame, "Podaj ID klienta", "", JOptionPane.PLAIN_MESSAGE);
                        wybierzKonto(Long.valueOf(klient));
                        break;
                    case "wyjdz":
                        dispose();
                        break;
                    case "odblokujKonto":

                        break;
                    case "zablokujKonto":

                        break;
                    case "dodajKonto":
                        layout.show(getContentPane(), "dodajKonto");
                        break;
                    case "zmienDane":
                        layout.show(getContentPane(), "zmienDane");
                        break;
                    case "usunKonto":

                        break;
                    case "wyplata":
                        String wyplac = (String) JOptionPane.showInputDialog(frame, "Aktualny stan konta: " + aktualneKonto.getStan(), "", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "wplac":
                        String wplata = (String) JOptionPane.showInputDialog(frame, "Podaj kwotę wpłaty", "", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "przelew":
                        String przelew = (String) JOptionPane.showInputDialog(frame, "Podaj kwotę przelewu", "", JOptionPane.PLAIN_MESSAGE);
                        String nrKonta = (String) JOptionPane.showInputDialog(frame, "Podaj numer konta", "", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "dodajKarte":

                        break;
                    case "zablokujKarte":
                        karta = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "odblokujKarte":
                        karta = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "usunKarte":
                        karta = (String) JOptionPane.showInputDialog(frame, "Podaj numer karty", "", JOptionPane.PLAIN_MESSAGE);
                        break;
                }
            }
        });
    }

    private void wybierzKonto(Long nrKonta) {
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                aktualneKonto=kontoKlienta;
                break;
            }
        }
    }

    private void komendyZmienDane() {
        zmienDane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String akcja = e.getActionCommand();
                System.out.println(akcja);
                switch (akcja) {
                    case "wybor":

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
                            KontoKlienta kontoKlienta=dodajKonto.getKonto();
                            kontoKlienta.setNrKonta(losujNrKonta());
                            JOptionPane.showMessageDialog(frame, "Numer konta:"+kontoKlienta.getNrKonta(), "", JOptionPane.PLAIN_MESSAGE);
                            listaKlientow.add(kontoKlienta);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                        layout.show(getContentPane(), "oknoGlowne");
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
        long nrKonta = (long) (Math.random()* 1000000000000L);
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                return losujNrKonta();
            }
        }
        return nrKonta;
    }

    public String losujNrKarty() {
        String nrKarty="";

        for (int i = 0; i < 4; i++) {
            String temp=null;
            int kawalek = (int)(Math.random() * 9999);
            if (kawalek < 1000){
                temp += "0";
            }if(kawalek < 100){
                temp += "0";
            }if(kawalek < 10){
                temp += "0";
            }
            temp+=Integer.toString(kawalek);

            nrKarty+=temp;
        }

        for (KontoKlienta kontoKlienta : listaKlientow) {
            for (int ii = 0; ii < kontoKlienta.getKarty().size(); ii++) {
                if (kontoKlienta.getKarty().get(ii).getNrKarty() == nrKarty) {
                    nrKarty = losujNrKarty();
                    return nrKarty;
                }
            }
        }

        return nrKarty;
    }
}
