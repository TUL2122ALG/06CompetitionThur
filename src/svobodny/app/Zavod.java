/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny.app;
import svobodny.app.Zavodnik;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringJoiner;
import svobodny.utils.ComparatorZavodnikBySurname;
import svobodny.Gender;

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
            Zavodnik r = null;
            br.readLine(); // preskoceni hl
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                try {
                    r = new Zavodnik(parts[0], parts[1], Integer.parseInt(parts[2]), Gender.of(parts[3].charAt(0)), parts[4]);
                    r.setClub(parts[4]);
                } catch (IllegalArgumentException e) {
                    
                }
                
                competitors.add(r);
            }
        }
    }
    
    public void loadFinish(File finishFile) throws FileNotFoundException {
        try(Scanner in = new Scanner(finishFile)) {
            int number;
            String finishTime;
            Zavodnik r;
            in.nextLine();
            while(in.hasNext()) {
                number = in.nextInt();
                finishTime = in.next();
                r = findByStartNumber(number);
                r.setFinishTime(finishTime);
            }
        }
    }
    
    public void saveToFile(File results) throws IOException {
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results,true)))) {
            pw.println(String.format("%5s %5s %10s %5s %10s %10s %10s",
                    "Por.", "Cis.", "Jmeno", "Prijmeni", "Vek", "F", "Start", "Finish", "Doba")); // hlavicka
            int rank = 1;
            for (Zavodnik competitor : competitors) {
                pw.print(String.format("%4d.", rank));
                pw.println(competitor);
                rank++;
            }
        }
    }
    
    public void saveToBinaryFile(File results) throws FileNotFoundException, IOException {
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(results))) {
            int nLetters;
            out.writeInt(competitors.size());
            for (Zavodnik competitor : competitors) {
                out.writeInt(competitor.getRegNumber());
                nLetters = competitor.getFirstName().length();
                out.writeInt(nLetters);
                for (int i = 0; i < nLetters; i++) {
                    out.writeChar(competitor.getFirstName().charAt(i));            
                }
                out.writeUTF(competitor.getSurname());
                out.writeLong(competitor.getTimeInSeconds()); // cas jako cislo
            }
        }
    }
    
    public String readFromBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        int nCompetitors, number, lengthName, time;
        String name, surname;
        int rank = 1;
        try(DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            while (!end) {
                try {
                    nCompetitors = in.readInt();
                    for (int i = 0; i < nCompetitors; i++) {
                        number = in.readInt();
                        lengthName = in.readInt();
                        name = "";
                        for (int j = 0; j < lengthName; j++) {
                            name += in.readChar();
                        }
                        surname = in.readUTF();
                        time = in.readInt();
                        sb.append(String.format("%3d. %10s %10s %3d %10s%n",
                                rank, name, surname, number, LocalTime.ofSecondOfDay(time).format(DateTimeFormatter.ISO_TIME)));
                        rank++;
                    }
                    sb.append("\n");
                } catch (EOFException e) {
                    end = true;
                }
            }
            
        }
        return sb.toString();
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
            if (competitor.getRegNumber() == startNumber) {
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
        return competitors.get(findFastestIndex()).getRegNumber();
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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        String parent = System.getProperty("user.dir") + File.separator + "data";
        File dataDirectory = new File(parent);
        try {
             while(true) {
                try {
                    System.out.println("Zadej soubor startu");
                    jiz50.loadStart(new File(dataDirectory, sc.next()));
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadej znova");
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
            jiz50.loadFinish(new File(dataDirectory, "finish.txt"));
    //        jiz50.setFinishTimeOf(1, 10, 0, 0);
    //        jiz50.setFinishTimeOf(2, LocalTime.of(10, 56, 32));
    //        jiz50.setFinishTimeOf(3, 10, 1, 0);
            System.out.println(jiz50);
    //        System.out.println("");
    //        System.out.println("Nejrychlejsi:\n" + jiz50.findFastest());
    //        System.out.println("");
            jiz50.sortByTime();
            System.out.println(jiz50);
    //        System.out.println("");
    //        jiz50.sortBySurname();
    //        System.out.println(jiz50);
    //        System.out.println("");
            System.out.println("Zadej soubor pro vysledky");
            String filename = sc.next();
            jiz50.saveToFile(new File(dataDirectory, filename));
            jiz50.saveToBinaryFile(new File(dataDirectory, "results.dat"));
            System.out.println(jiz50.readFromBinaryResults(new File(dataDirectory, "results.dat")));
        } catch (IOException e) {
            System.out.println("Systemova chyba pri praci se souborem");
            System.out.println(e.getMessage());
        }
    }
}
