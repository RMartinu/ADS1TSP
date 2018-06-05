/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import java.util.LinkedList;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class ArrayReservoir implements Reservoir
{

    Node[] nodes;
    AdjacentList adj=null;
    int storedNodes;

    public ArrayReservoir(Node[] inList)
    {
        nodes = inList.clone();
        storedNodes=this.getLength();
    }
    public ArrayReservoir(AdjacentList inList)
    {
        adj=inList;
        nodes=adj.NodeList.clone();
        storedNodes=getLength();
    }

    @Override
    public boolean add(Node a)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Node[] a)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * peeks next node
     * @return returns next node
     */
   
    @Override
    public Node getNext()
    {
        for (int i = 0; i < nodes.length; i++)
        {
            if (nodes[i] != null)
            {
                Node t = nodes[i];
                //nodes[i] = null;
                return t;
            }
        }
        return null;
    }
    /**
     * Finds the Node with the highest weighted connection
     * returns null if no clear choices present
     * @param A
     * @return 
     */
    public Node extractByHighestWeight(Node A)
    {
        Node t=null;
        double maxW=-1;
        for (int i =0; i<this.nodes.length; i++)
        {
            if (nodes[i]!=null)
            {
                //System.out.println("in: " + A + " out " + nodes[i]);
             double currW=adj.getWeight(A, nodes[i]);
             if(currW>maxW)
             {
                 maxW=currW;
                 t=nodes[i];
             }
            }
        }
        if(maxW<0.1)
            return null;
        if(t!=null){}
        //System.out.println("Found:" + t.toString());
        else{System.err.println("empty quiver");}
        extractNode(t);
        
        
        return t;
    }

    @Override
    public Node getByName(String s)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getByIndex(int i)
    {
        System.out.println("Search for:"+i);
        Node t=null;
        int j=-1,k=0;
        while (k<nodes.length&& j<i)
        {
            if(nodes[k]!=null)
            {
                t=nodes[k++];
                j++;
            }
            else
                k++;
        }
        
        System.out.println("Fond" + t.toString());
        return t;
    }

    /**
     * 
     * @return returns randomly selected element
     */
    
    @Override
    public Node extractRandom()
    {
        int l = this.getLength();
        int nth = (int) (Math.random() * l);
        for (int i = 0; i < nodes.length; i++)
        {
            if (nodes[i] != null)
            {
                if (nth == 0)
                {
                    Node n = nodes[i];
                    nodes[i] = null;
                    storedNodes--;
                    return n;
                } else
                {
                    nth--;
                }
            }
        }
        return null;
    }

    @Override
    public int getLength()
    {
        int length = 0;
        for (int i = 0; i < this.nodes.length; i++)
        {
            if (nodes[i] != null)
            {
                length++;
            }
        }
        return length;
    }

    /**
     * 
     * @param A node to search
     * @return returns node
     */
    
    @Override
    public Node extractNode(Node A)
    {
        for (int i = 0; i < nodes.length; i++)
        {
            if (nodes[i] == A)
            {
                nodes[i] = null;
                storedNodes--;
            }
        }
        return A;

    }

    /**
     * returns and deletes node from reservoir
     * @return reutrns next node
     */
    
    @Override
    public Node extractNext()
    {
        Node t;
        for (int i = 0; i < nodes.length; i++)
        {
            if (nodes[i] != null)
            {
                t = nodes[i];
                nodes[i] = null;
                storedNodes--;
                return t;
            }
        }
        return null;
    }

    public String toString()
    {
        String x = "";
        for (Node n : this.nodes)
        {
            x += n.toString();
        }

        return x;
    }

    @Override
    public Node[] getData()
    {
        return this.nodes;
    }

    @Override
    public boolean isEmpty() {
        if (this.storedNodes==0)
            return true;
        else return false;
    }
    
    

}
