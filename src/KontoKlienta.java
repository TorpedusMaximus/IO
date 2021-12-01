import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KontoKlienta extends DaneOsobowe {
    private final String nrDowodu;
    private final int nrKonta;
    private final Date dataUrodzenia;
    private List<Karta> karty=new ArrayList();

    public KontoKlienta(int pesel, String haslo, String imie, String nazwisko, String nrDowodu, Date dataUrodzenia,int nrKonta) {
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
        this.karty=aktualneKonto.getKarty();
    }

    public String getNrDowodu() {
        return nrDowodu;
    }

    public int getNrKonta() {
        return nrKonta;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public List<Karta> getKarty() {
        return karty;
    }

    public void usunKarte(long nrKarty, int CVC, Date dataWaznosci){
        Karta karta=new Karta(nrKarty,CVC,dataWaznosci);
        karty.remove(karta);
    }

    public void dodajKarte(long nrKarty){
        int CVC= (int) ((10000*Math.random())%1000);
        Date dataWaznosci = new Date();
        dataWaznosci.setYear(dataWaznosci.getYear()+5);
        Karta karta=new Karta(nrKarty,CVC,dataWaznosci);
        karty.add(karta);
    }

}
