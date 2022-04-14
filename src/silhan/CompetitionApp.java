
package silhan;

import java.util.Scanner;


public class CompetitionApp {

    private static Scanner sc = new Scanner(System.in);
    private static Competition competition;
    
    public static void main(String[] args) {
        System.out.println("Zadejte jmeno zavodu");
        competition = new Competition(sc.next());
        boolean end=false; int choice;
        while(true){
            displayMenu();
            choice = sc.nextInt();
            if(choice == 0){
                break;
            }
            menuChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("1 Vytvorit novy zavod");
        System.out.println("2 Registrace zavodniku");
        System.out.println("3 Nastavit startovaci cas");
        System.out.println("4 Nastavit cas v cili");
        System.out.println("5 Vypsat zavodniky");
        System.out.println("6 Vysledkova listina");
        System.out.println("0 Konec");
    }

    private static void menuChoice(int choice) {
        switch(choice){
            case 1:
                createCompetition();
                break;
            case 2:
                registerRunners();
                break;
            case 3:
                setStarTime();
                break;
            case 4:
                setFinishTime();
                break;
            case 5:
                displayCompetition();
                break;
            case 6:
                displayResults();
                break;
        }
    }
    
    public static void registerRunners(){
        String name; String surname; int year; char gender;
        while(true){
            System.out.println("Jmeno:");
            name = sc.next();
            System.out.println("Prijmeni");
            surname = sc.next();
            System.out.println("Rocnik");
            year = sc.nextInt();
            System.out.println("Pohlavi");
            gender = sc.next().toUpperCase().charAt(0);
            competition.addCompetitor(name, surname, year, gender);
            System.out.println("Chcete pokracovat? a/n");
            if(sc.next().toLowerCase().charAt(0)=='n'){
                break;
            }
        }
    }

    private static void createCompetition() {
        System.out.println("Zadejte jmeno zavodu:");
        competition = new Competition(sc.next());
        System.out.println("Zavod " + competition.getName() + " byl vytvoren");
    }

    private static void setStarTime() {
        System.out.println("Jake jsou casove rozestupy mezi startujicimi:");
        int interval = sc.nextInt();
        System.out.println("Zadejte cas startu: (hod min sec)");
        competition.setStartTimeAll(sc.nextInt(),sc.nextInt(),sc.nextInt(),interval);
    }

    private static void setFinishTime() {
        System.out.println("Id zavodnika");
        int id = sc.nextInt();
        System.out.println("Zadejte cas");
        competition.setFinishTimeOf(id,sc.nextInt(),sc.nextInt(),sc.nextInt());
    }

    private static void displayCompetition() {
        System.out.println(competition);
    }

    private static void displayResults() {
        competition.sortByTotalTime();
        System.out.println(competition);
    }
    
}
