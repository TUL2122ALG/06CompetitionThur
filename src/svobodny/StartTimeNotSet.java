/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class StartTimeNotSet extends IllegalStateException {

    public StartTimeNotSet(Zavodnik z) {
        super(String.format("U zavodnika %d nebyl nastaven cas startu, nelze nastavit cas v cili.", z.getStartNumber()));
    }
    
}
