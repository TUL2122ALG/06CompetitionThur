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
    MALE("muži", 'M'), FEMALE("ženy", 'Z');
    
    // Instance variables
    public final String name;
    public char ch;
    
    // Constructor
    private Gender(String name, char ch) {
        this.name = name;
        this.ch = ch;
    }
    
    // toString
    @Override
    public String toString() {
        return this.name;
    }
}
