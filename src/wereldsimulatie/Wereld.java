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
    private ArrayList<Beest> zwemmers;
    static final Integer WERELD_BREEDTE = 160;
    static final Integer WERELD_HOOGTE = 120;
    private final Integer WERELD_MARGIN_BREEDTE = WERELD_BREEDTE / 20;
    private final Integer WERELD_MARGIN_HOOGTE = WERELD_HOOGTE / 20;
    private final Integer EILAND_BREEDTE = WERELD_BREEDTE / 2 - 2* WERELD_MARGIN_BREEDTE ;
    private final Integer EILAND_HOOGTE = WERELD_HOOGTE / 2 - 2* WERELD_MARGIN_HOOGTE;
    private ArrayList<Beest> opruimLijst;
    
    
    public Wereld() {
        this.eilanden = new ArrayList<>();
        this.oppervlakEiland1 = new ArrayList<>();
        this.oppervlakEiland2 = new ArrayList<>();
        opruimLijst = new ArrayList<>();
        zwemmers = new ArrayList<>();
        for (int i = 0; i < EILAND_BREEDTE; i++) {
            for (int j = 0; j < EILAND_HOOGTE ; j++) {
                this.oppervlakEiland1.add(WERELD_MARGIN_BREEDTE + i);
                this.oppervlakEiland1.add(WERELD_HOOGTE / 2 - EILAND_HOOGTE / 2 + j);
                this.oppervlakEiland2.add(WERELD_BREEDTE - WERELD_MARGIN_BREEDTE - EILAND_BREEDTE + i);
                this.oppervlakEiland2.add(WERELD_MARGIN_HOOGTE + j);
            }
        }
        eilanden.add(new Eiland(oppervlakEiland1, this));
        eilanden.add(new Eiland(oppervlakEiland2, this));
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
            for (Beest b : zwemmers) {
                if (!(this.staatOpPositie(this.nieuwePositie(b).get(0), this.nieuwePositie(b).get(1)) instanceof Obstakel)) {
                    b.beweeg(this.nieuwePositie(b).get(0), this.nieuwePositie(b).get(1));
                    if (b.getEnergie() <= 0) {
                        opruimLijst.add(b);
                    }                
                }
                for (Eiland el : eilanden) {
                    for (int i = 0; i < el.getEilandOppervlak().size(); i += 2) {
                        if (el.getEilandOppervlak().get(i) == this.nieuwePositie(b).get(0) && el.getEilandOppervlak().get(i+1) == this.nieuwePositie(b).get(1)) {
                            el.getBeesten().add(b);
                            opruimLijst.add(b);
                        }
                    } 

                }
            }
            zwemmers.removeAll(opruimLijst);
            opruimLijst.clear();
        }
        ArrayList<Object> lijstObjecten = new ArrayList<>();
        for (Eiland e : eilanden) {
            lijstObjecten.addAll(e.getBeesten());
            lijstObjecten.addAll(e.getPlanten());
            lijstObjecten.addAll(e.getObstakels());
        }
        for (Beest b : zwemmers) {
            lijstObjecten.addAll(zwemmers);
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
    }
    
    public ArrayList<Beest> getZwemmers() {
        return zwemmers;
    }
    
    public ArrayList<Integer> nieuwePositie(Beest b) {
        ArrayList<Integer> nieuwePos = new ArrayList<>();
        int newX = ((Integer)b.getPositie().get(0) + (Integer)b.getRichting().get(0)) % Wereld.WERELD_BREEDTE;
        int newY = ((Integer)b.getPositie().get(1) + (Integer)b.getRichting().get(1)) % Wereld.WERELD_HOOGTE; 
        nieuwePos.add(newX);
        nieuwePos.add(newY);
        return nieuwePos;
    }
    
    public Object staatOpPositie(Integer x, Integer y) {
        for (Eiland e : eilanden) {
            for (Beest b : e.getBeesten()) {
                    if (b.getPositie().get(0) == x && b.getPositie().get(1) == y) {
                        return b;
                    }
            }
            for (Obstakel o : e.getObstakels()) {
                    if (o.getPositie().get(0) == x && o.getPositie().get(1) == y) {
                        return o;
                    }
            }
            for (Plant p : e.getPlanten()) {
                    if (p.getPositie().get(0) == x && p.getPositie().get(1) == y) {
                        return p;
                    }
            }            
        }
        
        return null;
    }    
    
}
