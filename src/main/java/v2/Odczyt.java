package v2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Odczyt {
    private List<KontoKlienta> listaKlientow = new ArrayList<>();
    private List<KontoPracownika> listaPracownikow = new ArrayList<>();

    public Odczyt() {
        try {
            wczytajKlientow();
            wczytajPracownikow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.jetbrains.annotations.NotNull
    private JSONArray wczytajPlik(String plik) throws IOException {
        String dane = new String(Files.readAllBytes(Paths.get(plik)));
        return new JSONArray(dane);
    }

    private void wczytajPracownikow() throws IOException {
        JSONArray pracownicy = wczytajPlik("pracownicy.json");
        for (int i = 0; i < pracownicy.length(); i++) {
            JSONObject pracownik = pracownicy.getJSONObject(i);
            String imie = pracownik.getString("Imie");
            int pesel = pracownik.getInt("PESEL");
            String nazwisko = pracownik.getString("Nazwisko");
            String haslo = pracownik.getString("Haslo");
            int idPracownika = pracownik.getInt("ID");
            boolean stan = pracownik.getBoolean("Stan");
            KontoPracownika kontoPracownika = new KontoPracownika(pesel, haslo, imie, nazwisko, idPracownika, stan);
            listaPracownikow.add(kontoPracownika);
        }
    }

    public void wczytajKlientow() throws IOException {
        JSONArray klienci = wczytajPlik("klienci.json");
        for (int i = 0; i < klienci.length(); i++) {
            JSONObject klient = klienci.getJSONObject(i);

            long nrKonta = klient.getLong("NrKonta");
            String imie = klient.getString("Imie");
            int pesel = klient.getInt("PESEL");
            String nrDowodu = klient.getString("NrDowodu");
            long pieniadze = klient.getLong("Pieniadze");
            boolean stan = klient.getBoolean("Stan");
            String haslo = klient.getString("Haslo");
            String nazwisko = klient.getString("Nazwisko");
            String data = klient.getString("DataUrodzenia");

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date dataUrodzenia = null;
            try {
                dataUrodzenia = formatter.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            List<Karta> karty = new ArrayList();
            JSONArray listaKart = klient.getJSONArray("Karty");
            for (int ii = 0; i < listaKart.length(); i++) {
                JSONObject kartaJSON = listaKart.getJSONObject(ii);
                String nrKarty = kartaJSON.getString("NrKarty");
                int CVC = kartaJSON.getInt("CVC");
                String dataKarty = kartaJSON.getString("DataWaznosci");
                boolean stanKarty = kartaJSON.getBoolean("Stan");
                Date dataWaznosci = null;
                try {
                    dataWaznosci = formatter.parse(dataKarty);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Karta karta = new Karta(nrKarty, CVC, dataWaznosci, stan);
                karty.add(karta);
            }
            KontoKlienta kontoKlienta = new KontoKlienta(pesel, haslo, imie, nazwisko, nrDowodu, dataUrodzenia, nrKonta, stan);
            kontoKlienta.dodajPieniadze(pieniadze);
            kontoKlienta.setKarty(karty);
            listaKlientow.add(kontoKlienta);
        }
    }

    public List<KontoKlienta> klienci() {
        return listaKlientow;
    }

    public List<KontoPracownika> pracownicy() {
        return listaPracownikow;
    }
}
