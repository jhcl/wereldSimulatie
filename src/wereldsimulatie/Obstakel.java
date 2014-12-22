/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Een obstakel is een sta-in-de-weg waar niets op groeit en waar niet in of 
 * doorheen gelopen kan worden.
 * @author Lars Ko Tarkan
 */
public class Obstakel {
    private ArrayList<Integer> positie;
    
    /**
     * Constructor. Een obstakel is een sta-in-de-weg op 1 x-y coordinaat.
     * Er kunnen geen planten op groeien en er kan niet in of doorheen gelopen 
     * worden.
     * @param positie List van 2 integers &lt;x,y&gt;-coordinaat
     */
    public Obstakel(ArrayList<Integer> positie) {
        this.positie = positie;
    }
    
}
