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
 * @author Lars Ko Tarkan
 */
public class Plant extends Observable implements Serializable {
    private ArrayList<Integer> positie;
    private int grootte;
    protected int energie;
    private int tellerAantalKeerNul;
    private int onderGronds;
    
    
    /**
     * Constructor voor plant. <br>
     * Maximale grootte is 150.
     * Energie is 20% van grootte.
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
     * 
     */
    public void groei() {
        if (this.tellerAantalKeerNul < 5) {
            this.energie += 1;
        }
        else {
            onderGronds++;
        }
        if (onderGronds == 100) {
            this.tellerAantalKeerNul = 0;
            this.onderGronds = 0;
        } 
//        setChanged();
//        notifyObservers();         
    }
    
    /**
     *Methode waarbij energie van plant wordt bijgehouden en de aantalkeer dat plant op nul is gekomen
     * @param hoeveelheid energie dat er af gaat bij plant
     */
    public void wordtGegeten(int hoeveelheid) {
        this.energie -= hoeveelheid*10;
        
        if(this.energie == 0)
        {
            tellerAantalKeerNul++;
        }
        else
        {
            //do nothing
        }
        setChanged();
        notifyObservers(); 
        
    }
    
    
    
    public int getEnergie() {
        return energie;
    }
    
    public void setEnergie(int x) {
        this.energie += x;
    }
    
    public ArrayList<Integer> getPositie() {
        return positie;
    }    

    public int getTellerAantalKeerNul() {
        return tellerAantalKeerNul;
    }

    public void setTellerAantalKeerNul(int tellerAantalKeerNul) {
        this.tellerAantalKeerNul = tellerAantalKeerNul;
    }
    
    
    
}
