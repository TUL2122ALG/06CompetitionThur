package stepan;

/**
 *
 * @author mates
 */
public class Zavodnik {
    String start;
    String finish;
    int rocnik;
    String jmeno;
    String prijmeni;
    String pohlavi;
    
    private Zavodnik(String jmeno, String prijmeni, int rocnik, String start, String finish){
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.start = start;
        this.finish = finish;
    }
    
    
}
