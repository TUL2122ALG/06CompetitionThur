/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @author tomas.chmelar
 */
public class Zavod {
    private String jmeno; //kategorie
    //private Zavodnik[] zavodnici = new Zavodnik[100];
    private ArrayList<Zavodnik> zavodici;
    //private int zavodnik_pocet = 0;
    
    public Zavod(String jmeno){
        this.jmeno = jmeno;
        zavodici = new ArrayList<>();
    }

    public String getJmeno() {
        return jmeno;
    }
    //kopie pro uživatele
    public ArrayList<Zavodnik> getZavodici() {
        ArrayList<Zavodnik> zavod_copy = new ArrayList<Zavodnik>();
        for (Zavodnik zavodnik : zavodici) {
            zavod_copy.add(new Zavodnik(zavodnik));
        }
        return zavod_copy;
    }

    public int getZavodnik_pocet() {
        //return zavodnik_pocet;
        return zavodici.size();
    }
    
    
    
    public void addZavodnik(Zavodnik zavodnik){
        //zavodnici[zavodnik_pocet] = zavodnik;
        zavodici.add(zavodnik);
        //zavodnik_pocet++;
    }
    
    public void addZavodnik(String jmeno, String prijmeni, int rocnik, char pohlavi){
        zavodici.add(Zavodnik.getInstance(jmeno,prijmeni,rocnik,pohlavi));
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Zavodnik zavodnik : zavodici) {
            sb.append(zavodnik).append("\n");
        }
        return sb.toString();
    }
    
    public void setstartTimeAll(int h, int m, int s, int offsetInMinutes){
        //int time = h*3600 + m*60+s;
        for (int i = 0; i < zavodici.size(); i++) {
            zavodici.get(i).setStartTime(h,m + i*offsetInMinutes,s);
        }
    }
    
    public void setFinishTime(int registrationNumber, int h, int m, int s){
        findByRegNumber(registrationNumber).setFinishTime(h, m, s);
    }
    
    private Zavodnik findByRegNumber(int regNum){
        for (Zavodnik zavodnik : zavodici) {
            if (zavodnik.getRegistracniCislo() == regNum) {
                return zavodnik;
            }
        }
        throw new NoSuchElementException("...nemáme přijďte zas!");
    }
    
    public Zavodnik nejrychlejsi(){
        Zavodnik nejrychlejsi = zavodici.get(0);
        for (int i = 1; i < zavodici.size(); i++) {  
            if (nejrychlejsi.getTime() > zavodici.get(i).getTime()) {
                nejrychlejsi = zavodici.get(i);
            }
        }
        //return nejrychlejsi;
        return new Zavodnik(nejrychlejsi);
    }
    
    private void sortByTime(){
        Collections.sort(zavodici);
    }
    
    private void sortByPrijmeni(){
        Comparator cbp = new ComparatorZavodnikByPrijmeni();
        Collections.sort(zavodici, cbp);
    }
    
    public void addCompetitor(String name, String surname, int year, char gender, String club){
        zavodici.add(Zavodnik.getInstance(name, surname, year, gender));
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
        jiz50.setFinishTime(1, 10, 0, 0);
        jiz50.setFinishTime(2, 10, 10, 0);
        jiz50.setFinishTime(3, 10, 1, 0);
        System.out.println(jiz50);
        System.out.println("Nejrychlejsi: " + jiz50.nejrychlejsi());
        jiz50.sortByTime();
        System.out.println(jiz50);
        jiz50.sortByPrijmeni();
        System.out.println(jiz50);
    }
    
    
}
