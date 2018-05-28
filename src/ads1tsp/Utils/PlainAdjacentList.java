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
public class PlainAdjacentList implements AdjacentList,Updateable{
    boolean isReady=false;
    int length;
    HashMap<String, Node>  NodeHashList;
    
    Node [] NodeList;
    double Adjacents[][];
    
    public PlainAdjacentList()
    {
        NodeHashList=new HashMap<>();
    }
    public PlainAdjacentList(Node[] inputList)
    {
        NodeHashList=new HashMap<>();
        NodeList= new Node[inputList.length];
        //System.arraycopy(inputList, 0, NodeList, 0, inputList.length);
        for (int i =0; i<inputList.length; ++i)
        {
            NodeList[i]=inputList[i];
            NodeList[i].setIndex(i);
            //NodeHashList.put(Integer.toString(inputList[i].index), inputList[i]);
            System.out.print(NodeList[i].toString());
        }
        
        buildAdjacentList();
        
    }
    
    private void buildAdjacentList()
    {
        System.out.println("\n now Building");
        int numberOfNodes=NodeList.length;
        length=numberOfNodes;
        Adjacents=new double[numberOfNodes][];
        for (int i = 0;i<Adjacents.length; i++)
        {
            Adjacents[i]=new double[i+1];
            for (int j=0; j<=i; j++)
            {
               // Adjacents[i][j]=NodeList[i].calculateDistance(NodeList[j]);
                Adjacents[i][j]=Node.calculateDistance(NodeList[i], NodeList[j]);
            }
        }
        this.isReady=true;
        System.out.println("Done");
        this.print();
        System.out.println("And Done");
                
        
    }
    
//    @Override
//     public void setEdgeWeight( Node A, Node B, double w)
//    {}
//    @Override
//    public double getEdgeWeight(Node A, Node B)
//    {return -1;}
    public void rebuildAdjacentList()
    {
        //Distances can be recalculated, don't need to carry them over
        System.err.print("rebuilding...");
        buildAdjacentList();
        System.err.println("Done");
    }
    
    
    public void addNode(Node A){
        System.out.println("    shoudl do somehing");
        Node [] tempNodeList=new Node[NodeList.length+1];
        for (int i =0; i<NodeList.length; i++)
        {tempNodeList[i]=NodeList[i];}
        tempNodeList[tempNodeList.length-1]=A;
        NodeList=tempNodeList;
        rebuildAdjacentList();
        isReady=true;
    }
    
    double getDistance(Node A, Node B)
    
    {//TODO: get actual Distance from the list instead of recalculating them every time
        if(A==null||B==null)
            return -1;
        if(A.index>B.index)
        return this.Adjacents[A.index][B.index];
        return this.Adjacents[B.index][A.index];
        //return A.calculateDistance(B);
    }

    @Override
    public void Notify() {
        System.out.println("k, lets start this all over...");
        isReady=false;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public Node[] getNodeList() {
        return this.NodeList;
    }

    @Override
    public void print() {
        System.out.println("Is Ready: " + this.isReady);
       for (Node n:NodeList)
       {
           if(n==null)
           {System.err.println("empty");
           return;
           }
           System.out.print(n);
       }
       System.out.println("\nnext");
       
        for (Node Node1 : NodeList) {
           for (Node Node2 : NodeList) {
               System.out.printf("%.2f\t",this.getDistance(Node1, Node2));
           }
            System.out.println();
        }
    }

    @Override
    public boolean isReady() {
       return this.isReady;
    }

}
