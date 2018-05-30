/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Solvers.DummySolver;
import ads1tsp.Solvers.SettingsPane;
import ads1tsp.Solvers.Solver;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.FileIO;
import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Robert Martinu
public class PlotterControl extends  HB
 */
public class PlotterControl extends  VBox{
    PlotterPane output;
    PlotList dataOutput;
    AdjacentList TSPData;
    Solver [] solvers;
    Solver currentSolver;
    SettingsPane SolverSettings;
    ReportPane stats;
    VBox verticalLayout;
    Button Launch;
    
    public PlotterControl(Plotter toPlot)
    {
        output=toPlot.plotPane;
        Label l = new Label("Muffin");
        Launch=new Button("Launch");
        Launch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               System.out.println("Been licked");
               currentSolver.step();
               dataOutput=currentSolver.getPlotList();
               output.setCurrentData(dataOutput);
               Statistics s;
               s=currentSolver.getStatistics();
               
               toPlot.report.L.setText(s.getMessage());
               
               
            }
        });
        verticalLayout=new VBox(l,Launch);
        this.getChildren().add(verticalLayout);
        try {
            TSPData=FileIO.NodeListFromFile(new File("D:\\TSP1.tsp"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlotterControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentSolver=new DummySolver();
        this.SolverSettings=currentSolver.getSettingsPane();
        this.getChildren().add(SolverSettings);
        if(TSPData!=null)
        {currentSolver.addAdjacentList(TSPData);}
        else{System.err.println("TSP empty");}
        dataOutput=currentSolver.getPlotList();
        dataOutput.evalBounds(toPlot);
        
        output.setCurrentData(dataOutput);
        
    }
    
    
}
