/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Vleeseter, extends Beest
 * @author Lars Ko Tarkan
 */
public class Carnivoor extends Beest<Beest> {
    
   
     public Carnivoor(ArrayList<Integer> pos) {
        this.positie = pos;
        strength = 50;
        energie = 5000;
        stamina = 5000;        
    }
    
    /**
     * @see wereldsimulatie.Beest#eet
     * @param b Beest dat gegeten wordt
    */
    @Override
    public void eet(Beest b) {
        
    }

    
}
