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
private int rocnik;
private int registracniCislo;
private int startTime;
private int finishTime;
private int time;
private char pohlavi;
private static int pocitadlo = 1;

    public Zavodnik(String jmeno, String prijmeni, int rocnik, char pohlavi) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rocnik = rocnik;
        this.pohlavi = pohlavi;
        this.registracniCislo = pocitadlo;
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
        this.time = z.getTime();
    }
    public static Zavodnik getInstance(String jmeno, String prijmeni, int rocnik, char pohlavi){
        Zavodnik zavodnik = new Zavodnik(jmeno, prijmeni,rocnik,pohlavi);
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
    
    public void setFinishTime(String time){
        this.finishTime = TimeTools.timeToSeconds(time);
    }
    public int getVek(int rocnik){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - rocnik;
    }
    
    public String toString(){
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s", this.registracniCislo,this.jmeno,this.prijmeni,getVek(this.getRocnik()), this.pohlavi, 
                TimeTools.secondsToTime(this.startTime), TimeTools.secondsToTime(this.finishTime),TimeTools.secondsToTime(this.startTime), 
                TimeTools.secondsToTime(this.getTime()));
    }

    @Override
    public int compareTo(Zavodnik o) {
        return this.getTime() - o.getTime();
    }
    
        public static void main(String[] args) {
        Zavodnik z = new Zavodnik("Někdo", "Neznamy", 1970, 'F');
        System.out.println(z);
        z.setStartTime(9,0,0);
        System.out.println(z);
        z.setFinishTime("10:02:05");
        System.out.println(z);
    }
    
}