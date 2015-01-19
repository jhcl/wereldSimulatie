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
public class EilandTest {
    
    public EilandTest() {
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
     * Test of maakEiland method, of class Eiland.
     */
    @Test
    public void testMaakEiland() {
        System.out.println("maakEiland");
        Eiland instance = null;
        instance.maakEiland();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObstakels method, of class Eiland.
     */
    @Test
    public void testGetObstakels() {
        System.out.println("getObstakels");
        Eiland instance = null;
        ArrayList<Obstakel> expResult = null;
        ArrayList<Obstakel> result = instance.getObstakels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEilandOppervlak method, of class Eiland.
     */
    @Test
    public void testGetEilandOppervlak() {
        System.out.println("getEilandOppervlak");
        Eiland instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getEilandOppervlak();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeesten method, of class Eiland.
     */
    @Test
    public void testGetBeesten() {
        System.out.println("getBeesten");
        Eiland instance = null;
        ArrayList<Beest> expResult = null;
        ArrayList<Beest> result = instance.getBeesten();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlanten method, of class Eiland.
     */
    @Test
    public void testGetPlanten() {
        System.out.println("getPlanten");
        Eiland instance = null;
        ArrayList<Plant> expResult = null;
        ArrayList<Plant> result = instance.getPlanten();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stapDoorSimulatie method, of class Eiland.
     */
    @Test
    public void testStapDoorSimulatie() {
        System.out.println("stapDoorSimulatie");
        Eiland instance = null;
        instance.stapDoorSimulatie();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
