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

    /**
     * Constructor voor Carnivoor. Hierbij worden de genetische eigenschappen van Omnivoor bepaald
     * @param pos De positie van de Carnivoor wordt meegegeven door Eiland klasse, deze positie is random.
     */
    public Carnivoor(ArrayList<Integer> pos) {
        super(pos);
        this.legs = 5;
        this.strength = 50;
        this.stamina = 100* this.strength;
        this.energie = this.stamina;
        this.snelheid = 3;    
        this.voortplantingsKosten = (int)Math.round(this.stamina * 0.1);
        this.beweegDrempel = (int)Math.round(this.stamina * 0.05);  
        this.hitsigheid = (int)Math.round(this.stamina * 0.60);
    }

    /**
     * eet methode voor Carnivoor
     * MAximale eetbare portie is stength maal 10.
     * Als de honger groter is dan deze portie, dan wordt dit er maximaal bij de enrgie opgetel, wanneer de gegeten beest ook deze energie beschikbaar heeft
     * Anders krijgt beest aantal energie dat andere beest beschikbaar heeft.
     * Als de Honger van de beest minder is dan de stength maal 10, dan gaat er ook evenveel energie bij andere beest af. afhankelijk van de honger
     * @param b Beest dat gegeten wordt
     */
    @Override
    public void eet(Beest b) {
        int schade = this.strength * 10;
        if (this.energie <= this.stamina - (schade)) {
            if (b.energie >= schade) {
                this.setEnergie(this.energie + (schade));
                b.setEnergie(b.energie - schade);
            } else {
                schade = b.energie;
                b.setEnergie(b.energie - schade);
                this.setEnergie(this.energie + schade);
            }
        } else {
            schade = this.stamina - this.energie;

            if (b.energie >= schade) {
                this.setEnergie(this.energie + (schade));
                b.setEnergie(b.energie - schade);
            } else {
                schade = b.energie;
                b.setEnergie(b.energie - schade);
                this.setEnergie(this.energie + schade);
            }
        }
       
    }

}
