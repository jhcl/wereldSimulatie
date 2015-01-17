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
 * @author Lars Ko Tarkan Geheel van eilanden en zee. Dit is een op zichzelf
 * staand geheel. De randen van een wereld bestaan altijd uit water
 */
public class Wereld extends Observable implements ModelFacade, Serializable {

    private ArrayList<Eiland> eilanden;
    private ArrayList<Integer> oppervlakEiland1;
    private ArrayList<Integer> oppervlakEiland2;
    private ArrayList<Beest> zwemmers;
    static Integer WERELD_BREEDTE = 160;
    static final Integer WERELD_HOOGTE = 120;
    private final Integer WERELD_MARGIN_BREEDTE = WERELD_BREEDTE / 20;
    private final Integer WERELD_MARGIN_HOOGTE = WERELD_HOOGTE / 20;
    private final Integer EILAND_BREEDTE = WERELD_BREEDTE / 2 - 2 * WERELD_MARGIN_BREEDTE;
    private final Integer EILAND_HOOGTE = WERELD_HOOGTE / 2 - 2 * WERELD_MARGIN_HOOGTE;
    private ArrayList<Beest> opruimLijst;
    private Eiland e1, e2;

    public Wereld() {
        this.eilanden = new ArrayList<>();
        this.oppervlakEiland1 = new ArrayList<>();
        this.oppervlakEiland2 = new ArrayList<>();
        this.opruimLijst = new ArrayList<>();
        this.zwemmers = new ArrayList<>();
        for (int i = 0; i < EILAND_BREEDTE; i++) {
            for (int j = 0; j < EILAND_HOOGTE; j++) {
                this.oppervlakEiland1.add(WERELD_MARGIN_BREEDTE + i);
                this.oppervlakEiland1.add(WERELD_HOOGTE / 2 - EILAND_HOOGTE / 2 + j);
                this.oppervlakEiland2.add(WERELD_BREEDTE - WERELD_MARGIN_BREEDTE - EILAND_BREEDTE + i);
                this.oppervlakEiland2.add(WERELD_MARGIN_HOOGTE + j);
            }
        }
//        e1 = new Eiland(this.oppervlakEiland1, this);
//        e2 = new Eiland(this.oppervlakEiland2, this);

//        this.eilanden.add(e2);
//        this.eilanden.add(e1);
        eilanden.add(new Eiland(oppervlakEiland1, this));
        eilanden.add(new Eiland(oppervlakEiland2, this));
    }

    /**
     * getter voor lijst van eilanden
     *
     * @return ArrayList van Eiland objecten
     */
    public ArrayList<Eiland> getEilanden() {
        return this.eilanden;
    }

    /**
     * Roept de methode aan om model een simulatiestap te laten zetten als
     * return van die stap true is dan setchaged en notifyobservers aanroepen
     */
    @Override
    public void step() {
        for (Eiland e : eilanden) {
            e.stapDoorSimulatie();
        }
        for (Beest b : zwemmers) {

            // controleer of we aan de rand van een eiland met een obstakel terecht gekomen zijn 
            boolean erStaatEenObstakel = false;
            int newX = this.nieuwePositie(b).get(0);
            int newY = this.nieuwePositie(b).get(1);
            for (Object o : this.staatOpPositie(newX, newY)) {
                if (o instanceof Obstakel) {
                    erStaatEenObstakel = true;
                }
            }
            if (!erStaatEenObstakel) {
                b.beweeg(newX, newY);
                if (b.getEnergie() <= 0) {
                    opruimLijst.add(b);
                }

                // zijn we geland ?
                for (Eiland el : eilanden) {
                    for (int i = 0; i < el.getEilandOppervlak().size(); i += 2) {
                        if (el.getEilandOppervlak().get(i) == newX && el.getEilandOppervlak().get(i + 1) == newY) {
                            el.getBeesten().add(b);
                            opruimLijst.add(b);
                        }
                    }
                }
            } else {
                b.bots();
                b.kiesAndereRichting();
            }
        }
        zwemmers.removeAll(opruimLijst);
        opruimLijst.clear();

        ArrayList<Object> lijstObjecten = new ArrayList<>();
        for (Eiland e : eilanden) {
            lijstObjecten.addAll(e.getBeesten());
            lijstObjecten.addAll(e.getPlanten());
            lijstObjecten.addAll(e.getObstakels());
        }
        lijstObjecten.addAll(zwemmers);

        setChanged();
        notifyObservers(lijstObjecten);
    }

    /**
     *
     * @return ArrayList lengte 2, eerste element breedte(x), 2e element lengte
     * (y)
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
        int newX = (Wereld.WERELD_BREEDTE + ((Integer) b.getPositie().get(0) + (Integer) b.getRichting().get(0))) % Wereld.WERELD_BREEDTE;
        int newY = (Wereld.WERELD_HOOGTE + ((Integer) b.getPositie().get(1) + (Integer) b.getRichting().get(1))) % Wereld.WERELD_HOOGTE;
        nieuwePos.add(newX);
        nieuwePos.add(newY);
        return nieuwePos;
    }

    public ArrayList<Object> staatOpPositie(int x, int y) {
        ArrayList<Object> staatOpElkaar = new ArrayList<>();
        for (Eiland e : this.eilanden) {
            for (Beest b : e.getBeesten()) {
                if ((int) b.getPositie().get(0) == x && (int) b.getPositie().get(1) == y) {
                    staatOpElkaar.add(b);
                }
            }
            for (Plant p : e.getPlanten()) {
                if (p.getPositie().get(0) == x && p.getPositie().get(1) == y) {
                    staatOpElkaar.add(p);
                }
            }
            for (Obstakel o : e.getObstakels()) {
                if (o.getPositie().get(0) == x && o.getPositie().get(1) == y) {
                    staatOpElkaar.add(o);
                }
            }

        }
        return staatOpElkaar;
    }

}
