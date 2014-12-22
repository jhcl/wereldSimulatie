/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.ArrayList;


/**
 * Leefgebied
 * @author Lars Ko Tarkan
 */
public class Eiland {
    
    /**
     * getter voor lijst van alle obstakelobjecten van dit eiland
     * @return ArrayLis&lt;Obstakel&gt;
     */
    public ArrayList<Obstakel> getObstakels() {
        return null;
    }
    
    /**
     * getter voor lijst van alle Beest-objecten van dit eiland
     * @return ArrayLis&lt;Beest&gt;
     */
    public ArrayList<Beest> getBeesten() {
        return null;
    }  
    
    /**
     * getter voor lijst van alle Plant-objecten van dit eiland
     * @return ArrayLis&lt;Plant&gt;
     */
    public ArrayList<Plant> getPlanten() {
        return null;
    }
    
    /**
     * Eiland klasse coordineert objecten die zich op het eiland bevinden,
     * deze methode wordt 1x per simulatiestap uitgevoerd.<br>
     * - methode roept beweeg() aan voor beesten en groei() voor planten<br>
     * - als iets eetbaars op dezelfde positie als het beest staat gaan ze
     *   eten gelang de honger (maximaal tot energieniveau gelijk is aan stamina.<br>
     * - als beesten op dezelfde positie staan gaan ze paren (afh van 
     *   hitsigheid) of eten<br>
     * - bij contentie tussen eten en paren (mogelijk in geval van carnivoren) 
     *   wordt er gepaard indien beide besten hitsig zijn, anders eet het
     *   beest dat op dat moment verwerkt wordt het andere beest.<br>
     * - als paden van beesten elkaar kruisen zonder dat ze na een 
     *   simulatiestap op dezelfde positie staan gebeurd er niets<br>
     * - aan water wordt bij &lt;40% energie gezwommen, anders blijven ze op het
     *   eiland<br>
     * - het energieverlies en snelheid voor het zwemmen is hetzelfde als voor 
     *   bewegen op land<br>
     * - Tijdens zwemmen wordt niet gepaard of gegeten<br>
     * - Botsen op een obstakel halveert energie van het beest en het blijft
     *   er vervolgens voor stilstaan, er wordt vervolgens een niuwe richting gekozen<br>
     * @see wereldsimulatie.Plant#groei
     * @see wereldsimulatie.Beest#beweeg
     * @see wereldsimulatie.Beest#paar
     * @see wereldsimulatie.Beest#eet
     * @see wereldsimulatie.Beest#paar
     */
    public void stapDoorSimulatie() {
        
    }
}
