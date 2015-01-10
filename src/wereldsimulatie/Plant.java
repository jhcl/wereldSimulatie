/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Eetbare vegetatie dat op land groeit
 * @author Lars Ko Tarkan
 */
public class Plant implements Serializable{
    private ArrayList<Integer> positie;
    
    /**
     * Constructor voor plant. <br>
     * Maximale grootte is 150.
     * Energie is 20% van grootte.
     * @param positie List van 2 integers &lt;x,y&gt;-coordinaat
     */
    public Plant(ArrayList<Integer> positie) {
        this.positie = positie;
    }
    
    /**
     * als plant niet dood is groeit het per method call 1 energie unit.
     * 
     */
    public void groei() {
        
    }
    
    public ArrayList<Integer> getPositie() {
        return positie;
    }    
    
}
