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
/**
     * Constructor voor Herbivoor. Hierbij worden de genetische eigenschappen van Omnivoor bepaald
     * @param pos De positie van de Herbivoor wordt meegegeven door Eiland klasse, deze positie is random.
 */
    public Herbivoor(ArrayList<Integer> pos) {
        super(pos);
        this.legs = 3;
        this.strength = 30;
        this.stamina = 100 * this.strength;
        this.energie = this.stamina;
        this.snelheid = this.legs - (int) Math.floor((this.energie - this.strength) / 1000.0);
        this.voortplantingsKosten = (int) Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int) Math.round(this.stamina * 0.05);
        this.hitsigheid = (int) Math.round(this.stamina * 0.60);

    }


    /**
     * @see wereldsimulatie.Beest#eet
     * @param p plant object dat gegeten wordt
     */
    @Override
    public void eet(Plant p) {

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
        }
        else
        {
            behoefte = this.stamina - this.energie;
            schadePlant = (int)Math.round(behoefte/strength);
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
