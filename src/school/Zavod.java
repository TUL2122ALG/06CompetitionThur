package school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Zavod {
    
    //data
    private String name;
    private ArrayList<Zavodnik> competitors;
    
    //konstruktor
    public Zavod(String name){
        this.name = name;
        this.competitors = new ArrayList<>();
    }
    
    //gettery
    public String getName() {
        return name;
    }
    
    //defensive deep copy
    public ArrayList<Zavodnik> getCompetitors() {
        ArrayList<Zavodnik> copy = new ArrayList<>();
        for (Zavodnik zavodnik : competitors) {
            copy.add(new Zavodnik(zavodnik));
        }
        return copy;
    }

    public int getnCompetitors() {
        return competitors.size();
    }
    
    //methods
    public void addCompetitor(String name, String surname, int year, char gender, String club){
        competitors.add(Zavodnik.getInstance(name, surname, year, gender, club));
    }
    
    public void setstartTimeAll(int hours, int minutes, int seconds){
        for (Zavodnik competitor : competitors) {
            competitor.setStartTime(hours, minutes, seconds);
        }
    }
    
    public void setstartTimeAll(int hours, int minutes, int seconds, int offsetInMinutes){
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setStartTime(hours, minutes + i*offsetInMinutes, seconds);
        }
    }
    
    public void setFinishTimeOf(int registrationNumber, int hours, int minutes, int seconds ){
        Zavodnik z = findByRegNumber(registrationNumber);
        z.setFinishTime(hours, minutes, seconds);
    }
    
    private Zavodnik findByRegNumber(int registrationNumber){
        for (Zavodnik competitor : competitors) {
            if(competitor.getRegistracniCislo() == registrationNumber){
                return competitor;
            }
        }
        throw new NoSuchElementException("Zavodnik s cislem " + registrationNumber + " neexistuje.");
    }
    
    public Zavodnik findFastest(){
        int fastestTime = Integer.MAX_VALUE; int fastestIndex = -1;
        for(int i = 0; i < competitors.size(); i++){
            if(competitors.get(i).getTime() < fastestTime){
                fastestTime = competitors.get(i).getTime();
                fastestIndex = i;
            }
        }
        return new Zavodnik(competitors.get(fastestIndex));
    }
    
    public int findFastestNumber(){
        int fastestTime = Integer.MAX_VALUE; int fastest = -1;
        for(int i = 0; i < competitors.size(); i++){
            if(competitors.get(i).getTime() < fastestTime){
                fastestTime = competitors.get(i).getTime();
                fastest = competitors.get(i).getRegistracniCislo();
            }
        }
        return fastest;
    }
    
    private void sortByTime(){
        Collections.sort(competitors);
    }
    
    private void sortByPrijmeni(){
        Comparator cbp = new ComparatorZavodnikByPrijmeni();
        Collections.sort(competitors, cbp);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Zavodnik competitor : competitors) {
           sb.append(competitor).append("\n");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        jiz50.addCompetitor("Alice", "Mala", 1980, 'F', "Sk Liberec");
        jiz50.addCompetitor("Bob", "Hruby", 1969, 'M', "Sk Liberec");
        jiz50.addCompetitor("Cyril", "drahy", 1991, 'M', "Sk Jablonec");
        System.out.println(jiz50);
        jiz50.setstartTimeAll(9, 0, 0, 2);
        System.out.println(jiz50);
        jiz50.setFinishTimeOf(1, 10, 0, 0);
        jiz50.setFinishTimeOf(2, 10, 10, 0);
        jiz50.setFinishTimeOf(3, 10, 1, 0);
        System.out.println(jiz50);
        System.out.println("Nejrychlejsi: " + jiz50.findFastest());
        jiz50.sortByTime();
        System.out.println(jiz50);
        jiz50.sortByPrijmeni();
        System.out.println(jiz50);
    }
}
