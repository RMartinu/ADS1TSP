/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.ArrayDistanceFinder;
import ads1tsp.Utils.ArrayReservoir;
import ads1tsp.Utils.Link;
import ads1tsp.Utils.Node;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.Route;
import ads1tsp.Utils.TimeKeeper;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class NearestNeighbor implements Solver {

    AdjacentList workData;
    PlotList output;
    TimeKeeper keeper;
    Statistics stats;
    //ArrayList<Link> linkList;
    Updateable UD;

    boolean isBlank = true;
    boolean isFinished = false;
    boolean isPrepared = false;
    ArrayDistanceFinder ADF;
    ArrayReservoir AR;
    Route R;
    Node currentNode = null;
    Node nextNode;
    Node previous;

    public NearestNeighbor() {
        stats = new Statistics("BaC");
        keeper = new TimeKeeper();

    }

    /**
     * finds nearest available neighbour of first/current town and builds street between them
     */
    @Override
    public void step() {
        if (this.isFinished) {
            return;
        }
        //output = new PlotList(this);
        //this.addAdjacentList(workData);

        stats.increment();
        keeper.start();

        //start the route
        if (currentNode == null) {

            currentNode = this.AR.extractNext();
            previous = currentNode;
            R.addNode(currentNode);
        }
        nextNode = this.ADF.extractClosestNeighbor(currentNode);
        //nextNode=AR.extractNext();
        if (nextNode == null) {
            nextNode = R.getStartNode();
            this.isFinished = true;
        }
        previous = currentNode;
        R.addNode(nextNode);
        currentNode = nextNode;

        this.stats.setMessage("Iteration: " + stats.getIterations() + "" + "\nLast It: " + keeper.getIterationTime() / 1000000 + "ms " + (keeper.getIterationTime() % 1000000) / 1000 + "us " + "\nTotal: " + keeper.getTotalTime() / 1000000 + "ms " + (keeper.getTotalTime() % 1000000) / 1000 + "us" + "\nLength: " + R.getRouteLength());
        keeper.stop();
        this.output = new PlotList(this);
        //output.generateFromAdjacentList((PlainAdjacentList)workData,R.getLinkArrayList());
        // System.out.println("Adding l: " + previous + " and " + currentNode);

        output.generateFromAdjacentList((AdjacentList) workData, R.getLinkArrayList());
        output.addRoad(previous, currentNode, Color.RED);

    }

    @Override
    public PlotList getPlotList() {
        return this.output;
    }

    private void prepare() {
        stats = new Statistics();
        keeper.reset();
        nextNode = null;
        currentNode = null;
        previous = null;
        AR = new ArrayReservoir(workData.getNodeList());
        ADF = new ArrayDistanceFinder(AR);
        this.output = new PlotList(this);
        R = new Route(workData.getLength());
        output.generateFromAdjacentList((AdjacentList) workData);

        isPrepared = true;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        this.isPrepared = false;
        this.workData = input;
        workData.setListener(this);

        prepare();

    }

    @Override
    public Statistics getStatistics() {
        return stats;
    }

    @Override
    public void Notify() {
        this.isPrepared = false;
        this.isFinished = false;
        this.isBlank = true;
        prepare();

        sendMessage();
    }

    public void sendMessage() {
        if (this.UD != null) {
            UD.Notify();
        }
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
        this.UD = that;
    }

    @Override
    public void finish() {
        for (int i =0; i<10; i++)
        {
            this.step();
        }
                
    }

}
