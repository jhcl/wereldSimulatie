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
        this.snelheid = 1; 
        this.voortplantingsKosten = (int) Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int) Math.round(this.stamina * 0.05);
    }


    /**
     * Eet methode voor Herbivoor
     * Maximale optelbare energie bij Herbivoor is strength maal 10
     * Maximale gegeten portie van plan is 50
     * Wanneer Herbivooor minder energie nodig heeft dan stneght maal 10, dan wordt energie aangevuld to maximale stamina.
     * Bij plant gaat er dan ook niet de maximale portie van 50 af, maar procentueel berekend aan de hand van de behoefte van Herbivoor
     * Als de plant minder dan 50 energie heeft, dan gaat er overgebleven energie vanaf en Herbivoor krijgt energie procentueel berekend van de energie Plant
     * @param p plant object dat gegeten wordt
     */
    @Override
    public void eet(Plant p) {

        int behoefte = this.strength * 10;
        int schadePlant = 50;

        if (this.energie <= this.stamina - (behoefte)) {

            if (p.getEnergie() >= schadePlant) {
                this.energie = this.energie + (behoefte);
                //p.energie = p.energie - schadePlant;
            } else {
                
               float result = ((float)p.getEnergie()/(float)schadePlant) * ((float)strength * (float)10);
                
                behoefte = (int)result;
                
                schadePlant = p.getEnergie();

                this.energie = this.energie + behoefte;
            }
            p.wordtGegeten(schadePlant);
        }
        else
        {
            behoefte = this.stamina - this.energie;
            
            //schadePlant = 0;
            if (p.getEnergie() >= schadePlant) {
                
                float result = ((float)behoefte/((float)this.strength * 10)) * schadePlant ;
                Math.round(result);
                
                schadePlant = (int)result;
                
                this.energie = this.energie + (behoefte);
                
                //p.energie = p.energie - schadePlant;
            } else {
               
                
               float result = ((float)p.getEnergie()/(float)schadePlant) * behoefte;
                
                behoefte = (int)result;
                schadePlant = p.getEnergie();

                this.energie = this.energie + behoefte;
            }
            p.wordtGegeten(schadePlant);            
        }

    }

}
