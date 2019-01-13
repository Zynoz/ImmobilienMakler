package model;

public class Grundstueck extends Immobilie
{
    private char widmung;
    private double qmPreis;

    public Grundstueck(String adresse, double flaeche, char widmung, double qmPreis)  throws ImmobilienException
    {
        super(adresse);
        setFlaeche(flaeche);
        setWidmung(widmung);
        setQmPreis(qmPreis);
    }
    // ------------------------------------------- getter ---------------------------------
    public double getQmPreis()
    {
        return qmPreis;
    }
    public char getWidmung()
    {
        return widmung;
    }
    // ------------------------------------------- setter ---------------------------------
    public void setFlaeche(double flaeche) throws ImmobilienException
    {
        if (flaeche >= 100. && flaeche <= 100000.)
            super.setFlaeche(flaeche);
        else
            throw new ImmobilienException("Falscher Parameterwert ("+flaeche+") für Grundstueck.setFlaeche()!!");
    }
    public void setQmPreis(double qmPreis) throws ImmobilienException
    {
        if (qmPreis >= 1. && qmPreis <= 10000.)
            this.qmPreis = qmPreis;
        else
            throw new ImmobilienException("Falscher Parameterwert ("+qmPreis+") für Grundstueck.setQmPreis()!!");
    }
    public void setWidmung(char widmung) throws ImmobilienException
    {
    	char[] widmungsArten = new char[]{'b','g','n','B','G','N'};
    	for (char w : widmungsArten)
    	    if (widmung == w) 
    	    {
        		this.widmung = widmung;
        		return; // sofortige Beendung der Method OHNE Rückgabewert
    	    }
            throw new ImmobilienException("Falscher Parameterwert ("+widmung+") für Grundstueck.setWidmung()!!");
    }
    // ------------------------------------------- other ---------------------------------
    public double berechneVerkehrswert()
    {
        double preis = getFlaeche() * qmPreis;
        if (widmung == 'N')
            preis /= 2.;
        else
            if (widmung == 'B')
                preis *= 2.;
        return preis;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Grundstück @ ").append(super.toString()).append(" -> ");        
        switch(widmung)
        {
             case 'b':
             case 'B':	sb.append("Bauland"); break;
             case 'g':
             case 'G':	sb.append("Grünland"); break;
             case 'n':
             case 'N':	sb.append("Naturschutz"); break;
             default:	sb.append("Widmung unbekannt");
        }
        sb.append(", € ").append(berechneVerkehrswert());
        return sb.toString();
    }
}
