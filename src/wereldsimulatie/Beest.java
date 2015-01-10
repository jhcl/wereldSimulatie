/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * abstracte klasse. Diverse soorten Beest worden hiervan afgeleidt
 * @author Lars Ko Tarkan 
 * @param <T> Generics, te specificeren in child class
 */
abstract public class Beest<T> extends Observable implements Serializable {
    
    protected ArrayList<Integer> positie;  
    protected ArrayList<Integer> richting; 
    protected int strength;
    protected int energie;
    protected int stamina;    
    protected int gewicht;
    protected int legs;  
    
    
    /**
     * Strength: Carnivoor: 50, Herbivoor: 30, Omnivoor: 40<br>
     * Stamina = 100 * strength<br>
     * Aantal poten: Carnivoor: 5, Herbivoor: 3, Omnivoor: 4 <br>
     * Voortplantingskosten: 10% van stamina per ouder, deze hoeveelheid 
     *                       energie wordt aan het kind doorgegeven<br>
     * Beweegdrempel: 5% van stamina, op dat moment blijft een beest stilstaan 
     *                maar kan nog wel eten of gegeten worden<br>
     * Gewicht: #poten * 10 + (energie - strength) if energie - strength &gt; 0
     *         anders: #poten * 10<br>
     * snelheid (ofwel aantal stappen per simulatiestap): <br>
     *      #poten - Math.floor(energie-strength / 1000)<br>
     * Bij 0 (nul) energie is een beest dood en verdwijnt het object<br>
     */
    public Beest() {
        this.positie = new ArrayList<>();
        this.richting = new ArrayList<>();        
        Random rnd = new Random();
        int xRichting = rnd.nextInt(3) - 1;
        int yRichting = rnd.nextInt(3) - 1;
        richting.add(xRichting);
        richting.add(yRichting);
    }
    
    public Beest(int strength, int legs) {
        this.positie = new ArrayList<>();
        this.richting = new ArrayList<>();
        Random rnd = new Random();
        int xRichting = rnd.nextInt(3) - 1;
        int yRichting = rnd.nextInt(3) - 1;
        richting.add(xRichting);
        richting.add(yRichting);
        this.strength = strength;
        this.stamina = 100 * strength;
        this.energie = stamina;
        this.legs = legs;
        this.gewicht = legs * 10;
    }
    
    /**
     * Abstracte methode die als argument een eetbaar object verwacht.
     * Voor herbivoren zijn dat plantobjecten en voor carnivoren beestobjecten, 
     * omnivoren eten beide.<br>
     * Als een beest gegeten wordt is het dood als zijn energie op 0 (nul) komt,  
     * als een plant gegeten wordt groeit het weer aan. Na 10x gegeten te zijn  
     * (energieniveau 0) blijft een plant 100 simulatiestappen ondergronds.<br>
     * In geval van keuze tussen eetbare objecten wordt het eerste object dat 
     * het algoritme tegenkomt gegeten en is de beurt voorbij.<br>
     * Per simulatiestap kan er maximaal 10 energieunits door 1 beest van een
     * plant gegeten worden. Dit levert voor het beest echter een 
     * energietoename van 10*strength op. <br>
     * Als een beest een ander beest eet gaat bij de gegetene 10*strength 
     * van de eter vanaf. Deze hoeveelheid energieunits komt er bij de eter bij.
     * Als het energieniveau van de eter de stamina hoeveelheid bereikt kan 
     * er echter niets meer bij en gaat er ook alleen zoveel vanaf bij de
     * gegetene.<br>
     * @param o (voor herbivoren is dit een plant, voor carnivoren een beest
     *          en voor omnivoren kan dit beide zijn)
     */
    abstract void eet(T o);
    
    /**
     * Initiëel maak een stap in een random richting (x en/of y pos +1). 
     * De afstand die per stap gezet wordt bepaald door de snelheid van het 
     * beest.<br>
     * Na die eerste stap wordt voor volgende simulatiestappen dezelfde richting 
     * aangehouden totdat er een interactie plaatsvind met water of een ander 
     * object (obstakel, plant, beest, plant). Dan wordt er een nieuwe random
     * richting gekozen. In geval van interactie met een obstakel blijft het 
     * beest voor het obstakel staan en wordt zijn energie gehalveerd. Dan
     * wordt een nieuwe random richting gekozen uit alle richtingen behalve
     * de vorige. Bij interactie met water wordt de beslissing genomen om
     * door te gaan en te zwemmen (energieniveau &lt;=40%) van stamina) of niet.
     * In het laatste geval wordt een nieuwe richting gekozen die als 
     * eerstvolgende stap niet in het water leidt.<br>
     * Deze methode wordt aangeroepen door de container dat het beest kent.  
     * Daar ligt de veranwoordelijkheid te controleren of de stap mogelijk is.<br>
     * Een beweeg() in een simulatiestap kost evenveel energie als het gewicht
     * van het beest.<br>
     */
    public void beweeg(int x, int y) {
        positie.set(0, x);
        positie.set(1, y); 
        energie -= 1;
    }
    
    /**
     * Maak één (1) nieuw Beest. Het nieuwe beest erft eigenschappen van ouders:<br>
     * Digestie, Stamina, Legs, Hitsigheid, Voortplantingskosten, Strength, 
     * Zwemdrempel, Beweegdrempel <br>
     * Eigenschappen van het kind zijn het gemiddelde van bovenstaande 
     * waarden +/- random waarde tussen 0 en 10% van het verschil van de  
     * betreffende waarde van de ouders.<br> 
     * Een beest is bereid om te paren vanaf 60% van zijn stamina. Dit punt 
     * wordt ook wel zijn hitsigheid genoemd. Dat paren vindt echter alleen 
     * plaats als het andere beest (zie argument) dat punt van hitsigheid 
     * ook bereikt heeft. <br>
     * Als verschillende soorten beesten paren winnen de dominante genen, 
     * de volgorde van dominante genen bij beesten:<br>
     * 1. Omnivoren <br>
     * 2. Carnivoren<br>
     * 3. Herbivoren<br>
     * @return Beest (kind)
     * @param b Beest waarmee gepaard wordt
     */
    public Beest paar(Beest b) {
        return null;
    }
    
    public ArrayList<Integer> getPositie() {
        return positie;
    }
    
    public boolean wilZwemmen() {
        if (energie < 0.4*stamina) { return true;}
        return false;
    }   
    
    public ArrayList<Integer> getRichting() {
        return richting;
    }
    
    public void setRichting(int x, int y) {
        richting.set(0,x);
        richting.set(1,y);
    }
}
