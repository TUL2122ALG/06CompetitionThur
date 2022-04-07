import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Race {
    private String name;
    private ArrayList<Racer> racers = new ArrayList<>();
    private int timeStartSeconds;
    private int timeEndSeconds;
    private int timeResultSeconds;
    private int registrationNumber;

    public Race(String name) {
        this.name = name;
    }

    public ArrayList<Racer> getRacers() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racers) {
            copy.add(new Racer(racer));
        }
        return copy;
    }

    public void setRacers(Racer racer) {
        this.racers.add(racer);
    }

    public int getTimeStartSeconds() {
        return this.timeStartSeconds;
    }

    public void setTimeStartSeconds(int timeStartSeconds) {
        this.timeStartSeconds = timeStartSeconds;
    }

    public void setRacerStartNumber(int racerStartNumber) {
        this.registrationNumber = racerStartNumber;

    }

    public int getRacerStartNumber() {
        return this.registrationNumber;

    }

    public int getTimeEndSeconds() {
        return this.timeEndSeconds;
    }

    public void setTimeEndSeconds(int timeEndSeconds) {
        this.timeEndSeconds = timeEndSeconds;
    }

    public int getTimeResultSeconds() {
        return TimeTools.timeCompare(this.timeStartSeconds, this.timeEndSeconds);
    }

    public String getName() {
        return this.name;
    }

    public void sortByTime() {
        Collections.sort(racers);
    }

    public void sortByLastName() {
        Comparator cbp = new ComparatorRacerByLastName();
        Collections.sort(racers, cbp);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Race)) {
            return false;
        }
        Race race = (Race) o;
        return Objects.equals(racers, race.racers) && timeStartSeconds == race.timeStartSeconds
                && timeEndSeconds == race.timeEndSeconds && timeResultSeconds == race.timeResultSeconds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(racers, timeStartSeconds, timeEndSeconds, timeResultSeconds);
    }

    @Override
    public String toString() {
        return "Závod: " + getName() + "\n" + getRacers();
    }

}
