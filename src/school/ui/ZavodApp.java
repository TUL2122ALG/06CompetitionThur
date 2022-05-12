/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package school.ui;

import school.app.Zavod;
import java.util.Scanner;

/**
 *
 * @author 
 */
public class ZavodApp {
    static Scanner sc = new Scanner(System.in);
    static Zavod zavod;
    //static List<Zavodnik> bezci;
    
    public static void main(String[] args) {
        zavod = new Zavod("jiz50");
        addCompetitor();
        setStartTime();
    }
    
    public static void addCompetitor(){
            System.out.println("Jmeno:");
            String name = sc.next();
            System.out.println("Prijmeni");
            String surname = sc.next();
            System.out.println("Rocnik");
            int year = sc.nextInt();
            System.out.println("Pohlavi");
            char gender = sc.next().charAt(0);
            System.out.println("Klub");
            String club = sc.next();
            zavod.addCompetitor(name, surname, year, gender, club);
    }
    
    public static void setStartTime(){
        System.out.println("Hours, minutes, seconds");
        zavod.setstartTimeAll(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }
}
