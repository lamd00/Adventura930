package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída VybavaTest slouží ke komplexnímu otestování
 * třídy {@link VybavaTest}.
 *
 * @author    Dominik Lamacz
 * @version   8.1. 2017
 */
public class VybavaTest
{
 
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
     * Test metody vlozitVec a najdiVec
     */
    @Test
    public void testVec()
    {
        Vybava vybava = new Vybava();
        Vec vec1 = new Vec("aaa", "popis", true);
        Vec vec2 = new Vec("pas", "popis", true);
        Vec vec3 = new Vec("paska", "popis", false);
        
        assertEquals(true, vybava.vlozitVec(vec1));
        assertEquals(true, vybava.vlozitVec(vec2));
        assertEquals(true, vybava.odeberVec("aaa")); // test kapacity
        assertEquals(true, vybava.vlozitVec(vec3)); // test kapacity
        assertSame(vec2, vybava.najdiVec("pas")); 
        assertTrue(vybava.jePaska());
        
        

        
    }
    

}



