package v2;

public class KontoPracownika extends DaneOsobowe {
    private final int idPracownika;

    public KontoPracownika(int pesel, String haslo, String imie, String nazwisko,int idPracownika) {
        super(pesel, haslo, imie, nazwisko);
        this.idPracownika=idPracownika;
    }

    public KontoPracownika(int pesel, String haslo, String imie, String nazwisko,int idPracownika, boolean stan) {
        super(pesel, haslo, imie, nazwisko);
        this.idPracownika=idPracownika;
        this.setStan(stan);
    }

    public int getIdPracownika() {
        return idPracownika;
    }
}
