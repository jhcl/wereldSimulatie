/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Lars Ko Tarkan
 * 
 * Simulatie waarin beesten in een wereld op eilanden rondlopen die planten 
 * en soms elkaar kunnen eten of met elkaar kunnen paren. Eilanden zijn  
 * gescheiden door water en beesten kunnen de oversteek maken door te zwemmen.<br>  
 * Bij elke simulatiestap beweegt een beest naar gelang zijn snelheid en kan 
 * als de gelegenheid zich voordoet eten of paren.
 */
public class WereldSimulatie extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
