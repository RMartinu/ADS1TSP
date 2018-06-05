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
 * @author Julia Pichler
 *
 * contains list of all nodes contains list of all distances of nodes contains
 * list of all edge weights between nodes
 */
public class AdjacentList implements Updateable
{

    boolean isReady = false;
    int length;
    HashMap<String, Node> NodeHashList;
    Updateable listener;
    int currentListenerIndex;

    Node[] NodeList;
    Node[] salvage;
    int[] NodeLookup;
    double Adjacents[][];
    double edgeWeigths[][];
    double[][] oldEdgeWeights;

    public AdjacentList()
    {
        NodeHashList = new HashMap<>();

    }

    public AdjacentList(Node[] inputList)
    {
        //listener=new Updateable[10];
        NodeHashList = new HashMap<>();
        NodeList = new Node[inputList.length];
        //System.arraycopy(inputList, 0, NodeList, 0, inputList.length);
        for (int i = 0; i < inputList.length; ++i)
        {
            NodeList[i] = inputList[i];
            NodeList[i].setIndex(i);
            //NodeHashList.put(Integer.toString(inputList[i].index), inputList[i]);
            // System.out.print(NodeList[i].toString());
        }

        buildAdjacentList();

    }

    /**
     * builds Adjacent and edge weight Lists from Array NodeList Adjacent List
     * exploits symmetry of matrizes to save space
     */
    private void buildAdjacentList()
    {
        //System.out.println("\n now Building");
        this.oldEdgeWeights = this.edgeWeigths;
        for (Node n : NodeList)
        {
            n.setListener(this);
        }
        int numberOfNodes = NodeList.length;
        //System.err.println("NumOfNodes" + numberOfNodes);
        length = numberOfNodes;
        Adjacents = new double[numberOfNodes][];
        edgeWeigths = new double[numberOfNodes][];
        for (int i = 0; i < Adjacents.length; i++)
        {
            Adjacents[i] = new double[i + 1];
            edgeWeigths[i] = new double[i + 1];
            for (int j = 0; j <= i; j++)
            {
                // Adjacents[i][j]=NodeList[i].calculateDistance(NodeList[j]);
                Adjacents[i][j] = Node.calculateDistance(NodeList[i], NodeList[j]);
                edgeWeigths[i][j] = 0;
                //System.out.println("Dist: " + Node.calculateDistance(NodeList[i], NodeList[j]) + " bet " + NodeList[i].toString() + " & " + NodeList[j].toString());
            }
        }
        //print();
        this.isReady = true;

    }

    /**
     * rebuilds the lists the costs of rebuilding are justified because this
     * will be called rarely
     */
    public void rebuildAdjacentList()
    {
        //Distances can be recalculated, don't need to carry them over
        //System.out.print("rebuilding...");
        buildAdjacentList();
        restoreEdges();
        //System.out.println("Done");
        isReady = true;

    }

    /**
     * adds Node to NodeList and rebuilds Lists
     *
     * @param A the Node to be added
     */
    public void addNode(Node A)
    {
        //System.out.println("    shoudl do somehing");
        isReady = false;
        A.index = NodeList.length;
        Node[] tempNodeList = new Node[NodeList.length + 1];
        for (int i = 0; i < NodeList.length; i++)
        {
            tempNodeList[i] = NodeList[i];
        }
        tempNodeList[tempNodeList.length - 1] = A;
        NodeList = tempNodeList;
        rebuildAdjacentList();

    }

    /**
     * looks up the distance in Adjacent List
     *
     * @param A first node
     * @param B second node
     * @return returns square of distance
     */
    public double getDistance(Node A, Node B)

    {
        if (A == null || B == null)
        {
            return -1;
        }
        if (A.index > B.index)
        {
            return this.Adjacents[A.index][B.index];
        }
        return this.Adjacents[B.index][A.index];
        //return A.calculateDistance(B);
    }

    /**
     * retrieve previous edge weight
     *
     * @param a index of first node in previous lists
     * @param b indx of second node in previous lists
     * @return returns previous edge weight if possible
     */
    private double getSalvagedWeight(int a, int b)
    {

        if (oldEdgeWeights == null)
        {
            return -1;
        }
        if (a >= oldEdgeWeights.length || b >= oldEdgeWeights.length)
        {
            return -10;
        }
        //System.out.println("Want: " + a + ", " + b + " have " + oldEdgeWeights.length);

        if (a > b)
        {
            if (a >= oldEdgeWeights.length || b >= oldEdgeWeights[a].length)
            {
                return -10;
            }
            return oldEdgeWeights[a][b];
        } else
        {
            if (b >= oldEdgeWeights.length || a >= oldEdgeWeights[b].length)
            {
                return -10;
            }
            return oldEdgeWeights[b][a];
        }

    }

    /**
     * retrieves the weight of the edge between the indizes of two nodes
     * @param a index of first current node
     * @param b index of second current node
     * @return returns current weight between nodes
     */
    public double getWeight(int a, int b)
    {
        if (a > b)
        {
            return this.edgeWeigths[a][b];
        }
        return this.edgeWeigths[b][a];
    }

        /**
     * retrieves the weight of the edge between two nodes
     * @param A first current node
     * @param B second current node
     * @return returns current weight between nodes
     */
    
    public double getWeight(Node A, Node B)
    {
        if (A == null || B == null)
        {
            return -1;
        }
        return getWeight(A.index, B.index);
    }

    /**
     * changes value of weight between two nodes 
     * @param A first current node
     * @param B second current node
     * @param value new value of weight
     */
    
    public void setWeight(Node A, Node B, double value)
    {//ut.println("I'm "+value);
        if (A == null || B == null)
        {
            return;
        }

        // System.out.println("Accessing: [" + A.index+"]["+B.index+"]");
        if (A.index > B.index)
        {//System.out.println(this.edgeWeigths.length + " and " +edgeWeigths[A.index].length);
            this.edgeWeigths[A.index][B.index] = value;
        } else
        {
            //System.out.println(this.edgeWeigths.length + " and " +edgeWeigths[B.index].length);
            this.edgeWeigths[B.index][A.index] = value;
        }
        // print();
    }
    
    /**
     * Modifies all edge Weights by supplied Multiplier
     * @param factor Multiplier for each edge
     */
    public void decayEdgeWeights(double factor)
    {
        for (double[] e1: edgeWeigths)
        {
            for (int i=0; i<e1.length; i++)
            {
                e1[i]*=factor;
            }
        }
    }
    
    /**
     * Determinate the largest weight in the edgeWeight list.
     * Makes the access to this information implementation independent
     * @return max weight amongst the edges
     */
    public double getMaxWeight()
    {
        double max=0;
                for (double[] e1: edgeWeigths)
        {
            for (int i=0; i<e1.length; i++)
            {
                if(e1[i]>max)
                    max=e1[i];
            }
        }
        return max;
    }
    
    /**
     * Collects a List of all Links with weights above a certain threshold
     * @param threshold minimum weights for edges to be collected
     * @return List of Links that match description
     */
    public ArrayList<Link> getActiveLinks(double threshold)
    {
        ArrayList<Link> LinkCollector=new ArrayList<>();
        for (int i =0; i<edgeWeigths.length; i++)
        {
            for (int j =0; j<edgeWeigths[i].length; j++)
            {
                if (edgeWeigths[i][j]>threshold)
                {
                    LinkCollector.add(new Link(NodeList[i], NodeList[j]));
                }
            }
        }
        return LinkCollector;
    }

    /**
     * on notiy update the lists and notify listener
     */
    @Override
    public void Notify()
    {
        isReady = false;
        //System.out.println("k, lets start this all over...");
        oldEdgeWeights = this.edgeWeigths;
        // print();
        clearUnusedNodes();
        // print();

        //rebuildAdjacentList();
        sendMessage();
    }

    /**
     * notifies listener
     */
    
    public void sendMessage()
    {
        if (listener != null)
        {//System.out.println("PADJL sends to " + listener.toString());
            listener.Notify();
        }
    }

    public int getLength()
    {
        return length;
    }

    public Node[] getNodeList()
    {
        return this.NodeList;
    }
    


    public void print()
    {
        System.out.println("Is Ready: " + this.isReady);
        for (Node n : NodeList)
        {
            if (n == null)
            {
                System.err.println("empty");
                return;
            }
            //System.out.print(n);
        }
        System.out.println("\nnext");

        for (Node Node1 : NodeList)
        {
            for (Node Node2 : NodeList)
            {
                System.out.printf("%.1f | %.1f | %.1f\t", this.getDistance(Node1, Node2), this.getWeight(Node1, Node2), this.getSalvagedWeight(Node1.index, Node2.index));
            }
            System.out.println();
        }
    }

    /**
     * checks if lists are coherent and ready to be used
     * @return returns if lists are ready
     */
    
    public boolean isReady()
    {
        return this.isReady;
    }

    public void removeNode(Node in)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * removes unused nodes (index = -1), creates new Node List and rebuilds data structure
     */    
    public void clearUnusedNodes()
    {
        //ut.println("cleaning up");
        int survivors = 0;
        for (int i = 0; i < NodeList.length; ++i)
        {
            if (NodeList[i].index == -1)
            {
                NodeList[i] = null;
            } else
            {
                survivors++;
            }
        }
        Node[] tempList = new Node[survivors];
        NodeLookup = new int[survivors];

        int j = 0;
        for (int i = 0; i < NodeList.length; i++)
        {
            if (NodeList[i] != null)
            {
                NodeLookup[j] = i;
                tempList[j] = NodeList[i];
                //to match old nodes with new ones during salvage
                NodeList[i].setIndex(j);
                tempList[j].setIndex(j);
                j++;
            }
        }
        salvage = NodeList;

        NodeList = tempList;

//        for (Node n : NodeList)
//        {
//            System.out.println(n.toString());
//        }
        rebuildAdjacentList();
    }

    /**
     * gets edge weigths from old list
     */
    
    private void restoreEdges()
    {
        //System.out.println("Salvage OP");

        for (int i = 0; i < this.edgeWeigths.length; i++)
        {
            for (int j = 0; j < this.edgeWeigths[i].length; j++)
            {
                if (this.NodeList.length < this.oldEdgeWeights.length)
                {
                    edgeWeigths[i][j] = this.getSalvagedWeight(this.NodeLookup[i], this.NodeLookup[j]);
                } else
                {
                    edgeWeigths[i][j] = this.getSalvagedWeight(j, j);
                }
            }
        }
        //System.out.println("Done sALvaging");
        //print();

    }

    @Override
    public void setListener(Updateable that)
    {
        this.listener = that;
    }

}
