package test;
import model.*;

public class TestWohnhaus
{
    @SuppressWarnings("unused")
    public static void main(String[] args)
    {
       try//--------------------------------- Fehler   Fl‰che zu klein ----------------------
        {
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",49f,'E',9999f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Fl‰che zu groﬂ ----------------------
        {
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",5001f,'E',9999f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Kategorie falsch ----------------------
        {
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",500f,'E',9999f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Einheitswert zu klein ----------------------
        {
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",500f,'A',9999f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- Fehler   Einheitswert zu groﬂ ----------------------
        {
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",500f,'A',50000001f);
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
        try//--------------------------------- korrekt ----------------------
        {										// Verkehrs-Werte
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Spengergasse 20",500f,'A',100000f);  //  500.000,-
            System.out.println(w1);
            Wohnhaus w2 = new Wohnhaus("1150 Wien, Linzerstraﬂe 120",500f,'B',100000f); //  300.000,-
            System.out.println(w2);
            Wohnhaus w3 = new Wohnhaus("1250 Wien, Wergasse 220",500f,'C',400000f);     //  800.000,-
            System.out.println(w3);
            Wohnhaus w4 = new Wohnhaus("1350 Wien, Sp‰nglergasse 320",500f,'D',400000f);//  400.000,-
            System.out.println(w4);
            										//-----------
            										//2.000.000,-
//          System.out.println(w1.berechneVerkehrswert());
//          System.out.println(w2.berechneVerkehrswert());
//          System.out.println(w3.berechneVerkehrswert());
//          System.out.println(w4.berechneVerkehrswert());
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
