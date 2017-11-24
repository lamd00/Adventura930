package logika;
/*import java.util.Map;
import java.util.HashMap;*/
import java.util.*;

/**
 * Trida Vybava slouzi pro spravu seznamu veci (hash mapu), ktere hrac v prubehu hry zvednul prikazem vezmi
 * 
 * @author Dominik Lamacz
 * @version 8.1. 2017
 */
public class Vybava
{
    private final static int VELIKOST = 2  ; //velikost vybavy
    private Map<String, Vec> seznamVeci;

    /**
     * Konstruktor pro Vybava vytvari hash mapu, do ktere se vkladaji (a z ktere se odebiraji) veci zvednute v prubehu hry
     */
    public Vybava()
    {
        seznamVeci = new HashMap<>(); 
    }

    /**
     * Metoda vlozitVec slouzi pro vkladani veci do vybavy. Zkontroluje, jestli neni plna a pokud neni, vlozi vec
     * 
     * @param vec - vkladana vec (nazev)
     */
    public boolean vlozitVec (Vec vec) { 
        if (seznamVeci.size() < VELIKOST ) {  
            seznamVeci.put(vec.getNazev(), vec); 
            return true;
        }
        return false;
    } 

    /**
     * Metoda hlida, jestli neni prostor pro vybavu plny
     * 
     * @return - true, pokud maji veci ve vybave stejny pocet jako je kapacita => kapacita je plna
     */
    public boolean plno() {
        if (seznamVeci.size() == VELIKOST) {
            return true;
        }
        return false;
    }

    /**
     * Metoda kontroluje, jestli je ve vybave predmet "paska", aby mohl byt vykonan prikaz strel
     * 
     * @return - true, pokud paska ve vybave je
     */

    public boolean jePaska ()
    {
        if (seznamVeci.containsKey("paska"))
        {
            return true;
        }
        return false;
    }

    /**
     * Metoda pro odebirani veci z vybavy
     * 
     * @param nazevVeci - nazev odebirane veci ze seznamu vybavy
     */
    public boolean odeberVec (String nazevVeci)
    {
        Vec vec = seznamVeci.get(nazevVeci);
        return seznamVeci.remove(nazevVeci, vec);
    }
    
    /**
     * Metoda pro nalezeni veci ve vybave, slouzi pro jeji odebrani
     * 
     * @param nazevVeci - nazev veci ve vybave
     */
     public Vec najdiVec (String nazevVeci)
    {
        return seznamVeci.get(nazevVeci);
    }
     
     public Map<String, Vec> getCelaVybava() {
        return seznamVeci;
    }



}

