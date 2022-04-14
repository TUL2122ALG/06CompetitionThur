/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class Zavod {
    // Instance variables
    private String name;
    private ArrayList<Zavodnik> competitors;
    
    // Constructor
    public Zavod(String name) {
        this.name = name;
        this.competitors = new ArrayList<>();
    }
    
    
    // Getters

    public String getName() {
        return name;
    }

    public ArrayList<Zavodnik> getCompetitors() {
        ArrayList<Zavodnik> copy = new ArrayList<>();
        for (Zavodnik zavodnik: competitors) {
            copy.add(new Zavodnik(zavodnik));
        }
        return copy;
    }
    
    public int getnCompetitors() {
        return competitors.size();
    }
    
    
    // Methods
    
    public void addCompetitor(String name, String surname, int birthYear, Gender gender, String club) {
        competitors.add(Zavodnik.getInstance(name, surname, birthYear, gender, club));
    }
    
    public void setStartTimeAll(int hours, int minutes, int seconds, int offsetInMinutes) {
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setStartTime(hours, minutes + i*offsetInMinutes, seconds);
        }
    }
    
    public void setFinishTimeOf(int startNumber, int hours, int minutes, int seconds) {
        Zavodnik z = findByStartNumber(startNumber);
        z.setFinishTime(hours, minutes, seconds);
    }
    
    private Zavodnik findByStartNumber(int startNumber) {
        for (Zavodnik competitor: competitors) {
            if (competitor.getStartNumber() == startNumber) {
                return competitor;
            }
        }
        throw new NoSuchElementException(String.format("Zavodnik s cislem %d neexistuje.", startNumber));
    }
    
    public int findFastest() {
        
    }
    
    public int findFastestNumber() {
        long fastestTime = Integer.MAX_VALUE;
        int fastest = -1;
        long time;
        for (int i = 0; i < competitors.size(); i++) {
            if ((time = competitors.get(i).getTimeInSeconds()) < fastestTime) {
                fastestTime = time;
                fastest = competitors.get(i).getStartNumber();
            }
        }
        return fastest;
    }
    
    private void sortByTime() {
        Collections.sort(competitors);
    }
    
    private void sortBySurname() {
        Comparator cbs = new ComparatorZavodnikBySurname();
        Collections.sort(competitors,cbs);
    }
    
    // Testing
    public static void main(String[] args) {
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        jiz50.addCompetitor("Alice", "Mala", 1980, Gender.FEMALE, "SK Liberec");
        jiz50.addCompetitor("Bob", "Hruby", 1980, Gender.MALE, "SK Liberec");
        jiz50.addCompetitor("Cyril", "Drahy", 1980, Gender.MALE, "SK Liberec");
        System.out.println(jiz50);
        jiz50.setStartTimeAll(9,0,0,2);
        System.out.println(jiz50);
        jiz50.setFinishTimeOf(1,10,0,0);
        jiz50.setFinishTimeOf(2,10,10,0);
        jiz50.setFinishTimeOf(3,10,1,0);
        //jiz50.setFinishTimeOf(6,10,10,0);
        System.out.println(jiz50);
    }
}
