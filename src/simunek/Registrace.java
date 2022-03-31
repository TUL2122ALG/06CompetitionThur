
package simunek;

public class Registrace {
    private static int IDcitac = 0;
    private Zavodnik zavodnik;
    private Kategorie kategorie;
    private int registracniCislo;
    // times are recorded as number of seconds saved as a double
    private double casStartu;
    private double casCile;
    private double dosazenyCas;
    private int poradiKategorie;
    private int poradiCelkove;
    
    
    public Registrace(Zavodnik zavodnik){
        this.zavodnik = zavodnik;
        this.registracniCislo = IDcitac++;
        this.kategorie = urcitKategorii(zavodnik);
    }
    
    private Kategorie urcitKategorii(Zavodnik zavodnik){
        int vek = 2022 - zavodnik.getRocnik();
        if (zavodnik.getPohlavi() == Pohlavi.MUZ){    
            if (vek < 30){
                return Kategorie.MUZI30;
            }
            if (vek < 50){
                return Kategorie.MUZI50;
            }
            return Kategorie.MUZI50p;
        }
        if (vek < 30){
            return Kategorie.ZENY30;
        }
        if (vek < 50){
            return Kategorie.ZENY50;
        }
        return Kategorie.ZENY50p;
    }
    
    
    
    public void nastavitCasStartu(double sekundy){
        this.casStartu = sekundy;
    }
    
    public void nastavitCasCile(double sekundy){
        this.casCile = sekundy;
    }
    
    public void vypocitatCasZavodu(){
        this.dosazenyCas = this.casCile - this.casStartu;
    }
    
    public void nastavitPoradiKategorie(int poradi){
        this.poradiKategorie = poradi;
    }
    
    public void nastavitPoradiCelkove(int poradi){
        this.poradiCelkove = poradi;
    }
}
