/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Planteneter, extends Beest
 * @author Lars Ko Tarkan
 */
public class Herbivoor extends Beest<Plant> {
    

     public Herbivoor(ArrayList<Integer> pos) {
        this.positie = pos;
        strength = 30;
        energie = 3000;
        stamina = 3000;
    }    
    
    /**
     * @see wereldsimulatie.Beest#eet
     * @param p plant object dat gegeten wordt
     */
    @Override
    public void eet(Plant p) {
        
    }    
    

    
}
