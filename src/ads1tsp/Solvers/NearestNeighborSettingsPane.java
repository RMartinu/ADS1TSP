/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Robert Martinu
 */
class NearestNeighborSettingsPane extends SettingsPane {
    Label l;
    VBox vLayout;
    
    public NearestNeighborSettingsPane(Solver in) {
       // super(in);
       l=new Label();
       l.setText("I am Justice!");
       vLayout=new VBox(l);
       this.getChildren().add(vLayout);
       
    }
    
}
