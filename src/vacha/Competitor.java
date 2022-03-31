/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;

import java.util.Scanner;

enum RacerState {
    PREPARED, RUNNING, FINISHED, DISQUALIFIED;
}

enum Gender {
    MALE("M"), FEMALE("F"), OTHER("O"), HELICOPTER("O");
    private String value;

    private Gender(String value) {
        this.value = value;
    }

    public static Gender of(String value) {
        for (Gender g : Gender.values()) {
            if (g.value.equals(value)) {
                return g;
            }
        }
        return Gender.OTHER;
    }
}

/**
 *
 * @author pavel.vacha
 */
public class Competitor {

    private String name;
    private String surname;
    private Gender gender;
    private int year;
    private static int registrationNumberCounter = 1;
    private int registrationNumber;
    private int wave;
    private long startTime;
    private long finishTime;
    private String team;
    private RacerState currentState;
    private boolean isFeePaid;

    private Competitor(String name, String surname, int year, String gender) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.gender = Gender.of(gender);
        this.isFeePaid = false;
        this.registrationNumber = registrationNumberCounter;
        registrationNumber++;
    }

    public static Competitor getInstance(String name, String surname, int year, String gender) {
        return new Competitor(name, surname, year, gender);
    }

    public static Competitor registration(Scanner sc) {
        System.out.println("Vítejte v registraci");
        System.out.println("====================");

        System.out.print("Zadejte vaše jméno a příjmení: ");
        String name = sc.nextLine();
        String surname = sc.nextLine();
        System.out.println("Vaše pohlaví (M,F,O): ");
        String gender = sc.nextLine();
        System.out.println("Váš ročník: ");
        int year = sc.nextInt();

        return getInstance(name, surname, year, gender);
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setStartTime(int hour, int minutes, int seconds) {
        this.startTime = TimeTools.intsToLong(hour, minutes, seconds);
    }

    public void setStartTime(int seconds) {
        this.startTime = seconds;
    }

    public void setStartTime(String time) {
        this.startTime = TimeTools.stringToLong(time);
    }

    public void setFinishTime(int hour, int minutes, int seconds) {
        this.finishTime = TimeTools.intsToLong(hour, minutes, seconds);
    }

    public void setFinishTime(int seconds) {
        this.finishTime = seconds;
    }

    public void setFinishTime(String time) {
        this.finishTime = TimeTools.stringToLong(time);
    }

    public long getRunningTime() {
        return this.finishTime - this.startTime;
    }

    public String getFullname() {
        return String.format("%s %s", this.name, this.surname);
    }

}
