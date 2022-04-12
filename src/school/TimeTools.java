/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package school;

/**
 *
 * @author 
 */
public final class TimeTools {

    private TimeTools() {
    }

    public static String secondsToTime(int time){
        int hodiny, minuty, sekundy;
        hodiny = time / 3600;
        time = time % 3600;
        minuty = time / 60;
        sekundy = time % 60;
        return String.format("%02d:%02d:%02d", hodiny, minuty,sekundy);
    }
    
    public static int timeCompare(int startTime, int finishTime){
        return finishTime - startTime; 
    }
    
    public static int timeToSeconds(int hodiny, int minuty, int sekundy){
        return hodiny*3600 + minuty*60 + sekundy;
    }
    
    public static int timeToSeconds(String time){
        String[] arr = time.split(":", 3);
        return (Integer.parseInt(arr[0])*3600) + (Integer.parseInt(arr[1])*60) + (Integer.parseInt(arr[2]));
    }
}
