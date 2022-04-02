import java.util.ArrayList;

import javax.lang.model.element.Element;

public class RaceTest {
    public static void main(String[] args) {
        ArrayList<Racer> Racers = new ArrayList<Racer>();
        Racers.add(new Racer("Jakub", "Štěpánek", "2 7 2002"));
        Racers.add(new Racer("Pavel", "Vácha", "3 3 2001"));
        Racers.add(new Racer("Adam", "Sucharda", "23 2 2022"));
        Racers.add(new Racer("Matěj", "Štěpán", "13 2 1234"));
        Racers.add(new Racer("Zdeněk", "Pajda", "22 1 2002"));
        for (int i = 0; i < Racers.size(); i++) {
            Racers.get(i).setGender(Gender.of("M"));
            Racers.get(i).setStartingNumber();
            Racers.get(i).setRegistrationPayed(true);
            Racers.get(2).setRegistrationPayed(false);
            
        }

        System.out.println(Racers.toString());
    }
}
