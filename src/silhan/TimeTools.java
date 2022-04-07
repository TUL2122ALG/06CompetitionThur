
package silhan;

public class TimeTools {
    
    public static long timeToSeconds(int hour, int min, int sec){
        return hour*3600 + min*60 + sec; 
    }
    
    public static long timeToSeconds(String time){
        String[] parts = time.split("[:]");
        return Integer.parseInt(parts[0])*3600 + Integer.parseInt(parts[1])*60 + Integer.parseInt(parts[2]);
    }
    
    public static long timeCompare(long startTime, long finishTime){
        return finishTime - startTime;
    }
    public static String secondsToTime(int time){
        int hours = time/3600;
        time = time%3600;
        int mins = time/60;
        int secs = time%60;
        return hours + ":" + mins + ":" + secs;
    }
    
}
