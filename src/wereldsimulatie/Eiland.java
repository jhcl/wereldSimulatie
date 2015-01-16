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
    private Random rnd;
    private Wereld ouder;

    public Eiland(ArrayList<Integer> opp, Wereld w) {
        this.ouder = w;
        this.oppervlak = opp;
        this.rnd = new Random();
        this.beesten = new ArrayList<>();
        this.obstakels = new ArrayList<>();
        this.planten = new ArrayList<>();
        this.opruimLijst = new ArrayList<>();
        this.toevoegLijst = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            int willekeurigX = this.rnd.nextInt(this.oppervlak.size());
            if (willekeurigX % 2 != 0) {
                if (willekeurigX != 0) {
                    willekeurigX--;
                } else {
                    willekeurigX++;
                }
            }
            ArrayList<Integer> pos = new ArrayList<>();
            pos.add(this.oppervlak.get(willekeurigX));
            pos.add(this.oppervlak.get(willekeurigX + 1));

            // Snelle manier om aantal objecten naar verhouding te maken
            int temp = this.rnd.nextInt(10);
            if (temp == 0 || temp == 1 || temp == 2 || temp == 3) {
                this.beesten.add(new Carnivoor(pos));
            } else if (temp == 4) {
                this.beesten.add(new Herbivoor(pos));
            } else if (temp == 5) {
                this.beesten.add(new Omnivoor(pos));
            } else if (temp == 6) {
                this.obstakels.add(new Obstakel(pos));
            } else if (temp == 7 || temp == 8 || temp == 9) {
                this.planten.add(new Plant(pos));
            }
        }

    }

    /**
     * getter voor lijst van alle obstakelobjecten van dit eiland
     *
     * @return ArrayLis&lt;Obstakel&gt;
     */
    public ArrayList<Obstakel> getObstakels() {
        return this.obstakels;
    }

    public ArrayList<Integer> getEilandOppervlak() {
        return this.oppervlak;
    }

    /**
     * getter voor lijst van alle Beest-objecten van dit eiland
     *
     * @return ArrayLis&lt;Beest&gt;
     */
    public ArrayList<Beest> getBeesten() {
        return this.beesten;
    }

    /**
     * getter voor lijst van alle Plant-objecten van dit eiland
     *
     * @return ArrayLis&lt;Plant&gt;
     */
    public ArrayList<Plant> getPlanten() {
        return this.planten;
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
        for (Plant p : this.planten) {
            p.groei();
        }

        for (Beest b : this.beesten) {

            // niet te lang stilstaan ?
//            if ((int) b.getRichting().get(0) == 0 && (int) b.getRichting().get(1) == 0) {
//                b.setRichting(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
//            }
            boolean opLand = false;
            boolean doorlopen = true;
            int newX = this.ouder.nieuwePositie(b).get(0);
            int newY = this.ouder.nieuwePositie(b).get(1);

            // is de volgende positie nog land
            for (int i = 0; i < this.oppervlak.size(); i += 2) {
                if (this.oppervlak.get(i) == newX && this.oppervlak.get(i + 1) == newY) {
                    opLand = true;
                    break;
                }
            }
            if (opLand) {
                int stappenTeller = 0;
//                while (stappenTeller < b.getSnelheid() && doorlopen) {
                newX = this.ouder.nieuwePositie(b).get(0);
                newY = this.ouder.nieuwePositie(b).get(1);

                // lopen we tegen een obstakel aan ?
                boolean erStaatEenObstakel = false;
                for (Object o : this.ouder.staatOpPositie(newX, newY)) {
                    if (o instanceof Obstakel) {
                        erStaatEenObstakel = true;
                    }
                }
                if (erStaatEenObstakel) {
                    b.bots();
                    b.kiesAndereRichting();
                    doorlopen = false;
                } else {
                    b.beweeg(newX, newY);
                    if (b.getEnergie() <= 0) {
                        this.opruimLijst.add(b);
                        b.deleteObservers();
                    }
                }
                stappenTeller++;
//                }
                ArrayList<Object> gezelschap = this.ouder.staatOpPositie((int) b.getPositie().get(0), (int) b.getPositie().get(1));
                gezelschap.remove(b);
                if (!gezelschap.isEmpty()) {
                    for (Object o : gezelschap) {
                        if (!gezelschap.isEmpty()) {
                            boolean eetbaar = false;
                            if (b instanceof Carnivoor && o instanceof Beest) {
                                b.eet((Beest) o);
                                b.kiesAndereRichting();
                                eetbaar = true;
                                break;
                            }
                            if (b instanceof Herbivoor && o instanceof Plant) {
                                b.eet((Plant) o);
                                b.kiesAndereRichting();
                                eetbaar = true;
                                break;
                            }
                            if (b instanceof Omnivoor && (o instanceof Beest || o instanceof Plant)) {
                                b.eet(o);
                                b.kiesAndereRichting();
                                eetbaar = true;
                                break;
                            }
                            if (!eetbaar) {
                                if (b instanceof Beest && o instanceof Beest && b.isHitsig() && ((Beest) o).isHitsig()) {
                                    if (o != slaOver) {
                                        slaOver = (Beest) o;
                                        Beest baby = b.paar((Beest) o);
 //                                       System.out.println(baby);
                                        //                                       if (baby != null) {
                                        this.iederZijnsWeegs(baby, b, (Beest) o);
//                                        System.out.println(b.isHitsig() + " + " + ((Beest) o).isHitsig() + " = " + baby.isHitsig());
//                                        System.out.println(b.getClass() + " + " + ((Beest) o).getClass() + " = " + baby.getClass());
//                                        System.out.println(b.hashCode() + " + " + ((Beest) o).hashCode() + " = " + baby.hashCode());
//                                        System.out.println(b.getRichting() + " + " + ((Beest) o).getRichting() + " = " + baby.getRichting());
                                        toevoegLijst.add(baby);
                                        //                                       }
                                    } else {
                                        slaOver = null;
                                    }
                                    break;
                                }

                            }
                        }
                    }
                }
            } else {
                if (b.wilZwemmen()) {
                    b.beweeg(newX, newY);
                    this.ouder.voegZwemmersToe(b);
                    this.opruimLijst.add(b);
                } else {
                    b.kiesAndereRichting();
                }
//                b.setRichting(rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
            }
        }
        this.beesten.addAll(this.toevoegLijst);
        this.beesten.removeAll(this.opruimLijst);
        this.opruimLijst.clear();
    }

    private void iederZijnsWeegs(Beest a, Beest b, Beest c) {
        ArrayList<ArrayList<Integer>> opties = new ArrayList<>();
        int temp;
        opties.add(new ArrayList<Integer>());
        opties.get(0).add(-1);
        opties.get(0).add(-1);
        opties.add(new ArrayList<Integer>());
        opties.get(1).add(-1);
        opties.get(1).add(0);
        opties.add(new ArrayList<Integer>());
        opties.get(2).add(-1);
        opties.get(2).add(1);
        opties.add(new ArrayList<Integer>());
        opties.get(3).add(0);
        opties.get(3).add(-1);
        opties.add(new ArrayList<Integer>());
        opties.get(4).add(0);
        opties.get(4).add(0);
        opties.add(new ArrayList<Integer>());
        opties.get(5).add(0);
        opties.get(5).add(1);
        opties.add(new ArrayList<Integer>());
        opties.get(6).add(1);
        opties.get(6).add(-1);
        opties.add(new ArrayList<Integer>());
        opties.get(7).add(1);
        opties.get(7).add(0);
        opties.add(new ArrayList<Integer>());
        opties.get(8).add(1);
        opties.get(8).add(1);
        temp = rnd.nextInt(opties.size());
        a.setRichting(opties.get(temp).get(0), opties.get(temp).get(1));
        opties.remove(temp);
        temp = rnd.nextInt(opties.size());
        b.setRichting(opties.get(temp).get(0), opties.get(temp).get(1));
        opties.remove(temp);
        temp = rnd.nextInt(opties.size());
        c.setRichting(opties.get(temp).get(0), opties.get(temp).get(1));
    }

}
