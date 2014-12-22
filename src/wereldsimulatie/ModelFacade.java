/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Interactie van het model met de buitenwereld
 * @author Lars Ko Tarkan
 */
public class ModelFacade {
    
    /**
     * Roept de methode aan om model een simulatiestap te laten zetten
     * als return van die stap true is dan setchaged en notifyobservers
     * aanroepen
     */
    public void step() {
        
    }
    
   
    
    /**
     * Maak instantie van wereld met twee (2) eilanden.
     * @return wereld
     */
    public Wereld maakWereld() {
        return null;
    }
    
    /**
     * 
     * @return ArrayList lengte 2, eerste element breedte(x), 2e element lengte (y)
     */
    public ArrayList<Double> getWereldSize() {
        return null;
    }
}
