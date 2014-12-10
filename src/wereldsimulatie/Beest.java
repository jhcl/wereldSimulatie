/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

/**
 *
 * @author nl08940
 */
abstract class Beest {
    
    /**
     * 
     */
    public Beest() {
        
    }
    
    /**
     * abstracte methode die als argument een eetbaar object verwacht.
     * Voor herbivoren zijn dat plantobjecten en voor carnivoren beestobjecten, 
     * omnivoren eten beide.
     * Als een beest gegeten wordt is het dood, als een plant gegeten wordt 
     * groeit het weer aan. Na 10x gegeten te zijn blijft een plant 100 
     * simulatiestappen ondergronds.
     * @param o 
     */
    abstract void eet(Object o);
    
    /**
     * maak een stap in een random richting (x en/of y pos +1). Deze methode 
     * wordt aangeroepen door de container dat het beest kent. Daar ligt de 
     * veranwoordelijkheid te controleren of de stap mogelijk is.
     */
    public void beweeg() {
        
    }
    
    /**
     * maak new Beest. Het nieuwe beest erft eigenschappen van ouders:
     * Digestie, Stamina, Legs, Hitsigheid, Voortplantingskosten, Strength, 
     * Zwemdrempel, Beweegdrempel
     * @return Beest
     */
    public Beest paar(Beest b) {
        return null;
    }
    
}
