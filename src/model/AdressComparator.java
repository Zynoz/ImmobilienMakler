package model;

import java.util.Comparator;

/**
 * Aufgabe 5
 * Comparator Klasse, welche die Adressen der Immobilien vergleicht.
 */
public class AdressComparator implements Comparator<Immobilie> {
    @Override
    public int compare(Immobilie o1, Immobilie o2) {
        return o1.getAdresse().compareTo(o2.getAdresse());
    }
}