import java.util.ArrayList;
import java.util.Scanner;

public class RaceApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Race race = new Race("Jizerská 50");

        // System.out.println("=== Výdejte v registračním okně ===");
        // System.out.println("Zadávejte prosím pravdivé informace.");
        // System.out.print("Jméno Příjmení: ");
        // String[] inArr = sc.nextLine().split(" ");
        // System.out.print("Datum narození [DD MM YYYY]: ");
        // String dateOfBirth = sc.nextLine();
        // System.out.print("Pohlaví: [F/M/O]: ");

        // Racer r = Racer.getInstanceOf(inArr[0], inArr[1], dateOfBirth,
        // Gender.of(sc.nextLine().toUpperCase()));
        // System.out.print("start: ");
        // r.setRaceStartTimeSeconds(sc.nextInt());
        // System.out.print("konec: ");
        // r.setRaceEndTimeSeconds(sc.nextInt());

        ArrayList<Racer> racers = new ArrayList<>();

        racers.add(new Racer("Jakub", "Štěpánek", "2 2 22", Gender.MUŽ));
        racers.add(new Racer("Pavel", "Vácha", "2 2 22", Gender.MUŽ));
        racers.add(new Racer("Matěj", "Štěpán", "2 2 22", Gender.MUŽ));
        racers.add(new Racer("Adam", "Sucharda", "2 2 22", Gender.MUŽ));

        for (int i = 0; i < racers.size(); i++) {
            race.setRacers(racers.get(i));
        }

        System.out.println(race);
        // System.out.println(r);

    }

}
