/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

/**
 *
 * @author tomas.chmelar
 */
public class Date {
    int hour;
    int min;
    int seconds;

    public Date(int hour, int min, int seconds) {
        this.hour = hour;
        this.min = min;
        this.seconds = seconds;
    }
    
    public Date(String time){
        String[] arr = time.split(":",3);
        this.hour = Integer.parseInt(arr[0]);
        this.min = Integer.parseInt(arr[1]);
        this.seconds = Integer.parseInt(arr[2]);
    }
    
    public Date(int time){
                int hodiny, minuty, sekundy;
        this.hour = time / 3600;
        time = time % 3600;
        this.min = time / 60;
        this.seconds = time % 60;
    }
    
    public int getTime(){
        return this.hour*3600+this.min*60*this.seconds;
    }
    
    public String getTimeString(){
        return String.format("%02d:%02d:%02d", this.hour, this.min,this.seconds);
    }
    
    public int timeCompare(Date time){
        
        return time.getTime() - this.getTime();
    }
}
