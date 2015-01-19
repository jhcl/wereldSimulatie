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

    /**
     * Constructor voor omnivoor. Hierbij worden de genetische eigenschappen van
     * Omnivoor bepaald
     *
     * @param pos De positie van de Omnivoor wordt meegegeven door Eiland
     * klasse, deze positie is random.
     */
    public Omnivoor(ArrayList<Integer> pos) {
        super(pos);
        this.legs = 4;
        this.strength = 40;
        this.stamina = 100 * this.strength;
        this.energie = this.stamina;
        this.snelheid = 2;
        this.voortplantingsKosten = (int) Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int) Math.round(this.stamina * 0.05);
        this.hitsigheid = (int) Math.round(this.stamina * 0.60);

    }

    /**
     * Eetmethode voor Omnivoor.
     * Wanneer Omnivoor op plant staat @see methode eet Herbivoor
     * Wanneer Omnivoor op beest staat @see methdoe eet Carnivoor
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
            Plant p = (Plant) o;
            int behoefte = this.strength * 10;
            int schadePlant = 50;

            if (this.energie <= this.stamina - (behoefte)) {

                if (p.energie >= schadePlant) {
                    this.energie = this.energie + (behoefte);
                } else {
                    float result = ((float) p.energie / (float) schadePlant) * ((float) strength * (float) 10);
                    behoefte = (int) result;
                    schadePlant = p.energie;
                    this.energie = this.energie + behoefte;
                }
                p.wordtGegeten(schadePlant);
            } else {
                behoefte = this.stamina - this.energie;

                //schadePlant = 0;
                if (p.energie >= schadePlant) {
                    float result = ((float) behoefte / ((float) this.strength * 10)) * schadePlant;
                    Math.round(result);
                    schadePlant = (int) result;
                    this.energie = this.energie + (behoefte);
                } else {
                    float result = ((float) p.energie / (float) schadePlant) * behoefte;
                    behoefte = (int) result;
                    schadePlant = p.energie;
                    this.energie = this.energie + behoefte;
                }
                p.wordtGegeten(schadePlant);
            }
        }

    }

}
