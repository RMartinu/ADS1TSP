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
import ads1tsp.Solvers.kOpt;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    Button SingleStep, Load;
    Button Start, Stop;
    EventHandler<ActionEvent> tick;
    RadioButton kOpt, FENum,NN, Ant;
    ToggleGroup solverSelect;
    Plotter thePlotter;
    
    FileChooser fc;
    

    public PlotterControl(Plotter toPlot) {
        thePlotter=toPlot;
        output = toPlot.plotPane;
        Label l = new Label("ALG1TSP");
        SingleStep = new Button("Single Step");
        
        solverSelect=new ToggleGroup();
        kOpt=new RadioButton("kopt");
        FENum=new RadioButton("Full Enum");
        NN=new RadioButton("Nearest");
        

        
        Button FullEnum=new Button("Full Enum");
        FullEnum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentSolver=new FullEnumeration();
                prepareSolver();
                triggerSolver();
            }
        });
        Button NearestN=new Button ("NearestN");
        NearestN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentSolver=new NearestNeighbor();
                prepareSolver();
                triggerSolver();
            }
        });
        Button AntColony=new Button("Ants");
        AntColony.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.out.print("ants\n");
               currentSolver=new AntColony();
               prepareSolver();
               triggerSolver();
            }
        });
        Button kOpt=new Button("kOpt");
        kOpt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentSolver=new kOpt();
                prepareSolver();
                triggerSolver();
            }
        });
        
       

        tick=new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //System.out.println("Been licked");
               triggerSolver();

            }
        };
        SingleStep.setOnAction(tick);

        TextField TFInput= new TextField();
        Load = new Button("Load File");
        Load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                File inpFile=new File(TFInput.getText());
                System.out.println(inpFile.getAbsolutePath());
                if(inpFile==null)
                    System.out.println("No File");
                else{
                    try {
                        TSPData=FileIO.NodeListFromFile(inpFile);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(PlotterControl.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               // prepareSolver();
                
                
            }
        });

        Start = new Button("Start");
        Start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                currentSolver.finish();
                triggerSolver();
            }
        });

        Stop = new Button("Stop");

        verticalLayout = new VBox(l, SingleStep,TFInput,Load, Start, FullEnum, NearestN, AntColony, kOpt);this.getChildren().add(verticalLayout);

        Thread t= new Thread(new triggerLoop(this));
       // t.start();

    }
void prepareSolver() 
{
//        try {
//            TSPData=FileIO.NodeListFromFile(new File("D:\\TSP2.tsp"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PlotterControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
           
      
        //currentSolver=new DummySolver();     try {
//            TSPData=FileIO.NodeListFromFile(new File("D:\\TSP2.tsp"));
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PlotterControl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //currentSolver = new NearestNeighbor();
       //currentSolver=new AntColony();
       //currentSolver=new kOpt();
        
        //currentSolver=new FullEnumeration();
        if (TSPData != null) {
            currentSolver.addAdjacentList(TSPData);
        } else {
            System.err.println("TSP empty");
            Load.fire();
        }

        currentSolver.setListener(this);
        this.SolverSettings = currentSolver.getSettingsPane();
        this.setSolver();
        this.getChildren().add(SolverSettings);

        dataOutput = currentSolver.getPlotList();
        
        dataOutput.evalBounds(thePlotter);
      

        output.setCurrentData(dataOutput);
}
    void triggerSolver()
    {
         currentSolver.step();
                dataOutput = currentSolver.getPlotList();
                output.setCurrentData(dataOutput);
                Statistics s;
                s = currentSolver.getStatistics();

                if(s!=null)
                thePlotter.report.seText(s.getMessage());
                else
                {System.err.println("no stats");}
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

class triggerLoop implements Runnable{

    PlotterControl remote;
    boolean run;
    public triggerLoop(PlotterControl in)
    {
        remote=in;
        run=true;
    }
    public void shutdown()
    {run=false;}
    public void restart()
    {run=true;}
    @Override
    public void run() {
        while(run){
        System.out.println("Outo");
        if(run)
        {
            remote.SingleStep.fire();
        }
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(triggerLoop.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        
        
    }
}
