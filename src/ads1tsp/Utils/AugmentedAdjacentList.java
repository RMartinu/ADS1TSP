/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import ads1tsp.Updateable;

/**
 *
 * @author Robert Martinu
 */
public class AugmentedAdjacentList implements  Updateable {
    boolean isReady;
    double Costs[][];
    AdjacentList baseList;
    public AugmentedAdjacentList()
    {
        baseList=new AdjacentList();
    }
    
    public AugmentedAdjacentList(Node [] inputList){
        baseList=new AdjacentList(inputList);
    }
    
    public void setCost(Node A, Node B, double cost)
    {if(A==null||B==null)
        return;
    
    if(A.index>B.index)
        Costs[A.index][B.index]=cost;
    Costs[B.index][A.index]=cost;
    }
    public double getCost(Node A, Node B)
    {
        if(A==null||B==null)
            return -1;
        if(A.index>B.index)
            return this.Costs[A.index][B.index];
        return Costs[B.index][A.index];
        //return 0;
    }
    
    public AdjacentList getPlainList()
    {
        return baseList;
    }

    @Override
    public void Notify() {
        isReady=false;
        this.baseList.Notify();
    }

    
    public int getLength() {
        return this.baseList.getLength();
    }

    
    public Node[] getNodeList() {
        return this.baseList.getNodeList();
    }

    public void setEdgeWeight(Node a, Node b, double w) {
        
    }

    public double getEdgeWeight(Node a, Node b) {
        return -1;
    }

    
    public void print() {
        
    }

    
    public boolean isReady() {
        if(baseList.isReady())
        return this.isReady;
        return false;
    }

    
    public void rebuildAdjacentList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void addNode(Node in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void removeNode(Node in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 

    
    public void clearUnusedNodes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
