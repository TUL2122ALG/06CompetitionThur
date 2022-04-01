/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sikola;

/**
 *
 * @author kubas
 */
public class timeTools {
    public static int getCompetitorTime(int start, int end){
        return start - end;
    }
    
    public static String timeToStr(int secs){
        int hours = secs/3600;
        int minutes = (secs % 3600) / 60;
        int seconds = (secs % 3600) % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        
    }
}
