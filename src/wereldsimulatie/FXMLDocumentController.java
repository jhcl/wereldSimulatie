/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Controller die met data uit het model de view aanstuurt.
 * @author Lars Ko Tarkan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    /**
     * 
     * @param event Buttonclick
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    /**
     * 
     * @param url standaard javafx
     * @param rb  standaard javafx
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    /**
     * Dit is de eventhandler voor een GUI control. 
     * Veranderd de tijd tussen timerevents om simulatie sneller of langzamer 
     * te laten draaien.
     * @param snelheid stel tijd tussen timertikken in in ns
     */
    public void veranderSnelheid(long snelheid) {
        
    }
    
    /**
     * start timer om simulatie te starten
     */
    public void startSimulatie() {
        
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

    
}
