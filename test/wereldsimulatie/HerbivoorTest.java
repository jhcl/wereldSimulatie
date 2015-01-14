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
public class HerbivoorTest {
    
    ArrayList<Integer> pos = new ArrayList<>();
    
    public HerbivoorTest() {
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
     * Test of eet method, of class Herbivoor.
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Plant p = new Plant(pos);
        Herbivoor instance = new Herbivoor(pos);
        instance.setEnergie(-1000);
        
        
        instance.eet(p);
        assertTrue(p.getEnergie() ==  20);
        assertTrue(instance.getEnergie() == 2300);  

        
    }
    
}
