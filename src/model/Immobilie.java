package model;

import java.io.Serializable;

public abstract class Immobilie implements Comparable<Immobilie>, Serializable
{
    private String adresse;
    private double flaeche;

    /**
     * Aufgabe 1
     * @param adresse Adresse der Immobilie
     * @throws ImmobilienException
     */
    public Immobilie(String adresse) throws ImmobilienException
    {
        setAdresse(adresse);
   }
    // ------------------------------------------- getter ---------------------------------
    public String getAdresse()
    {
        return adresse;
    }
    public double getFlaeche()
    {
        return flaeche;
    }
    // ------------------------------------------- setter ---------------------------------
    public void setAdresse(String adresse) throws ImmobilienException
    {
        if (adresse != null)
            this.adresse = adresse;
        else
            throw new ImmobilienException("null-Referenz f�r Immobilie.setAdresse()!!");
    }
    public void setFlaeche(double flaeche) throws ImmobilienException
    { 		//  Plausibilit�tspr�fung erfolgt bei den Sub-Klassen
            this.flaeche = flaeche;
    }
// ------------------------------------------- other ---------------------------------
    public abstract double berechneVerkehrswert();
    
    public String toString()
    {
        return adresse + " | " + flaeche + " m2";
    }

    /**
     * Teil von Aufgabe 11
     * @return Gibt die Immobilie im CSV Format zur�ck.
     */
    public String toStringCSV() {
        String test = this.getClass() +
                ";" +
                this.getAdresse() +
                ";" +
                this.getFlaeche() +
                ";" +
                this.berechneVerkehrswert();
        return test.substring(6);   //Substring(6), da mit this.getClass() sonst "class.mode.Immobilie" zur�ckgegeben wird.
    }

    @Override
    public int compareTo(Immobilie o) {
        return Double.compare(this.berechneVerkehrswert(), o.berechneVerkehrswert());
    }
}