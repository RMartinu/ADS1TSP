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
public class DummySettingsPane extends SettingsPane{
    Label l1,l2;
    DummySolver mySolver;
    VBox VLayout;
    public DummySettingsPane(DummySolver in)
    {
        super(in);
        l1=new Label("Just a Standin");
        l2=new Label("Nothin' to see");
        VLayout=new VBox(l1,l2);
        this.getChildren().add(VLayout);
        
        this.mySolver=in;
    }
    
}
