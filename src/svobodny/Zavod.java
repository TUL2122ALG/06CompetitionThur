/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;

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
    
    private void loadStart(File startFile) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            String[] parts;
            Zavodnik r;
            br.readLine(); // preskoceni hl
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new Zavodnik(parts[0], parts[1], Integer.parseInt(parts[2]), Gender.of(parts[3].charAt(0)), parts[4]);
                competitors.add(r);
            }
        }
    }
    
    public void loadFinish(File finishFile) throws FileNotFoundException {
        try(Scanner in = new Scanner(finishFile)) {
            int number;
            String casDobehu;
            Zavodnik r;
            in.nextLine();
            while(in.hasNext()) {
                number = in.nextInt();
                casDobehu = in.next();
                r = findByStartNumber(number);
                
            }
        }
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
    
    public void setStartTimeAll(int hours, int minutes, int seconds) {
        for (Zavodnik competitor : competitors) {
            competitor.setStartTime(hours, minutes, seconds);
        }
    }
    
    public void setStartTimeAll(LocalTime time) {
        for (Zavodnik competitor : competitors) {
            competitor.setStartTime(time);
        }
    }
    
    public void setStartTimeAll(int hours, int minutes, int seconds, int offsetInMinutes) {
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setStartTime(hours, minutes + i*offsetInMinutes, seconds);
        }
    }
    
    public void setStartTimeAll(LocalTime time, Duration offset) {
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setStartTime(time = time.plus(offset));
        }
    }
    
    public void setFinishTimeOf(int startNumber, int hours, int minutes, int seconds) {
        Zavodnik z = findByStartNumber(startNumber);
        z.setFinishTime(hours, minutes, seconds);
    }
    
    public void setFinishTimeOf(int startNumber, LocalTime time) {
        Zavodnik z = findByStartNumber(startNumber);
        z.setFinishTime(time);
    }
    
    private Zavodnik findByStartNumber(int startNumber) {
        for (Zavodnik competitor: competitors) {
            if (competitor.getStartNumber() == startNumber) {
                return competitor;
            }
        }
        throw new NoSuchElementException(String.format("Zavodnik s cislem %d neexistuje.", startNumber));
    }
    
    private int findFastestIndex() {
        long fastestTime = Integer.MAX_VALUE;
        int fastestIndex = -1;
        long time;
        for (int i = 0; i < competitors.size(); i++) {
            if ((time = competitors.get(i).getTimeInSeconds()) < fastestTime) {
                fastestTime = time;
                fastestIndex = i;
            }
        }
        
        return fastestIndex;
    }
    
    public Zavodnik findFastest() {
        return new Zavodnik(competitors.get(findFastestIndex()));
    }
    
    public int findFastestNumber() {
        return competitors.get(findFastestIndex()).getStartNumber();
    }
    
    private void sortByTime() {
        Collections.sort(competitors);
    }
    
    private void sortBySurname() {
        Comparator cbs = new ComparatorZavodnikBySurname();
        Collections.sort(competitors,cbs);
    }
    
    public String getResultList() {
        sortByTime();
        return this.toString();
    }
    
    public String getCompetitorList() {
        sortBySurname();
        return this.toString();
    }
    
    // TODO
    @Override
    public String toString() {
        Scanner sc = new Scanner(System.in);
        
        StringJoiner sj = new StringJoiner(System.getProperty("line.separator"));
        for (Zavodnik competitor : competitors) {
            sj.add(competitor.toString());
        }
        return sj.toString();
    }
    
    // Testing
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        while(true) {
            try {
                jiz50.loadStart(new File(sc.next()));
                break;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("Zadej znova");
            } catch (IOException e) {
                System.out.println("Systemova chyba pri praci se souborem");
            }
        }
        
//        jiz50.addCompetitor("Alice", "Mala", 1980, Gender.FEMALE, "Sliberec");
//        jiz50.addCompetitor("Bob", "Hruby", 1969, Gender.MALE, "Skiberec");
//        jiz50.addCompetitor("Cyril", "drahy", 1991, Gender.MALE, "Skjablonec");
        System.out.println(jiz50);
        System.out.println("");
        //jiz50.setStartTimeAll(9, 0, 0, 2);
        jiz50.setStartTimeAll(LocalTime.of(9,0,0),Duration.of(30,ChronoUnit.SECONDS));
        System.out.println(jiz50);
        System.out.println("");
        jiz50.setFinishTimeOf(1, 10, 0, 0);
        jiz50.setFinishTimeOf(2, LocalTime.of(10, 56, 32));
        jiz50.setFinishTimeOf(3, 10, 1, 0);
        System.out.println(jiz50);
        System.out.println("");
        System.out.println("Nejrychlejsi:\n" + jiz50.findFastest());
        System.out.println("");
        jiz50.sortByTime();
        System.out.println(jiz50);
        System.out.println("");
        jiz50.sortBySurname();
        System.out.println(jiz50);
        System.out.println("");
    }
}
