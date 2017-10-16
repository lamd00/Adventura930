package logika;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VecTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VecTest
{
    /**
     * Default constructor for test class VecTest
     */
    public VecTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void test2()
    {
        logika.Vec vec1 = new logika.Vec("hulka", "bla bla", true);
		logika.Vec vec2 = new logika.Vec("sadlo", "mastne", false);
		Vybava vybava = new Vybava();
		assertEquals("hulka", vec1.getNazev());
		assertEquals("mastne", vec2.getPopis());
		assertEquals(true, vec1.isPrenositelna());
		assertEquals(false, vec2.isPrenositelna());
		
    }
}


