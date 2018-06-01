/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import ads1tsp.Updateable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Robert Martinu
 */
public class AdjacentList implements Updateable{
    boolean isReady=false;
    int length;
    HashMap<String, Node>  NodeHashList;
    Updateable listener;
    int currentListenerIndex;
    
    Node [] NodeList;
    Node[] salvage;
    int []NodeLookup;
    double Adjacents[][];
    double edgeWeigths[][];
    double[][] oldEdgeWeights;
    
    public AdjacentList()
    {
        NodeHashList=new HashMap<>();
       
    }
    public AdjacentList(Node[] inputList)
    {
        //listener=new Updateable[10];
        NodeHashList=new HashMap<>();
        NodeList= new Node[inputList.length];
        //System.arraycopy(inputList, 0, NodeList, 0, inputList.length);
        for (int i =0; i<inputList.length; ++i)
        {
            NodeList[i]=inputList[i];
            NodeList[i].setIndex(i);
            //NodeHashList.put(Integer.toString(inputList[i].index), inputList[i]);
           // System.out.print(NodeList[i].toString());
        }
        
        buildAdjacentList();
        
    }
    
    private void buildAdjacentList()
    {
        System.out.println("\n now Building");
        this.oldEdgeWeights=this.edgeWeigths;
        for (Node n: NodeList)
            n.setListener(this);
        int numberOfNodes=NodeList.length;
        System.err.println("NumOfNodes" + numberOfNodes);
        length=numberOfNodes;
        Adjacents=new double[numberOfNodes][];
        edgeWeigths=new double[numberOfNodes][];
        for (int i = 0;i<Adjacents.length; i++)
        {
            Adjacents[i]=new double[i+1];
            edgeWeigths[i]=new double[i+1];
            for (int j=0; j<=i; j++)
            {
               // Adjacents[i][j]=NodeList[i].calculateDistance(NodeList[j]);
                Adjacents[i][j]=Node.calculateDistance(NodeList[i], NodeList[j]);
                edgeWeigths[i][j]=0;
                //System.out.println("Dist: " + Node.calculateDistance(NodeList[i], NodeList[j]) + " bet " + NodeList[i].toString() + " & " + NodeList[j].toString());
            }
        }
        //print();
        this.isReady=true;
       
 
        
        
                
        
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
        System.out.print("rebuilding...");
        buildAdjacentList();
        restoreEdges();
        System.out.println("Done");
                isReady=true;
        
    }
    
    
    public void addNode(Node A){
        System.out.println("    shoudl do somehing");
        isReady=false;
        A.index=NodeList.length;
        Node [] tempNodeList=new Node[NodeList.length+1];
        for (int i =0; i<NodeList.length; i++)
        {tempNodeList[i]=NodeList[i];}
        tempNodeList[tempNodeList.length-1]=A;
        NodeList=tempNodeList;
        rebuildAdjacentList();

    }
    
    public double getDistance(Node A, Node B)
    
    {
        if(A==null||B==null)
            return -1;
        if(A.index>B.index)
        return this.Adjacents[A.index][B.index];
        return this.Adjacents[B.index][A.index];
        //return A.calculateDistance(B);
    }
    
        private double getSalvagedWeight(int a, int b)
    {
        
        if(oldEdgeWeights==null)
            return -1;
        if(a>=oldEdgeWeights.length||b>=oldEdgeWeights.length)
            return -10;
        //System.out.println("Want: " + a + ", " + b + " have " + oldEdgeWeights.length);
        
        if (a>b){if(a>=oldEdgeWeights.length||b>=oldEdgeWeights[a].length)return -10;
            return oldEdgeWeights[a][b];}
        else{if(b>=oldEdgeWeights.length||a>=oldEdgeWeights[b].length)return -10;
        return oldEdgeWeights[b][a];}
        
       
        
    }
    double getWeight(int a, int b)
    {
        if (a>b)
            return this.edgeWeigths[a][b];
        return this.edgeWeigths[b][a];
    }
    public double getWeight(Node A, Node B)
    {
        if(A==null||B==null)
            return -1;
        return getWeight(A.index, B.index);
    }
    
    public void setWeight(Node A, Node B, double value){//ut.println("I'm "+value);
    if(A==null||B==null)
        return;
    
    
   // System.out.println("Accessing: [" + A.index+"]["+B.index+"]");
    
    if(A.index>B.index)
    {//System.out.println(this.edgeWeigths.length + " and " +edgeWeigths[A.index].length);
        this.edgeWeigths[A.index][B.index]=value; }else{
    //System.out.println(this.edgeWeigths.length + " and " +edgeWeigths[B.index].length);
    this.edgeWeigths[B.index][A.index]=value;}
   // print();
    }
    
    

    @Override
    public void Notify() {
        isReady=false;
        System.out.println("k, lets start this all over...");
        oldEdgeWeights=this.edgeWeigths;
       // print();
        clearUnusedNodes();
       // print();


        
       //rebuildAdjacentList();
       sendNotification();
    }
    private void sendNotification()
    {
        if(listener!=null)
        {//System.out.println("PADJL sends to " + listener.toString());
            listener.Notify();}
    }

    
    public int getLength() {
        return length;
    }

    
    public Node[] getNodeList() {
        return this.NodeList;
    }

    
    public void print() {
        System.out.println("Is Ready: " + this.isReady);
       for (Node n:NodeList)
       {
           if(n==null)
           {System.err.println("empty");
           return;
           }
           //System.out.print(n);
       }
       System.out.println("\nnext");
       
        for (Node Node1 : NodeList) {
           for (Node Node2 : NodeList) {
               System.out.printf("%.1f | %.1f | %.1f\t",this.getDistance(Node1, Node2),this.getWeight(Node1, Node2),this.getSalvagedWeight(Node1.index, Node2.index));
           }
            System.out.println();
        }
    }

    
    public boolean isReady() {
       return this.isReady;
    }

    
    public void removeNode(Node in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    
    public void clearUnusedNodes() {
        //ut.println("cleaning up");
        int survivors=0;
        for (int i =0; i<NodeList.length; ++i)
        {if(NodeList[i].index==-1){NodeList[i]=null;}else survivors++;}
        Node []tempList=new Node[survivors];
        NodeLookup=new int[survivors];
        
        int j=0;
        for (int i =0; i<NodeList.length;i++)
        {
            if(NodeList[i]!=null)
            {
                NodeLookup[j]=i;
                tempList[j]=NodeList[i];
                //to match old nodes with new ones during salvage
                NodeList[i].setIndex(j);
                tempList[j].setIndex(j);
                j++;
            }
        }
        salvage=NodeList;
        
        NodeList=tempList;
        
        for (Node n: NodeList)
            System.out.println(n.toString());
        rebuildAdjacentList();
    }
    
    private void restoreEdges()
    {
        //System.out.println("Salvage OP");
        
        for (int i =0; i<this.edgeWeigths.length;i++)
        {
            for (int j=0; j<this.edgeWeigths[i].length;j++)
            {
                if(this.NodeList.length<this.oldEdgeWeights.length)
                edgeWeigths[i][j]=this.getSalvagedWeight(this.NodeLookup[i], this.NodeLookup[j]);
                else
                {
                    edgeWeigths[i][j]=this.getSalvagedWeight(j, j);}
            }
        }
        //System.out.println("Done sALvaging");
        //print();
        
        
    }

    @Override
    public void setListener(Updateable that) {
        this.listener=that;
    }

}
