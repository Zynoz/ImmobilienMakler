package model;

import java.util.Comparator;

public class AbsteigendAdressComparator implements Comparator<Immobilie> {
    @Override
    public int compare(Immobilie o1, Immobilie o2) {
        return o2.getAdresse().compareTo(o1.getAdresse());
    }
}