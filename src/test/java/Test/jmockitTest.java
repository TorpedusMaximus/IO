package Test;

import mockit.Mocked;
import org.junit.Test;
import v2.Aplikacja;
import v2.KontoKlienta;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class jmockitTest {
    @Mocked
    KontoKlienta konto;

    @Test
    public void dodajPieniadzeTest() {
        konto = new KontoKlienta(252724, "12345", "test", "testowy", "tst", new Date(), 252766);
        konto.dodajPieniadze(420);
        assertEquals("dodanie pieniedzy", 420, konto.getPieniadze());
    }

    @Test
    public void dodajPieniadzeTest2() {
        konto = new KontoKlienta(252724, "12345", "test", "testowy", "tst", new Date(), 252766);
        konto.dodajPieniadze(420);
        assertEquals("dodanie pieniedzy", 540, konto.getPieniadze());
    }

    @Test
    public void dodajKarteTest() {
        konto = new KontoKlienta(252724, "12345", "test", "testowy", "tst", new Date(), 252766);
        konto.dodajKarte("kochamIO", Aplikacja.getFrame());
        assertSame("dodanie karty", "kochamIO", konto.getKarty().get(0).getNrKarty());
    }


}
