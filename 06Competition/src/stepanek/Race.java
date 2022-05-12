import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class Race {
    private String name;
    private ArrayList<Racer> racers = new ArrayList<>();
    private int registrationNumber;
    private int raceStartTime;

    public Race(String name) {
        this.name = name;
    }

    public void loadStart(File file) throws FileNotFoundException, IOException {
        int index = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String[] parts;
            Racer r;
            // skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                parts = line.split("[ ]+");
                r = new Racer(parts[0], parts[1], parts[2], parts[4]);
                racers.add(r);
                r.setStartingNumber(index);
                index++;
            }
        }
    }

    public void loadFinish(File file) throws FileNotFoundException {
        int number;
        String finishTime;
        Racer r;
        try (Scanner in = new Scanner(file)) {
            in.nextLine();
            while (in.hasNext()) {
                number = in.nextInt();
                finishTime = in.next();
                r = findByRegNumber(number);
                r.setRaceEndTimeSeconds(TimeTools.timeToSeconds(finishTime));
            }
        }
    }

    public Racer findByRegNumber(int number) {
        for (Racer racer : this.racers) {
            try {
                if (racer.getStartingNumber() == number) {
                    return new Racer(racer);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }

    public ArrayList<Racer> getRacers() {
        ArrayList<Racer> copy = new ArrayList<>();
        for (Racer racer : this.racers) {
            copy.add(new Racer(racer));
        }
        return copy;
    }

    public Racer getlastRacer() {
        // TODO: safety copy racer
        return this.racers.get(racers.size() - 1);
    }

    public void setRacer(Racer racer) {
        this.racers.add(racer);
    }

    public int getTimeStartSeconds(int racerID) {
        return racers.get(racerID).getRaceStartTimeSeconds();
    }

    public void setRacerTimeStartSeconds() throws IOException, StartTimeNotSet {
        try {
            if (this.raceStartTime == 0) {
                throw new StartTimeNotSet("Nemůžeš končit, dokud závod nezačal");
            }
            racers.get(0).setRaceStartTimeSeconds(this.raceStartTime);
            for (int i = 1; i < this.racers.size(); i++) {
                racers.get(i).setRaceStartTimeSeconds(this.raceStartTime + i * 5);
            }
        } catch (StartTimeNotSet e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Chybné zadání!");
        }

    }

    public void setRacerStartNumber(int racerStartNumber) {
        this.registrationNumber = racerStartNumber;
    }

    public int getRacerStartNumber() {
        return this.registrationNumber;

    }

    public int getTimeEndSeconds(int racerID) {
        return racers.get(racerID).getRaceEndTimeSeconds();
    }

    public String getName() {
        return this.name;
    }

    public void sortByTime() {
        Collections.sort(racers);
    }

    public void sortByLastName() {
        Comparator cbp = new ComparatorRacerByLastName();
        Collections.sort(racers, cbp);
    }

    public void setRaceStartTime(String raceStartTime) {
        this.raceStartTime = TimeTools.timeToSeconds(raceStartTime);
    }

    public String getRaceStartTime() {
        return TimeTools.secondsToTimeString(this.raceStartTime);
    }

    public void saveToFile(File results) throws IOException {
        // results, true = append true
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results)))) {
            pw.println(String.format("%10s %10s %10s %5s %1s %10s %10s %10s", "Por", "Jméno", "Příjmení,", "Věk",
                    "Pohlaví", "Start", "Stop", "Něco"));
            int rank = 1;
            for (Racer racer : racers) {
                pw.println(String.format("%d.", rank));
                pw.println(racer);
                rank++;
            }
        }
    }

    public void saveToBinaryFile(File results) throws IOException {
        int tempLength;
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(results))) {
            out.writeInt(racers.size());
            for (Racer racer : racers) {
                out.writeInt(racer.getStartingNumber());
                tempLength = racer.getFirstName().length();
                out.writeInt(tempLength);
                for (int i = 0; i < tempLength; i++) {
                    out.writeChar(racer.getFirstName().charAt(i));
                }
                out.writeUTF(racer.getLastName());
                out.writeInt(racer.getRaceStartTimeSeconds());
            }
        }
    }

    public String readFromBinary(File file) throws FileNotFoundException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int countOfRacers, number, lengthOfName, time, rank = 1;
        String name, surname = "";

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file))) {
            boolean end = false;
            while (!end) {
                try {
                    rank = 1;
                    countOfRacers = dataInputStream.readInt();
                    for (int i = 0; i < countOfRacers; i++) {
                        number = dataInputStream.readInt();
                        lengthOfName = dataInputStream.readInt();
                        name = "";
                        for (int j = 0; j < lengthOfName; j++) {
                            name += dataInputStream.readChar();
                        }
                        surname = dataInputStream.readUTF();
                        time = dataInputStream.readInt();
                        stringBuilder.append(String.format("%3d. %10s %10s %3d %10s %n", rank, name, surname, number,
                                TimeTools.secondsToTimeString(time)));
                        rank++;
                    }
                } catch (EOFException e) {
                    end = true;
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Race)) {
            return false;
        }
        Race race = (Race) o;
        return Objects.equals(racers, race.racers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(racers);
    }

    @Override
    public String toString() {
        return "Závod: " + getName() + "\n"
                + "     Jméno     Příjmení     Datum_narození     START   KONEC     ČAS_CELKEM"
                + getRacers();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Race jiz50 = new Race("jiz50");
        Scanner sc = new Scanner(System.in);
        String parentPath = System.getProperty("user.dir") + File.separator + "data";
        File dataDirectory = new File(parentPath);
        try {
            while (true) {
                try {
                    jiz50.loadStart(new File(dataDirectory, sc.nextLine()));
                    System.out.println(jiz50);
                    jiz50.loadFinish(new File(sc.nextLine()));
                    System.out.println(jiz50);
                    System.out.println("Zadej soubor pro výsledky");
                    jiz50.saveToFile(new File(sc.nextLine()));
                    jiz50.saveToBinaryFile(new File("results.dat"));
                    System.out.println(jiz50.readFromBinary(new File("results.dat")));
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadej jméno souboru znovu.");
                }
            }

        } catch (

        IOException e) {
            System.out.println("Systémová chyba");
        }
    }
}
