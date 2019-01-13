package test;
import model.*;

import java.io.IOException;

public class ImmobilienZumTesten
{
    public static void main(String[] args)
    {

        try
        {
            Immobilienmakler im = new Immobilienmakler("test");
        	
            Grundstueck g1 = new Grundstueck("1050 Wien, Bacherplatz 1",2000f, 'G',100f);//			   200.000
            Grundstueck g2 = new Grundstueck("9876 Au, Weide 11",4000f, 'N',50f);	     //			   100.000
            Grundstueck g3 = new Grundstueck("2233 Schrems, Uferpromenade",10000f, 'B',100f);//  	 2.000.000
            Grundstueck g4 = new Grundstueck("1220 Wien, Pfarrwiese",5000f, 'B',100f);		//   	 1.000.000
            
            Wohnhaus w1 = new Wohnhaus("1050 Wien, Bacherplatz 1",500f,'A',100000f);  		//  	   500.000,-
            Wohnhaus w2 = new Wohnhaus("1150 Wien, Linzerstraﬂe 120",500f,'B',100000f); 	//  	   300.000,-
            Wohnhaus w3 = new Wohnhaus("1250 Wien, Wergasse 220",500f,'C',400000f);     	//  	   800.000,-
            Wohnhaus w4 = new Wohnhaus("1350 Wien, Sp‰nglergasse 320",500f,'D',400000f);	//  	   400.000,-

            
            System.out.println(g1);
            System.out.println(w3);
            System.out.println(g4);
            System.out.println(w1);
            System.out.println(g3);
            System.out.println(g2);
            System.out.println(w4);
            System.out.println(w2);

            im.add(g1);
            im.add(g2);
            im.add(g3);
            im.add(g4);

            im.add(w1);
            im.add(w2);
            im.add(w3);
            im.add(w4);

            System.out.println("unsorted:");
            System.out.println(im.toString());
            System.out.println("adresse sortiert:");
            im.sortiereAdresse();
            System.out.println(im.toString());
            System.out.println("verkehrswert sortiert:");
            im.sortiereVerkehrswert();
            System.out.println(im.toString());

            System.out.println(im.remove("1050 Wien, Bacherplatz 1"));
            System.out.println(im.toString());

            im.saveImmobilien("c:\\scratch\\immobilien.ser");

            im.clear();

            im.loadImmobilien("c:\\scratch\\immobilien.ser");
            System.out.println(im.toString());
            im.exportImmobilienCsv("c:\\scratch\\immobilien.csv");

            im.saveMakler("c:\\scratch\\" + im.getName() + ".ser");
        }
        catch (ImmobilienException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}