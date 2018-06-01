/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.Node;
import ads1tsp.Utils.Permutator;
import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.Route;
import ads1tsp.Utils.Statistics;
import ads1tsp.Utils.TimeKeeper;

/**
 *
 * @author Robert Martinu
 */
public class FullEnumeration implements Solver,Updateable{

    AdjacentList workData;
    PlotList output;
    Updateable UD;
    TimeKeeper keeper;
    Statistics stats;
    Route shortestRoute;
    Route Candidate;
    Node [] workNodes;
    Permutator permutatrix;
    
    boolean isPrepared;
    boolean isFinished;
    
    @Override
    public void step() {
        if(this.isFinished)
            return;
        if(this.workNodes.length>12)
            
        {System.err.println("to many nodes for reasonable time");return;}
        if(workNodes.length<2)
        {System.err.println("need at least two towns");return;}
        
        
        
        while(!isFinished){keeper.start();
         Candidate=nextRoute();
        
                        if(!permutatrix.hasMore())
        {
            this.isFinished=true;
        }
                        if(this.shortestRoute.getRouteLength()>Candidate.getRouteLength())
                        {shortestRoute=Candidate;}
        keeper.stop();}
        
        stats.increment();
        stats.setMessage("Iteration: " + stats.getIterations() + "\nshort: "+shortestRoute.getRouteLength() + "\ncurrent: " + Candidate.getRouteLength()+"\nLast It: " + keeper.getIterationTime()/1000000 + "ms "+(keeper.getIterationTime()%1000000)/1000+"us " + "\nTotal: " + keeper.getTotalTime()/1000000+"ms "+ (keeper.getTotalTime()%1000000)/1000+"us");
        output=new PlotList(this);
        output.generateFromAdjacentList((PlainAdjacentList)this.workData, shortestRoute.getLinkArrayList());
        output.addRoad(shortestRoute.getStartNode(), shortestRoute.getEndNode());
        
    }
    private Route nextRoute()
    {
        Route t=new Route(this.workNodes.length);
        int[] order=permutatrix.getNext();
        for (int i:order)
        {
            t.addNode(workNodes[i]);
        }
       // System.out.println("created a new route");
        return t;
    }


    @Override
    public PlotList getPlotList() {
        return this.output;
    }


    @Override
    public Statistics getStatistics() {
        return this.stats;
    }

    @Override
    public void Notify() {
        this.isFinished=false;
        this.isPrepared=false;
        prepare();
        //this.output=new PlotList(this);
        sendMessage();
    }

    @Override
    public SettingsPane getSettingsPane() {
        return new FullEnumerationSettingsPane();
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        this.isFinished=false;
        this.isPrepared=false;
        this.workData=input;
        workData.setListener(this);
        prepare();
    }

    @Override
    public void setListener(Updateable that) {
        this.UD=that;
    }

    private void prepare() {
        
        this.stats=new Statistics();
        this.keeper=new TimeKeeper();
        this.output=new PlotList(this);
        workNodes=workData.getNodeList();
        output.generateFromAdjacentList((PlainAdjacentList)workData);
        shortestRoute=new Route();
        permutatrix=new Permutator(workNodes.length);
        for (Node n:workNodes)
        {shortestRoute.addNode(n);}
        this.isPrepared=true;
    }

    private void sendMessage() {
        if(this.UD!=null)
            UD.Notify();
            
    }
    
}



