package stepanek;

public class Racer {
    enum Gender {
        MALE("M"), FEMALE("F"), OTHER("O");

    }

    private final String firstName;
    private final String lastName;
    private int dateOfBirth;
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

    private Racer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Racer getInstanceOf(String firstName, String lastName) {
        return new Racer(firstName, lastName);
    }

}