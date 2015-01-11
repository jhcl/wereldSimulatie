/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Alleseter, extends Beest
 *
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

        if (o instanceof Beest) {
            Beest b = (Beest) o;

            int schade = strength * 10;

            if (energie <= stamina - (schade)) {
                if (b.energie >= schade) {
                    energie = energie + (schade);
                    b.energie = b.energie - schade;
                } else {
                    schade = b.energie;
                    b.energie = b.energie - schade;
                    energie = energie + schade;
                }
            } else {
                schade = stamina - energie;

                if (b.energie >= schade) {
                    energie = energie + (schade);
                    b.energie = b.energie - schade;
                } else {
                    schade = b.energie;
                    b.energie = b.energie - schade;
                    energie = energie + schade;
                }
            }
        } else {
            Plant p = (Plant) o;
            int behoefte = strength * 10;
            int schadePlant = 10;

            if (p.energie >= schadePlant) {
                energie = energie + (behoefte);
                p.energie = p.energie - schadePlant;
            } else {
                behoefte = p.energie * 10;
                p.energie = p.energie - p.energie;
                energie = energie + behoefte;
            }
        }

    }

}
