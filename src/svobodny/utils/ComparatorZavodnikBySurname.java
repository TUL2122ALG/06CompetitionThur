/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny.utils;

import svobodny.app.Zavodnik;
import java.util.Comparator;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class ComparatorZavodnikBySurname implements Comparator<Zavodnik>{

    @Override
    public int compare(Zavodnik o1, Zavodnik o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
    
}
