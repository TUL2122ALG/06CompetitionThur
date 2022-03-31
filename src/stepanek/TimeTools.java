package stepanek;

public class TimeTools {
    private int hours;
    private int minutes;
    private int seconds;

    public TimeTools(String time) {
        Strint[] arrOfTime = time.split(" ");
        this.hours = String.format("%02d", arrOfTime[0]);
        this.minutes = String.format("%02d", arrOfTime[1]);
        this.seconds = String.format("%02d", arrOfTime[2]);

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

    public String getTimeInString(int seconds) {
        // TO-DO: StringBuilder sb = new StringBuilder();
        int h, m, s, sLeft;
        h = seconds / 3600;
        sLeft = seconds - h * 3600;
        m = sLeft / 60;
        s = sLeft - m * 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public int[] getTimeInArray(int seconds) {
        int[] ret = new int[3];
        int sLeft;
        ret[0] = seconds / 3600;
        sLeft = seconds - ret[0] * 3600;
        ret[1] = sLeft / 60;
        ret[2] = sLeft - ret[1] * 60;
        return ret;
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
