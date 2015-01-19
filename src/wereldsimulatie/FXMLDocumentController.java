/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * Controller die met data uit het model de view aanstuurt.
 *
 * @author Lars Ko Tarkan
 */
public class FXMLDocumentController implements Initializable, Observer {

    // link to fxml components
    @FXML
    private ScrollPane scroll;
    @FXML
    private Label labelSnelheid;
    @FXML
    private Label labelZoom;
    @FXML
    private Button save;
    @FXML
    private Button restore;
    @FXML
    private Button pauzeer;
    @FXML
    private Button stap;
    @FXML
    private Button start;
    @FXML
    private Slider slider;
    @FXML
    private GridPane grid_totaal;
    @FXML
    private FlowPane flow;
    @FXML
    private ListView listview;
    @FXML
    private ListView listviewAantal;
    @FXML
    private ListView listviewTotaal;
    @FXML
    private ListView listviewBeesten;
    @FXML
    private ListView listviewBeestenAantal;
    @FXML
    private ListView listviewBeestenEnergie;

    private final Pane pane = new Pane();
    private final List<Polygon> p = new ArrayList<>();
    private Random rand = new Random();
    long tikken = 100000000;
    private ModelFacade model;
    private int schaalX, schaalY;
    private long aantalStappen;
    Text aantalSimulatieStappen = new Text();
    //        AnimationTimer timer = new AnimationTimer() {
    BeestTimer timer;
    BufferedWriter buffSim;

    /**
     *
     * @param model
     */
    public FXMLDocumentController(ModelFacade model) {
        this.model = model;
    }

    /**
     * Initialiseer GUI, zet rand- en startwaarden van zijn componemten, start
     * timer en teken eilanden
     *
     * @param url standaard javafx
     * @param rb standaard javafx
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            buffSim = new BufferedWriter(new FileWriter("outSim.txt"));
        } catch (IOException e) {

        }
        timer = new BeestTimer();
        aantalStappen = 0;
        pane.setPrefSize(((double) model.getWereldSize().get(0) / model.getWereldSize().get(1)) * scroll.getPrefHeight(), scroll.getPrefHeight());
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setContent(pane);
        schaalX = (int) scroll.getPrefWidth() / model.getWereldSize().get(0);
        //       schaalY = (int)pane.getPrefHeight() / model.getWereldSize().get(1);
        schaalY = schaalX;
        for (Eiland e : model.getEilanden()) {
            for (int i = 0; i < e.getEilandOppervlak().size() - 1; i += 2) {
                pane.getChildren().add(new Rectangle(schaalX * e.getEilandOppervlak().get(i), schaalY * e.getEilandOppervlak().get(i + 1), schaalX, schaalY));
            }
        }
        for (Node r : pane.getChildren()) {
            if (r instanceof Rectangle) {
                ((Rectangle) r).setFill(Color.WHITE);
            }
        }
        pane.getStyleClass().add("grid");
        grid_totaal.add(aantalSimulatieStappen, 0, 0);
        aantalSimulatieStappen.setTranslateX(10);
        slider.setValue(0);
        slider.setMax(100);
        slider.setMin(-100);
        listview.getItems().add("Beest: ");
        listview.getItems().add("Plant: ");
        listview.getItems().add("Obstakel: ");
        listviewBeesten.getItems().add("Carnivoor: ");
        listviewBeesten.getItems().add("Omnivoor: ");
        listviewBeesten.getItems().add("Herbivoor: ");
        timer.start();
        start.setDisable(true);
        pauzeer.setDisable(false);
        stap.setDisable(true);
    }

    /**
     * Dit is de eventhandler voor een GUI control. Veranderd de tijd tussen
     * timerevents om simulatie sneller of langzamer te laten draaien.<br>
     * Tijd voor stuurklok neemt bij slider naar rechts (positief) linear af tot 1 
     * tik per seconde waardoor snelheid toeneemt als (1/kloksnelheid). <br>
     * Bij negatieve slider (naar links) neemt de tikwaarde kwadratisch toe. Ook
     * hier geldt de 1/kloksnelheid relatie tussen tijd en snelheid. 
     * @param event
     */
    @FXML
    public void veranderSnelheid(javafx.scene.input.MouseEvent event) {
        if (event.getSource() == slider) {
            timer.stop();
            if (slider.getValue() > 0) {

                tikken = 100000000 - 1000000 * (int) slider.getValue();
            } else {

                tikken = 100000 * (long) Math.pow(slider.getValue(), 2) + 100000000L;
            }
            timer = new BeestTimer();
            timer.start();
        }

    }
    
    /**
     * Schrijft het model weg naar zelf te kiezen file
     * @param event buttonklik die wegschrijf actie initieert.
     * @throws FileNotFoundException 
     */
    @FXML
    public void saveSim(javafx.scene.input.MouseEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File file = fc.showSaveDialog(null);
        if (file != null) {
            FileOutputStream outFile = new FileOutputStream(file, false);
            try {
                ObjectOutputStream out = new ObjectOutputStream(outFile);
                out.writeObject((ModelFacade) model);
            } catch (IOException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    outFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Schrijft het model terug van file in de simulatie
     * @param event buttonklik die restore actie initieert.
     * @throws java.io.FileNotFoundException
     */
    @FXML
    public void restoreSim(javafx.scene.input.MouseEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if (file != null) {
            FileInputStream inFile = new FileInputStream(file);
            try {
                model.getEilanden().clear();
                ObjectInputStream in = new ObjectInputStream(inFile);
                model = (ModelFacade) in.readObject();
                ArrayList<Polygon> opruimLijst = new ArrayList<>();
                for (Object pi : pane.getChildren()) {
                    if (pi instanceof Poppetje) {
                        opruimLijst.add((Poppetje) pi);
                    }
                }
                pane.getChildren().removeAll(opruimLijst);
                opruimLijst.clear();
                ((Wereld) model).addObserver(this);
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                try {
                    inFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * start timer om simulatie te starten
     */
    public void startSim() {
        timer.start();
        pauzeer.setDisable(false);
        start.setDisable(true);
        stap.setDisable(true);
    }

    /**
     * Zoom (in en uit) met scrollwheel van de muis.
     * @param event 
     */
    @FXML
    private void zoom(ScrollEvent event) {
        pane.setScaleX(pane.getScaleX() + event.getDeltaY() / 2000);
        pane.setScaleY(pane.getScaleY() + event.getDeltaY() / 2000);
        scroll.getContent().setTranslateX((scroll.getPrefWidth() / 2 - event.getX()) / 2);

        scroll.getContent().setTranslateY((scroll.getPrefHeight() / 2 - event.getY()) / 2);
        if (scroll.getContent().getBoundsInParent().getWidth() <= scroll.getContent().getBoundsInLocal().getWidth()) {
            scroll.getContent().setTranslateX(0);
            scroll.getContent().setTranslateY(0);
        }
    }

    /**
     * stop timer om simulatie te pauzeren
     */
    @FXML
    public void pauzeerSim() {
        timer.stop();
        start.setDisable(false);
        pauzeer.setDisable(true);
        stap.setDisable(false);

    }

    /**
     * voer simulatiestappen buiten timer om uit. Maw roep methode aan die timer
     * ook aanroept maar dan andmatig.
     */
    @FXML
    public void stapDoorSimulatie() {
        model.step();
        aantalStappen++;
    }

    /**
     * Het wereldobject stuurt als het een update heeft gehad een lijst van 
     * objecten (planten, obstakels en beesten). De objecten krijgen een visueel 
     * object toegekend (polygon) dat verder de visualisatie van het wereldobject 
     * voor zijn rekening neemt. Als er al een polygon aan een wereldobject toegekend
     * is wordt het overgeslagen.
     * <p>
     * Simulatiestatistieken over aantal en energie van wereldobjecten worden 
     * verzameld, getoond in de GUI en weggeschreven naar file outSim.txt
     * @param o wereld object 
     * @param arg ArrayList van objecten die gevisualiserd moeten worden
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o == this.model) {
            pane.getChildren().removeAll(p);
            p.clear();
            if (arg instanceof ArrayList<?>) {

                int aantalBeesten = 0;
                int aantalPlanten = 0;
                int aantalObstakels = 0;
                int aantalCarnivoren = 0;
                int aantalHerbivoren = 0;
                int aantalOmnivoren = 0;
                int energieCarnivoren = 0;
                int energieHerbivoren = 0;
                int energieOmnivoren = 0;
                int energiePlanten = 0;
                for (Object pt : (ArrayList<Object>) arg) {

                    if (pt instanceof Beest) {
                        aantalBeesten++;
                        if (pt instanceof Carnivoor) {
                            aantalCarnivoren++;
                            energieCarnivoren += ((Beest) pt).getEnergie();
                        } else if (pt instanceof Herbivoor) {
                            aantalHerbivoren++;
                            energieHerbivoren += ((Beest) pt).getEnergie();
                        } else if (pt instanceof Omnivoor) {
                            aantalOmnivoren++;
                            energieOmnivoren += ((Beest) pt).getEnergie();
                        }
                        if (((Beest) pt).countObservers() != 1) {
                            Poppetje polp = new Poppetje(new double[]{0.0, 0.0, 10.0, 0.0, 5.0, 5.0});
                            ((Beest) pt).addObserver((Observer) polp);
                            polp.translateXProperty().set((Integer) ((Beest) pt).getPositie().get(0) * schaalX);
                            polp.translateYProperty().set((Integer) ((Beest) pt).getPositie().get(1) * schaalY);
                            if (pt instanceof Carnivoor) {
                                polp.setFill(Color.RED);
                            } else if (pt instanceof Herbivoor) {
                                polp.setFill(Color.BROWN);
                            } else if (pt instanceof Omnivoor) {
                                polp.setFill(Color.YELLOW);
                            }
                            pane.getChildren().add(polp);
                        }

//                        Polygon pol = new Polygon(new double[]{0.0, 0.0, 10.0, 0.0 ,5.0, 5.0});
//                        pol.translateXProperty().set((Integer)((Beest)pt).getPositie().get(0)*schaalX);
//                        pol.translateYProperty().set((Integer)((Beest)pt).getPositie().get(1)*schaalY);
//                        if (pt instanceof Carnivoor) {pol.setFill(Color.RED);}
//                        else if (pt instanceof Herbivoor) {pol.setFill(Color.BROWN);}
//                        else if (pt instanceof Omnivoor) {pol.setFill(Color.YELLOW);}
//                        p.add(pol);
                    }

                    if (pt instanceof Obstakel) {
                        aantalObstakels++;
//                        Polygon pol = new Polygon(new double[]{5.0, 0.0, 10.0, 10.0 ,0.0, 10.0});
//                        pol.translateXProperty().set((Integer)((Obstakel)pt).getPositie().get(0)*schaalX);
//                        pol.translateYProperty().set((Integer)((Obstakel)pt).getPositie().get(1)*schaalY);  
//                        pol.setFill(Color.BLACK);
//                        p.add(pol);
//                    }

                        if (((Obstakel) pt).countObservers() != 1) {

                            Poppetje polpp = new Poppetje(new double[]{5.0, 0.0, 10.0, 10.0, 0.0, 10.0});
                            ((Obstakel) pt).addObserver((Observer) polpp);
                            polpp.translateXProperty().set((Integer) ((Obstakel) pt).getPositie().get(0) * schaalX);
                            polpp.translateYProperty().set((Integer) ((Obstakel) pt).getPositie().get(1) * schaalY - 5);
                            polpp.setFill(Color.BLACK);
                            pane.getChildren().add(polpp);
                        }
                    }

                    if (pt instanceof Plant) {
                        aantalPlanten++;
                        energiePlanten += ((Plant) pt).getEnergie();
                        if (((Plant) pt).countObservers() != 1) {
                            Poppetje polpp = new Poppetje(new double[]{5.0, 0.0, 10.0, 10.0, 0.0, 10.0});
                            ((Plant) pt).addObserver((Observer) polpp);
                            polpp.translateXProperty().set((Integer) ((Plant) pt).getPositie().get(0) * schaalX);
                            polpp.translateYProperty().set((Integer) ((Plant) pt).getPositie().get(1) * schaalY - 5);
                            polpp.setFill(Color.GREEN);
                            pane.getChildren().add(polpp);
                        }

//                        Polygon pol = new Polygon(new double[]{5.0, 0.0, 10.0, 10.0 ,0.0, 10.0});
//                        pol.translateXProperty().set((Integer)((Plant)pt).getPositie().get(0)*schaalX);
//                        pol.translateYProperty().set((Integer)((Plant)pt).getPositie().get(1)*schaalY);  
//                        pol.setFill(Color.GREEN); 
//                        p.add(pol);
                    }
                }
                int totaalEnergieBeesten = energieCarnivoren + energieOmnivoren + energieHerbivoren;
                pane.getChildren().addAll(p);
                listviewAantal.getItems().clear();
                listviewTotaal.getItems().clear();
                listviewBeestenAantal.getItems().clear();
                listviewBeestenEnergie.getItems().clear();
                listviewAantal.getItems().add(String.valueOf(aantalBeesten));
                listviewAantal.getItems().add(String.valueOf(aantalPlanten));
                listviewAantal.getItems().add(String.valueOf(aantalObstakels));
                listviewAantal.getItems().add(String.valueOf(aantalObstakels));
                listviewTotaal.getItems().add(String.valueOf(totaalEnergieBeesten));
                listviewTotaal.getItems().add(String.valueOf(energiePlanten));
                listviewTotaal.getItems().add("");

                listviewBeestenAantal.getItems().add(String.valueOf(aantalCarnivoren));
                listviewBeestenAantal.getItems().add(String.valueOf(aantalOmnivoren));
                listviewBeestenAantal.getItems().add(String.valueOf(aantalHerbivoren));
                listviewBeestenEnergie.getItems().add(String.valueOf(energieCarnivoren));
                listviewBeestenEnergie.getItems().add(String.valueOf(energieOmnivoren));
                listviewBeestenEnergie.getItems().add(String.valueOf(energieHerbivoren));
                aantalSimulatieStappen.setText(String.valueOf(aantalStappen));
                /**
                 * Scrijf simulatiegegevens weg als csv file met de naam
                 * outSim.txt in de project root folder met de velden in de
                 * volgende volgorde: #beesten, #carnivoren, #omnivoren,
                 * #herbivoren, #planten, #obstakels, cumulatieve energie
                 * beesten, cumulatieve energie carnivoren, cumulatieve energie
                 * omnivoren, cumulatieve energie herbivoren, cumulatieve
                 * energie planten
                 */
                if (aantalBeesten != 0) {
                    try {
                        String writeString = aantalBeesten + "," + aantalCarnivoren + ","
                                + aantalOmnivoren + "," + aantalHerbivoren + "," + aantalPlanten
                                + "," + aantalObstakels + "," + totaalEnergieBeesten + ","
                                + energieCarnivoren + "," + energieOmnivoren + "," + energieHerbivoren
                                + "," + energiePlanten;
                        buffSim.write(writeString);
                        buffSim.write("\r\n");
                        buffSim.flush();
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    /**
     * Timer die de simulatie aanstuurt. 
     */
    private class BeestTimer extends AnimationTimer {

        private long prevUpdate;
        private long lag;

        /**
         * verplichte AnimationTimer methode
         * @param now current time in nanoseconden
         */
        @Override
        public void handle(long now) {
            lag = now - prevUpdate;
            if (lag >= tikken) {
                prevUpdate = now;
                model.step();
                aantalStappen++;
            }
        }
        
        /**
         * Standaard AnimationTimer methode
         */
        @Override
        public void start() {
            prevUpdate = System.nanoTime();
            super.start();
        }
    };
}
