import java.util.Comparator;

public class ComparatorByDefectCount implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return (MechanicService.getSumNumberOfBreaks(o2)) - (MechanicService.getSumNumberOfBreaks(o1));
    }
}