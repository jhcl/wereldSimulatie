/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

/**
 * Controller die met data uit het model de view aanstuurt.
 * @author Lars Ko Tarkan
 */
public class FXMLDocumentController implements Initializable, Observer {
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
     
    private Pane pane = new Pane();
    private List<Polygon> p = new ArrayList<>(); 
    private Random rand = new Random();
    long tikken = 100000000;
    private ModelFacade model;
    private int schaalX, schaalY;

    
    public FXMLDocumentController(ModelFacade model) {
        this.model = model;
    }
    
    /**
     * 
     * @param url standaard javafx
     * @param rb  standaard javafx
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane.setPrefSize(((double)model.getWereldSize().get(0)/model.getWereldSize().get(1))*scroll.getPrefHeight(), scroll.getPrefHeight());    
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);
        scroll.setContent(pane);
       schaalX = (int)scroll.getPrefWidth() / model.getWereldSize().get(0);
 //       schaalY = (int)pane.getPrefHeight() / model.getWereldSize().get(1);
        schaalY = schaalX;
        for (Eiland e : model.getEilanden()) {
            for (int i = 0; i < e.getEilandOppervlak().size() - 1; i +=2) {
                pane.getChildren().add(new Rectangle(schaalX * e.getEilandOppervlak().get(i),schaalY * e.getEilandOppervlak().get(i+1),schaalX,schaalY));
            }
        }
        for (Node r : pane.getChildren()) {
            if (r instanceof Rectangle) {
                ((Rectangle)r).setFill(Color.WHITE);
            }
        }    
        pane.getStyleClass().add("grid");
        slider.setValue(0);
        slider.setMax(100);
        slider.setMin(-100);
        timer.start();
        
    } 
    
    /**
     * Dit is de eventhandler voor een GUI control. 
     * Veranderd de tijd tussen timerevents om simulatie sneller of langzamer 
     * te laten draaien.
     * @param event
     * @param snelheid stel tijd tussen timertikken in in ns
     */
    
    @FXML
    public void veranderSnelheid(javafx.scene.input.MouseEvent event) {
        if (event.getSource() == slider) {
            timer.stop();
            if (slider.getValue() > 0) { 
                tikken = 100000000 - 1000000 * (int)slider.getValue();
            }
            else {
                tikken = 100000*(long)Math.pow(slider.getValue(),2) + 100000000L;
                System.out.println(tikken + " " + slider.getValue());
            }
            timer = new BeestTimer();
            timer.start();
        }        
        
    }
    
    @FXML
    public void saveSim(javafx.scene.input.MouseEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser(); 
        File file = fc.showSaveDialog(null);
        if (file != null) {
            OutputStream outFile = new FileOutputStream(file, false);
            OutputStream buffer = new BufferedOutputStream(outFile);
            try {
                ObjectOutput out = new ObjectOutputStream(buffer);
                out.writeObject(model);
            }
            catch (IOException e) {System.out.println(e.toString());}
            finally { 
                try {
                    outFile.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }        
    
    /**
     *
     * @param event
     */
    @FXML
    public void restoreSim(javafx.scene.input.MouseEvent event) throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);
        if (file != null) {        
            InputStream inFile = new FileInputStream(file);
            InputStream buffer = new BufferedInputStream(inFile);
            try {
                ObjectInput in = new ObjectInputStream(buffer);
                model = (Wereld)in.readObject();
            }
            catch (Exception e) {System.out.println(e.toString());}
            finally { 
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
    }
    
    @FXML
    private void zoom(ScrollEvent event) {
        pane.setScaleX(pane.getScaleX() + event.getDeltaY()/2000);
        pane.setScaleY(pane.getScaleY() + event.getDeltaY()/2000);
        double minX = scroll.getContent().getBoundsInParent().getMinX();
        double minY = scroll.getContent().getBoundsInParent().getMinY();
        double maxX = scroll.getContent().getBoundsInParent().getMaxX();
        double maxY = scroll.getContent().getBoundsInParent().getMaxY();
        scroll.getContent().setTranslateX((scroll.getPrefWidth()/2 - event.getX())/2);
        System.out.println(scroll.getPrefWidth());
        System.out.println(event.getX());
        
        scroll.getContent().setTranslateY((scroll.getPrefHeight()/2 - event.getY())/2);
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

    }
    
    /**
     * voer simulatiestappen buiten timer om uit. maw roep methode aan die timer
     * ook aanroept.
     */
    @FXML
    public void stapDoorSimulatie() {
            model.step();
        
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o==this.model) {
            pane.getChildren().removeAll(p);
            p.clear();
            if (arg instanceof ArrayList<?>) {
                for (Object pt : (ArrayList<Object>)arg) {
                    if (pt instanceof Beest) { 
                        Polygon pol = new Polygon(new double[]{0.0, 0.0, 10.0, 0.0 ,5.0, 5.0});
                        pol.translateXProperty().set((Integer)((Beest)pt).getPositie().get(0)*schaalX);
                        pol.translateYProperty().set((Integer)((Beest)pt).getPositie().get(1)*schaalY);
    //                   pt.addObserver((Observer) pol);
                        if (pt instanceof Carnivoor) {pol.setFill(Color.RED);}
                        else if (pt instanceof Herbivoor) {pol.setFill(Color.BROWN);}
                        else if (pt instanceof Omnivoor) {pol.setFill(Color.YELLOW);}
                        p.add(pol);
//                        pane.getChildren().add(pol);
                    }
                    if (pt instanceof Obstakel) {
                        Polygon pol = new Polygon(new double[]{5.0, 0.0, 10.0, 10.0 ,0.0, 10.0});
                        pol.translateXProperty().set((Integer)((Obstakel)pt).getPositie().get(0)*schaalX);
                        pol.translateYProperty().set((Integer)((Obstakel)pt).getPositie().get(1)*schaalY);  
                        pol.setFill(Color.BLACK);
                        pane.getChildren().add(pol);
                    }
                    if (pt instanceof Plant) {
                        Polygon pol = new Polygon(new double[]{5.0, 0.0, 10.0, 10.0 ,0.0, 10.0});
                        pol.translateXProperty().set((Integer)((Plant)pt).getPositie().get(0)*schaalX);
                        pol.translateYProperty().set((Integer)((Plant)pt).getPositie().get(1)*schaalY);  
                        pol.setFill(Color.GREEN);  
                        pane.getChildren().add(pol);
                    }
                }
                pane.getChildren().addAll(p);
            }
        }
    }
    
//        AnimationTimer timer = new AnimationTimer() {
    BeestTimer timer = new BeestTimer();
    private class BeestTimer extends AnimationTimer {
        private long prevUpdate;
        private long lag;
        
        public void changeSpeed(long verandering) {
            if (lag > verandering) {
                lag *= verandering;
            }
        }

        
        @Override
        public void handle(long now) {
            lag = now - prevUpdate;
            if (lag >= tikken) {
                prevUpdate = now;
                model.step();
            }
        }
        @Override
        public void start() {
        prevUpdate = System.nanoTime();
        super.start();
        }
    };      
}
