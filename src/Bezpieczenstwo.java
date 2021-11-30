public class Bezpieczenstwo {
    private KontoKlienta aktualneKonto;
    private KontoKlienta noweKonto;
    private Polaczenie polaczenie;


    public Bezpieczenstwo(KontoKlienta aktualneKonto, KontoKlienta noweKonto) {
        this.aktualneKonto = aktualneKonto;
        this.noweKonto = noweKonto;
    }

    public void proceduraBezpieczenstwa(){
        weryfikacjaZmian();
        weryfikacjaTozsamosci();
    }

    private boolean weryfikacjaZmian(){
        boolean ok=true;
        return ok;
    }

    private boolean weryfikacjaTozsamosci(){
        int pesel = 0;
        String hasloKlienta = null;
        int idPracownika = 0;
        String hasloPracownika=null;

        if(pesel!=aktualneKonto.getPesel())return false;
        if(hasloKlienta!=aktualneKonto.getHaslo())return false;
        if(!polaczenie.potwierdzDanePracownika(idPracownika,hasloPracownika))return false;
        return true;
    }



}
