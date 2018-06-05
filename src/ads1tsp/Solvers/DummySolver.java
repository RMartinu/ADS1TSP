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
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.TimeKeeper;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class DummySolver implements Solver {

    AdjacentList workData;
    PlotList outDAta;
    TimeKeeper timek;
    Statistics myStat;
    Updateable listener;
    Color []rainbow={Color.ALICEBLUE,Color.GREEN,Color.RED,Color.BLUEVIOLET,Color.PLUM,Color.BEIGE,Color.CRIMSON,Color.DARKMAGENTA};
    public DummySolver()
    {
        timek=new TimeKeeper();
        myStat=new Statistics("Virgn Snow");
        //rainbow=[Color.ALICEBLUE,];
        
        
    }
    @Override
    public void sendMessage()
    {
        if(listener!=null)
        listener.Notify();
    }
    @Override
    public void step() {
        System.out.println("I'm tickin");
        if(!workData.isReady())
            return;
     //Execute the actual business logic here
     outDAta=new PlotList(this);
     this.addAdjacentList(workData);
     
     timek.start();
        Node[] nl=workData.getNodeList();
        Node origin=nl[(int)(Math.random()*nl.length)];
        Color current=rainbow[(int)(Math.random()*rainbow.length)];
        for (Node destiny : nl) {
            outDAta.addRoad(origin, destiny,current,0.25);
            this.workData.setWeight(origin, destiny, (origin.getIndex()+1)*(destiny.getIndex()+1));
        }
        timek.stop();
        myStat.increment();
        this.myStat.setMessage("Iteration: "+myStat.getIterations()+""+"\nLast It: " + timek.getIterationTime()/1000000 + "ms "+(timek.getIterationTime()%1000000)/1000+"us " + "\nTotal: " + timek.getTotalTime()/1000000+"ms "+ (timek.getTotalTime()%1000000)/1000+"us");
        
    }

    @Override
    public PlotList getPlotList() {
        return outDAta;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        workData=input;
        workData.setListener(this);
        outDAta=new PlotList(this);
        outDAta.generateFromAdjacentList((AdjacentList)workData);
        sendMessage();
        
    }



    @Override
    public Statistics getStatistics() {
        
        
        return this.myStat;
    }

    
    /**
     * Important: every Solver must pass those notifications through
     * Potentialy, and likely, treat this as a reset
     */
    @Override
    public void Notify() {
        System.out.println("DummyPlug got a call, forward to " + this.listener);
        outDAta=new PlotList(this);
        outDAta.generateFromAdjacentList((AdjacentList)workData);
        sendMessage();
        
    }

    @Override
    public SettingsPane getSettingsPane() {
        DummySettingsPane dsp=new DummySettingsPane(this);
        return dsp;
    }

    @Override
    public boolean isReady() {
        return true;   }

    @Override
    public void setListener(Updateable that) {
        this.listener=that;
    }

    @Override
    public void finish() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
