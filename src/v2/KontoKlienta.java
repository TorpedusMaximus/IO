package v2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class KontoKlienta extends DaneOsobowe {
    private String nrDowodu;
    private long nrKonta;
    private long pieniadze=0;
    private final Date dataUrodzenia;
    private List<Karta> karty=new ArrayList();

    public KontoKlienta(int pesel, String haslo, String imie, String nazwisko, String nrDowodu, Date dataUrodzenia,long nrKonta) {
        super(pesel, haslo, imie, nazwisko);
        this.nrDowodu=nrDowodu;
        this.dataUrodzenia=dataUrodzenia;
        this.nrKonta=nrKonta;
    }

    public KontoKlienta(KontoKlienta aktualneKonto){
        super(aktualneKonto.getPesel(),aktualneKonto.getHaslo(),aktualneKonto.getImie(),aktualneKonto.getNazwisko());
        this.nrDowodu=aktualneKonto.getNrDowodu();
        this.dataUrodzenia=aktualneKonto.getDataUrodzenia();
        this.nrKonta=aktualneKonto.getNrKonta();
        this.dodajPieniadze(aktualneKonto.getPieniadze());
        setKarty(aktualneKonto.getKarty());
    }

    public void setKarty(List<Karta> listaKart){
        for (Karta karta : listaKart){
            Karta nowaKarta = new Karta(karta);
            this.karty.add(nowaKarta);
        }
    }

    public String getNrDowodu() {
        return nrDowodu;
    }

    public long getNrKonta() {
        return nrKonta;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public List<Karta> getKarty() {
        return karty;
    }

    public void usunKarte(String nrKarty){
        karty.removeIf(karta -> Objects.equals(karta.getNrKarty(), nrKarty));
    }

    public void dodajKarte(String nrKarty, JFrame frame){
        int CVC= (int) ((10000*Math.random())%1000);
        Date dataWaznosci = new Date();
        dataWaznosci.setYear(dataWaznosci.getYear()+5);
        Karta karta=new Karta(nrKarty,CVC,dataWaznosci);
        karty.add(karta);
        String informacje ="Dodano kartę:\nNumer karty:"+nrKarty+ "\nCVC: "+CVC+"\nData ważności: "+dataWaznosci;
        JOptionPane.showMessageDialog(frame, informacje, "", JOptionPane.WARNING_MESSAGE);
    }

    public void dodajKarte(Karta karta){
        karty.add(karta);
    }

    public void setNrKonta(long nrKonta) {
        this.nrKonta = nrKonta;
    }

    public void setNrDowodu(String nrDowodu) {
        this.nrDowodu = nrDowodu;
    }

    public void dodajPieniadze(long kwota){
        pieniadze+=kwota;
    }

    public long getPieniadze() {
        return pieniadze;
    }

    public void zablokujKarte(String nrKarty){
        for(Karta karta: karty){
            if(Objects.equals(karta.getNrKarty(), nrKarty)){
                karta.setStan(false);
            }
        }
    }

    public void odblokujKarte(String nrKarty){
        for(Karta karta: karty){
            if(Objects.equals(karta.getNrKarty(), nrKarty)){
                karta.setStan(true);
            }
        }
    }

}
