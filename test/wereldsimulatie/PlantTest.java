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
public class PlantTest {
    ArrayList<Integer> pos = new ArrayList<>();
    
    public PlantTest() {
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
     * Test of groei method, of class Plant. Plant groeit met 1 energie unit
     */
    @Test
    public void testGroei() {
        System.out.println("groei");
        Plant instance = new Plant(pos);
        int x = instance.getEnergie();
        instance.groei();
        
        assertTrue(instance.getEnergie() == x + 1);
       
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wordtGegeten method, of class Plant.
     */
    @Test
    public void testWordtGegeten() {
        System.out.println("wordtGegeten");
        int hoeveelheid = 15;
        Plant instance = new Plant(pos);
        int x = instance.getEnergie();
        instance.wordtGegeten(hoeveelheid);
        
        assertTrue(instance.getEnergie() == x - hoeveelheid);
        // TODO review the generated test code and remove the default call to fail.
          //fail("The test case is a prototype.");
    }


    
}
