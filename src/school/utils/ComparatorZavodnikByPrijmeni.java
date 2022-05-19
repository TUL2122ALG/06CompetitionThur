/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package school.utils;

import school.app.Zavodnik;
import java.util.Comparator;

/**
 *
 * @author 
 */
public class ComparatorZavodnikByPrijmeni implements Comparator<Zavodnik>{

    @Override
    public int compare(Zavodnik o1, Zavodnik o2) {
        return o1.getPrijmeni().compareTo(o2.getPrijmeni());
    }

}
