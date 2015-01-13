/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;

/**
 * Vleeseter, extends Beest
 *
 * @author Lars Ko Tarkan
 */
public class Carnivoor extends Beest<Beest> {

    public Carnivoor(ArrayList<Integer> pos) {
        super(pos);
        this.legs = 5;
        this.strength = 50;
        this.stamina = 100* this.strength;
        this.energie = this.stamina;
        this.snelheid = 1;//this.legs;   
        this.voortplantingsKosten = (int)Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int)Math.round(this.stamina * 0.05);  
        this.hitsigheid = (int)Math.round(this.stamina * 0.60);
    }

    public Carnivoor(ArrayList<Integer> positie, int strength, int legs) {
        super(positie, strength, legs);
        this.strength = 50;
        this.legs = 5;
        this.positie = positie;
    }

    /**
     * @see wereldsimulatie.Beest#eet
     * @param b Beest dat gegeten wordt
     */
    @Override
    public void eet(Beest b) {
        int schade = this.strength * 10;
        if (this.energie <= this.stamina - (schade)) {
            if (b.energie >= schade) {
                this.energie = this.energie + (schade);
                b.energie = b.energie - schade;
            } else {
                schade = b.energie;
                b.energie = b.energie - schade;
                this.energie = this.energie + schade;
            }
        } else {
            schade = this.stamina - this.energie;

            if (b.energie >= schade) {
                this.energie = this.energie + (schade);
                b.energie = b.energie - schade;
            } else {
                schade = b.energie;
                b.energie = b.energie - schade;
                this.energie = this.energie + schade;
            }
        }
    }

}
