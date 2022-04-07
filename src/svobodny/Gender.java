/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package svobodny;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public enum Gender {
    MALE("muži"), FEMALE("ženy");
    
    // Instance variables
    private String str;
    
    // Constructor
    private Gender(String name) {
        this.str = name;
    }
    
    // toString
    public String toString() {
        return this.str;
    }
}
