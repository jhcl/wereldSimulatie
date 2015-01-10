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
 * @author Lars Ko Tarkan
 * Geheel van eilanden en zee. Dit is een op zichzelf staand geheel. De randen
 * van een wereld bestaan altijd uit water
 */
public class Wereld extends Observable implements ModelFacade, Serializable  {
    
    private final ArrayList<Eiland> eilanden;
    private final ArrayList<Integer> oppervlakEiland1;
    private final ArrayList<Integer> oppervlakEiland2;
    ArrayList<Beest> zwemmers;
    static final Integer WERELD_BREEDTE = 160;
    static final Integer WERELD_HOOGTE = 108;
    private final Integer WERELD_MARGIN_BREEDTE = WERELD_BREEDTE / 20;
    private final Integer WERELD_MARGIN_HOOGTE = WERELD_HOOGTE / 20;
    private final Integer EILAND_BREEDTE = WERELD_BREEDTE / 2 - 2* WERELD_MARGIN_BREEDTE ;
    private final Integer EILAND_HOOGTE = WERELD_HOOGTE / 2 - 2* WERELD_MARGIN_HOOGTE;
    
    
    public Wereld() {
        this.eilanden = new ArrayList<>();
        this.oppervlakEiland1 = new ArrayList<>();
        this.oppervlakEiland2 = new ArrayList<>();
        for (int i = 0; i < EILAND_BREEDTE; i++) {
            for (int j = 0; j < EILAND_HOOGTE ; j++) {
                this.oppervlakEiland1.add(WERELD_MARGIN_BREEDTE + i);
                this.oppervlakEiland1.add(WERELD_HOOGTE / 2 - EILAND_HOOGTE / 2 + j);
                this.oppervlakEiland2.add(WERELD_BREEDTE - WERELD_MARGIN_BREEDTE - EILAND_BREEDTE + i);
                this.oppervlakEiland2.add(WERELD_MARGIN_HOOGTE + j);
            }
        }
        eilanden.add(new Eiland(oppervlakEiland1));
        eilanden.add(new Eiland(oppervlakEiland2));
    }
    
    /**
     * Maak eiland met vaste positie/oppervlak en creeer objecten volgens
     * verhouding:<br>
     * 10 % obstakel, 40% carnivoor, 30% planten,10% herbivoor, 10% omnivoor
     * @return Eiland object
     */    
    /**
     * Maak eiland met vaste positie/oppervlak en creeer objecten volgens
     * verhouding:<br>
     * 10 % obstakel, 40% carnivoor, 30% planten,10% herbivoor, 10% omnivoor
     * @return Eiland object
     */
//    public Eiland maakEiland() {
//        ArrayList<Integer> opp = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            for (int j = 0; j < 100; j++) {
//                opp.add(j);
//                opp.add(i);
//            }
//        }
//        return new Eiland(opp);
//        
//        
//    }
    
    /**
     * getter voor lijst van eilanden
     * @return ArrayList van Eiland objecten
     */
    public ArrayList<Eiland> getEilanden() {
        return eilanden;
    }
    
    
    /**
     * Roept de methode aan om model een simulatiestap te laten zetten
     * als return van die stap true is dan setchaged en notifyobservers
     * aanroepen
     */    
    @Override
    public void step() {
        for (Eiland e : eilanden) {
            e.stapDoorSimulatie();
        }
        ArrayList<Object> lijstObjecten = new ArrayList<>();
        for (Eiland e : eilanden) {
            lijstObjecten.addAll(e.getBeesten());
            lijstObjecten.addAll(e.getPlanten());
            lijstObjecten.addAll(e.getObstakels());
            
        }
        setChanged();
        notifyObservers(lijstObjecten);
    }
    
    
    /**
     * 
     * @return ArrayList lengte 2, eerste element breedte(x), 2e element lengte (y)
     */
    @Override
    public ArrayList<Integer> getWereldSize() {
        ArrayList<Integer> grootte = new ArrayList<>();
        grootte.add(WERELD_BREEDTE);
        grootte.add(WERELD_HOOGTE);
        return grootte;
    }

    public void voegZwemmersToe(Beest b) {
        zwemmers.add(b);
        System.out.println(zwemmers.size());
    }
    
}
