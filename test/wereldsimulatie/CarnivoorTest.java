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
     * Test of eet method, of class Carnivoor.
     */
    @Test
    public void testEet() {
        System.out.println("eet");
        b = new Herbivoor(pos);
        Carnivoor instance = new Carnivoor(pos);
        instance.eet(b);
        // TODO review the generated test code and remove the default call to fail.
        //
    }
    
}
