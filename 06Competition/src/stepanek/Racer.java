import java.util.Objects;
import java.util.Random;

public class Racer {

    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private int raceCategory;
    private Gender gender;
    private String club;
    private int startingNumber;
    private int startingWave;
    private boolean registrationPayed;
    private String raceStartTime;
    private String raceEndTime;
    private String raceTime;
    private int standings;
    private Random r = new Random();

    public Racer(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Racer getInstanceOf(String firstName, String lastName, String dateOfBirth) {
        return new Racer(firstName, lastName, dateOfBirth);
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
        return this.raceCategory;
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

    public String getRaceStartTime() {
        return this.raceStartTime;
    }

    public void setRaceStartTime(String raceStartTime) {
        this.raceStartTime = raceStartTime;
    }

    public String getRaceEndTime() {
        return this.raceEndTime;
    }

    public void setRaceEndTime(String raceEndTime) {
        this.raceEndTime = raceEndTime;
    }

    public String getRaceTime() {
        return this.raceTime;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public int getStandings() {
        return this.standings;
    }

    public void setStandings(int standings) {
        this.standings = standings;
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
                && registrationPayed == racer.registrationPayed && Objects.equals(raceStartTime, racer.raceStartTime)
                && Objects.equals(raceEndTime, racer.raceEndTime) && Objects.equals(raceTime, racer.raceTime)
                && standings == racer.standings;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, raceCategory, gender, club, startingNumber, startingWave,
                registrationPayed, raceStartTime, raceEndTime, raceTime, standings);
    }

    @Override
    public String toString() {
        if (this.startingNumber < 0) {
            setStartingNumber();
        }
        return "Závodník, " + this.firstName + " " + this.lastName + String.format(" [%s]", getGender()) + ", narozen "
                + getDateOfBirth()
                + ", se startovacím číslem "
                + String.format("%05d", this.startingNumber) + "\n";
    }

    public static void main(String[] args) {
        // Racer r = new Racer("Jakub", "Štěpánek", "23 7 2002");
        // r.setStartingNumber(123);
        // System.out.println(r);
    }

}