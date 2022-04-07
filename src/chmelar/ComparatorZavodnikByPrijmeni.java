/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

import java.util.Comparator;

/**
 *
 * @author tomas.chmelar
 */
public class ComparatorZavodnikByPrijmeni implements Comparator<Zavodnik>{

    @Override
    public int compare(Zavodnik o1, Zavodnik o2) {
        return o1.getPrijmeni().compareTo(o2.getPrijmeni());
    }
    
}
