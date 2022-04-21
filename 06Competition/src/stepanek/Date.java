import java.util.Objects;

public class Date {
    private int hours;
    private int minutes;
    private int seconds;

    public Date(String time) {
        String[] arrOfTime = time.split(" ");
        int[] arrInt = new int[3];
        for (int i : arrInt) {
            arrInt[i] = Integer.parseInt(arrOfTime[i]);
        }
        this.hours = arrInt[0];
        this.minutes = arrInt[1];
        this.seconds = arrInt[2];

    }

    public Date(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
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

    public int timeCompare(Date endTimeSeconds) {
        return endTimeSeconds.timeToSeconds() - this.timeToSeconds();
    }

    public int timeToSeconds() {
        return this.hours * 3600 + this.minutes * 60 + this.seconds;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Date)) {
            return false;
        }
        Date date = (Date) o;
        return hours == date.hours && minutes == date.minutes && seconds == date.seconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

}
