/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package school;

/**
 *
 * @author 
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Zavodnik implements Comparable<Zavodnik>{

    private String jmeno;
    private String prijmeni;
    private int rocnik;
    private int registracniCislo;
//    private Date startTime;
//    private LocalTime finishTime;
    private int startTime;
    private int finishTime;
    private int time;
    private char pohlavi;
    private static int pocitadlo = 1;
    
    private String klub;

    public Zavodnik(String jmeno, String prijmeni, int rocnik, char pohlavi, String klub) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.pohlavi = pohlavi;
        this.klub = checkClub(klub);
        this.registracniCislo = pocitadlo;
        Zavodnik.pocitadlo++;
    }
    
    private String checkClub(String club){//Sokol So Sooooo
        if(!club.matches("^[A-Z][a-z]+$")){
            throw new IllegalArgumentException("Nevalidni nazev klubu. Validni zacina velkym pismenem a ma jedna a vice dalsich pismen");
        }
        return club;
    }
   
    
   //kopie zavodnika | metoda clone()
    public Zavodnik(Zavodnik z){
        this.jmeno = z.jmeno;
        this.prijmeni = z.prijmeni;
        this.rocnik = z.rocnik;
        this.pohlavi = z.pohlavi;
        this.klub = z.klub;
        this.registracniCislo = z.registracniCislo;
        this.startTime = z.startTime;
        this.finishTime = z.finishTime;
        this.time = z.getTime();
    }
    
    public static Zavodnik getInstance(String jmeno, String prijmeni, int rocnik, char pohlavi, String klub){
        return new Zavodnik(jmeno, prijmeni, rocnik, pohlavi,klub);
    }

    public String getKlub() {
        return klub;
    }

    public int getVek() {
        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear();
        return year - rocnik  ;
    }

    public int getRegistracniCislo() {
        return registracniCislo;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRocnik() {
        return rocnik;
    }

    public int getStartTime() {
        //return startTime.getTime();
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public int getTime() {
        if(getStavZavodnika() == StavZavodnika.UKONCEN){
            time = TimeTools.timeCompare(startTime, finishTime);
        }
        return time;
    }

    public char getPohlavi() {
        return pohlavi;
    }

    public static int getPocitadlo() {
        return pocitadlo;
    }

    public String getStringTime() {
        return TimeTools.secondsToTime(time);
    }

    public StavZavodnika getStavZavodnika() {
        if (this.startTime == 0) {
            return StavZavodnika.NEZAHAJEN;
        } else if (this.startTime != 0 && this.finishTime == 0) {
            return StavZavodnika.NEUKONCEN;
        } else {
            return StavZavodnika.UKONCEN;
        }
    }

    public void setStartTime(int startTime) { //9*3600 + 12*60
        this.startTime = startTime;
        //this.startTime = new Date(startTime);
    }

    public void setFinishTime(int finishTime) {
        //if (startTime == null){
        if(this.startTime == 0){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili.");
        }
        //this.finishTime = LocalTime.ofSecondOfDay(finishTime);
        getTime();
    }

    public void setStartTime(int hodiny, int minuty, int sekundy) { //9 12 0
        this.startTime = TimeTools.timeToSeconds(hodiny, minuty, sekundy);
    }

    public void setFinishTime(int hodiny, int minuty, int sekundy) { 
        if(this.startTime == 0){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili.");
        }
        this.finishTime = TimeTools.timeToSeconds(hodiny, minuty, sekundy);
    }

    public void setStartTime(String time) { //09:12:00
        this.startTime = TimeTools.timeToSeconds(time);
    }

    public void setFinishTime(String time) {
        if(this.startTime == 0){
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili.");
        }
        this.finishTime = TimeTools.timeToSeconds(time);
    }
    
    public String toString(){
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s",
                this.registracniCislo, this.jmeno, this.prijmeni, this.getVek(), this.pohlavi,
                TimeTools.secondsToTime(this.startTime), TimeTools.secondsToTime(this.finishTime),
                TimeTools.secondsToTime(this.getTime()));
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        try{
            Zavodnik z = new Zavodnik("Alice", "Mala", sc.nextInt(), 'F', "Sk Liberec");
            System.out.println(z);
            //z.setStartTime(9,0,0);
            System.out.println(z);
       
            z.setFinishTime("10:02:05");
            System.out.println(z);
        }catch(StartTimeNotSet e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Chyby");
        }
    }

    @Override
    public int compareTo(Zavodnik o) {
        return this.getTime() - o.getTime();
//        if(this.getTime() < o.getTime()){
//            return -1;
//        } else if(this.getTime() > o.getTime()){
//            return 1;
//        }else {
//            return 0;
//        }
    }

}
