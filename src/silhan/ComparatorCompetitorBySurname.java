/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package silhan;

import java.util.Comparator;

/**
 *
 * @author kubix
 */
public class ComparatorCompetitorBySurname implements Comparator<Competitor> {

    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
    
}
