/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

/**
 *
 * @author tomas.chmelar
 */
public class Zavodnik {
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
        return time;
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

    public void setTime(int hodiny, int minuty, int sekundy){
        this.startTime = TimeTools.timeToSeconds(hodiny, minuty, sekundy);        
    }
    
    public void setTime(String time){
        this.startTime = TimeTools.timeToSeconds(time);
    }
}