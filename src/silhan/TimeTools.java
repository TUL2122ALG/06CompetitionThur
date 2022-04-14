
package silhan;

public class TimeTools {
    
    public static int timeToSeconds(int hour, int min, int sec){
        return hour*3600 + min*60 + sec; 
    }
    
    public static int timeToSeconds(String time){
        String[] parts = time.split("[:]");
        return Integer.parseInt(parts[0])*3600 + Integer.parseInt(parts[1])*60 + Integer.parseInt(parts[2]);
    }
    
    public static int timeCompare(int startTime, int finishTime){
        return finishTime - startTime;
    }
    public static String secondsToTime(int time){
        int hours = time/3600;
        time = time%3600;
        int mins = time/60;
        int secs = time%60;
        return String.format("%02d:%02d:%02d", hours, mins, secs);
    }
    
}
