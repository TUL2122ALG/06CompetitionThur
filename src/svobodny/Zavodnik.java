package svobodny;

import java.time.LocalTime;
import java.time.LocalDate;
import java.time.Duration;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class Zavodnik implements Comparable<Zavodnik> {
    // Instance variables
    private String firstName;
    private String surname;
    private int birthYear;
    private Gender gender;
    private String club;
    private int startNumber;
    private static int startNumberCounter = 1;
    private LocalTime startTime;
    private LocalTime finishTime;
    
    
    // Constructors
    
    public Zavodnik(String firstName, String surname, int birthYear, Gender gender, String club) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthYear = birthYear;
        this.gender = gender;
        this.club = checkClub(club);
        this.startNumber = startNumberCounter;
        startNumberCounter++;
    }
    
    // copy | clone() method
    public Zavodnik(Zavodnik z) {
        this.firstName = z.firstName;
        this.surname = z.surname;
        this.birthYear = z.birthYear;
        this.gender = z.gender;
        this.club = z.club;
        this.startNumber = z.startNumber;
        this.startTime = z.startTime;
        this.finishTime = z.finishTime;
        this.time = z.getTime();
    }
    
    // Factory method
    public static Zavodnik getInstance(String firstName, String surname, int birthYear, Gender gender, String club) {
        return new Zavodnik(firstName, surname, birthYear, gender, club);
    }
    
    
    // Input check methods
    
    private String checkClub(String club) { //Sokol
        if(!club.matches("^[A-Z][a-z0-9 ]+$")) {
            throw new IllegalArgumentException("Nevalidni nazev kloubu. Validni zacina velkym pismenem");
        }
        
        return club;
    }
    
    
    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Gender getGender() {
        return gender;
    }

    public String getClub() {
        return club;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }
    
    public Duration getTime() {
        if (getState() == ZavodnikState.FINISHED) {
            return Duration.between(startTime, finishTime);
        } else {
            throw new IllegalStateException("Zavodnik jeste nedokoncil zavod.");
        }
    }
    
    public long getTimeInSeconds() {
        return getTime().getSeconds();
    }
    
    // Setters
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public void setStartTime(int hours, int minutes, int seconds) {
        setStartTime(LocalTime.of(hours, minutes, seconds));
    }
    
    public void setStartTime(String time) {
        setStartTime(LocalTime.parse(time));
    }

    public void setFinishTime(LocalTime finishTime) {
        if (this.startTime == null) {
            throw new StartTimeNotSet("Nebyl nastaven cas startu, nelze nastavit cas v cili.");
        }
        this.finishTime = finishTime;
        getTime();
    }
    
    public void setFinishTime(int hours, int minutes, int seconds) {
        setFinishTime(LocalTime.of(hours, minutes, seconds));
    }
    
    public void setFinishTime(String time) {
        setFinishTime(LocalTime.parse(time));
    }
    
    // Methods

    public int getAge() {
        return LocalDate.now().getYear() - birthYear;
    }
    
    public ZavodnikState getState() {
        if (startTime == null) {
            return ZavodnikState.NOT_STARTED;
        } else if (finishTime == null) {
            return ZavodnikState.UNFINISHED;
        } else {
            return ZavodnikState.FINISHED;
        }
    }
    
    
    @Override
    public int compareTo(Zavodnik z) {
        return this.getTime().compareTo(z.getTime());
    }
    
    // toString
    @Override
    public String toString() {
        return String.format("%5d %10s %10s %5d %1s %10s %10s %10s", 
                this.startNumber,this.firstName, this.surname, this.getAge(), this.gender.ch, TimeTools.timeToString(startTime), TimeTools.timeToString(finishTime), TimeTools.timeToString(getTime()));
    }
    
    // Testing main
    public static void main(String[] args) {
        try {
        Zavodnik z = new Zavodnik("Alice", "Mala", 1980, Gender.FEMALE, "Sberec");
        System.out.println(z);
        //z.setStartTime(9,0,0);
        System.out.println(z);
            z.setFinishTime("10:02:05");
            System.out.println(z);
        } catch (StartTimeNotSet e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Chyby");
        }
        //z.setFinishTime("10:02:01");
        //System.out.println(z);
    }
}
