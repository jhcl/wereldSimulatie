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
 * Geheel van eilanden en zee. Dit is een op zichzelf
 * staand geheel. De randen van een wereld bestaan altijd uit water
 */
public class Wereld extends Observable implements ModelFacade, Serializable {

    private ArrayList<Eiland> eilanden;
    private ArrayList<Integer> oppervlakEiland1;
    private ArrayList<Integer> oppervlakEiland2;
    private ArrayList<Beest> zwemmers;
    private static final Integer WERELD_BREEDTE = 160;
    private static final Integer WERELD_HOOGTE = 120;
    private final Integer WERELD_MARGIN_BREEDTE = WERELD_BREEDTE / 20;
    private final Integer WERELD_MARGIN_HOOGTE = WERELD_HOOGTE / 20;
    private final Integer EILAND_BREEDTE = WERELD_BREEDTE / 2 - 2 * WERELD_MARGIN_BREEDTE;
    private final Integer EILAND_HOOGTE = WERELD_HOOGTE / 2 - 2 * WERELD_MARGIN_HOOGTE;
    private ArrayList<Beest> opruimLijst;

    /**
     * Constructor van wereld, hierin wordt meteen de eilanden gedefiniëerd.
     */
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
     * Roept de methode aan om model een simulatiestap te laten zetten. De eilanden 
     * opdracht geven omdat te doen voor zijn objecten en zelf doen voor de zwemmers.
     * Daarna setchaged en notifyobservers aanroepen
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
                        if (el.getEilandOppervlak().get(i).equals(newX) && el.getEilandOppervlak().get(i + 1).equals(newY)) {
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
     * getter voor breedte en lengte van de wereld
     * @return ArrayList size 2, eerste element breedte(x), 2e element lengte
     * (y)
     */
    @Override
    public ArrayList<Integer> getWereldSize() {
        ArrayList<Integer> grootte = new ArrayList<>();
        grootte.add(WERELD_BREEDTE);
        grootte.add(WERELD_HOOGTE);
        return grootte;
    }

    /**
     * Voeg beest toe aan lijst met beesten die niet een eiland staan
     * @param b zwemmend beest dat gaat zwemmen
     */
    public void voegZwemmersToe(Beest b) {
        zwemmers.add(b);
    }

    /**
     * getter van lijst met zwemmende beesten
     * @return ArrayLlist&lt;Beest&gt; zwemmers
     */
    public ArrayList<Beest> getZwemmers() {
        return zwemmers;
    }

    /**
     * Bereken positie van het beest nadat die een stap gezet heeft (huidige
     *  positie + richting)
     * @param b beest waar richting van bepaald wordt
     * @return Nieuwe positie
     */
    public ArrayList<Integer> nieuwePositie(Beest b) {
        ArrayList<Integer> nieuwePos = new ArrayList<>();
        int newX = (Wereld.WERELD_BREEDTE + ((Integer) b.getPositie().get(0) + (Integer) b.getRichting().get(0))) % Wereld.WERELD_BREEDTE;
        int newY = (Wereld.WERELD_HOOGTE + ((Integer) b.getPositie().get(1) + (Integer) b.getRichting().get(1))) % Wereld.WERELD_HOOGTE;
        nieuwePos.add(newX);
        nieuwePos.add(newY);
        return nieuwePos;
    }

    /**
     * Zoek in alle objecten die op land staan welke er op een specifieke positie
     *  staan
     * @param x x-coordinaat van gevraagde positie
     * @param y y-coordinaat van gevraagde positie
     * @return Lijst van objecten die op gevraagde positie staan
     */
    public ArrayList<Object> staatOpPositie(int x, int y) {
        ArrayList<Object> staatOpElkaar = new ArrayList<>();
        for (Eiland e : this.eilanden) {
            for (Beest b : e.getBeesten()) {
                if ((int)b.getPositie().get(0) == x && (int) b.getPositie().get(1) == y) {
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
