import java.lang.reflect.InaccessibleObjectException;
import java.util.Scanner;

public class Registration {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Výdejte v registračním okně: ");
        System.out.println("Zadávejte prosím pravdivé informace.");
        System.out.print("Jméno Příjmení: ");
        String[] inArr = sc.nextLine().split(" ");
        System.out.print("Datum narození [DD MM YYYY]: ");
        String dateOfBirth = sc.nextLine();
        Racer r = new Racer(inArr[0], inArr[1], dateOfBirth);
        System.out.print("Pohlaví: [F/M/O]: ");
        r.setGender(Gender.of(sc.nextLine().toUpperCase()));

        System.out.println(r);
    }

}
