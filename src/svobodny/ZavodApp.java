/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package svobodny;

import java.util.Scanner;

/**
 *
 * @author hynek
 */
public class ZavodApp {
    
    static Scanner sc = new Scanner(System.in);
    static Zavod competition;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        displayCredits();
        
        char choice;
        do {
            displayMenu();
            
            createCompetition();
            
            choice = sc.next().charAt(0);
            sc.nextLine(); // buffer cleanup
            switch (choice) {
                case '1':
                    registerCompetitors();
                    break;
                case '2':
                    setStartTime();
                    break;
                case '3':
                    setStartTimeOffset();
                    break;
                case '4':
                    setFinishTime();
                    break;
                case '5':
                    displayCompetitors();
                    break;
                case '6':
                    displayResultList();
                    break;
            }
            
        } while (choice != '0');
    }

    private static void displayCredits() {
        System.out.println("ZavodApp");
        System.out.println("");
    }
    
    private static void createCompetition() {
        System.out.print("Zadej jmeno zavodu: ");
        String name = sc.next();
        sc.nextLine(); // buffer cleanup
        competition = new Zavod(name);
    }

    private static void displayMenu() {
        System.out.println("Vyber:");
        System.out.println("1. Zapsani zavodniku");
        System.out.println("2. Nastavit cas startu (hromadne)");
        System.out.println("3. Nastavit cas startu (s odstupy)");
        System.out.println("4. Nastavit cas cile");
        System.out.println("5. Vypsat zavodniky");
        System.out.println("6. Vysledkova listina");
        System.out.println("0. Konec");
    }

    private static void registerCompetitors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void setStartTime() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void setStartTimeOffset() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void setFinishTime() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void displayCompetitors() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void displayResultList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
