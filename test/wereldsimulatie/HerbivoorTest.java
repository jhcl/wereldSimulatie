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
     * Beest behoefte is 1000, kan maximaal 300 eten, plant heeft energie 100
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Plant p = new Plant(pos);
        Herbivoor instance = new Herbivoor(pos);
        instance.setEnergie(2000);
        p.setEnergie(70);
        
        int x = p.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(p);
        assertTrue(p.getEnergie() == x - 50);
        assertTrue(instance.getEnergie() == y + 300);  
    }
    
        /**
     * Test of eet method, of class Herbivoor.
     * Beest behoefte is 200, plant heeft energie 100 en maximaal 50 gegeten worden
     */
    @Test
    public void testEet2() {
        System.out.println("eet");
        Plant p = new Plant(pos);
        Herbivoor instance = new Herbivoor(pos);
        instance.setEnergie(2800);
        p.setEnergie(70);
        int x = p.getEnergie();
        int y = instance.getEnergie();
        
        
        instance.eet(p);
        
        float result =  ((float)200 /((float)(instance.strength * 10)) * 50);
        int r = (int)result;
        
        assertTrue(p.getEnergie() == x - r);
        assertTrue(instance.getEnergie() == y + 200 );  
    }
    
    
            /**
     * Test of eet method, of class Herbivoor.
     * Beest behoefte is 200, plant heeft energie 5. 
     */
    @Test
    public void testEet3() {
        System.out.println("eet");
        Plant p = new Plant(pos);
        Herbivoor instance = new Herbivoor(pos);
        instance.setEnergie(2800);
        p.setEnergie(5);
        
        int x = p.getEnergie();
        int y = instance.getEnergie();
        instance.eet(p);
        assertTrue(p.getEnergie() == x - 5);
        assertTrue(instance.getEnergie() == y + 20 );  
    }
    
      /**
     * Test of eet method, of class Herbivoor.
     * Beest behoefte is 1000, kan max 300 eten.  plant heeft energie 5. Dus Dier kan maximaal 5/50 * 300 = 30 eten
     */
        @Test
    public void testEet4() {
        System.out.println("eet");
        Plant p = new Plant(pos);
        Herbivoor instance = new Herbivoor(pos);
        instance.setEnergie(2000);
        p.setEnergie(5);
        
        int x = p.getEnergie();
        int y = instance.getEnergie();
        instance.eet(p);
        assertTrue(p.getEnergie() == x - 5);
        assertTrue(instance.getEnergie() == y + 30 );  
    }
    
}
