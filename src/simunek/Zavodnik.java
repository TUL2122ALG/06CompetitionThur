package simunek;

public class Zavodnik {
    private String jmeno;
    private String prijmeni;
    private Pohlavi pohlavi;
    private int rocnik;
    
    
    public static Zavodnik getInstance(String jmeno, String prijmeni, Pohlavi pohlavi, int rocnik){        
        return new Zavodnik(jmeno, prijmeni, pohlavi, rocnik);
    }
    
    private Zavodnik(String jmeno, String prijmeni, Pohlavi pohlavi, int rocnik){
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.pohlavi = pohlavi;
        this.rocnik = rocnik;
    }
    
    public String getJmeno(){
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public Pohlavi getPohlavi() {
        return pohlavi;
    }

    public int getRocnik() {
        return rocnik;
    }
    
}
