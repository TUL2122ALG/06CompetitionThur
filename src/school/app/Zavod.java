package school.app;

import school.utils.ErrorLinesException;
import school.app.Zavodnik;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import school.utils.ComparatorZavodnikByPrijmeni;
import school.utils.TimeTools;

public class Zavod {

    //data
    private String name;
    private ArrayList<Zavodnik> competitors;

    //konstruktor
    public Zavod(String name) {
        this.name = name;
        this.competitors = new ArrayList<>();
    }

    public void loadStart(File startFile) throws FileNotFoundException, IOException {
        List<Integer> errorLines = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            String[] parts;
            Zavodnik r = null;
            br.readLine(); //preskoceni hlavicky
            int lineNumber = 2;
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                    r = new Zavodnik(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3].charAt(0));
                try{
                    r.setClub(parts[4]);
                }catch(IllegalArgumentException e){
                    errorLines.add(lineNumber);
                }    
                competitors.add(r);
                lineNumber++;
            }
        }
        if(!errorLines.isEmpty()){
            throw new ErrorLinesException("Nevalidni klub na radcich " + errorLines.toString());    
        }
    }

    public void loadFinish(File finishFile) throws FileNotFoundException {
        try ( Scanner in = new Scanner(finishFile)) {
            int number;
            String casDobehu;
            Zavodnik r;
            in.nextLine();
            while (in.hasNext()) {
                number = in.nextInt();
                casDobehu = in.next();
                r = findByRegNumber(number);
                r.setFinishTime(casDobehu);
            }
        }
    }

    public void saveToFile(File results) throws IOException {
        //new PrintWriter(new OutputStreamWriter () pouzit, kdyz chci kodovani
        try ( PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results, true)))) {
            pw.println(String.format("%5s %5s %10s %10s %5s %1s %10s %10s %10s",
                    "Por.", "Cis.", "Jmeno", "Prijmeni", "Vek", "P", "Start", "Finish", "Doba")); //hlavicka
            int rank = 1;
            for (Zavodnik competitor : competitors) {
                pw.print(String.format("%4d.", rank));
                pw.println(competitor);
                rank++;
            }
        }
    }

    public void saveToBinaryFile(File results) throws FileNotFoundException, IOException {
        try ( DataOutputStream out = new DataOutputStream(new FileOutputStream(results, true))) {
            int nLetters;
            out.writeInt(competitors.size());
            for (Zavodnik competitor : competitors) {
                out.writeInt(competitor.getRegistracniCislo());
                nLetters = competitor.getJmeno().length();
                out.writeInt(nLetters);
                for (int i = 0; i < nLetters; i++) {
                    out.writeChar(competitor.getJmeno().charAt(i));
                }
                out.writeUTF(competitor.getPrijmeni());
                out.writeInt(competitor.getTime()); //cas jako cislo
            }
        }
    }

    public String readFromBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        int nCompetitors, number, lengthName, time;
        String name = "", surname = "";
        int rank = 1;
        try ( DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            while (!end) {
                try {
                    rank = 1;
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
                                rank, name, surname, number, TimeTools.secondsToTime(time)));
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
    public void addCompetitor(String name, String surname, int year, char gender, String club) {
        competitors.add(Zavodnik.getInstance(name, surname, year, gender, club));
    }

    public void setstartTimeAll(int hours, int minutes, int seconds) {
        for (Zavodnik competitor : competitors) {
            competitor.setStartTime(hours, minutes, seconds);
        }
    }

    public void setstartTimeAll(int hours, int minutes, int seconds, int offsetInMinutes) {
        for (int i = 0; i < competitors.size(); i++) {
            competitors.get(i).setStartTime(hours, minutes + i * offsetInMinutes, seconds);
        }
    }

    public void setFinishTimeOf(int registrationNumber, int hours, int minutes, int seconds) {
        Zavodnik z = findByRegNumber(registrationNumber);
        z.setFinishTime(hours, minutes, seconds);
    }

    private Zavodnik findByRegNumber(int registrationNumber) {
        for (Zavodnik competitor : competitors) {
            if (competitor.getRegistracniCislo() == registrationNumber) {
                return competitor;
            }
        }
        throw new NoSuchElementException("Zavodnik s cislem " + registrationNumber + " neexistuje.");
    }

//    public Zavodnik findFastest(){
//        int fastestTime = Integer.MAX_VALUE; int fastestIndex = -1;
//        for(int i = 0; i < competitors.size(); i++){
//            if(competitors.get(i).getTime() < fastestTime){
//                fastestTime = competitors.get(i).getTime();
//                fastestIndex = i;
//            }
//        }
//        return new Zavodnik(competitors.get(fastestIndex));
//    }
//    public int findFastestNumber(){
//        int fastestTime = Integer.MAX_VALUE; int fastest = -1;
//        for(int i = 0; i < competitors.size(); i++){
//            if(competitors.get(i).getTime() < fastestTime){
//                fastestTime = competitors.get(i).getTime();
//                fastest = competitors.get(i).getRegistracniCislo();
//            }
//        }
//        return fastest;
//    }
    private void sortByTime() {
        Collections.sort(competitors);
    }

    private void sortByPrijmeni() {
        Comparator cbp = new ComparatorZavodnikByPrijmeni();
        Collections.sort(competitors, cbp);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Zavodnik competitor : competitors) {
            sb.append(competitor).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        Zavod jiz50 = new Zavod("Jiz50");
        System.out.println(jiz50);
        String parent = System.getProperty("user.dir")+ File.separator +"data";
        File dataDirectory = new File(parent);
        //try {
            while (true) {
                try {
                    System.out.println("Zadej nazev vstupniho souboru");
                    //try{
                        jiz50.loadStart(new File(dataDirectory, sc.next()));
                    //}catch(ErrorLinesException e){
                        //System.out.println(e.getMessage());
                    //}
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadej znova");
                }catch(RuntimeException e){
                    jiz50 = new Zavod("Jiz50");
                    System.out.println(e.getMessage());
                    System.out.println("Nektery udaj v souboru je spatne. Oprav ho.");
                }
            }
            
            //        jiz50.addCompetitor("Alice", "Mala", 1980, 'F', "Sk Liberec");
            //        jiz50.addCompetitor("Bob", "Hruby", 1969, 'M', "Sk Liberec");
            //        jiz50.addCompetitor("Cyril", "drahy", 1991, 'M', "Sk Jablonec");
            System.out.println(jiz50);


//            jiz50.setstartTimeAll(9, 0, 0, 2);
//            System.out.println(jiz50);
//            jiz50.loadFinish(new File(dataDirectory, "finish.txt"));
////        jiz50.setFinishTimeOf(1, 10, 0, 0);
////        jiz50.setFinishTimeOf(2, 10, 10, 0);
////        jiz50.setFinishTimeOf(3, 10, 1, 0);
//            System.out.println(jiz50);
////        System.out.println("Nejrychlejsi: " + jiz50.findFastest());
//            jiz50.sortByTime();
//            System.out.println(jiz50);
//            System.out.println("Zadej soubor pro vysledky");
//            String filename = sc.next();
//            jiz50.saveToFile(new File(dataDirectory, filename));
//            jiz50.saveToBinaryFile(new File(dataDirectory,"results.dat"));
//            System.out.println(jiz50.readFromBinaryResults(new File(dataDirectory,"results.dat")));
////        jiz50.sortByPrijmeni();
////        System.out.println(jiz50);
//        } catch (IOException e) {
//            System.out.println("systemova chyba pri praci se souborem");
//
//        }
    }
}
