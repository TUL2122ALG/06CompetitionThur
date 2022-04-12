package silhan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class Competition {    
    //data
    private String name;
    private ArrayList<Competitor> competitors;
    private int counter=1;
    
    //konstruktor
    public Competition(String name){
        this.name = name;
        this.competitors = new ArrayList<>(); //konstruktor
    }
    
    //gettery
    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }
    
    //defensive deep copy
    public ArrayList<Competitor> getCompetitors() {
        ArrayList<Competitor> copy = new ArrayList<>();
        for (Competitor competitor : competitors){ //prochazim pole competitors (v kazdem kroku jednoho zavodnika)
            copy.add(new Competitor(competitor)); //volam kopirujici konstruktor a pridavam do pole copy
        }
        return copy;
    }

    public int getnCompetitors() {
        return competitors.size();
    }
    
    //methods    
    public void addCompetitor(String name, String surname, int year, char gender){
        competitors.add(Competitor.getInstance(name, surname, year, gender, this.getCounter()));
        counter++;
    }
    
    public void setStartTimeAll(int hours, int min, int sec){
        for (Competitor competitor : competitors) {
            competitor.setStartTime(hours, min, sec);
        }
    }
    
    //zavodnici startuji s casovym rozestupem podle registracnich cisel
    public void setStartTimeAll(int hours, int min, int sec, int offsetInMinutes){
        for (int i = 0; i < competitors.size();i++) {            
            competitors.get(i).setStartTime(hours, min + i*offsetInMinutes, sec);
        }
    }
    
    public void setFinishTimeOf(int regNum, int hours, int min, int sec){
        findByRegNum(regNum).setFinishTime(hours, min, sec);
    }
    
    private Competitor findByRegNum(int regNum){
        for (Competitor competitor : competitors) {
            if(competitor.getRegNum()==regNum){
                return competitor;
            }
        }
        throw new NoSuchElementException("Zavodnik s cislem " + regNum + " neexistuje");
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Competitor competitor : competitors) {
            sb.append(competitor).append("\n"); // /n ukoncuje radek
        }
        return sb.toString();
    }
   
    public Competitor findFastest(){
        long fastestTime = Integer.MAX_VALUE; int fastestIndex = -1;
        for(int i =0;i< competitors.size();i++){
            if(competitors.get(i).getTotalTime()<fastestTime){
                fastestTime= competitors.get(i).getTotalTime();
                fastestTime = competitors.get(i).getTotalTime();
                fastestIndex = i;
            }
        }
        return new Competitor(competitors.get(fastestIndex));
    }
    
    public int findFastestNumber(){
        long fastestTime = Integer.MAX_VALUE; int fastest=-1;
        for(int i =0;i< competitors.size();i++){
            if(competitors.get(i).getTotalTime()<fastestTime){
                fastestTime= competitors.get(i).getTotalTime();
                fastest = competitors.get(i).getRegNum();
            }
        }
        return fastest;
    }
    //sort?
    public void sortByTotalTime(){
        Collections.sort(competitors);
    }
    
    private void sortBySurname(){ //Comparator je nadrazeny datovy typ pro ComparatorCompetitorBySurname
        Comparator cbp = new ComparatorCompetitorBySurname(); //objekt kterým vlastně dávám kód metody compare
        Collections.sort(competitors, cbp); //metodu vlastna balim do objektu
    }
    /*
    public void sortCompetitors(){
        int kam; Competitor pom;
        int vymena = competitors.length;
        while(vymena>0){
            kam = vymena;
            vymena = 0;
            for(int i=0; i<kam;i++){
                if(competitors[i].getTotalTime()<competitors[i+1].getTotalTime()){
                    pom = competitors[i];
                    competitors[i]=competitors[i+1];
                    competitors[i+1]=competitors[i];
                    vymena = i;
                }
            }
        }
    }
*/
    //testovani
    /*
    public static void main(String[] args) {
        Competition jiz50 = new Competition("Jiz50");
        System.out.println(jiz50);
        jiz50.addCompetitor("Jakub", "Silhan", 2002, 'M');
        jiz50.addCompetitor("Ladislav", "Silhan", 1973, 'M');
        jiz50.addCompetitor("Ivona", "Silhanova", 1978, 'F');
        System.out.println(jiz50);
        jiz50.setStartTimeAll(9, 0, 0, 2);
        System.out.println(jiz50);
        jiz50.setFinishTimeOf(1, 10, 0, 0);
        jiz50.setFinishTimeOf(2, 10, 10, 0);
        jiz50.setFinishTimeOf(3, 10, 1, 0);
        System.out.println(jiz50);
        System.out.println("Nejrychlejsi:" + jiz50.findFastest());
        jiz50.sortByTotalTime();
        System.out.println(jiz50);
        jiz50.sortBySurname();
        System.out.println(jiz50);
    }
*/
}
