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
public class CarnivoorTest {
    Beest b;
    ArrayList<Integer> pos = new ArrayList<>();
    
    public CarnivoorTest() {
        
        b = new Herbivoor(pos);
        
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
     * Unit test
     * Test of eet method, of class Carnivoor.
     * Eatable portion is 500, other b has also enough energy to provide
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(-1000); 
        instance.eet(b);

        assertTrue(b.getEnergie() ==  2500);

    }
    /**
     * Unit test 
     * Carnivoor energie is set to 4000, his hunger is 1000, he can eat 500, but animal only has 300
     * Energy of Herbivoor will be set to 300. Maximum eatable portion is 300, is true when energie is 0
     */
        @Test
    public void testEet2() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(-1000);
        b.setEnergie(-2700);
       
        instance.eet(b);
        int x = b.getEnergie();
        assertTrue(b.getEnergie() == 0);
        // TODO review the generated test code and remove the default call to fail.
        //    
    }
    /**
     * Unit test
     * Available hunger Carnivoor is 150, other b has more than this to provide
     */
        @Test
    public void testEet3() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(-150);
               
        instance.eet(b);
        int x = b.getEnergie();
        assertTrue(b.getEnergie() == 2850);
 
    }
    
    /**
     * Available hunger Carnivoor is 300, other b has only 200 to provide
     */
        @Test
    public void testEet4() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(-300);
        b.setEnergie(-2800);
               
        instance.eet(b);
        int x = b.getEnergie();
        assertTrue(b.getEnergie() == 0);
 
    }

    
}
