package fisar;

/**
 *
 * @author lukas.fisar
 */
public class Zavodnik {
    private String jmeno;
    private String prijmeni;
    private String zavod;
    private int rokNarozeni;
    private String pohlavi;
    private String klub;
    private int startovniCislo;
    private String startovniVlna;
    private boolean zaplatil;
    private int start;
    private int cil;
    private int time;
    private int poradi;

    public Zavodnik(String jmeno, String prijmeni, String zavod, int rokNarozeni, String pohlavi) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.zavod = zavod;
        this.rokNarozeni = rokNarozeni;
        this.pohlavi = pohlavi;
    }

    

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public String getZavod() {
        return zavod;
    }

    public int getRokNarozeni() {
        return rokNarozeni;
    }

    public String getPohlavi() {
        return pohlavi;
    }

    public String getKlub() {
        return klub;
    }

    public int getStartovniCislo() {
        return startovniCislo;
    }

    public String getStartovniVlna() {
        return startovniVlna;
    }

    public boolean isZaplatil() {
        return zaplatil;
    }

    public int getStart() {
        return start;
    }

    public int getCil() {
        return cil;
    }

    public int getTime() {
        return time;
    }

    public int getPoradi() {
        return poradi;
    }
    
    
}
