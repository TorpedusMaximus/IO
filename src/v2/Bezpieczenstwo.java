package v2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bezpieczenstwo {
    private final KontoKlienta aktualneKonto;
    JFrame frame;
    String zmiana;
    boolean wykonajPrzelew = false;
    boolean usunKonto = false;
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();

    public Bezpieczenstwo(String zmiana, JFrame frame, KontoKlienta aktualneKonto, List<KontoKlienta> listaKlientow, List<KontoPracownika> listaPracownikow) {
        this.aktualneKonto = aktualneKonto;
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
        this.frame = frame;
        this.zmiana = zmiana;
    }

    public void proceduraBezpieczenstwa() {
        if (!weryfikacjaZmian()) {
            JOptionPane.showMessageDialog(frame, "Zmiany zostały cofnięte.", "", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!weryfikacjaTozsamosci()) {
            JOptionPane.showMessageDialog(frame, "Nieautoryzowany dostęp, zmiany zostały cofnięte.", "", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(Objects.equals(zmiana, "usunKonto")){
            usunKonto=true;
        }

        if(Objects.equals(zmiana, "przelew")){
            wykonajPrzelew = true;
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
        String zmiany = "";
        for (KontoKlienta konto : listaKlientow) {
            if (konto.getNrKonta() == aktualneKonto.getNrKonta()) {
                zmiany = znajdzZmiany(konto);
                break;
            }
        }
        int weryfikacja = JOptionPane.showConfirmDialog(frame, zmiany, "Zmiany:", JOptionPane.YES_NO_OPTION);
        return weryfikacja == JOptionPane.YES_OPTION;
    }

    private String znajdzZmiany(KontoKlienta konto) {
        String zmiany = "";
        long kwota;

        switch (zmiana) {
            case "dodajKarte":
                int index = aktualneKonto.getKarty().size();
                zmiany = "Dodanie karty o numerze: " + aktualneKonto.getKarty().get(index - 1).getNrKarty();
                break;
            case "usunKarte":
                if(aktualneKonto.getKarty().size()==0){
                    zmiany = "Usunięcie karty o numerze: " + konto.getKarty().get(0).getNrKarty();
                    break;
                }
                for (int i = 0; i < aktualneKonto.getKarty().size(); i++) {
                    if (konto.getKarty().get(i) != aktualneKonto.getKarty().get(i)) {
                        zmiany = "Usunięcie karty o numerze: " + konto.getKarty().get(i).getNrKarty();
                        break;
                    }
                }
                zmiany = "Usunięcie karty o numerze: " + konto.getKarty().get(aktualneKonto.getKarty().size()).getNrKarty();
                break;
            case "zablokujKarte":
                for (int i = 0; i < konto.getKarty().size(); i++) {
                    if (konto.getKarty().get(i).getStan() != aktualneKonto.getKarty().get(i).getStan()) {
                        zmiany = "Zablokowanie karty o numerze: " + konto.getKarty().get(i).getNrKarty();
                        break;
                    }
                }
                break;
            case "odblokujKarte":
                for (int i = 0; i < konto.getKarty().size(); i++) {
                    if (konto.getKarty().get(i).getStan() != aktualneKonto.getKarty().get(i).getStan()) {
                        zmiany = "Odblokowanie karty o numerze: " + konto.getKarty().get(i).getNrKarty();
                        break;
                    }
                }
                break;
            case "usunKonto":
                zmiany = "Usunięcie konta";
                break;
            case "zablokujKonto":
                zmiany = "Zablokowanie konta";
                break;
            case "odblokujKonto":
                zmiany = "Odblokowanie konta";
                break;
            case "zmienDane":
                zmiany = "Zmiana danych konta:\n";
                if (!Objects.equals(konto.getImie(), aktualneKonto.getImie())) {
                    zmiany += "Imię: " + konto.getImie() + "->" + aktualneKonto.getImie() + "\n";
                }
                if (!Objects.equals(konto.getNazwisko(), aktualneKonto.getNazwisko())) {
                    zmiany += "Nazwisko: " + konto.getNazwisko() + "->" + aktualneKonto.getNazwisko() + "\n";
                }
                if (!Objects.equals(konto.getHaslo(), aktualneKonto.getHaslo())) {
                    zmiany += "Zmieniono hasło\n";
                }
                if (Objects.equals(konto.getNrDowodu(), aktualneKonto.getNrDowodu())) {
                    zmiany += "Numer dowodu: " + konto.getNrDowodu() + "->" + aktualneKonto.getNrDowodu() + "\n";
                }
                break;
            case "wplac":
                kwota = aktualneKonto.getPieniadze() - konto.getPieniadze();
                zmiany = "Wpłacono " + kwota + " zł.";
                break;
            case "wyplac":
                kwota = konto.getPieniadze() - aktualneKonto.getPieniadze();
                zmiany = "Wypłacono " + kwota + " zł.";
                break;
            case "przelew":
                kwota = konto.getPieniadze() - aktualneKonto.getPieniadze();
                zmiany = "Przelano " + kwota + " zł.";
                break;
        }
        return zmiany;
    }

    private boolean weryfikacjaTozsamosci() {
        long nrKonta = Long.parseLong(JOptionPane.showInputDialog(frame, "Podaj numer konta", "", JOptionPane.PLAIN_MESSAGE));
        String hasloKlienta = JOptionPane.showInputDialog(frame, "Podaj hasło konta klienta", "", JOptionPane.PLAIN_MESSAGE);
        int idPracownika = Integer.parseInt(JOptionPane.showInputDialog(frame, "Podaj id pracownika", "", JOptionPane.PLAIN_MESSAGE));
        String hasloPracownika = JOptionPane.showInputDialog(frame, "Podaj hasło konta pracownika", "", JOptionPane.PLAIN_MESSAGE);

        if (nrKonta != aktualneKonto.getNrKonta()) return false;
        if (!Objects.equals(hasloKlienta, aktualneKonto.getHaslo())) return false;
        for (KontoPracownika kontoPracownika : listaPracownikow) {
            if (kontoPracownika.getIdPracownika() == idPracownika) {
                return Objects.equals(kontoPracownika.getHaslo(), hasloPracownika);
            } else {
                return false;
            }
        }
        System.out.println("5");
        return false;
    }

    public List<KontoKlienta> wyniki() {
        return listaKlientow;
    }

    public void przelej(long przelew, long nrKonta) {
        if(wykonajPrzelew){
            for (KontoKlienta konto : listaKlientow) {
                if (konto.getNrKonta() == nrKonta) {
                    konto.dodajPieniadze(przelew);
                }
            }
        }
    }

    public void usunKonto(){
        if(usunKonto){
            listaKlientow.remove(aktualneKonto);
        }
    }
}
