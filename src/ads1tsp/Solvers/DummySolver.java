/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.Node;
import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.Statistics;

/**
 *
 * @author Robert Martinu
 */
public class DummySolver implements Solver {

    AdjacentList workData;
    PlotList outDAta;
    @Override
    public void step() {
        System.out.println("I'm tickin");
        if(!workData.isReady())
            return;
     
        Node[] nl=workData.getNodeList();
        Node origin=nl[(int)(Math.random()*nl.length)];
        for (int i =0;i<nl.length;i++)
        {
            Node destiny=nl[i];
            outDAta.addRoad(origin, destiny);
        }
        
    }

    @Override
    public PlotList getPlotList() {
        return outDAta;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        workData=input;
        outDAta=new PlotList();
        outDAta.generateFromAdjacentList((PlainAdjacentList)workData);
        
    }

    @Override
    public void addControlPanel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Statistics getStatistics() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Notify() {
        
    }

    @Override
    public SettingsPane getSettingsPane() {
        DummySettingsPane dsp=new DummySettingsPane(this);
        return dsp;
    }

    @Override
    public boolean isReady() {
        return true;   }
    
}
