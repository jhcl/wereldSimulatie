/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Alleseter, extends Beest
 * @author Lars Ko Tarkan
 */
public class Omnivoor extends Beest {
 
    
     public Omnivoor(ArrayList<Integer> pos) {
        this.positie = pos;
        strength = 40;
        energie = 4000;
        stamina = 4000;        
    }    
    
    /**
     * @see wereldsimulatie.Beest#eet
     * @param o plantobject als het een plant eet of een Beestobject als een 
     * Beest gegeten wordt
     */
    @Override
    public void eet(Object o) {
        
    }    

}
