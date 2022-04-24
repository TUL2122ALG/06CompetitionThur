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
    MALE("muzi", 'M'), FEMALE("zeny", 'Z');
    
    // Instance variables
    public final String name;
    public char ch;
    
    // Constructor
    private Gender(String name, char ch) {
        this.name = name;
        this.ch = ch;
    }
    
    public static Gender of(String name) {
        switch (name) {
            case "muzi":
                return MALE;
            case "zeny":
                return FEMALE;
            default:
                return null;
        }
    }
    
    public static Gender of(char ch) {
        switch (ch) {
            case 'M':
                return MALE;
            case 'Z':
                return FEMALE;
            default:
                return null;
        }
    }
    
    // toString
    @Override
    public String toString() {
        return this.name;
    }
}
