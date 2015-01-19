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
 * @author 310054544
 */
public class BeestTest {
    Beest b;
    ArrayList<Integer> pos = new ArrayList<>();
    private ArrayList<Integer> positie;
    
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
     * Test of paar method, of class Beest.
     */
    @Test
    public void testPaar() {
        System.out.println("paar");
        Beest b = new Herbivoor(pos);
        Beest instance = new Carnivoor(pos) ;
        
        
        Beest expResult = null;
        
        Beest result = instance.paar(b);
        
        if(result instanceof Omnivoor )
        {
            expResult = result;
        }
        else if(result instanceof Herbivoor )
        {
            expResult = result;
        }
        else
        {
            result = null;
        }
            
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of beweeg method, of class Beest.
     */
     
    @Test
    public void testBeweeg() {
        System.out.println("beweeg");
        int x = 2;
        int y = 3;
        pos.add(x);
        pos.add(y);
        Carnivoor c = new Carnivoor(pos);
        Beest instance = c;
        instance.beweeg(x, y);
        assertTrue(c.getPositie().get(0) == 2 && c.getPositie().get(1) == 3);

        //TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isHitsig method, of class Beest.
     */
    @Test
    public void testIsHitsig() {
        System.out.println("isHitsig");
        Beest instance = new Carnivoor(pos);
        boolean expResult = true;
        boolean result = instance.isHitsig();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }


    /**
     * Test of kostenStaminaBeest method, of class Beest.
     */
    @Test
    public void testKostenStaminaBeest() {
        System.out.println("kostenStaminaBeest");
        Beest instance = new Herbivoor(pos);
        double resultTemp = instance.energie * 0.1;
        int expResult = instance.stamina - (int)resultTemp;
        int result = instance.kostenStaminaBeest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }


    /**
     * Test of wilZwemmen method, of class Beest.
     */
    @Test
    public void testWilZwemmen() {
        System.out.println("wilZwemmen");
        Beest instance = new Omnivoor(pos);
        boolean expResult = false;
        boolean result = instance.wilZwemmen();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of bots method, of class Beest.
     */
    @Test
    public void testBots() {
        System.out.println("bots");
        Beest instance = new Carnivoor(pos);
        int expResult = instance.energie/2;
        int result = instance.bots();
        assertEquals(expResult, result);

    }




   public ArrayList<Integer> getPositie() {
        return this.positie;
    }

    
    















    
    
}
