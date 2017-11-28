/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.*;


/*******************************************************************************
 * Trida Vec slouzi pro seznam veci, ktere jsou ve hre dostupne a urcuje jejich vlastnosti
 *
 * @author    Dominik Lamacz
 * @version   7.1. 2017
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    private Map<String,Vec>seznamVeci;  // novy seznam veci - private pro Vec
    private boolean zobrazena;
    private String obrazek;


    //== Konstruktory a tovární metody =============================================
    /***************************************************************************
     *  Konstruktor vytvari vec, udava jeji nazev, popis a vlastnost (ne)prenositelnosti
     * @param nazev nazev veci
     * @param popis popis veci
     * @param prenositelna je prenositelna/neni
     * @param zobrazena je zobrazena/neni
     * @param obrazek obrazek veci
     */
    public Vec(String nazev, String popis, boolean prenositelna, boolean zobrazena, String obrazek)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
        this.zobrazena = zobrazena;
        this.obrazek = obrazek;
        this.seznamVeci = seznamVeci;

    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vypise nazev dane veci
     * 
     * @return nazev - slouzi k vypsani veci podle nazvu
     */
    public String getNazev() { // vypise nazev veci
        return nazev;
    }

    /**
     * Metoda vypise popis dane veci, vyuzije se pri zvedani dane veci
     * 
     * @return popis - popis vec
     */
    public String getPopis() { // vypise pripadny popis veci
        return popis;
    }

    /**
     * Metoda je binarni a ukazuje, zdali je dana vec prenositelna, nebo ne
     * 
     * @return prenositelna - true v pripade, ze je prenositelna
     */
    public boolean isPrenositelna() { 
        return prenositelna;
    }
    
      public boolean isZobrazena() {
        return zobrazena;
    }

    
    /**
     * Metoda urci, jestli je vec v prostoru, kde je hrac
     *
     * @param isZobrazena ukaze jestli je veci viditelna
     */
    public void setZobrazena(boolean isZobrazena) {
        this.zobrazena = isZobrazena;
    }

    public String getObrazek() {
        return obrazek;
    }




    //== Soukromé metody (instancí i třídy) ========================================
}
