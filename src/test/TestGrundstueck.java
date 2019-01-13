package test;
import model.*;

public class TestGrundstueck
{
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
        try//--------------------------------- Fehler   null für Adresse ----------------------
        {
            Grundstueck g1 = new Grundstueck(null, 99f, 'Z', 9f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Fläche zu klein ----------------------
        {
            Grundstueck g1 = new Grundstueck("1010 Wien, Stephansplatz 1", 99f, 'Z', 9f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Fläche zu groß ----------------------
        {
            Grundstueck g1 = new Grundstueck("1010 Wien, Stephansplatz 1", 100001f, 'Z', 9f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   widmung falsch ----------------------
        {
            Grundstueck g1 = new Grundstueck("1010 Wien, Stephansplatz 1", 1000f, 'Z', 9f);
       }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   qm-Preis zu klein ----------------------
        {
            Grundstueck g1 = new Grundstueck("1010 Wien, Stephansplatz 1", 1000f, 'B', .9f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   qm-Preis zu groß ----------------------
        {
            Grundstueck g1 = new Grundstueck("1010 Wien, Stephansplatz 1", 1000f, 'B', 10001f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- korrekt ----------------------
        {																			 	 //   Verkehrs-Werte
            Grundstueck g1 = new Grundstueck("1050 Wien, Bacherplatz 1",2000f, 'G',100f);//		   200.000
            System.out.println(g1);
            Grundstueck g2 = new Grundstueck("9876 Au, Weide 11",4000f, 'N',50f);	     //		   100.000
            System.out.println(g2);
            Grundstueck g3 = new Grundstueck("2233 Schrems, Uferpromenade",10000f, 'B',100f);//  	 2.000.000
            System.out.println(g3);
            Grundstueck g4 = new Grundstueck("1220 Wien, Pfarrwiese",5000f, 'B',100f);		//   	 1.000.000
            System.out.println(g4);                                                         	//      -----------
           											//       3.300.000
//            System.out.println(g1.berechneVerkehrswert());
//            System.out.println(g2.berechneVerkehrswert());
//            System.out.println(g3.berechneVerkehrswert());
//            System.out.println(g4.berechneVerkehrswert());
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
