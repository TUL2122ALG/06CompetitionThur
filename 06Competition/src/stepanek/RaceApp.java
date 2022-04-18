import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class RaceApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        Race race = registration();
        System.out.println("Zadejte čas startu: ");
        race.setRaceStartTime(sc.nextLine());
        race.setRacerTimeStartSeconds();
        race.sortByLastName();
        boolean end = false;
        String input = "";
        while (!end) {
            menu();
            System.out.println(race);
            switch (input) {
                case "1":
                    race.setRacer(generateRacer());
                    break;
                case "2":
                    // race.deleteRacer(int);
                    break;
                case "q":
                    end = true;
                    break;
                default:
                    break;
            }
        }
    }

    public static void menu() {

        System.out.println("1 ...přidat závodníka");
        System.out.println("2 ...odstranit závodníka");
        // System.out.println("3 ...změnit status závodníka");
        System.out.println("q ...konec");

    }

    public static Race registration() {
        StringJoiner sj = new StringJoiner(" ");
        System.out.print("Zadejte název závodu: ");
        Race race = setRaceName(sc.nextLine());
        System.out.println("Vítejte v registraci");
        System.out.println("********************");

        boolean end = false;
        while (!end) {
            race.setRacer(generateRacer());
            System.out.println("Přejete si pokračovat? [a/n]");
            if (!sc.nextLine().equalsIgnoreCase("a")) {
                end = true;
            } else {
                end = false;
            }
        }
        return race;
    }

    private static Racer generateRacer() {
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
        return new Racer(firstName, lastName, dateOfBirth, Gender.of(sc.nextLine().toUpperCase()));
    }

    public static Race setRaceName(String race) {
        return new Race(race);
    }

}
