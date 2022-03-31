/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chmelar;

/**
 *
 * @author tomas.chmelar
 */
public final class TimeTools {

    private TimeTools() {
    }

    public static String secondsToTime(int time){
        int hodiny, minuty, sekundy;
        hodiny = time % 3600;
        time = time - (hodiny * 3600);
        minuty = time % 60;
        sekundy = time - (minuty * 60);
        return hodiny + ":" + minuty + ":" + sekundy;
    }
    
    public static int timeCompare(int startTime, int finishTime){
        return finishTime - startTime; 
    }
    
    public static int timeToSeconds(int hodiny, int minuty, int sekundy){
        return hodiny*3600 + minuty*60 + sekundy;
    }
    
    public static int timeToSeconds(String time){
        String[] arr = time.split(":", 3);
        return Integer.parseInt(arr[0])*3600 + Integer.parseInt(arr[1])*60+60;
    }
}
