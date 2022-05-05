/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

import java.util.Calendar;

/**
 *
 * @author tomas.chmelar
 */
public class Zavodnik implements Comparable<Zavodnik> {
private String jmeno;
private String prijmeni;
private String klub;
private int rocnik;
private int registracniCislo;
private int startTime;
private int finishTime;
private int time;
private char pohlavi;
private static int pocitadlo = 1;

    public Zavodnik(String jmeno, String prijmeni, int rocnik, char pohlavi, String klub) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.pohlavi = pohlavi;
        this.registracniCislo = pocitadlo;
        this.klub = klub;
        Zavodnik.pocitadlo ++;
    }
    //kopie
    public Zavodnik(Zavodnik z){
        this.jmeno = z.jmeno;
        this.prijmeni = z.prijmeni;
        this.rocnik = z.rocnik;
        this.pohlavi = z.pohlavi;
        this.registracniCislo = z.registracniCislo;
        this.startTime = z.startTime;
        this.finishTime = z.finishTime;
        this.klub = z.klub;
        this.time = z.getTime();
    }
    public static Zavodnik getInstance(String jmeno, String prijmeni, int rocnik, char pohlavi, String klub){
        Zavodnik zavodnik = new Zavodnik(jmeno, prijmeni,rocnik,pohlavi, klub);
        return zavodnik;
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
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public int getTime() {
        if(getStartTime() > 0  && getFinishTime() > 0){
            return time = TimeTools.timeCompare(startTime, finishTime);
        }
        return 0;
    }

    public char getPohlavi() {
        return pohlavi;
    }

    public static int getPocitadlo() {
        return pocitadlo;
    }

    public void setStartTime(int startTime) {
        //this.startTime = startTime;
        this.startTime = startTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public void setStartTime(int hodiny, int minuty, int sekundy){
        this.startTime = TimeTools.timeToSeconds(hodiny, minuty, sekundy);        
    }
    
    public void setFinishTime(int hodiny, int minuty, int sekundy){
        this.finishTime = TimeTools.timeToSeconds(hodiny, minuty, sekundy);        
    }
        
    public void setStartTime(String time){
        this.startTime = TimeTools.timeToSeconds(time);
    }
    
    public void setTime(){
        this.time = TimeTools.timeCompare(this.startTime, this.finishTime);
    }
    
    public void setFinishTime(String time){
        if (this.startTime == 0) {
            throw new StartTimeNotSet("Nebyl nastaven čas startu, nelze nastavit čas cíle.");
        }
        this.finishTime = TimeTools.timeToSeconds(time);
        getTime();
    }
    public int getVek(int rocnik){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - rocnik;
    }
    
    private String checkClub(String club){
        if (club.matches("^[A-Z][a-z]+$")) {
            throw new IllegalArgumentException("nevalidni nazev klubu");
        }
        return club;
    }
    
    public String toString(){
        setTime();
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s", this.registracniCislo,this.jmeno,this.prijmeni,getVek(this.getRocnik()), this.pohlavi, 
                TimeTools.secondsToTime(this.startTime), TimeTools.secondsToTime(this.finishTime),TimeTools.secondsToTime(this.time), 
                TimeTools.secondsToTime(this.getTime()));
    }

    @Override
    public int compareTo(Zavodnik o) {
        return this.getTime() - o.getTime();
    }
    
        public static void main(String[] args) {
        Zavodnik z = new Zavodnik("Někdo", "Neznamy", 1970, 'F', "SK nic");
        System.out.println(z);
        z.setStartTime(9,0,0);
        System.out.println(z);
        try{
        z.setFinishTime("10:02:05");
        System.out.println(z);
        }
        catch(StartTimeNotSet e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println("Chyby");
        }
    }
    
}