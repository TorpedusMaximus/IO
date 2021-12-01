import java.util.ArrayList;
import java.util.List;

public class Bezpieczenstwo {
    private final KontoKlienta aktualneKonto;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();


    public Bezpieczenstwo(KontoKlienta aktualneKonto, List<KontoKlienta> listaKlientow, List<KontoPracownika> listaPracownikow) {
        this.aktualneKonto = aktualneKonto;
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
    }

    public void proceduraBezpieczenstwa() {
        if(!weryfikacjaZmian()){
            //bład
            return;
        }
        if(!weryfikacjaTozsamosci()){
            //bład
            return;
        }
        for(int i=0;i<listaKlientow.size();i++){
            if(listaKlientow.get(i).getNrKonta()==aktualneKonto.getNrKonta()){//dodanie zmian
                listaKlientow.remove(i);
                listaKlientow.add(aktualneKonto);
                break;
            }
        }
    }

    private boolean weryfikacjaZmian() {
        boolean ok = true;   //pobierane z okienka
        return ok;
    }

    private boolean weryfikacjaTozsamosci() {
        int nrKonta = 0;//pobierane z okienka
        String hasloKlienta = null;
        int idPracownika = 0;
        String hasloPracownika = null;

        if (nrKonta != aktualneKonto.getNrKonta()) return false;//testy tożsamosci
        if (hasloKlienta != aktualneKonto.getHaslo()) return false;

        for (KontoPracownika kontoPracownika : listaPracownikow) {
            if (kontoPracownika.getIdPracownika() == idPracownika) {
                return kontoPracownika.getHaslo() == hasloPracownika;
            }
        }

        return false;
    }


    public List<KontoKlienta> getListaKlientow() {
        return listaKlientow;
    }
}
