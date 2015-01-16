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















    
    
}
