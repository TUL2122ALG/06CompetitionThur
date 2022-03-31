import java.util.Objects;

public class TimeTools {
    private int hours;
    private int minutes;
    private int seconds;

    public TimeTools(String time) {
        String[] arrOfTime = time.split(" ");
        int[] arrInt = new int[3];
        for (int i : arrInt) {
            arrInt[i] = Integer.parseInt(arrOfTime[i]);
        }
        this.hours = arrInt[0];
        this.minutes = arrInt[1];
        this.seconds = arrInt[2];

    }

    public TimeTools(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public TimeTools hours(int hours) {
        setHours(hours);
        return this;
    }

    public TimeTools minutes(int minutes) {
        setMinutes(minutes);
        return this;
    }

    public TimeTools seconds(int seconds) {
        setSeconds(seconds);
        return this;
    }

    public String secondsToTimeString(int seconds) {
        // TO-DO: StringBuilder sb = new StringBuilder();
        int h, m, s, sLeft;
        h = seconds / 3600;
        sLeft = seconds - h * 3600;
        m = sLeft / 60;
        s = sLeft - m * 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public int[] secondsToTimeArray(int seconds) {
        int[] ret = new int[3];
        int sLeft;
        ret[0] = seconds / 3600;
        sLeft = seconds - ret[0] * 3600;
        ret[1] = sLeft / 60;
        ret[2] = sLeft - ret[1] * 60;
        return ret;
    }

    public static int timeCompare(int startTime, int endTime) {
        return endTime - startTime;
    }

    public static int timeToSeconds(int hours, int minutes, int seconds) {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static int timeToSeconds(String seconds) {
        String arr[] = seconds.split(":", 3);
        return Integer.parseInt(arr[0] + ":" + arr[1] + ":" + arr[2]);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TimeTools)) {
            return false;
        }
        TimeTools timeTools = (TimeTools) o;
        return hours == timeTools.hours && minutes == timeTools.minutes && seconds == timeTools.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return "{" +
                " hours='" + getHours() + "'" +
                ", minutes='" + getMinutes() + "'" +
                ", seconds='" + getSeconds() + "'" +
                "}";
    }

}
