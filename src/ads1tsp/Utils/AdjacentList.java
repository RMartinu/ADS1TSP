/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import ads1tsp.Updateable;
import java.util.HashMap;

/**
 *
 * @author Robert Martinu
 */
public class AdjacentList implements Updateable{
    HashMap<String, Node>  NodeHashList;
    
    Node [] NodeList;
    double Adjacents[][];
    
    public AdjacentList()
    {
        NodeHashList=new HashMap<>();
    }
    public AdjacentList(Node[] inputList)
    {
        NodeList= new Node[inputList.length];
        System.arraycopy(inputList, 0, NodeList, 0, inputList.length);
        buildAdjacentList();
        
    }
    
    private void buildAdjacentList()
    {
        int numberOfNodes=NodeHashList.size();
        Adjacents=new double[numberOfNodes][];
        for (int i = 0;i<Adjacents.length; i++)
        {
            Adjacents[i]=new double[i+1];
        }
        
    }
    
     void setEdgeWeight( Node A, Node B)
    {}
    double getEdgeWeight(Node A, Node B)
    {return -1;}
    protected void rebuildAdjacentList()
    {}
    
    
    public void addNode(Node A){}
    
    double getDistance(Node A, Node B)
    {return 0;}

    @Override
    public void Notify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
