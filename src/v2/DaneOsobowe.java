package v2;

public abstract class DaneOsobowe {
    private int pesel;
    private String haslo;
    private String imie;
    private String nazwisko;
    private boolean stan;

    public DaneOsobowe(int pesel, String haslo, String imie, String nazwisko) {
        this.pesel = pesel;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stan=true;
    }

    public DaneOsobowe(int pesel, String haslo, String imie, String nazwisko, boolean stan) {
        this.pesel = pesel;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.stan=stan;
    }


    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public boolean getStan() {
        return stan;
    }

    public void setStan(Boolean stan) {
        this.stan = stan;
    }
}
