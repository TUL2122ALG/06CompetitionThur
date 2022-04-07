import java.util.Objects;
import java.util.Random;

public class Racer implements Comparable<Racer> {

    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private int raceCategory;
    private Gender gender;
    private String club;
    private int startingNumber;
    private int startingWave;
    private boolean registrationPayed;

    private int raceStartTimeSeconds;
    private int raceEndTimeSeconds;
    private int raceTime;
    private int standings;
    private Random r = new Random();

    // TO-DO: get state of racer ... enum Racingstatus

    Racer(String firstName, String lastName, String dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public static Racer getInstanceOf(String firstName, String lastName, String dateOfBirth, Gender gender) {
        return new Racer(firstName, lastName, dateOfBirth, gender);
    }

    public void setStartingNumber(int startingNumber) {
        this.startingNumber = startingNumber;
    }

    public void setStartingNumber() {
        int low = 1;
        int high = 10000;
        this.startingNumber = r.nextInt(high - low) + low;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDateOfBirth() {
        String[] arr = this.dateOfBirth.split(" ");
        int[] res = new int[3];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return String.format("%02d %02d %04d", res[0], res[1], res[2]);
    }

    public int getRaceCategory() {

        // TODO: categories...
        return 0;
    }

    public void setRaceCategory(int raceCategory) {
        this.raceCategory = raceCategory;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getClub() {
        return this.club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getStartingNumber() {
        return this.startingNumber;
    }

    public int getStartingWave() {
        // TODO STARTING WAVE LOGIC
        return this.startingWave;
    }

    public void setStartingWave(int startingWave) {
        this.startingWave = startingWave;
    }

    public boolean isRegistrationPayed() {
        return this.registrationPayed;
    }

    public boolean getRegistrationPayed() {
        return this.registrationPayed;
    }

    public void setRegistrationPayed(boolean registrationPayed) {
        this.registrationPayed = registrationPayed;
    }

    public int getRaceStartTimeSeconds() {
        return this.raceStartTimeSeconds;
    }

    public int getRaceEndTimeSeconds() {
        return this.raceEndTimeSeconds;
    }

    public void setRaceStartTimeSeconds(int raceStartTimeSeconds) {
        this.raceStartTimeSeconds = raceStartTimeSeconds;
    }

    public void setRaceEndTimeSeconds(int raceEndTimeSeconds) {
        this.raceEndTimeSeconds = raceEndTimeSeconds;
    }

    public int getTimeResultSeconds(int startTimeSeconds, int endTimeSeconds) {
        return TimeTools.timeCompare(startTimeSeconds, endTimeSeconds);
    }

    // TODO: FIND how clone works
    public Racer(Racer r) {
        this.firstName = r.firstName;
        this.lastName = r.lastName;
        this.dateOfBirth = r.dateOfBirth;
        this.raceCategory = r.raceCategory;
        this.gender = r.gender;
        this.club = r.club;
        this.startingNumber = r.startingNumber;
        this.startingWave = r.startingWave;
        this.registrationPayed = r.registrationPayed;
        this.raceStartTimeSeconds = r.raceStartTimeSeconds;
        this.raceEndTimeSeconds = r.raceEndTimeSeconds;
        this.raceTime = r.raceTime;
        this.standings = r.standings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Racer)) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(firstName, racer.firstName) && Objects.equals(lastName, racer.lastName)
                && dateOfBirth == racer.dateOfBirth && raceCategory == racer.raceCategory
                && Objects.equals(gender, racer.gender) && Objects.equals(club, racer.club)
                && startingNumber == racer.startingNumber && startingWave == racer.startingWave
                && registrationPayed == racer.registrationPayed
                && Objects.equals(raceStartTimeSeconds, racer.raceStartTimeSeconds)
                && Objects.equals(raceEndTimeSeconds, racer.raceEndTimeSeconds)
                && Objects.equals(raceTime, racer.raceTime)
                && standings == racer.standings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, raceCategory, gender, club, startingNumber, startingWave,
                registrationPayed, raceStartTimeSeconds, raceEndTimeSeconds, raceTime, standings);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.startingNumber < 0) {
            setStartingNumber();
        }
        return this.firstName + " " + this.lastName + String.format(" [%s]", getGender()) + ", narozen "
                + getDateOfBirth()
                + ", se startovacím číslem "
                + String.format("%05d", getStartingNumber()) +
                ", START: " + TimeTools.secondsToTimeString(getRaceStartTimeSeconds()) + ", KONEC: "
                + TimeTools.secondsToTimeString(getRaceEndTimeSeconds()) + ",TIME: "
                + TimeTools
                        .secondsToTimeString(getTimeResultSeconds(getRaceStartTimeSeconds(), getRaceEndTimeSeconds()))
                +
                "\n";
    }

    public static void main(String[] args) {
        // Racer r = new Racer("Jakub", "Štěpánek", "23 7 2002");
        // r.setStartingNumber(123);
        // System.out.println(r);
    }

    @Override
    public int compareTo(Racer o) {
        // TODO:

        return this.getTimeResultSeconds(getRaceStartTimeSeconds(), getRaceEndTimeSeconds())
                - o.getTimeResultSeconds(getRaceStartTimeSeconds(), getRaceEndTimeSeconds());
        // if(this.getRaceTime() < o.getRaceTime()){
        // return -1;
        // }else if(this.getRaceTime()> o.getRaceTime()){
        // return 1;
        // }else {
        // return 0;
        // }

    }

}