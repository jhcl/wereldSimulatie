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
 *
 * @author Lars Ko Tarkan
 * @param <T> Generics, te specificeren in child class
 */
abstract public class Beest<T> extends Observable implements Serializable {

    private ArrayList<Integer> positie;

    /**
     *
     */
    protected ArrayList<Integer> richting;

    /**
     *
     */
    protected int strength;

    /**
     *
     */
    protected int energie;

    /**
     *
     */
    protected int stamina;

    /**
     *
     */
    protected int gewicht;

    /**
     *
     */
    protected int legs;

    /**
     *
     */
    protected int hitsigheid;

    /**
     *
     */
    protected int voortplantingsKosten;

    /**
     *
     */
    protected int beweegDrempel;

    /**
     *
     */
    protected int snelheid;
    private Random rnd;

    /**
     * Strength: Carnivoor: 50, Herbivoor: 30, Omnivoor: 40<br>
     * Stamina = 100 * strength<br>
     * Aantal poten: Carnivoor: 5, Herbivoor: 3, Omnivoor: 4 <br>
     * Voortplantingskosten: 10% van stamina per ouder, deze hoeveelheid energie
     * wordt aan het kind doorgegeven<br>
     * Beweegdrempel: 5% van stamina, op dat moment blijft een beest stilstaan
     * maar kan nog wel eten of gegeten worden<br>
     * Gewicht: #poten * 10 <br>
     * snelheid (ofwel aantal stappen per simulatiestap): <br>
     * 3 voor carnivoor, 2 voor omnivoor, 1 voor herbivoor<br>
     * Bij 0 (nul) energie is een beest dood en verdwijnt het object<br>
     *
     * @param positie ArrayList Integer, positie van beest wordt opgeslagen om
     * deze op eiland te plaatsen
     */
    public Beest(ArrayList<Integer> positie) {
        this.rnd = new Random();
        int xRichting = rnd.nextInt(3) - 1;
        int yRichting = rnd.nextInt(3) - 1;
        this.positie = new ArrayList<>();
        this.positie = positie;
        this.richting = new ArrayList<>();
        this.richting.add(xRichting);
        this.richting.add(yRichting);

    }

    /**
     * Abstracte methode die als argument een eetbaar object verwacht. Voor
     * herbivoren zijn dat plantobjecten en voor carnivoren beestobjecten,
     * omnivoren eten beide.<br>
     * Als een beest gegeten wordt is het dood als zijn energie op 0 (nul) komt,
     * als een plant gegeten wordt groeit het weer aan. Na 10x gegeten te zijn
     * (energieniveau 0) blijft een plant 100 simulatiestappen ondergronds.<br>
     * Per simulatiestap kan er maximaal 10 energieunits door 1 beest van een
     * plant gegeten worden. Dit levert voor het beest echter een energietoename
     * van 10*strength op. <br>
     * Als een beest een ander beest eet gaat bij de gegetene 10*strength van de
     * eter vanaf. Deze hoeveelheid energieunits komt er bij de eter bij. Als
     * het energieniveau van de eter de stamina hoeveelheid bereikt kan er
     * echter niets meer bij en gaat er ook alleen zoveel vanaf bij de
     * gegetene.<br>
     *
     * @param o (voor herbivoren is dit een plant, voor carnivoren een beest en
     * voor omnivoren kan dit beide zijn)
     */
    abstract void eet(T o);

    /**
     * Deze methode wordt aangeroepen door de container dat het beest kent. Daar
     * ligt de veranwoordelijkheid te controleren of de stap mogelijk is.<br>
     * Een beweeg() in een simulatiestap kost evenveel energie als 10% van het gewicht
     * van het beest .<br>
     * Na positieverandering roep setChanged() en notifyObservers() aan om view
     *  te signalere.
     * @param x int op x waarde mee te geven
     * @param y int om y waarde mee te geven
     */
    public void beweeg(int x, int y) {
        this.positie.set(0, x);
        this.positie.set(1, y);
        this.energie -= this.getGewicht() / 10;
        setChanged();
        notifyObservers();
    }

    /**
     * getter om energie op te vragen
     *
     * @return energie
     */
    public int getEnergie() {
        return this.energie;
    }

     /**
     * Maak één (1) nieuw Beest. Het nieuwe beest erft eigenschappen van ouders:<br>
     * Digestie, Stamina, Legs, Strength,Energie <br>
     * Eigenschappen van het kind zijn het gemiddelde van bovenstaande waarden
     * +/- random waarde tussen 0 en 10% van het verschil van de betreffende
     * waarde van de ouders.<br>
     * Een beest is bereid om te paren vanaf 60% van zijn stamina. Dit punt
     * wordt ook wel zijn hitsigheid genoemd. Dat paren vindt echter alleen
     * plaats als het andere beest (zie argument) dat punt van hitsigheid ook
     * bereikt heeft. Dit wordt in klasse Eiland bepaald door methode isHitsig( van
     * het beest aan te roepen.<br>
     * Als 2 verschillende soorten beesten paren 50% kans op één van de twee type beesten:<br>
     * Bij 2 dezelfde soort beesten 100% kans bij paren op dat type beest.
     * @return Beest (kind)
     * @param b Beest waarmee gepaard wordt
     */
    
   public Beest paar(Beest b) {
        // Als beide dieren hitsigheid boven 60 hebben ga dan paren

        // Trek voortplantingskosten ouders af
        this.kostenStaminaBeest();
        b.kostenStaminaBeest();

        // Bepaal de values van een beest
        // Gemiddelde bepalen en deze casten naar een int
        int gemiddelde_strength = (int) (this.getStrength() + b.getStrength()) / 2;
        int gemiddelde_legs = (int) (this.getLegs() + b.getLegs()) / 2;

        int verschil_strength = Math.abs(this.getStrength() - b.getStrength());
        int verschil_legs = Math.abs(this.getLegs() - b.getLegs());
        // Percentage Bepalen
 //       double percentage_strength = (double) (Math.random() * 10) / 100;
        double percentage = (double) (Math.random() * 10) / 100;

        // Zet percentage double om in Integer
        // Marge wat kind van de ouders nog overerft
        double marge_strengthTemp = (double)verschil_strength * percentage;
        int marge_strength = (int)marge_strengthTemp;
        
        double marge_legsTemp = (double)verschil_legs * percentage;
        int marge_legs = (int)Math.ceil(marge_legsTemp);
       
        int strengthTemp = 0;
        int legsTemp = 0;

        // Random met een kans van 50 / 50
        Random random = new Random();
        int num = random.nextInt(2);

        if (num == 0) {
            strengthTemp = gemiddelde_strength + marge_strength;
            legsTemp = gemiddelde_legs + marge_legs;

        } 
        else {
            strengthTemp = gemiddelde_strength - marge_strength;
            legsTemp = gemiddelde_legs - marge_legs;
        }

        // Als beide beesten omnivoor zijn doe dan dit ook uitvoeren                                      
        if (this instanceof Omnivoor && b instanceof Omnivoor) {
            // Maak Omnivoor
            Omnivoor o = new Omnivoor(this.getPositie());
            o.setStrength(strengthTemp);
            o.legs = legsTemp;
            int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
            o.setEnergie(overgevenVoortplantingsEnergie);

            return o;
        } // Als beide beesten carnivoor zijn doe dan dit ook uitvoeren                                      
        else if (this instanceof Carnivoor && b instanceof Carnivoor) {
            // Maak Omnivoor
            Carnivoor c = new Carnivoor(this.getPositie());
            c.setStrength(strengthTemp);
            c.legs = legsTemp;
            int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
            c.setEnergie(overgevenVoortplantingsEnergie);
            return c;
        } // Als beide beesten herbivoor zijn doe dan dit ook uitvoeren                                      
        else if (this instanceof Herbivoor && b instanceof Herbivoor) {
            // Maak Omnivoor
            Herbivoor h = new Herbivoor(this.getPositie());
            h.setStrength(strengthTemp);
            h.legs = legsTemp;
            int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
            h.setEnergie(overgevenVoortplantingsEnergie);
            return h;
        } // Omnivoor en carnivoor dan dit uitvoeren
        else if ((this instanceof Omnivoor && b instanceof Carnivoor)
                || (this instanceof Carnivoor && b instanceof Omnivoor)) {
            // 50 / 50
            // Random met een kans van 50 / 50
            int rbeest = random.nextInt(2);
            if (rbeest == 0) {
                // Maak Omnivoor
                Omnivoor o = new Omnivoor(this.getPositie());
                o.setStrength(strengthTemp);
                o.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                o.setEnergie(overgevenVoortplantingsEnergie);
                return o;
            }
            if (rbeest == 1) {
                // Maak Carnivoor
                Carnivoor c = new Carnivoor(this.getPositie());
                c.setStrength(strengthTemp);
                c.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                c.setEnergie(overgevenVoortplantingsEnergie);
                return c;
            }
        } // Omnivoor en herbivoor dan dit uitvoeren
        else if ((this instanceof Omnivoor && b instanceof Herbivoor)
                || (this instanceof Herbivoor && b instanceof Omnivoor)) {
            // 50 / 50
            // Random met een kans van 50 / 50
            int rbeest = random.nextInt(2);
            if (rbeest == 0) {
                // Maak Omnivoor
                Omnivoor o = new Omnivoor(this.getPositie());
                o.setStrength(strengthTemp);
                o.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                o.setEnergie(overgevenVoortplantingsEnergie);
                return o;
            }
            if (rbeest == 1) {
                // Maak Herbivoor
                Herbivoor h = new Herbivoor(this.getPositie());
                h.setStrength(strengthTemp);
                h.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                h.setEnergie(overgevenVoortplantingsEnergie);
                return h;
            }
        } // Carnivoor en herbivoor dan dit uitvoeren
        else if ((this instanceof Carnivoor && b instanceof Herbivoor)
                || (this instanceof Herbivoor && b instanceof Carnivoor)) {
            // @TODO Positie bepalen
            // 50 / 50
            // Random met een kans van 50 / 50
            int rbeest = random.nextInt(2);
            if (rbeest == 0) {
                // Maak Omnivoor
                Omnivoor o = new Omnivoor(this.getPositie());
                o.setStrength(strengthTemp);
                o.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                o.setEnergie(overgevenVoortplantingsEnergie);
                return o;
            }
            if (rbeest == 1) {
                // Maak Herbivoor
                Herbivoor h = new Herbivoor(this.getPositie());
                h.setStrength(strengthTemp);
                h.legs = legsTemp;
                int overgevenVoortplantingsEnergie = this.voortplantingsKosten + b.voortplantingsKosten;
                h.setEnergie(overgevenVoortplantingsEnergie);
                return h;
            }
        }
        return null;
    }


    /**
     * Methode om hitsigheid te controleren
     *
     * @return true als energie 95 procent is van stamina
     */
    public boolean isHitsig() {
        return (int) Math.round(this.stamina * 0.6) < this.energie;
    }

    /**
     * getter voor gewicht
     *
     * @return het gewicht van beest
     */
    public int getGewicht() {
        if (this.energie - this.strength > 0) {
//            return 10 * legs + (energie - strength);
            return 10 * this.legs;
        } else {
            return 10 * this.legs;
        }
    }

    /**
     * getter voor strength
     *
     * @return strength
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * getter voor stamina
     *
     * @return stamina
     */
    public int getStamina() {
        return this.stamina;
    }

    /**
     * getter voor aantal poten
     *
     * @return legs
     */
    public int getLegs() {
        return this.legs;
    }

    // Voor paren, kosten 10% stamina van ouders aftrekken @author Lars
    /**
     * Methode om voortplantingskosten te berekenen
     *
     * @return energie
     */
    public int kostenStaminaBeest() {
        double voortplantingskosten = 0.1 * this.getStamina();
        this.energie = this.energie - (int) voortplantingskosten;
        return this.energie;
    }

    @Override
    public String toString() {
        return "Strength: " + strength + "Stamina " + stamina + "Legs: " + legs + "Energie: " + energie;
    }

    /**
     * Getter voor positie om huidige positie op te vragen
     *
     * @return positie
     */
    public ArrayList<Integer> getPositie() {
        return this.positie;
    }

    /**
     * Methode om te checken of beest will zwemmen
     *
     * @return true als enegie onder 40 procent is
     */
    public boolean wilZwemmen() {
        return this.getEnergie() < 0.4 * this.getStamina();
    }

    /**
     * getter voor richting
     *
     * @return richting
     */
    public ArrayList<Integer> getRichting() {
        return this.richting;
    }

    /**
     * Setter voor Richint, hier wordt integers meegegeven voor nieuwe
     * coordinaten
     *
     * @param x integer x
     * @param y integer y
     */
    public void setRichting(int x, int y) {
        this.richting.set(0, x);
        this.richting.set(1, y);
    }

    /**
     * getter voor snelheid
     *
     * @return snelheid
     */
    public int getSnelheid() {
        return this.snelheid;
    }

    /**
     *Methode om energie van beest te halveren wanneer deze tegen een obstakel botst
     * @return
     */
    public int bots() {
        this.energie = this.energie / 2;
        return this.energie;
    }

    /**
     * Check of beest moet bewegen
     *
     * @return true als energie grother is dan beweegdrempel
     */
    public boolean kanBewegen() {
        return this.getEnergie() > (0.05 * this.getStamina());
    }

    /**
     * Methode om een richtign te bepalen na een actie
     */
    public void kiesAndereRichting() {
        ArrayList<Integer> temp = new ArrayList<>();
        int xRichting;
        int yRichting;
        temp.add(-1);
        temp.add(0);
        temp.add(1);
        temp.remove(this.richting.get(0));
        xRichting = temp.get(rnd.nextInt(2));
        temp.add(this.richting.get(0));
        temp.remove(this.richting.get(1));
        yRichting = temp.get(this.rnd.nextInt(2));
        this.setRichting(xRichting, yRichting);
    }
    
    /**
     * om energie van beest te zetten na genoorte
     *
     * @param nieuweEnergie integer
     */
    public void setEnergie(int nieuweEnergie) {
        this.energie = nieuweEnergie;
        setChanged();
        notifyObservers();
    }

    /**
     *Setter voor de strenght
     * @param strength integer
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
}
