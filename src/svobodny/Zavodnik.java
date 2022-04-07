package svobodny;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

/**
 *
 * @author hynek.vaclav.svobodny
 */
public class Zavodnik implements Comparable {
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
    private Duration time;
    
    
    // Constructors
    
    public Zavodnik(String firstName, String surname, int birthYear, Gender gender, String club) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthYear = birthYear;
        this.gender = gender;
        this.club = club;
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
        if (getState() != ZavodnikState.FINISHED) {
            throw new IllegalStateException("Zavodnik nedokoncil zavod");
        }
        
        return time = Duration.between(startTime, finishTime);
    }
    
    public long getTimeInSeconds() {
        return getTime().getSeconds();
    }
    
    
    // Methods

    // TODO
    public int getAge() {
        
    }
    
    public ZavodnikState getState() {
        if (startTime >= )
    }
    
    
    // Setters
    
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    
    public void setStartTime(int hours, int minutes, int seconds) {
        setStartTime(LocalTime.of(hours, minutes, seconds));
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
        getTime();
    }
    
    public void setFinishTime(int hours, int minutes, int seconds) {
        setFinishTime(LocalTime.of(hours, minutes, seconds));
    }
    
    // toString
    public String toString() {
        return String.format("%5d %10s %10s %5d %1s %10s %10s", 
                this.startNumber,this.firstName, this.surname, this.getAge(), this.gender, startTime.format(DateTimeFormatter.ISO_LOCAL_TIME), finishTime.format(DateTimeFormatter.ISO_LOCAL_TIME), 
    }
    
    // Testing main
    public static void main(String[] args) {
        Zavodnik z = new Zavodnik("Alice", "Mala", 1980, Gender.FEMALE, "SK Liberec");
        System.out.println(z);
        z.setStartTime(9,0,0);
        System.out.println(z);
        z.setFinishTime("10:02:01");
    }

    @Override
    public int compareTo(Zavodnik z) {
        if (this.getTime() < z.getTime()) {
            
        }
    }
}
