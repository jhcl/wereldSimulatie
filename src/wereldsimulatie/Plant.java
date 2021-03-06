/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Eetbare vegetatie dat op land groeit
 *
 * @author Lars Ko Tarkan
 */
public class Plant extends Observable implements Serializable {

    private ArrayList<Integer> positie;
    final private int GROOTTE = 150;
    private int energie;
    private int tellerAantalKeerNul;
    private int onderGronds;

    /**
     * Constructor voor plant. <br>
     * Maximale grootte is 150. Energie is 20% van grootte.
     *
     * @param positie List van 2 integers &lt;x,y&gt;-coordinaat
     */
    public Plant(ArrayList<Integer> positie) {
        this.positie = positie;
        this.energie = 30;
        this.tellerAantalKeerNul = 0;
        this.onderGronds = 0;
    }

    /**
     * als plant niet dood is groeit het per method call 1 energie unit.
     * Na 10 keer nul te zijn geweest, groeit plant niet meer voor 100 simulatiestappen
     * als plant 100 simulatieplant onder gind is geweest wordt teller aantalKeerNul en ondergronds weer op Nul gezet
     *
     */
    public void groei() {
        if (this.tellerAantalKeerNul < 5) {
            
            if(energie != GROOTTE)
            {
                this.energie += 1;
                
            }
            
        } else {
            onderGronds++;
        }
        if (onderGronds == 100) {
            this.tellerAantalKeerNul = 0;
            this.onderGronds = 0;

        }
        setChanged();
        notifyObservers();
    }

    /**
     * Methode waarbij energie van plant wordt bijgehouden en de aantalkeer dat
     * plant op nul is gekomen
     *
     * @param hoeveelheid energie dat er af gaat bij plant
     */
    public void wordtGegeten(int hoeveelheid) {
        this.energie -= hoeveelheid;

        if (this.energie == 0) {
            tellerAantalKeerNul++;
        } else {
            //do nothing
        }
        setChanged();
        notifyObservers();

    }

    /**
     *opvragen huidige energie van plant
     * @return energie van plant
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * Setter energie van plan
     * @param energie zet energie naar nieuwe (integer) waarde
     */
    public void setEnergie(int energie) {
        this.energie = energie;
    }

    /**
     *Opvragen positie
     * @return geef referentie van positie terug
     */
    public ArrayList<Integer> getPositie() {
        return positie;
    }

    /**
     *Opvragen hoeveel keer de plant op Nul is geweest
     * @return getter voor interne teller
     */
    public int getTellerAantalKeerNul() {
        return tellerAantalKeerNul;
    }

    /**
     *Hier wordt de teller gezet. 
     * @param tellerAantalKeerNul integer dat wordt megegeven
     */
    public void setTellerAantalKeerNul(int tellerAantalKeerNul) {
        this.tellerAantalKeerNul = tellerAantalKeerNul;
    }

}
