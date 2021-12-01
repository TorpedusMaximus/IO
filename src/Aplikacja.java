import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Aplikacja {

    private JPanel panel;
    private JButton wybor;
    private JButton karty;
    private JButton pieniadze;
    private JButton konto;
    private JButton wybraniePieniedzy;
    private JButton przelew;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JLabel pieniadzeLabel;
    private JLabel kontoLabel;
    private JLabel kartyLabel;
    private JButton button1;
    private JButton button7;
    private JButton button8;
    private JButton dodajPieniadze;

    public Aplikacja() {
        wybor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        konto.addActionListener(new ActionListener() {
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
}
