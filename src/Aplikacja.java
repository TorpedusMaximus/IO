import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Aplikacja {
    private KontoKlienta aktualneKonto;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();

    private JPanel panel;
    private JButton wybor;
    private JButton karty;
    private JButton pieniadze;
    private JButton dodajKonto;
    private JButton button2;
    private JButton button3;
    private JButton dodajKarte;
    private JButton usunKarte;
    private JButton button6;
    private JLabel pieniadzeLabel;
    private JLabel kontoLabel;
    private JLabel kartyLabel;
    private JButton zablokujKarte;
    private JButton odblokujKarte;
    private JButton usunKonto;
    private JButton button9;

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
        pieniadze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        karty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        Date data=new Date();

        KontoKlienta klient=new KontoKlienta(207298,"aaaa","KAcper","m","g",data,123);
        long karta=1;
        klient.dodajKarte(karta);
        System.out.println(klient.getKarty().toString());

    }

    void dodajZmiany(){
        Bezpieczenstwo zapis=new Bezpieczenstwo(aktualneKonto,listaKlientow,listaPracownikow);
        zapis.proceduraBezpieczenstwa();
        listaKlientow=zapis.getListaKlientow();
    }

    void wybierzKlienta(){
        int nrKonta=0;
        for (KontoKlienta kontoKlienta : listaKlientow) {
            if (kontoKlienta.getNrKonta() == nrKonta) {
                aktualneKonto = kontoKlienta;
                break;
            }
        }
    }

}
