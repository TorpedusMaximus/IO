package v2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Zapis {
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();


    public Zapis(List<KontoKlienta> listaKlientow, List<KontoPracownika> listaPracownikow) {
        this.listaKlientow = listaKlientow;
        this.listaPracownikow = listaPracownikow;
    }

    private JSONArray generujKlientow() {
        JSONArray klienci = new JSONArray();
        for (KontoKlienta konto : listaKlientow) {
            JSONObject klient = generujKlienta(konto);
            klienci.put(klient);
        }

        return klienci;
    }

    private JSONObject generujKlienta(KontoKlienta konto) {
        JSONObject klient = new JSONObject();
        JSONArray karty = new JSONArray();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        klient.put("NrKonta", konto.getNrKonta());
        klient.put("Imie", konto.getImie());
        klient.put("Nazwisko", konto.getNazwisko());
        klient.put("PESEL", konto.getPesel());
        klient.put("NrDowodu", konto.getNrDowodu());
        klient.put("Stan", konto.getStan());
        klient.put("Pieniadze", konto.getPieniadze());
        klient.put("Haslo", konto.getHaslo());

        for (Karta karta : konto.getKarty()) {
            JSONObject kartaJSON = new JSONObject();
            kartaJSON.put("NrKarty", karta.getNrKarty());
            kartaJSON.put("CVC", karta.getCVC());
            kartaJSON.put("Stan", karta.getStan());

            String dataWaznosci = formatter.format(karta.getDataWaznosci());
            kartaJSON.put("DataWaznosci", dataWaznosci);
            karty.put(kartaJSON);
        }
        klient.put("Karty", karty);

        String dataUrodzenia=formatter.format(konto.getDataUrodzenia());
        klient.put("DataUrodzenia", dataUrodzenia);

        return klient;
    }

    private JSONArray generujPracownikow() {
        JSONArray pracownicy = new JSONArray();
        for (KontoPracownika konto : listaPracownikow) {
            JSONObject pracownik = generujPracownika(konto);
            pracownicy.put(pracownik);
        }

        return pracownicy;
    }

    private JSONObject generujPracownika(KontoPracownika konto) {
        JSONObject pracownik = new JSONObject();

        pracownik.put("ID", konto.getIdPracownika());
        pracownik.put("Imie", konto.getImie());
        pracownik.put("Nazwisko", konto.getNazwisko());
        pracownik.put("PESEL", konto.getPesel());
        pracownik.put("Stan", konto.getStan());
        pracownik.put("Haslo", konto.getHaslo());

        return pracownik;
    }

    public void zapisz() {
        JSONArray klienci = generujKlientow();

        FileWriter zapisDoPliku = null;
        try {
            zapisDoPliku = new FileWriter("klienci.json");
            zapisDoPliku.write(klienci.toString(4));
            zapisDoPliku.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray pracownicy = generujPracownikow();
        try {
            zapisDoPliku = new FileWriter("pracownicy.json");
            zapisDoPliku.write(pracownicy.toString(4));
            zapisDoPliku.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
