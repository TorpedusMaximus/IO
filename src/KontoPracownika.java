public class KontoPracownika extends DaneOsobowe {
    private final int idPracownika;

    public KontoPracownika(int pesel, String haslo, String imie, String nazwisko,int idPracownika) {
        super(pesel, haslo, imie, nazwisko);
        this.idPracownika=idPracownika;
    }

    public int getIdPracownika() {
        return idPracownika;
    }
}
