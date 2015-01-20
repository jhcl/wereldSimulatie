/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Simulatie waarin beesten in een wereld op eilanden rondlopen die planten 
 * en soms elkaar kunnen eten of met elkaar kunnen paren. Eilanden zijn  
 * gescheiden door water en beesten kunnen de oversteek maken door te zwemmen.<br>  
 * Bij elke simulatiestap beweegt een beest naar gelang zijn snelheid en kan 
 * als de gelegenheid zich voordoet eten of paren.
 * @author Lars Ko Tarkan
 */
public class WereldSimulatie extends Application {
    final Wereld model = new Wereld();
    private FXMLDocumentController fdc;
    
    /**
     * Instantiëer zelfgemaakte controller zodat die als observer aan wereldobject
     *  gehangen kan worden.
     * @param stage standaard javafx gui element die de basis vormt van de scene
     * @throws Exception standaard javafx
     */
    @Override
    public void start(Stage stage) throws Exception {
                FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        loader.setControllerFactory(new Callback<Class<?>, Object>() {
            @Override
            public Object call(Class<?> klasse) {
                fdc = new FXMLDocumentController(model);
                return fdc;
            }
        });
        
        Parent root = loader.load();
        model.addObserver(fdc);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("wereld.css").toExternalForm());
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
