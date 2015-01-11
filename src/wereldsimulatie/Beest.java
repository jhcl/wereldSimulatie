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
    protected int hitsigheid; 
    
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
        this.hitsigheid = hitsigheid;
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
        energie -= 10;
        setChanged();
        notifyObservers();
    }
    
    public int getEnergie() {
        return energie;
    }
    
    /**
     * @author Lars
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
     * 
     * @TODO Als verschillende soorten beesten paren, winnen de dominante genen moet veranderd worden.(Oplossing: 50/50 type beesten die paren)
     */
    
     // @TODO ?Digestie bepalen
     // @TODO ?Hitsigheid bepalen
     // @TODO ?Energie bepalen en veranderen 
     // @TODO ? ADDED: Dit beest haalt van ander beest af bij bepalen marge
    public Beest paar(Beest b) {
        // Als beide dieren hitsigheid boven 60 hebben ga dan paren
        if(this.getHitsigheid() > 60 && b.getHitsigheid() > 60){
            // Trek voortplantingskosten ouders af
                this.kostenStaminaBeest();
                b.kostenStaminaBeest();
                 
                // Bepaal de values van een beest
                // Gemiddelde bepalen en deze casten naar een int
                int gemiddelde_strength = (int) (this.getStrength() + b.getStrength()) / 2;
                int gemiddelde_stamina = (int) (this.getStamina() + b.getStamina()) / 2;
                int gemiddelde_energie = (int) (this.getEnergie() + b.getEnergie()) / 2;
                int gemiddelde_legs = (int) (this.getLegs() + b.getLegs()) / 2;
                int gemiddelde_gewicht = (int) (this.getGewicht() + b.getGewicht()) / 2;
                // Verschil ouders berekenen
                int verschil_strength = this.getStrength() - b.getStrength();
                int verschil_stamina = this.getStamina() - b.getStamina();
                int verschil_energie = this.getEnergie() - b.getEnergie();
                int verschil_legs = this.getLegs() - b.getLegs();
                int verschil_gewicht = this.getGewicht() - b.getGewicht();
                // Percentage Bepalen
                double percentage_strength = (double) (Math.random() * 10) / 100;
                double percentage_stamina = (double) (Math.random() * 10) / 100;
                double percentage_energie = (double) (Math.random() * 10) / 100;
                double percentage_legs = (double) (Math.random() * 10) / 100;
                double percentage_gewicht = (double) (Math.random() * 10) / 100;
                // Zet percentage double om in Integer
                // Marge wat kind van de ouders nog overerft
                int marge_strength = verschil_strength * (int) percentage_strength;
                int marge_stamina = verschil_stamina * (int) percentage_stamina;
                int marge_energie = verschil_energie * (int) percentage_energie;
                int marge_legs = verschil_legs * (int) percentage_legs;
                int marge_gewicht = verschil_gewicht * (int) percentage_gewicht;
                
                // Random met een kans van 50 / 50
                Random random = new Random();
                int num = random.nextInt(2);
                // +
                if(num == 1){
                   strength = gemiddelde_strength + marge_strength;
                   stamina = gemiddelde_stamina + marge_stamina;
                   energie = gemiddelde_energie + marge_energie;
                   legs = gemiddelde_legs + marge_legs;
                   gewicht = gemiddelde_gewicht + marge_gewicht;
                }
                // -
                if(num == 2){
                   strength = gemiddelde_strength - marge_strength;
                   stamina = gemiddelde_stamina - marge_stamina;
                   energie = gemiddelde_energie - marge_energie;
                   legs = gemiddelde_legs - marge_legs;
                   gewicht = gemiddelde_gewicht - marge_gewicht;
                }
            
            // Als beide beesten omnivoor zijn doe dan dit ook uitvoeren                                      
            if(this instanceof Omnivoor && b instanceof Omnivoor){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // Maak Omnivoor
                Omnivoor o = new Omnivoor(pos);
                // Print out Omnivoor to Console
                o.toString();
                return o;
            }
            // Als beide beesten carnivoor zijn doe dan dit ook uitvoeren                                      
            if(this instanceof Carnivoor && b instanceof Carnivoor){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // Maak Omnivoor
                Carnivoor c = new Carnivoor(pos);
                // Print out Omnivoor to Console
                c.toString();
                return c;
            }
            // Als beide beesten herbivoor zijn doe dan dit ook uitvoeren                                      
            if(this instanceof Herbivoor && b instanceof Herbivoor){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // Maak Omnivoor
                Herbivoor h = new Herbivoor(pos);
                // Print out Omnivoor to Console
                h.toString();
                return h;
            }
            
            // Omnivoor en carnivoor dan dit uitvoeren
            if((this instanceof Omnivoor && b instanceof Carnivoor) || 
              (this instanceof Carnivoor && b instanceof Omnivoor)){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // 50 / 50
                // Random met een kans van 50 / 50
                int rbeest = random.nextInt(2);
                if(rbeest == 1){
                    // Maak Omnivoor
                    Omnivoor o = new Omnivoor(pos);
                    // Print out Omnivoor to Console
                    o.toString();
                    return o;
                }
                if(rbeest == 2){
                    // Maak Carnivoor
                    Carnivoor c = new Carnivoor(pos);
                    // Print out Omnivoor to Console
                    c.toString();
                    return c;
                }
                }
             // Omnivoor en herbivoor dan dit uitvoeren
             if((this instanceof Omnivoor && b instanceof Herbivoor) || 
              (this instanceof Herbivoor && b instanceof Omnivoor)){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // 50 / 50
                // Random met een kans van 50 / 50
                int rbeest = random.nextInt(2);
                if(rbeest == 1){
                    // Maak Omnivoor
                    Omnivoor o = new Omnivoor(pos);
                    // Print out Omnivoor to Console
                    o.toString();
                    return o;
                }
                if(rbeest == 2){
                    // Maak Herbivoor
                    Herbivoor h = new Herbivoor(pos);
                    // Print out Omnivoor to Console
                    h.toString();
                    return h;
                }
                }
            // Carnivoor en herbivoor dan dit uitvoeren
            if((this instanceof Carnivoor && b instanceof Herbivoor) || 
              (this instanceof Herbivoor && b instanceof Carnivoor)){
                // @TODO Positie bepalen
                ArrayList<Integer> pos = new ArrayList<>();
                // 50 / 50
                // Random met een kans van 50 / 50
                int rbeest = random.nextInt(2);
                if(rbeest == 1){
                    // Maak Omnivoor
                    Omnivoor o = new Omnivoor(pos);
                    // Print out Omnivoor to Console
                    o.toString();
                    return o;
                }
                if(rbeest == 2){
                    // Maak Herbivoor
                    Herbivoor h = new Herbivoor(pos);
                    // Print out Omnivoor to Console
                    h.toString();
                    return h;
                }
                }
            } 
        else{
            // @TODO als de beesten niet hitsig zijn en gaan paren
        }
        return null;
    }
    
    
    public int getHitsigheid(){
        return hitsigheid;
    }   
    public int getGewicht(){
        return gewicht;
    }
    public int getStrength(){
        return strength;
    }
    public int getStamina() {
        return stamina;
    }
    public int getLegs() {
        return legs;
    }
    // Voor paren, kosten 10% stamina van ouders aftrekken @author Lars
    public int kostenStaminaBeest() {
        double voortplantingskosten = this.getStamina() - this.getStamina();
        stamina = stamina - (int) voortplantingskosten;
        return stamina;
    }
    @Override
     public String toString() {
        return "Strength: " + strength + "Stamina " + stamina + "Legs: " + legs + "Energie: " + energie;
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
