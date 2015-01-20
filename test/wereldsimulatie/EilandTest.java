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
public class EilandTest {

    private final int OBJECTEN_PER_EILAND = 200;

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
        Wereld ouder = new Wereld();
        Eiland instance = ouder.getEilanden().get(0);
        for (int i = 0; i < 10000; i++) {
            instance.maakEiland();
        }
        System.out.println("maakEiland");
        // check verhouding beesten (60%), planten (30%), obstakels (10%) binnen marges
        assertTrue(instance.getBeesten().size() / 10000 > (OBJECTEN_PER_EILAND * 0.6 - 5));
        assertTrue(instance.getBeesten().size() / 10000 < (OBJECTEN_PER_EILAND * 0.6 + 5));
        assertTrue(instance.getPlanten().size() / 10000 < (OBJECTEN_PER_EILAND * 0.3 + 3));
        assertTrue(instance.getPlanten().size() / 10000 < (OBJECTEN_PER_EILAND * 0.3 + 3));
        assertTrue(instance.getObstakels().size() / 10000 < (OBJECTEN_PER_EILAND * 0.1 + 2));
        assertTrue(instance.getObstakels().size() / 10000 < (OBJECTEN_PER_EILAND * 0.1 + 2));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getObstakels method, of class Eiland.
     */
    @Test
    public void testGetObstakels() {
        System.out.println("getObstakels");
        Wereld ouder = new Wereld();
        Eiland instance = ouder.getEilanden().get(0);
        instance.maakEiland();
        boolean allesObstakels = true;
        for (Object o : instance.getObstakels()) {
            if (!(o instanceof Obstakel)) {
                allesObstakels = false;
            }
        }
        assertEquals(true, allesObstakels);
    }

    /**
     * Test of getEilandOppervlak method, of class Eiland.
     */
//    @Test
//    public void testGetEilandOppervlak() {
//        System.out.println("getEilandOppervlak");
//        Eiland instance = null;
//        ArrayList<Integer> expResult = null;
//        ArrayList<Integer> result = instance.getEilandOppervlak();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getBeesten method, of class Eiland.
     */
    @Test
    public void testGetBeesten() {
        System.out.println("getBeesten");
        Wereld ouder = new Wereld();
        Eiland instance = ouder.getEilanden().get(0);
        instance.maakEiland();
        boolean allesBeesten = true;
        for (Object o : instance.getBeesten()) {
            if (!(o instanceof Beest)) {
                allesBeesten = false;
            }
        }
        assertEquals(true, allesBeesten);
    }

    /**
     * Test of getPlanten method, of class Eiland.
     */
    @Test
    public void testGetPlanten() {
        System.out.println("getPlanten");
        Wereld ouder = new Wereld();
        Eiland instance = ouder.getEilanden().get(0);
        instance.maakEiland();
        boolean allesPlanten = true;
        for (Object o : instance.getPlanten()) {
            if (!(o instanceof Plant)) {
                allesPlanten = false;
            }
        }
        assertEquals(true, allesPlanten);
    }

    /**
     * Test of stapDoorSimulatie method, of class Eiland.
     */
    @Test
    public void testStapDoorSimulatie() {
        System.out.println("stapDoorSimulatie");
        Wereld ouder = new Wereld();
        Eiland instance = ouder.getEilanden().get(0);
        instance.maakEiland();
        ArrayList<Integer> oudPos = new ArrayList<>(); oudPos.add(0); oudPos.add(0);
        ArrayList<Integer> oudRicht = new ArrayList<>(); oudRicht.add(0);oudRicht.add(0);
        ArrayList<Integer> nieuw = new ArrayList<>(); nieuw.add(0); nieuw.add(0);

        ArrayList<Beest> kopie  = new ArrayList<>(instance.getBeesten());
        for (Beest t : kopie) {
            int newX = ouder.nieuwePositie(t).get(0);
            int newY = ouder.nieuwePositie(t).get(1);

            // is de volgende positie nog land
            boolean opLand = false;
            for (int i = 0; i < instance.getEilandOppervlak().size(); i = i + 2) {
                if (instance.getEilandOppervlak().get(i).equals(newX) && instance.getEilandOppervlak().get(i + 1).equals(newY)) {
                    opLand = true;
                    break;
                }
            }
            boolean erStaatEenObstakel = false;
            for (Object o : ouder.staatOpPositie(newX, newY)) {
                if (o instanceof Obstakel) {
                    erStaatEenObstakel = true;
                }
            }
            if (t instanceof Herbivoor) {
                if (opLand && !erStaatEenObstakel) {
                    oudPos.set(0,(int)t.getPositie().get(0));
                    oudPos.set(1,(int)t.getPositie().get(1));
                    oudRicht.set(0, (int)t.getRichting().get(0));
                    oudRicht.set(1, (int)t.getRichting().get(1));
                    System.out.println(oudPos + " " + oudRicht);
                    instance.stapDoorSimulatie();
                    nieuw.set(0, (int)t.getPositie().get(0));
                    System.out.println(nieuw);
                    nieuw.set(1, (int)t.getPositie().get(1));
                    assertTrue((int)oudPos.get(0) + (int)oudRicht.get(0) == (int)nieuw.get(0));
                    assertTrue((int)oudPos.get(1) + (int)oudRicht.get(1) == (int)nieuw.get(1));
                    break;
                }
            }
        }
    }

}
