import java.util.Date;

public class Karta {
    private final long nrKarty;
    private final int CVC;
    private final Date dataWaznosci;
    private boolean stan ;

    public Karta(long nrKarty, int CVC, Date dataWaznosci) {
        this.nrKarty = nrKarty;
        this.CVC = CVC;
        this.dataWaznosci = dataWaznosci;
        this.stan=true;
    }

    public long getNrKarty() {
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
