import java.util.Scanner;
import java.util.StringJoiner;

public class RaceApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Race race = registration();
        System.out.println(race);

    }

    public static Race registration() {
        StringJoiner sj = new StringJoiner(" ");
        System.out.print("Zadejte název závodu: ");
        Race race = setRaceName(sc.nextLine());
        System.out.println("Vítejte v registraci");
        System.out.println("********************");
        boolean end = false;

        // load name and format for NAME and SURNAME (all without the name)
        while (!end) {
            race.setRacers(setRacer());

            //TODO: getLastRacer() 
            
            
            setStartTime(race.getlastRacer(), sc.nextInt());
            System.out.println("Přejete si pokračovat? [a/n]");
            if (!sc.nextLine().toLowerCase().equals("a")) {
                end = true;
            } else {
                end = false;
            }
        }
        return race;
    }

    public static void setStartTime(Racer racer, int raceStartTimeSeconds) {
        racer.setRaceStartTimeSeconds(raceStartTimeSeconds);
    }

    private static Racer setRacer() {
        StringJoiner sj = new StringJoiner(" ");
        System.out.println("Zadejte: ");
        System.out.print("Jméno a příjmení závodníka: ");
        String[] aStrings = sc.nextLine().split(" ");
        String firstName = aStrings[0];
        for (int i = 1; i < aStrings.length; i++) {
            sj.add(aStrings[i]);
        }
        String lastName = sj.toString();

        // birthday as string
        System.out.print("Datum narození závodníka: ");
        String dateOfBirth = sc.nextLine();

        // gender as Gender
        System.out.print("Pohlaví závodníka [M/Ž/O]: ");
        return new Racer(firstName, lastName, dateOfBirth, Gender.of(sc.nextLine()));
    }

    public static Race setRaceName(String race) {
        return new Race(race);
    }

}
