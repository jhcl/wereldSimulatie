/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wereldsimulatie;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.shape.Polygon;

/**
 *
 * @author nl08940
 */
public class Poppetje extends Polygon implements Observer{
    
    @Override
    public void update(Observable o, Object arg) {
        if (((Beest)o).getEnergie() <= 0) {
            this.setVisible(false);
        }
        this.translateXProperty().set((Integer)((Beest)o).getPositie().get(0)*5);
        this.translateYProperty().set((Integer)((Beest)o).getPositie().get(1)*5); 
    }
    
    public Poppetje (double... points) {
        super(points);
    }
}
