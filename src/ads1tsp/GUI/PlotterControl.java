/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Solvers.SettingsPane;
import ads1tsp.Solvers.Solver;
import ads1tsp.Utils.AdjacentList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Robert Martinu
 */
public class PlotterControl extends  Pane{
    PlotterPane output;
    PlotList dataOutput;
    AdjacentList TSPData;
    Solver [] solvers;
    SettingsPane SolverSettings;
    statisticsPane stats;
    VBox verticalLayout;
    
    public PlotterControl(PlotterPane toPlot)
    {
        output=toPlot;
        
    }
    
    
}
