import java.util.Date;

public class Karta {
    private final long nrKarty;
    private final int CVC;
    private final Date dataWaznosci;

    public Karta(long nrKarty, int CVC, Date dataWaznosci) {
        this.nrKarty = nrKarty;
        this.CVC = CVC;
        this.dataWaznosci = dataWaznosci;
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
}
