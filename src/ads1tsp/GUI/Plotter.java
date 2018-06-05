/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 *
 * @author Robert Martinu
 */
public class Plotter extends Pane{
    PlotterPane plotPane;
    PlotterControl plotControl;ReportPane report;
    BorderPane HLayout;
    Stage myStage;
    
    public Plotter(Stage s)
    {
        plotPane=new PlotterPane();
        plotPane.minHeight(800);
        plotPane.minWidth(600);
//        plotPane.prefHeight(1200);
//        plotPane.prefWidth(1800);
        plotPane.setPrefWidth(750);
        plotPane.setPrefHeight(600);
        myStage=s;
        
        plotControl=new PlotterControl(this);
        
//        plotControl.setMaxWidth(250);
//        plotControl.setMinWidth(250);
//        plotControl.setPrefWidth(300);
//        plotControl.setLayoutX(900);
report=new ReportPane();

        HLayout=new BorderPane();
        
//        HLayout.getChildren().add(plotControl);
//        HLayout.getChildren().add(plotPane);
//        HLayout.getChildren().add(report)
HLayout.setLeft(plotControl);
HLayout.setRight(plotPane);
HLayout.setBottom(report);
       
        
        ;
        this.widthProperty().addListener((obs,a,b)->{plotPane.repaint();});
        
        this.getChildren().add(HLayout);
    }
    
}
