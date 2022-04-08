
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class RaceApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Race race = new Race("Jizerská 50");
        // ArrayList<Racer> racers = new ArrayList<>();

        // racers.add(new Racer("Jakub", "Štěpánek", "23 5 22", Gender.MUŽ));
        // racers.add(new Racer("Pavel", "Vácha", "3 2 22", Gender.MUŽ));
        // racers.add(new Racer("Matěj", "Štěpán", "2 2 22", Gender.MUŽ));
        // racers.add(new Racer("Adam", "Sucharda", "1 2 22", Gender.MUŽ));
        // // Starting times
        // racers.get(0).setRaceStartTimeSeconds(230);
        // racers.get(1).setRaceStartTimeSeconds(330);
        // racers.get(2).setRaceStartTimeSeconds(430);
        // racers.get(3).setRaceStartTimeSeconds(530);
        // // Ending times
        // racers.get(0).setRaceEndTimeSeconds(1030);
        // racers.get(1).setRaceEndTimeSeconds(1130);
        // racers.get(2).setRaceEndTimeSeconds(1230);
        // racers.get(3).setRaceEndTimeSeconds(1330);
        // // Add racers to race
        // for (int i = 0; i < racers.size(); i++) {
        // race.setRacers(racers.get(i));
        // }
        // System.out.println(race);

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
