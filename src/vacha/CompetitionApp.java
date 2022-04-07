/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vacha;

/**
 *
 * @author pavel.vacha
 */
public class CompetitionApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Competition competition = new Competition("Jizerská 50");
        System.out.println("Vítej na závodě " + competition.getName());

    }

}
