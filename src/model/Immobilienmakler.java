package model;

import java.io.*;
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
            throw new ImmobilienException("null-Referenz für ImmobilienMakler.setName()!!");
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
            throw new ImmobilienException("null-Referenz für ImmobilienMakler.hinzufuegen()!!");
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
    
    public void remove(int pos) {
        immobilien.remove(pos);
    }
    
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

    public void sortiereAdresse() {
        immobilien.sort(new AdressComparator());
    }

    public void sortiereVerkehrswert() {
        Collections.sort(immobilien);
    }

    public void saveImmobilien(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(immobilien);
        oos.close();
    }

    public void saveMakler(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.close();
    }

    public void exportImmobilienCsv(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        for (Immobilie i : immobilien) {
            fileWriter.append(i.toStringCSV()).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    @SuppressWarnings("unchecked")
    public void loadImmobilien(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        immobilien = (List<Immobilie>) ois.readObject();
        ois.close();
    }

    public void clear() {
        immobilien.clear();
        System.out.println("cleared");
    }
}