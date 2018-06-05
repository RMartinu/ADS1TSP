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
import ads1tsp.Utils.Node;
import ads1tsp.Utils.Route;
import ads1tsp.Utils.TimeKeeper;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class kOpt implements Solver {

    AdjacentList workData;
    PlotList output;
    Node[] workNodes;
    Updateable UD;
    TimeKeeper tk;
    Statistics stats;
    Route candidate;

    boolean isPrepared;
    boolean isFinished;
    int ifirst = 2, isecond = 4;

    @Override
    public void step() {
        if (!isPrepared || isFinished) {
            return;
        }
        Link old1, old2;

        tk.start();
        /**
         * 2-opt
         */
        Node tNode[] = candidate.getNodeList().clone();
        Node tNode2[] = candidate.getNodeList().clone();
        invertArraySegment(tNode, ifirst, isecond);

        Route t = new Route();
        t.addNodes(tNode);
        Route s = new Route();
        s.addNodes(tNode2);
        isecond++;
        if (isecond == candidate.getNodeList().length) {
            ifirst++;
            isecond = ifirst + 1;
            if (isecond == candidate.getNodeList().length) {
                this.isFinished = true;
            }
        }
        if (t.getRouteLength() > s.getRouteLength()) {
            candidate = s;
        } else {
            candidate = t;
        }
        /***********************************/
 

        tk.stop();
        output = new PlotList(this);
        output.generateFromAdjacentList(workData, candidate.getLinkArrayList());
        output.addRoad(candidate.getEndNode(), candidate.getStartNode());
    }

    private void invertArraySegment(Node[] in, int start, int end) {
        while (start < end) {
            Node t;
            t = in[start];
            in[start] = in[end];
            in[end] = t;
            start++;
            end--;
        }

    }

    private void prepare() {
        ifirst = 0;
        isecond = 0;
        this.stats = new Statistics("kOp");
        this.tk = new TimeKeeper();
        this.output = new PlotList(this);
        output.generateFromAdjacentList(workData);
        workNodes = workData.getNodeList();
        candidate = new Route(this.workNodes.length);
        for (Node n : workNodes) {
            candidate.addNode(n);
        }
        this.isPrepared = true;
    }

    @Override
    public PlotList getPlotList() {
        return this.output;
    }

    @Override
    public void addAdjacentList(AdjacentList input) {
        this.isFinished = false;
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
    public SettingsPane getSettingsPane() {
        return new kOptSettingsPane();
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Notify() {
        this.isFinished = false;
        this.isPrepared = false;
        prepare();
        sendMessage();
    }

    @Override
    public void setListener(Updateable that) {
        this.UD = that;
    }

    @Override
    public void sendMessage() {
        if (this.UD != null) {
            UD.Notify();
        }
    }

    @Override
    public void finish() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
