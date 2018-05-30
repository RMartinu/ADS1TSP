/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.Link;
import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.Statistics;
import ads1tsp.Utils.TimeKeeper;
import java.util.ArrayList;

/**
 *
 * @author Robert Martinu
 */
public class NearestNeighbor implements Solver {
    AdjacentList workData;
    PlotList output;
    TimeKeeper keeper;
    Statistics stats;
    ArrayList<Link> linkList;

    public NearestNeighbor() {
        stats=new Statistics("BaC");
        keeper=new TimeKeeper();
    }

    
    @Override
    public void step() {
        keeper.start();
        output.generateFromAdjacentList((PlainAdjacentList)workData);
        this.stats.setMessage("Iteration: "+stats.getIterations()+""+"\nLast It: " + keeper.getIterationZime()/1000000 + "ms "+(keeper.getIterationZime()%1000000)/1000+"us " + "\nTotal: " + keeper.getTotalTime()/1000000+"ms "+ (keeper.getTotalTime()%1000000)/1000+"us");
        keeper.stop();
    }

    @Override
    public PlotList getPlotList() {
        return this.output;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        this.workData=input;
        this.output=new PlotList(this);
        output.generateFromAdjacentList((PlainAdjacentList)workData);
    }

    @Override
    public void addControlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Statistics getStatistics() {
        return stats;
    }

    @Override
    public void Notify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NearestNeighborSettingsPane getSettingsPane() {
         return new NearestNeighborSettingsPane(this);
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setListener(Updateable that) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
