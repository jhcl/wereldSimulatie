/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Planteneter, extends Beest
 *
 * @author Lars Ko Tarkan
 */
public class Herbivoor extends Beest<Plant> {

    public Herbivoor(ArrayList<Integer> pos) {
        this.positie = pos;
        strength = 30;
        energie = 3000;
        stamina = 3000;
    }

    public Herbivoor(ArrayList<Integer> positie, int strength, int legs) {
        super(positie, strength, legs);
        this.strength = 30;
        this.legs = 3;
    }

    /**
     * @see wereldsimulatie.Beest#eet
     * @param p plant object dat gegeten wordt
     */
    @Override
    public void eet(Plant p) {

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
