/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sikola;

/**
 *
 * @author kubas
 */
public class Competitor {
    private String name;
    private char gender;
    private int yearOfBirth;
    private int timeStart;
    private int timeEnd;
    private int timeOfRace;
    private int regNum;
    

    private Competitor(String name, char gender, int yearOfBirth) {
        this.name = name;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
    
    public static Competitor getInstanceOf(String name, char gender, int yearOfBirth){
        return new Competitor(name,gender, yearOfBirth);
    }

    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }
    
    public int getTimeOfRace(){
        return timeTools.getCompetitorTime(this.timeStart, this.timeEnd);  
    }
    
    public String getTimeOfRaceStr(){
        return timeTools.timeToStr(this.timeOfRace);
    }
    
    public void seTimeStart(int timeStart){
        this.timeStart = timeStart;
    }
    
    public void setTimeEnd(int timeEnd){
        this.timeEnd = timeEnd;
    }
    
    public String getName(){
        return this.name;
    }
    
    
    
    
    
            
}
