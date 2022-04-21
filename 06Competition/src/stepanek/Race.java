import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class Race {
    private String name;
    private ArrayList<Racer> racers = new ArrayList<>();
    private int registrationNumber;
    private int raceStartTime;

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

    public Racer getlastRacer() {
        // TODO: safety copy racer
        return this.racers.get(racers.size());
    }

    public void setRacer(Racer racer) {
        this.racers.add(racer);
    }

    public int getTimeStartSeconds(int racerID) {
        return racers.get(racerID).getRaceStartTimeSeconds();
    }

    public void setRacerTimeStartSeconds() throws IOException, StartTimeNotSet {
        try {
            if (this.raceStartTime == 0) {
                throw new StartTimeNotSet("Nemůžeš končit, dokud závod nezačal");
            }
            racers.get(0).setRaceStartTimeSeconds(this.raceStartTime);
            for (int i = 1; i < this.racers.size(); i++) {
                racers.get(i).setRaceStartTimeSeconds(this.raceStartTime + i * 5);
            }
        } catch (StartTimeNotSet e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Chybné zadání!");
        }

    }

    public void setRacerStartNumber(int racerStartNumber) {
        this.registrationNumber = racerStartNumber;
    }

    public int getRacerStartNumber() {
        return this.registrationNumber;

    }

    public int getTimeEndSeconds(int racerID) {
        return racers.get(racerID).getRaceEndTimeSeconds();
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

    public void setRaceStartTime(String raceStartTime) {
        this.raceStartTime = TimeTools.timeToSeconds(raceStartTime);
    }

    public String getRaceStartTime() {
        return TimeTools.secondsToTimeString(this.raceStartTime);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Race)) {
            return false;
        }
        Race race = (Race) o;
        return Objects.equals(racers, race.racers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racers);
    }

    @Override
    public String toString() {
        return "Závod: " + getName() + "\n"
                + "     Jméno     Příjmení     Datum_narození     START   KONEC     ČAS_CELKEM"
                + getRacers();
    }

}
