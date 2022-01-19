package Test;

import org.json.JSONException;
import org.junit.Test;
import v2.Aplikacja;
import v2.KontoKlienta;
import v2.Odczyt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class junitTest {
    @Test
    public void wybierzKontoTest() throws JSONException {
        Aplikacja aplikacja = new Aplikacja();
        assertTrue("wybranie konta", aplikacja.wybierzKonto(1L));
    }

    @Test
    public void wybierzKontoTest2() throws JSONException {
        Aplikacja aplikacja = new Aplikacja();
        assertTrue("wybranie konta", aplikacja.wybierzKonto(2L));
    }

    @Test
    public void wybierzKontoTest3() throws JSONException {
        Aplikacja aplikacja = new Aplikacja();
        assertTrue("wybranie konta", aplikacja.wybierzKonto(3L));
    }

    @Test
    public void dodajKarteTest() {
        Aplikacja aplikacja = new Aplikacja();
        aplikacja.wybierzKonto(1L);
        KontoKlienta aktualneKonto = aplikacja.getAktualneKonto();

        assertTrue("dodanie karty", aktualneKonto.dodajKarte(aplikacja.losujNrKarty(), Aplikacja.getFrame()));
    }

    @Test
    public void odczytTest() {
        Odczyt odczyt = new Odczyt();

        assertEquals("wczytanie klientow", 1, odczyt.klienci().get(0).getNrKonta());
    }
}
