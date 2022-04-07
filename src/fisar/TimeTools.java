package fisar;

/**
 *
 * @author lukas.fisar
 */
public class TimeTools {
    
    private TimeTools() {
        
    }
    
    public static String SecondsToTime(int time) {
        int hours = time / 3600;
        int mins = time / 60;
        int seconds = time % 60;
        return hours + ":" + mins + ":" + seconds;
    }
    
    public static int TimeToSeconds(int hours, int mins, int seconds) {
        return hours*3600 + mins*60 + seconds;
    }
    
    public static int GetTime(int start, int cil) {
        return cil - start;
    }
}
