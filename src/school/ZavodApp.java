/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package school;

/**
 *
 * @author 
 */
public class ZavodApp {
    public static void main(String[] args) {
        
    }
    
    public void addCompetitors(int number){
        String name; String surname; int year; char gender; String club;
        int prevComp = nCompetitors;
        for(int i =prevComp;i<number;i++){
            if (i==competitors.length){
                extendArray();
            }
            System.out.println("Jmeno:");
            name = sc.next();
            System.out.println("Prijmeni");
            surname = sc.next();
            System.out.println("Rocnik");
            year = sc.nextInt();
            System.out.println("Pohlavi");
            gender = sc.next().charAt(0);
            System.out.println("Klub");
            club = sc.next();
            this.competitors[i]= Zavodnik.getInstance(name, surname, year, gender, club);
            nCompetitors++;
        }
    }
}
