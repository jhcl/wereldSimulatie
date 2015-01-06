/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * Controller die met data uit het model de view aanstuurt.
 * @author Lars Ko Tarkan
 */
public class FXMLDocumentController implements Initializable, Observer {
    @FXML
    private Pane grid;
    @FXML
    private Slider slider;
    @FXML
    private Slider slider2;
    final Button b = new Button();
    List<Polygon> p = new ArrayList<>();
    Random rand = new Random();
    
    private final ModelFacade model;
    @FXML
    private GridPane grid_totaal;
    
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
        for (int i = 5; i< 20; i++) {
            for (int j = 5; j < 50 ; j++) {
                grid.getChildren().add(new Rectangle(50+(2*i),50+(2*j),2,2));
            }
        }
        for (Node r : grid.getChildren()) {
            if (r instanceof Rectangle) {
                ((Rectangle)r).setFill(Color.RED);
            }
        }        
 //       polygon = new Polygon();
 //       polygon.getPoints().addAll(new Double[]{0.0, 0.0, 10.0, 10.0 ,20.0, 10.0, 30.0, 10.0, 40.0, 20.0 ,50.0, 0.0, 50.0, 20.0, 30.0, 50.0 ,0.0, 20.0});
 //       grid.getChildren().add(polygon);
        grid.getStyleClass().add("grid");
        slider.setMax(Math.PI);
        slider2.setMax(10);
        slider2.setMin(1);
        timer.start();
        List<Double> d = new ArrayList<>();
        Rectangle rect1 = new Rectangle(100,400,10,10);
        grid.getChildren().add(rect1);
        System.out.println(grid.getWidth()+ " / " + grid.getHeight());    } 
    
    /**
     * Dit is de eventhandler voor een GUI control. 
     * Veranderd de tijd tussen timerevents om simulatie sneller of langzamer 
     * te laten draaien.
     * @param snelheid stel tijd tussen timertikken in in ns
     */
    
    @FXML
    public void veranderSnelheid(javafx.scene.input.MouseEvent event) {
        if (event.getSource() == slider) {
        }        
        
    }
    
    /**
     * start timer om simulatie te starten
     */
    public void startSimulatie() {
        
    }
    
    @FXML
    private void zoom(javafx.scene.input.MouseEvent event) {
       if (event.getSource() == slider2) {
            grid.setScaleX(slider2.getValue());
            grid.setScaleY(slider2.getValue());
        }
    }    
    /**
     * stop timer om simulatie te pauzeren
     */
    public void pauzeerSimulatie() {
        
    }
    
    /**
     * voer simulatiestappen buiten timer om uit. maw roep methode aan die timer
     * ook aanroept.
     */
    public void stapDoorSimulatie() {
        
    }
    
    /**
     * sla simulatiedata (wereldobject recursief naar beneden) op op file
     */
    public void slaSimulatieOp() {
        
    }
    
    /**
     * laad simulatie van file
     */
    public void laadSimulatie() {
        
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o==this.model) {
//            polygon.setTranslateX((polygon.getTranslateX()+grid.getWidth()-51)%(grid.getWidth()-50));
//            polygon.setTranslateY((polygon.getTranslateY()+grid.getHeight()-51)%(grid.getHeight()-50));
            List<Polygon> ptemp = new ArrayList<Polygon>();
            p.clear();
            if (arg instanceof ArrayList<?>) {
            for (Beest pt : (ArrayList<Beest>)arg) {
                pt.addObserver(this);
//                System.out.println(pt.countObservers());
//                pt.getPoints().addAll(new Double[]{0.0, 0.0, 10.0, 10.0 ,0.0, 10.0});
  //              grid.add(pt,0,0);
  //              p.add(pt);                
            }
            }
//                Polygon pa = new Polygon();
           
            for (Polygon pl : p) {
                pl.translateXProperty().setValue((pl.translateXProperty().getValue()-(2*rand.nextDouble())+1.3)%(grid.getWidth()-10.0));
                pl.translateYProperty().setValue((pl.translateYProperty().getValue()-(2*rand.nextDouble())+1.5)%(grid.getHeight()-10.0));
            }
        }
    }
    
        AnimationTimer timer = new AnimationTimer() {
            
        private long prevUpdate;
        private long lag;

        @Override
        public void handle(long now) {
            lag = now - prevUpdate;
            if (lag >= 1000000) {
                prevUpdate = now;
            }
        }
        @Override
        public void start() {
        prevUpdate = System.nanoTime();
        super.start();
        }
    };      
}
