package model;

public class Wohnhaus extends Immobilie {
    private char kategorie;
    private double einheitsWert;
    
    public Wohnhaus(String adresse, double flaeche, char kategorie, double einheitsWert) throws ImmobilienException {
        super(adresse);
        setFlaeche(flaeche);
        setKategorie(kategorie);
        setEinheitsWert(einheitsWert);
    }

    // ------------------------------------------- getter ---------------------------------
    public double getEinheitsWert() {
        return einheitsWert;
    }

    public char getKategorie() {
        return kategorie;
    }

    // ------------------------------------------- setter ---------------------------------
    public void setFlaeche(double flaeche) throws ImmobilienException {
        if (flaeche >= 50. && flaeche <= 5000.) {
            super.setFlaeche(flaeche);
        } else {
            throw new ImmobilienException("Falscher Parameterwert (" + flaeche + " f�r Wohnhaus.setFlaeche()!!");
        }
    }

    public void setEinheitsWert(double einheitsWert) throws ImmobilienException {
        if (einheitsWert >= 10000. && einheitsWert <= 50000000.) {
            this.einheitsWert = einheitsWert;
        } else {
            throw new ImmobilienException("Falscher Parameterwert (" + einheitsWert + ") f�r Wohnhaus.setEinheitswert()!!");
        }
    }

    public void setKategorie(char kategorie) throws ImmobilienException {
    	char[] kategorien = new char[]{'A','B','C','D'};
    	for (char k : kategorien) {
            if (kategorie == k) {
                this.kategorie = kategorie;
                return; // sofortige Beendung der Method OHNE R�ckgabewert
            }
        }
         throw new ImmobilienException("Falscher Parameterwert ("+kategorie+") f�r Wohnhaus.setKategorie()!!");
    }

    // ------------------------------------------- other ---------------------------------
    public double berechneVerkehrswert() {
        switch (kategorie) {
            case 'A':  return einheitsWert * 5.;
            case 'B':  return einheitsWert * 3.;
            case 'C':  return einheitsWert * 2.;
            case 'D':  return einheitsWert;
        }
        return 0.;
    }

    public String toString() {
    	return "Wohnhaus @ " + super.toString() + " -> Kat. " + kategorie +
                ", � " + berechneVerkehrswert();
    }

    /**
     * Formatiert das Wohnhaus in ein CSV Format.
     * @return
     */
    public String toStringFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAdresse())
                .append(";")
                .append(getFlaeche())
                .append(";")
                .append(getKategorie())
                .append(";")
                .append(getEinheitsWert());
        return sb.toString();
    }
}