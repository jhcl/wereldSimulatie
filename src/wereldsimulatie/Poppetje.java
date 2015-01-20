/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Poppetje extends Polygon implements Observer
 * @author @author Lars Ko Tarkan
 */
public class Poppetje extends Polygon implements Observer{
    
    /**
     * Als het geobserveerde object een signaal geeft moet in geval van beest 
     * de postie in de gui gezet worden en in geval van plant de kleur die aangeeft
     * of de plant dormant is of niet.
     * @param o Beest of Plant object  dat geobserveerd wordt
     * @param arg blijft leeg
     */
    @Override
    public void update(Observable o, Object arg) {

        if (o instanceof Beest) {
            if (((Beest)o).getEnergie() <= 0) {
                this.setVisible(false);
            }
            this.translateXProperty().set((Integer)((Beest)o).getPositie().get(0)*5);
            this.translateYProperty().set((Integer)((Beest)o).getPositie().get(1)*5); 
        }
        
        if (o instanceof Plant) {
            if (((Plant)o).getEnergie() <= 0) {
                this.fillProperty().set(Color.CYAN);   
            }
            else { this.fillProperty().set(Color.GREEN); }
        } 
        
    }
    
    /**
     * Kopie van polygon klasse
     * @param points reeks punten die de vorm van dit object definiëren
     */
    public Poppetje (double... points) {
        super(points);
    }
}
