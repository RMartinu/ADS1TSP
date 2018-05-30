/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author Robert Martinu
 */
public class ReportPane extends Pane{
    Label L;
    public ReportPane()
    {
        L=new Label("...and I've never been here");
        this.getChildren().add(L);
    }
    
    
}
