/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;


/**
 *
 * @author nl08940
 */
public class Eiland {
    
    /**
     * getter voor lijst van alle obstakelobjecten van dit eiland
     * @return ArrayLis&lt.Obstakel&gt.
     */
    public ArrayList<Obstakel> getObstakels() {
        return null;
    }
    
    /**
     * getter voor lijst van alle Beest-objecten van dit eiland
     * @return ArrayLis&lt.Beest&gt.
     */
    public ArrayList<Beest> getBeesten() {
        return null;
    }  
    
    /**
     * getter voor lijst van alle Plant-objecten van dit eiland
     * @return ArrayLis&lt.Plant&gt.
     */
    public ArrayList<Plant> getPlanten() {
        return null;
    }
    
    /**
     * Eiland klasse coordineert objecten die zich op het eiland bevinden,
     * deze methode wordt 1x per simulatiestap uitgevoerd.
     * - methode roept beweeg aan voor beesten en groei voor planten
     * - als iets eetbaars naast beest staat gaan ze eten gelang de honger
     * - als beesten naast elkaar staan gaan ze paren afh van hitsigheid
     * - bij contentie tussen eten en paren beslist het lot (random)
     * - aan water beslist energieniveau of ze gaan zwemmen
     * @return
     */
    public void stapDoorSimulatie() {
        
    }
}
