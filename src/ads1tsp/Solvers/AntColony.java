/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.GUI.PlotList;
import ads1tsp.Updateable;
import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.ArrayReservoir;
import ads1tsp.Utils.Link;
import ads1tsp.Utils.Node;
import ads1tsp.Utils.Reservoir;
import ads1tsp.Utils.Route;
import ads1tsp.Utils.TimeKeeper;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class AntColony implements Solver {

    AdjacentList workData;
    PlotList output;
    Updateable UD;
    TimeKeeper tKeeper;
    Statistics stats;
    Route winningRoute;
    Node[] workNodes;
    boolean isPrepared;
    int numberOfAnts = 10000;
    Ant[] AntArray;
    double decayFactor=0.75;
    double PheromoneTotal=10000;

    @Override
    public void step() {
        if (!isPrepared) {
            return;
        }

        if (AntArray == null || AntArray.length != numberOfAnts) {
            AntArray = new Ant[numberOfAnts];
            for (int i = 0; i < AntArray.length; i++) {
                AntArray[i] = new Ant(this.workData);
            }
        }

        tKeeper.start();

        //send ants through the map an let them record their routes
        for (Ant a : AntArray) {
            a.takeTrip();
        }
        //decay the markers from the previos turn
        this.workData.decayEdgeWeights(decayFactor);

        //add this turns markers
        for (Ant a : AntArray) {
            //ask the ant for the Route it took
            Link[] ListOfUsedLinks = a.myTrack.getLinks();
            double totalLength = a.myTrack.getRouteLength();
            double pheromoneByUnit=this.PheromoneTotal/totalLength;
            double contribution;
            for (Link l : ListOfUsedLinks) {
                //add a proportional Amount of Pheromone
                contribution = pheromoneByUnit/Math.pow((l.length() / totalLength),3);
                Node st = l.getStartNode(), end = l.getEndNode();
                workData.setWeight(st, end, workData.getWeight(st, end) + contribution);
            }

        }

        tKeeper.stop();
        ArrayList<Link> LinksToDisplay = workData.getActiveLinks(workData.getMaxWeight() / 100000);
        //double maxWeight=workData.getMaxWeight();
        //System.out.println("HitNum: " + LinksToDisplay.size());
        winningRoute = this.traceBestRoute();
//        double minl=Double.POSITIVE_INFINITY;
//        for (Ant a: AntArray)
//        {
//            if(a.myTrack.getRouteLength()<minl)
//            {
//                minl=a.myTrack.getRouteLength();
//                winningRoute=a.myTrack;
//            }
//        }
        
        output = new PlotList(this);
        output.generateFromAdjacentList(workData);
        System.out.print("numolink "+LinksToDisplay.size());
        LinksToDisplay.forEach((l) -> {
            output.addRoad(l, Color.CADETBLUE, workData.getWeight(l.getStartNode(), l.getEndNode())*1000 / workData.getMaxWeight());
        });

        winningRoute.getLinkArrayList().forEach((link) -> {
            output.addRoad(link, Color.RED, 1);
        });

    }

    private Route traceBestRoute()
    {
        Reservoir r= new ArrayReservoir(workNodes);
        Route result= new Route(workNodes.length);
        //set the start Node
        Node start=r.extractNext();
        result.addNode(start);
        while(!r.isEmpty())
        {
           // System.out.println("ize "+r.getLength());
            Node candidate,bestNode=null;
            double maxWeight=0;
            for (int i=0; i<r.getLength(); i++)
            {
                candidate=r.getByIndex(i);
                double tWeight=workData.getWeight(start, candidate);
                if(tWeight>maxWeight)
                {
                    maxWeight=tWeight;
                    bestNode=candidate;
                }
            }
            if(bestNode==null)
            {System.err.println("Out of nodes"); break;}
            //System.out.println("xtract: " + bestNode.toString());
            r.extractNode(bestNode);
            result.addNode(bestNode);
            start=bestNode;
        }
        result.addNode(result.getStartNode());
        return result;
    }
    private void prepare() {
        this.tKeeper = new TimeKeeper();
        this.output = new PlotList(this);
        output.generateFromAdjacentList(workData);
        this.AntArray = null;

        this.workNodes = this.workData.getNodeList();
        this.stats = new Statistics("AntSolver");
        this.isPrepared = true;
    }

    @Override
    public PlotList getPlotList() {
        return this.output;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        this.workData = input;
        this.output = new PlotList(this);
        output.generateFromAdjacentList(input);
        prepare();

    }

    @Override
    public Statistics getStatistics() {
        return this.stats;
    }

    @Override
    public void Notify() {
        this.isPrepared = false;
        prepare();
        sendMessage();
    }

    @Override
    public SettingsPane getSettingsPane() {
        return new AntColonySettingsPane();
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setListener(Updateable that) {
        this.UD = that;
    }

    public void sendMessage() {
        if (UD != null) {
            UD.Notify();
        }
    }

}

class Ant {

    Route myTrack;
    ArrayReservoir needToVisit;
    Node[] map;
    AdjacentList adj;
    static int chooseRandom;
    double sumOfAdjacentWeights;
    Node start, end, current, previous;
    static int flakeyness=25;

    public Ant(AdjacentList in) {
        map = in.getNodeList();
        adj=in;

    }

    void takeTrip() {
        needToVisit = new ArrayReservoir(adj);

        myTrack = new Route(this.needToVisit.getLength());
        while (!needToVisit.isEmpty()) {
            current=chooseNextNode();
            myTrack.addNode(current);
            previous=current;

        }
        myTrack.addNode(myTrack.getStartNode());

    }

    private Node chooseNextNode() {
        if(current==null||Math.random()*100<this.flakeyness)
            current= needToVisit.extractRandom();
        else
        {
            current=needToVisit.extractByHighestWeight(previous);
            if(current==null){ current= needToVisit.extractRandom();}
        }
        
        return current;
        
    }
    


}
