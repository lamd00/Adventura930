package logika;

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProtihracTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Dominik Lamacz
 * @version   8.1. 2017
 */
public class ProtihracTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    /**
     * 
     */
    @Test
    public void testProtihrac()
    {
        Protihrac protihrac1 = new Protihrac("jagr", false);
        Protihrac protihrac2 = new Protihrac("kane", false);
        assertEquals("jagr", protihrac1.getJmeno());
        assertFalse(protihrac1.isTyp());
        assertFalse(protihrac1.isPorazen());
        protihrac1.setPorazen(true);
        assertTrue(protihrac1.isPorazen());
        assertFalse(protihrac2.isPorazen());
        
        
        
  
    }
}

