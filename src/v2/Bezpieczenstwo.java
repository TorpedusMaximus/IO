package v2;

import v1.KontoKlienta;
import v1.KontoPracownika;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Bezpieczenstwo {
    private final KontoKlienta aktualneKonto;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();
    JFrame frame;
    String zmiany;


    public Bezpieczenstwo(String zmiany,JFrame frame, KontoKlienta aktualneKonto, List<KontoKlienta> listaKlientow, List<KontoPracownika> listaPracownikow) {
        this.aktualneKonto = aktualneKonto;
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
        this.frame = frame;
        this.zmiany = zmiany;
    }

    public void proceduraBezpieczenstwa() {
        if (!weryfikacjaZmian()) {
            JOptionPane.showMessageDialog(frame, "Zmiany cofnięte.", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!weryfikacjaTozsamosci()) {
            JOptionPane.showMessageDialog(frame, "Nieautoryzowany dostęp, zmiany zostały cofnięte.", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < listaKlientow.size(); i++) {
            if (listaKlientow.get(i).getNrKonta() == aktualneKonto.getNrKonta()) {//dodanie zmian
                listaKlientow.remove(i);
                listaKlientow.add(aktualneKonto);
                break;
            }
        }
    }

    private boolean weryfikacjaZmian() {
        int weryfikacja = JOptionPane.showConfirmDialog(frame,"", "Zmiany:", JOptionPane.YES_NO_OPTION);
        return weryfikacja==JOptionPane.YES_OPTION;
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
