/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Solvers.AntColony;
import ads1tsp.Solvers.DummySolver;
import ads1tsp.Solvers.FullEnumeration;
import ads1tsp.Solvers.NearestNeighbor;
import ads1tsp.Solvers.SettingsPane;
import ads1tsp.Solvers.Solver;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.FileIO;
import ads1tsp.Solvers.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author Robert Martinu public class PlotterControl extends HB
 */
public class PlotterControl extends VBox implements Updateable {

    PlotterPane output;
    PlotList dataOutput;
    AdjacentList TSPData;
    Solver[] solvers;
    Solver currentSolver;
    SettingsPane SolverSettings;
    ReportPane stats;
    VBox verticalLayout;
    Button SingleStep, reset;
    Button Start, Stop;
    EventHandler<ActionEvent> tick;
    RadioButton dummy, FullEunm,NN, Ant;
    ToggleGroup solverSelect;
    

    public PlotterControl(Plotter toPlot) {
        output = toPlot.plotPane;
        Label l = new Label("Muffin");
        SingleStep = new Button("Single Step");
        
        solverSelect=new ToggleGroup();
        dummy=new RadioButton("Dummysolver");
        dummy.setToggleGroup(solverSelect);
        
        FullEunm=new RadioButton("Full Enum");
        FullEunm.setToggleGroup(solverSelect);
        NN=new RadioButton("Nearest\nNeighbor");
        NN.setToggleGroup(solverSelect);
        Ant=new RadioButton("Ant Colony");
        Ant.setToggleGroup(solverSelect);
        
       

        tick=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //System.out.println("Been licked");
                currentSolver.step();
                dataOutput = currentSolver.getPlotList();
                output.setCurrentData(dataOutput);
                Statistics s;
                s = currentSolver.getStatistics();

                if(s!=null)
                toPlot.report.L.setText(s.getMessage());
                else
                {System.err.println("no stats");}

            }
        };
        SingleStep.setOnAction(tick);

        reset = new Button("reset");

        Start = new Button("Start");

        Stop = new Button("Stop");

        verticalLayout = new VBox(l, SingleStep,reset, Start, Stop);
        this.getChildren().add(verticalLayout);
        try {
            TSPData = FileIO.NodeListFromFile(new File("D:\\TSP1.tsp"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlotterControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //currentSolver=new DummySolver();
        //currentSolver = new NearestNeighbor();
       currentSolver=new AntColony();
        
        //currentSolver=new FullEnumeration();
        if (TSPData != null) {
            currentSolver.addAdjacentList(TSPData);
        } else {
            System.err.println("TSP empty");
        }

        currentSolver.setListener(this);
        this.SolverSettings = currentSolver.getSettingsPane();
        this.setSolver();
        this.getChildren().add(SolverSettings);

        dataOutput = currentSolver.getPlotList();
        
        dataOutput.evalBounds(toPlot);

        output.setCurrentData(dataOutput);

    }

    void triggerUpdate() {
        System.out.println("I'm triggered");
    }

    private void setSolver() {
        output.setListener(currentSolver);
    }

    @Override
    public void Notify() {
        System.out.println("I am the god of a new world");
        dataOutput = currentSolver.getPlotList();
        output.setCurrentData(dataOutput);
    }

    @Override
    public void setListener(Updateable that) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
