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
     * Carnivoor heeft 1000 honger, eetbare portie is 500, ander beest heeft voldoende energie
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(4000); 
        
        int x = b.getEnergie(); 
        int y = instance.getEnergie();
        instance.eet(b);
        assertTrue(b.getEnergie() ==  x - 500);
        assertTrue(instance.getEnergie() == y + 500);

    }
    /**
     * Unit test 
     * Carnivoor energie is gezet 4000, zijn honger is 1000, hij kan maximaal 500 eten, maar ander beest heeft 300
     * Energie van Herbivoor wordt gezet op 300. Maximum eetbare portie is 300. 
     * 
     */
        @Test
    public void testEet2() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(4000);
        b.setEnergie(300);
       
        int x = b.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(b);
        assertTrue(b.getEnergie() == x - 300);
        assertTrue(instance.getEnergie() == y + 300);
 
    }
    /**
     * Unit test
     * hunger Carnivoor is 150, ander beest heeft meer energie dan dit.
     */
        @Test
    public void testEet3() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(4850);
        
        
        int x = b.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(b);
        assertTrue(b.getEnergie() == x - 150);
        assertTrue(instance.getEnergie() == y + 150);
 
    }
    
    /**
     * Honger Carnivoor is 300, ander beest heeft maar 200 
     */
        @Test
    public void testEet4() {
        System.out.println("eet");
        Carnivoor instance = new Carnivoor(pos);
        instance.setEnergie(4700);
        b.setEnergie(200);
        
        
        int x = b.getEnergie();
        int y = instance.getEnergie();
        
        instance.eet(b);
        assertTrue(b.getEnergie() == x - 200);
        assertTrue(instance.getEnergie() == y + 200);
 
    }

    
}
