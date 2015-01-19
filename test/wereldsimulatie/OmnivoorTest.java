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
public class OmnivoorTest {
    Beest b;
    Plant p;
    ArrayList<Integer> pos = new ArrayList<>();
    public OmnivoorTest() {
        
        b = new Herbivoor(pos);
        p = new Plant(pos);
        
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
     * Test of eet method, of class Omnivoor.
     *Omnivoor heeft 1000 behoefte, kan maximaal 400 eten, Omnivoor eet een herbivoor die genoeg energie heeft.
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Object o = (Beest)b;
        Omnivoor instance = new Omnivoor(pos);
        instance.setEnergie(3000);
        
        int x = b.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(o);
        assertTrue(b.getEnergie() == x - 400);
        assertTrue(instance.getEnergie() == y + 400);
    }
    
     /**
     * Test of eet method, of class Omnivoor.
     *Omnivoor heeft 1000 behoefte, kan maximaal 400 eten, Omnivoor eet een plant die genoeg energie heeft.
     */
    @Test
    public void testEet2() {
        System.out.println("eet");
        Object o = (Plant)p;
        Omnivoor instance = new Omnivoor(pos);
        instance.setEnergie(3000);
        p.setEnergie(100);
        
        int x = p.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(o);
        assertTrue(p.getEnergie() == x - 50);
        assertTrue(instance.getEnergie() == y + 400);
    }
    
}
