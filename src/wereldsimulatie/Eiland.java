/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Leefgebied
 *
 * @author Lars Ko Tarkan
 */
public class Eiland implements Serializable {

    private ArrayList<Beest> beesten;
    private ArrayList<Obstakel> obstakels;
    private ArrayList<Plant> planten;
    private ArrayList<Integer> oppervlak;
    private ArrayList<Beest> opruimLijst;
    private ArrayList<Beest> toevoegLijst;
    Wereld ouder;

    public Eiland(ArrayList<Integer> opp, Wereld w) {
        ouder = w;
        oppervlak = opp;
        Random rnd = new Random();
        beesten = new ArrayList<>();
        obstakels = new ArrayList<>();
        planten = new ArrayList<>();
        opruimLijst = new ArrayList<>();
        toevoegLijst = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int willekeurigX = rnd.nextInt(oppervlak.size());
            if (willekeurigX % 2 != 0) {
                if (willekeurigX != 0) {
                    willekeurigX--;
                } else {
                    willekeurigX++;
                }
            }
            ArrayList<Integer> pos = new ArrayList<>();
            pos.add(oppervlak.get(willekeurigX));
            pos.add(oppervlak.get(willekeurigX + 1));

            // Snelle manier om aantal objecten naar verhouding te maken
            int temp = rnd.nextInt(10);
            if (temp == 0 || temp == 1 || temp == 2 || temp == 3) {
                beesten.add(new Carnivoor(pos));
            } else if (temp == 4) {
                beesten.add(new Omnivoor(pos));
            } else if (temp == 5) {
                beesten.add(new Herbivoor(pos));
            } else if (temp == 6) {
                obstakels.add(new Obstakel(pos));
            } else if (temp == 7 || temp == 8 || temp == 9) {
                planten.add(new Plant(pos));
            }
        }

    }

    /**
     * getter voor lijst van alle obstakelobjecten van dit eiland
     *
     * @return ArrayLis&lt;Obstakel&gt;
     */
    public ArrayList<Obstakel> getObstakels() {
        return obstakels;
    }

    public ArrayList<Integer> getEilandOppervlak() {
        return oppervlak;
    }

    /**
     * getter voor lijst van alle Beest-objecten van dit eiland
     *
     * @return ArrayLis&lt;Beest&gt;
     */
    public ArrayList<Beest> getBeesten() {
        return beesten;
    }

    /**
     * getter voor lijst van alle Plant-objecten van dit eiland
     *
     * @return ArrayLis&lt;Plant&gt;
     */
    public ArrayList<Plant> getPlanten() {
        return planten;
    }

    /**
     * Eiland klasse coordineert objecten die zich op het eiland bevinden, deze
     * methode wordt 1x per simulatiestap uitgevoerd.<br>
     * - methode roept beweeg() aan voor beesten en groei() voor planten<br>
     * - als iets eetbaars op dezelfde positie als het beest staat gaan ze eten
     * gelang de honger (maximaal tot energieniveau gelijk is aan stamina.<br>
     * - als beesten op dezelfde positie staan gaan ze paren (afh van
     * hitsigheid) of eten<br>
     * - bij contentie tussen eten en paren (mogelijk in geval van carnivoren)
     * wordt er gepaard indien beide besten hitsig zijn, anders eet het beest
     * dat op dat moment verwerkt wordt het andere beest.<br>
     * - als paden van beesten elkaar kruisen zonder dat ze na een simulatiestap
     * op dezelfde positie staan gebeurd er niets<br>
     * - aan water wordt bij &lt;40% energie gezwommen, anders blijven ze op het
     * eiland<br>
     * - het energieverlies en snelheid voor het zwemmen is hetzelfde als voor
     * bewegen op land<br>
     * - Tijdens zwemmen wordt niet gepaard of gegeten<br>
     * - Botsen op een obstakel halveert energie van het beest en het blijft er
     * vervolgens voor stilstaan, er wordt vervolgens een niuwe richting
     * gekozen<br>
     *
     * @see Plant#groei
     * @see wereldsimulatie.Beest#beweeg
     * @see wereldsimulatie.Beest#paar
     * @see wereldsimulatie.Beest#eet
     * @see wereldsimulatie.Beest#paar
     */
    public void stapDoorSimulatie() {
        Random rnd = new Random();
        Beest slaOver = null;
        for (Beest b : beesten) {

            // niet te lang stilstaan ?
            if ((int) b.getRichting().get(0) == 0 && (int) b.getRichting().get(1) == 0) {
                b.setRichting(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
            }
            boolean opLand = false;
            boolean doorlopen = true;
            int newX = ouder.nieuwePositie(b).get(0);
            int newY = ouder.nieuwePositie(b).get(1);

            // is de volgende positie nog land
            for (int i = 0; i < oppervlak.size(); i += 2) {
                if (oppervlak.get(i) == newX && oppervlak.get(i + 1) == newY) {
                    opLand = true;
                    break;
                }
            }
            if (opLand) {
                int stappenTeller = 0;
                while (stappenTeller < b.getSnelheid() && doorlopen) {
                    newX = ouder.nieuwePositie(b).get(0);
                    newY = ouder.nieuwePositie(b).get(1);

                // lopen we tegen een obstakel aan ?
                boolean erStaatEenObstakel = false;
                for (Object o : ouder.staatOpPositie(newX, newY)) {
                    if (o instanceof Obstakel) { erStaatEenObstakel = true; }
                }                     
                    if (erStaatEenObstakel) {
                        b.bots();
                        b.kiesAndereRichting();
                        doorlopen = false;
                    } else {
                        b.beweeg(newX, newY);
                        if (b.getEnergie() <= 0) {
                            opruimLijst.add(b);
                            b.deleteObservers();
                        }
                    }
                    stappenTeller++;
                }
                ArrayList<Object> gezelschap = ouder.staatOpPositie((int) b.getPositie().get(0), (int) b.getPositie().get(1));
                gezelschap.remove(b);
                if (gezelschap != null) {
                    for (Object o : gezelschap) {
                        if (!gezelschap.isEmpty()) {
                            boolean eetbaar = false;
                            if (b instanceof Carnivoor && o instanceof Beest) {
                                b.eet((Beest) o);
                                eetbaar = true;
                                break;
                            }
                            if (b instanceof Herbivoor && o instanceof Plant) {
                                b.eet((Plant)o);
                                eetbaar = true;
                                break;
                            }
                            if (b instanceof Omnivoor && (o instanceof Beest || o instanceof Plant)) {
                                b.eet(o);
                                eetbaar = true;
                                break;
                            }
                            if (!eetbaar) {

                                if (b instanceof Beest && o instanceof Beest && b.isHitsig() && ((Beest)o).isHitsig()) {
                                    if (o != slaOver) {
                                        slaOver = (Beest)o;
                                        Beest baby = b.paar((Beest)o);
                                        if (baby != null) {
                                            if (baby.getRichting().equals(((Beest)o).getRichting())) {
                                                baby.kiesAndereRichting();
                                            }
                                            if (baby.getRichting().equals(b.getRichting())) {
                                                baby.kiesAndereRichting();
                                            }
                                            if (b.getRichting().equals(((Beest)o).getRichting()))
//                                            System.out.println(b.hashCode() + " + " + ((Beest)o).hashCode() + " = " + baby.hashCode());
//                                            System.out.println(b.getRichting() + " + " + ((Beest)o).getRichting() + " = " + baby.getRichting());
                                            toevoegLijst.add(baby);
                                        }
                                    }
                                    else { slaOver = null; }
                                    break;
                                }
                                
                            }
                        }
                    }
                }
            } else {
                if (b.wilZwemmen()) {
                    b.beweeg(newX, newY);
                    ouder.voegZwemmersToe(b);
                    opruimLijst.add(b);
                } else {
                    b.kiesAndereRichting();
                }
//                b.setRichting(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
            }
        }
        beesten.addAll(toevoegLijst);
        beesten.removeAll(opruimLijst);
        opruimLijst.clear();
    }

}
