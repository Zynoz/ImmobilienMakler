package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Immobilienmakler implements Serializable
{
    private String name;
    private List<Immobilie> immobilien;
    
    public Immobilienmakler(String name) throws ImmobilienException
    {
        setName(name);
        immobilien = new ArrayList<>();
    }

    //------------------------------------- getter -------------------
    public String getName()
    {
        return name;
    }
    //------------------------------------- setter -------------------
    public void setName(String name) throws ImmobilienException
    {
        if (name != null)
            this.name = name;
        else
            throw new ImmobilienException("null-Referenz f�r ImmobilienMakler.setName()!!");
    }
    //------------------------------------- other -------------------
    public void add(Immobilie immo) throws ImmobilienException
    {
        if (immo != null)
                if (!immobilien.contains(immo))
                    immobilien.add(immo);
                else
                    throw new ImmobilienException("Immobilie ("+immo+") ist schon in der Collection!!");
        else
            throw new ImmobilienException("null-Referenz f�r ImmobilienMakler.hinzufuegen()!!");
    }
    //------------------------------- toStrings ---------------------------------------
    public String toString()
    {
    	StringBuilder sb = new StringBuilder("Immobilienmakler \"").append(name).append("\", derzeit ").append(immobilien.size()).append( " Immobilien in Verwaltung").
                                                                    append("\n----------------------------------------------------------------------\n");
        for (Immobilie immo : immobilien)
        	sb.append(immo).append('\n');
        return sb.toString();
    }
    //------------------------------- Files ---------------------------------------
    /**
     * Aufgabe 5
     * Entgernt Immobilie an �bergebener Position.
     * @param pos
     * @throws ImmobilienException
     */
    public void remove(int pos) throws ImmobilienException {
        if (pos >= 0 && pos <= immobilien.size() - 1) {
            immobilien.remove(pos);
        } else {
            throw new ImmobilienException("Ung�ltige Position");
        }
    }

    /**
     * Aufgabe 5
     * Entfernt alle Immobilien mit angegebener Adresse und gibt Anzahl an entfernten Immobilien zur�ck.
     * @param adresse Adresse, von der Immobilien entfernt werden sollen.
     * @return Anzahl an entfernten Immobilien.
     */
    public int remove(String adresse) {
        Iterator iterator = immobilien.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            Immobilie i = (Immobilie) iterator.next();
            if (i.getAdresse().equals(adresse)) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    /**
     * Aufgabe 5
     * Enfernt alle Immobilien, deren Fl�che kleiner als die �bergeben Fl�che ist und gibt die Anzahl an entfernten Immobilien zur�ck.
     * @param flaeche
     * @return Anzahl an entfernten Immobilien.
     */
    public int remove(double flaeche) {
        if (flaeche >= 0) {
            int count = 0;
            Iterator<Immobilie> immobilieIterator = immobilien.iterator();
            while (immobilieIterator.hasNext()) {
                Immobilie i = immobilieIterator.next();
                if (i.getFlaeche() < flaeche) {
                    count++;
                    immobilieIterator.remove();
                }
            }
            return count;
        } else {
            return -1;
        }
    }

    /**
     * Aufgabe 5
     * Sortiert alle Immobilien absteigend mittels Comparator.
     * @see AdressComparator
     */
    public void sortiereAdresse() {
        immobilien.sort(new AdressComparator());
    }

    /**
     * Aufgabe 5
     * Sortiert alle Immobilien aufsteigend mittels Comparable.
     */
    public void sortiereVerkehrswert() {
        Collections.sort(immobilien);
    }

    /**
     * Aufgabe 5
     * Sortiert alle Immobilien je nachdem ob der �bergeben String "aufsteigend" oder "absteigend" ist.
     * @param value "aufsteigend" oder "absteigend". Andere Werte resultieren in einem Fehler.
     * @throws ImmobilienException
     */
    public void sortiereAdresse(String value) throws ImmobilienException {
        if (value.equals("aufsteigend")) {
            sortiereAdresse();
        } else if (value.equals("absteigend")) {
            immobilien.sort(new AbsteigendAdressComparator());
        } else {
            throw new ImmobilienException("Falscher Parameter: " + value);
        }
    }

    /**
     * Aufgabe 5
     * Berechnet den Gesamtwert aller Immobilien.
     * @return Gibt Gesamtwert aller Immobilien zur�ck.
     */
    public double berechneGesamtwert() {
        double value = 0;
        for (Immobilie i : immobilien) {
            value += i.berechneVerkehrswert();
        }
        return value;
    }

    /**
     * Aufgabe 5
     * Berechnet die Gesamtprovision.
     * @return Gibt die Gesamtprovision aller Immobilien zr�ck.
     */
    public double berechneGesamtProvision() {
        double value = 0;
        for (Immobilie i : immobilien) {
            if (i instanceof Grundstueck) {
                value += i.berechneVerkehrswert() * 0.03;
            } else if (i instanceof Wohnhaus) {
                value += i.berechneVerkehrswert() * 0.05;
            }
        }
        return value;
    }

    /**
     * Aufgabe 6
     * Speichert alle Immobilien in eine serialisierte Datei ab.
     * @param filename Pfad und Name, wo die die Datei gespeichert wird.
     * @throws ImmobilienException
     */
    public void saveImmobilien(String filename) throws ImmobilienException {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(immobilien);
            oos.close();
        } catch (IOException e) {
            throw new ImmobilienException("error: " + e.getMessage());
        }
    }

    /**
     * Aufgabe 7
     * L�dt eine serialisierte Datei ein und �berschreibt die momentane Immobilien collection.
     * @param filename Datei
     * @throws ImmobilienException
     */
    @SuppressWarnings("unchecked")
    public void loadImmobilien(String filename) throws ImmobilienException {
        if (Files.exists(Paths.get(filename))) {    //�berpr�ft, ob File existiert.
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                immobilien = (List<Immobilie>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new ImmobilienException("error: " + e.getMessage());
            }
        } else {
            throw new ImmobilienException("Ausgew�hltes File existiert nicht.");
        }
    }

    /**
     * Aufgabe 8
     * Schreibt alle Immobilien in eine txt Datei im CSV Format.
     * @param filename Pfad und Dateiname, wo die Datei gespeichert werden soll.
     * @throws ImmobilienException
     */
    public void exportImmobilienCsv(String filename) throws ImmobilienException {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            for (Immobilie i : immobilien) {
                fileWriter.append(i.toStringCSV()).append("\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new ImmobilienException("error: " + e.getMessage());
        }
    }

    /**
     * Methode zum testen von import/export Methoden.
     */
    public void clear() {
        immobilien.clear();
    }

    /**
     * Aufgabe 11(Bonus-Aufgabe)
     * Speichert den Makler samt allen dazugeh�rigen Daten mittels Serialisierung in eine Datei.
     * @param filename Pfad und Dateiname, wo diese Datei gespeichert werden soll.
     * @throws ImmobilienException
     */
    public void saveMakler(String filename) throws ImmobilienException {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            throw new ImmobilienException("error: " + e.getMessage());
        }
    }

    /**
     * Aufgabe 12
     * @param filename Pfad wo die einzulesende Datei ist.
     * @return Gibt einen neuen ImmobilienMakler zur�ck, wenn die Datei existiert. Ansonsten null
     * @throws ImmobilienException
     */
    public static Immobilienmakler loadMakler(String filename) throws ImmobilienException {
        if (Files.exists(Paths.get(filename))) {    //�berpr�ft, ob Datei existiert.
            try {
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
                return (Immobilienmakler) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new ImmobilienException("error: " + e.getMessage());
            }
        } else {
            throw new ImmobilienException("erro: File existiert nicht.");
        }
    }
}