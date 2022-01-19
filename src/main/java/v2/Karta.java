package v2;

import java.util.Date;

public class Karta {
    private final String nrKarty;
    private final int CVC;
    private final Date dataWaznosci;
    private boolean stan ;

    public Karta(String nrKarty, int CVC, Date dataWaznosci) {
        this.nrKarty = nrKarty;
        this.CVC = CVC;
        this.dataWaznosci = dataWaznosci;
        this.stan=true;
    }

    public Karta(Karta karta){
        this.nrKarty = karta.getNrKarty();
        this.CVC = karta.getCVC();
        this.dataWaznosci = karta.getDataWaznosci();
        this.stan=karta.getStan();
    }

    public Karta(String nrKarty, int cvc, Date dataWaznosci, boolean stan) {
        this.nrKarty = nrKarty;
        this.CVC = cvc;
        this.dataWaznosci = dataWaznosci;
        this.stan=stan;
    }

    public String getNrKarty() {
        return nrKarty;
    }

    public int getCVC() {
        return CVC;
    }

    public Date getDataWaznosci() {
        return dataWaznosci;
    }

    public boolean getStan() {
        return stan;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }
}
