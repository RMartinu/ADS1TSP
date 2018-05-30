/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.Statistics;

/**
 *
 * @author Robert Martinu
 */
public interface Solver extends Updateable{
    public void step();
    public PlotList getPlotList();
    public void addAdjacentList(AdjacentList input);
    public void addControlPanel();
    public Statistics getStatistics();
    public SettingsPane getSettingsPane();
    public boolean isReady();
    
    
    
}
