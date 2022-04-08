import java.util.ArrayList;
import java.util.Scanner;

public class RaceApp {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Race race = new Race("Jizerská 50");
        ArrayList<Racer> racers = new ArrayList<>();

        racers.add(new Racer("Jakub", "Štěpánek", "23 5 22", Gender.MUŽ));
        racers.add(new Racer("Pavel", "Vácha", "3 2 22", Gender.MUŽ));
        racers.add(new Racer("Matěj", "Štěpán", "2 2 22", Gender.MUŽ));
        racers.add(new Racer("Adam", "Sucharda", "1 2 22", Gender.MUŽ));
        // Starting times
        racers.get(0).setRaceStartTimeSeconds(230);
        racers.get(1).setRaceStartTimeSeconds(330);
        racers.get(2).setRaceStartTimeSeconds(430);
        racers.get(3).setRaceStartTimeSeconds(530);
        // Ending times
        racers.get(0).setRaceEndTimeSeconds(1030);
        racers.get(1).setRaceEndTimeSeconds(1130);
        racers.get(2).setRaceEndTimeSeconds(1230);
        racers.get(3).setRaceEndTimeSeconds(1330);
        // Add racers to race
        for (int i = 0; i < racers.size(); i++) {
            race.setRacers(racers.get(i));
        }
        System.out.println(race);
    }

}
