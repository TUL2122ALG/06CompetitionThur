/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package svobodny;

import java.time.LocalTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class TimeTools {
    private TimeTools() {}
    
    public static String timeToString(LocalTime time) {
        if (time == null) {
            return "N/A";
        } else {
            return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
    
    public static String timeToString(Duration time) {
        if (time == null) {
            return "N/A";
        } else {
            return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
    
    public static LocalTime stringToTime(String str) {
        return LocalTime.parse(str);
    }
}
