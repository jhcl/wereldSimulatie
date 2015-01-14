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
        super(pos);
        this.legs = 4;
        this.strength = 40;
        this.stamina = 100 * this.strength;
        this.energie = this.stamina;
        this.snelheid = this.legs - (int) Math.floor((this.energie - this.strength) / 1000.0);
        this.voortplantingsKosten = (int) Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int) Math.round(this.stamina * 0.05);
        this.hitsigheid = (int) Math.round(this.stamina * 0.60);

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
                    this.energie = this.energie + (schade);
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
            wereldsimulatie.Plant p = (wereldsimulatie.Plant) o;
            int behoefte = this.strength * 10;
            int schadePlant = 10;

            if (this.energie <= this.stamina - (behoefte)) {

                if (p.energie >= schadePlant) {
                    this.energie = this.energie + (behoefte);
                    //p.energie = p.energie - schadePlant;
                } else {
                    schadePlant = p.energie;
                    behoefte = p.energie * strength;
                    //p.energie = p.energie - p.energie;
                    this.energie = this.energie + behoefte;
                }
                p.wordtGegeten(schadePlant);
            } else {
                behoefte = this.stamina - this.energie;
                schadePlant = (int) Math.round(behoefte / strength);
                if (p.energie >= schadePlant) {
                    this.energie = this.energie + (behoefte);
                    //p.energie = p.energie - schadePlant;
                } else {
                    schadePlant = p.energie;
                    behoefte = schadePlant * strength;
                    //p.energie = p.energie - p.energie;
                    this.energie = this.energie + behoefte;
                }
                p.wordtGegeten(schadePlant);
            }
        }

    }

}
