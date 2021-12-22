package v2;

import java.util.ArrayList;
import java.util.List;

public class Zapis {
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();


    public Zapis(List<KontoKlienta> listaKlientow, List<KontoPracownika> listaPracownikow) {
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
        zapisz();
    }

    private void generujPlik(){

    }

    private void zapisz() {
        generujPlik();

    }
}
