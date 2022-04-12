
package simunek;

import java.util.ArrayList;
import java.util.List;

public class Zavod {
    private int ID;
    private String nazev;
    private final List<Registrace> registraceZavodu;
    
    public boolean registrovatZavodnika(Zavodnik zavodnik){
        // TODO check if racer is not already in race
        // return false;
        // TODO 
        var registrace = new Registrace(zavodnik);
        
        registraceZavodu.add(registrace);
        return true;
    }
    
    
    public Zavod(int ID, String nazev){
        this.ID = ID;
        this.nazev = nazev;
        registraceZavodu = new ArrayList<>();
    }
    
    
    
}
