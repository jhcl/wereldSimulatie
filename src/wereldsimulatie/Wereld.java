/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author Lars Ko Tarkan
 * Geheel van eilanden en zee. Dit is een op zichzelf staand geheel. De randen
 * van een wereld bestaan altijd uit water
 */
public class Wereld extends Observable implements ModelFacade  {
    
    ArrayList eilanden = new ArrayList<>();
    
    /**
     * Maak eiland met vaste positie/oppervlak en creeer objecten volgens
     * verhouding:<br>
     * 10 % obstakel, 40% carnivoor, 30% planten,10% herbivoor, 10% omnivoor
     * @return Eiland object
     */
    public Eiland maakEiland() {
        ArrayList<Integer> opp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 100; j++) {
                opp.add(j);
                opp.add(i);
            }
        }
        Eiland e1 = new Eiland(opp);
        return e1;
    }
    
    /**
     * getter voor lijst van eilanden
     * @return ArrayList van Eiland objecten
     */
    public ArrayList<Eiland> getEilanden() {
        return eilanden;
    }
    
    /**
     * Roep voor alle eilanden de simulatiestap aan
     */
    public void stapDoorSimulatie() {
        
    }
    
    /**
     * Roept de methode aan om model een simulatiestap te laten zetten
     * als return van die stap true is dan setchaged en notifyobservers
     * aanroepen
     */    
    @Override
    public void step() {
        
    }
    
    
    /**
     * 
     * @return ArrayList lengte 2, eerste element breedte(x), 2e element lengte (y)
     */
    @Override
    public ArrayList<Double> getWereldSize() {
        return null;
    }
    
}
