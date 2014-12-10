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
public class ModelFacade {
    
    /**
     * roept de methode aan om model een simulatiestap te laten zetten
     * als return van die stap true is dan setchaged en notifyobservers
     * aanroepen
     */
    public void step() {
        
    }
    
   
    
    /**
     * maak instantie van wereld
     * @return 
     */
    public Wereld maakWereld() {
        return null;
    }
    
    /**
     * 
     * @return ArrayList lengte 2, eerste element breedte, 2e element lengte
     */
    public ArrayList<Double> getWereldSize() {
        return null;
    }
}
