import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aplikacja extends JFrame {
    private KontoKlienta aktualneKonto=null;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();

    static Aplikacja frame = new Aplikacja();

    private JPanel menu;
    private JButton wybor;
    private JButton odblokujKonto;
    private JButton zablokujKonto;
    private JButton dodajKonto;
    private JButton wyplata;
    private JButton wplac;
    private JButton dodajKarte;
    private JButton usunKarte;
    private JButton zmienDane;
    private JLabel pieniadzeLabel;
    private JLabel kontoLabel;
    private JLabel kartyLabel;
    private JButton zablokujKarte;
    private JButton odblokujKarte;
    private JButton usunKonto;
    private JButton przelew;
    private JButton wyjdz;

    public Aplikacja() {
        wybor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dodajKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dodajKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Karta karta = new Karta();

            }
        });

        usunKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sprawdzCzyWybraneKonto()) {
                    JOptionPane.showMessageDialog(null, "Najpierw wybierz konto!");
                } else {
                    aktualneKonto.getKarty().remove(wyborKarty("0"));
                }
            }
        });
        zablokujKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sprawdzCzyWybraneKonto()) {
                    JOptionPane.showMessageDialog(null, "Najpierw wybierz konto!");
                } else {
                    int indeksKarty = wyborKarty("0");

                    if (aktualneKonto.getKarty().get(indeksKarty).getStan())
                        aktualneKonto.getKarty().get(indeksKarty).setStan(false);
                    else
                        JOptionPane.showMessageDialog(null, "Karta jest już zablokowana!");
                }
            }
        });
        odblokujKarte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sprawdzCzyWybraneKonto()) {
                    JOptionPane.showMessageDialog(null, "Najpierw wybierz konto!");
                } else {
                    int indeksKarty = wyborKarty("0");


                    if (!aktualneKonto.getKarty().get(indeksKarty).getStan()) //zamiast zera będzie wybor i guess?
                        aktualneKonto.getKarty().get(indeksKarty).setStan(true);
                    else
                        JOptionPane.showMessageDialog(null, "Nie można odblokować niezablokowanej karty!");
                }
            }
        });
        usunKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        zablokujKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sprawdzCzyWybraneKonto()) {
                    JOptionPane.showMessageDialog(null, "Najpierw wybierz konto!");
                } else {
                    if (aktualneKonto.getStan())
                        aktualneKonto.setStan(false);
                    else
                        JOptionPane.showMessageDialog(null, "Konto jest już zablokowane!");

                }
            }
        });
        odblokujKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sprawdzCzyWybraneKonto()) {
                    JOptionPane.showMessageDialog(null, "Najpierw wybierz konto!");
                } else {
                    if (!aktualneKonto.getStan())
                        aktualneKonto.setStan(true);
                    else
                        JOptionPane.showMessageDialog(null, "Nie można odblokować niezablokowanego konta!");
                }
            }
        });
        zmienDane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        wplac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        wyplata.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        przelew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        wyjdz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //zapis
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(500, 500);
                frame.setContentPane(new Aplikacja().menu);
                frame.setVisible(true);

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        Date data = new Date();
        KontoKlienta klient = new KontoKlienta(207298, "aaaa", "KAcper", "m", "g", data, 123);
        long karta = 1;
        klient.dodajKarte(karta);
        System.out.println(klient.getKarty().toString());

    }

    void dodajZmiany(){
        Bezpieczenstwo zapis=new Bezpieczenstwo(aktualneKonto,listaKlientow,listaPracownikow);
        zapis.proceduraBezpieczenstwa();
        listaKlientow=zapis.getListaKlientow();
    }

    void wybierzKlienta(int nrKonta) {
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                aktualneKonto = kontoKlienta;
                break;
            }
        }
    }

}
