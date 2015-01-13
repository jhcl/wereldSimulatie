/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nl08940
 */
public class BeestTest {
    
    public BeestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of eet method, of class Beest.
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Object o = null;
        Beest instance = null;
        instance.eet(o);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of beweeg method, of class Beest.
     */
    @Test
    public void testBeweeg() {
        System.out.println("beweeg");
        int x = 0;
        int y = 0;
        Beest instance = null;
        instance.beweeg(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnergie method, of class Beest.
     */
    @Test
    public void testGetEnergie() {
        System.out.println("getEnergie");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getEnergie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paar method, of class Beest.
     */
    @Test
    public void testPaar() {
        System.out.println("paar");
        Beest b = null;
        Beest instance = null;
        Beest expResult = null;
        Beest result = instance.paar(b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHitsigheid method, of class Beest.
     */
    @Test
    public void testGetHitsigheid() {
        System.out.println("getHitsigheid");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getHitsigheid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHitsig method, of class Beest.
     */
    @Test
    public void testIsHitsig() {
        System.out.println("isHitsig");
        Beest instance = null;
        boolean expResult = false;
        boolean result = instance.isHitsig();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGewicht method, of class Beest.
     */
    @Test
    public void testGetGewicht() {
        System.out.println("getGewicht");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getGewicht();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrength method, of class Beest.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getStrength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStamina method, of class Beest.
     */
    @Test
    public void testGetStamina() {
        System.out.println("getStamina");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getStamina();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLegs method, of class Beest.
     */
    @Test
    public void testGetLegs() {
        System.out.println("getLegs");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getLegs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kostenStaminaBeest method, of class Beest.
     */
    @Test
    public void testKostenStaminaBeest() {
        System.out.println("kostenStaminaBeest");
        Beest instance = null;
        int expResult = 0;
        int result = instance.kostenStaminaBeest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Beest.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Beest instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositie method, of class Beest.
     */
    @Test
    public void testGetPositie() {
        System.out.println("getPositie");
        Beest instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getPositie();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wilZwemmen method, of class Beest.
     */
    @Test
    public void testWilZwemmen() {
        System.out.println("wilZwemmen");
        Beest instance = null;
        boolean expResult = false;
        boolean result = instance.wilZwemmen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRichting method, of class Beest.
     */
    @Test
    public void testGetRichting() {
        System.out.println("getRichting");
        Beest instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getRichting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRichting method, of class Beest.
     */
    @Test
    public void testSetRichting() {
        System.out.println("setRichting");
        int x = 0;
        int y = 0;
        Beest instance = null;
        instance.setRichting(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSnelheid method, of class Beest.
     */
    @Test
    public void testGetSnelheid() {
        System.out.println("getSnelheid");
        Beest instance = null;
        int expResult = 0;
        int result = instance.getSnelheid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bots method, of class Beest.
     */
    @Test
    public void testBots() {
        System.out.println("bots");
        Beest instance = null;
        int expResult = 0;
        int result = instance.bots();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kanBewegen method, of class Beest.
     */
    @Test
    public void testKanBewegen() {
        System.out.println("kanBewegen");
        Beest instance = null;
        boolean expResult = false;
        boolean result = instance.kanBewegen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kiesAndereRichting method, of class Beest.
     */
    @Test
    public void testKiesAndereRichting() {
        System.out.println("kiesAndereRichting");
        Beest instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.kiesAndereRichting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnergie method, of class Beest.
     */
    @Test
    public void testSetEnergie() {
        System.out.println("setEnergie");
        int x = 0;
        Beest instance = null;
        instance.setEnergie(x);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BeestImpl extends Beest {

        public BeestImpl() {
            super(null);
        }

        public void eet(T o) {
        }
    }
    
}
