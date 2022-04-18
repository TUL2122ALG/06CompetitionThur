import java.util.Comparator;

public class ComparatorRacerByLastName implements Comparator<Racer> {

    @Override
    public int compare(Racer o1, Racer o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }

}
