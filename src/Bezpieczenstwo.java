import java.util.List;

public class Bezpieczenstwo {
    private KontoKlienta aktualneKonto;
    private List<KontoKlienta> listaKlientow;
    private List<KontoKlienta> listaPracownikow;


    public Bezpieczenstwo(KontoKlienta aktualneKonto, List<KontoKlienta> listaKlientow, List<KontoKlienta> listaPracownikow) {
        this.aktualneKonto = aktualneKonto;
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
    }

    public void proceduraBezpieczenstwa() {
        weryfikacjaZmian();
        weryfikacjaTozsamosci();
    }

    private boolean weryfikacjaZmian() {
        boolean ok = true;
        return ok;
    }

    private boolean weryfikacjaTozsamosci() {
        int pesel = 0;
        String hasloKlienta = null;
        int idPracownika = 0;
        String hasloPracownika = null;

        if (pesel != aktualneKonto.getPesel()) return false;
        if (hasloKlienta != aktualneKonto.getHaslo()) return false;
        return true;
    }


    public List<KontoKlienta> getListaKlientow() {
        return listaKlientow;
    }
}
