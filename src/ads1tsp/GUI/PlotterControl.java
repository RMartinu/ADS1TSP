/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Solvers.DummySolver;
import ads1tsp.Solvers.FullEnumeration;
import ads1tsp.Solvers.NearestNeighbor;
import ads1tsp.Solvers.SettingsPane;
import ads1tsp.Solvers.Solver;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.FileIO;
import ads1tsp.Utils.Statistics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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

    public PlotterControl(Plotter toPlot) {
        output = toPlot.plotPane;
        Label l = new Label("Muffin");
        SingleStep = new Button("Single Step");

        SingleStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //System.out.println("Been licked");
                currentSolver.step();
                dataOutput = currentSolver.getPlotList();
                output.setCurrentData(dataOutput);
                Statistics s;
                s = currentSolver.getStatistics();

                toPlot.report.L.setText(s.getMessage());

            }
        });

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
        currentSolver=new FullEnumeration();
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

}
