/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vacha;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 *
 * @author pavel.vacha
 */
public class Competition {

    private String name;
    ArrayList<Competitor> competitors = new ArrayList<>();

    public Competition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    

    public void registerCompetititor(Scanner sc) {
        this.competitors.add(Competitor.registration(sc));
    }

    public void removeCompetititor(int registrationNumber) {
        this.competitors.removeIf(c -> c.getRegistrationNumber() == registrationNumber);
    }

    public String registeredCommpetitors() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID  JMÉNO");
        sb.append("=========");

        this.competitors.forEach(c -> {
            sb.append(String.format(c.getRegistrationNumber() + c.getFullname()));
        }
        );

        return sb.toString();
    }

}
