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
 */
public class ArrayReservoir implements Reservoir{
    Node [] nodes;
    public ArrayReservoir (Node[] inList)
    {
        nodes=inList.clone();
    }

    @Override
    public boolean add(Node a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Node[] a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getNext() {
        for (int i =0; i<nodes.length;i++)
        {
            if(nodes[i]!=null)
            {
                Node t=nodes[i];
                nodes[i]=null;
                return t;
            }
        }
        return null;
    }

    @Override
    public Node getByName(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getByIndex(int i) {
return this.nodes[i];
    }

    @Override
    public Node extractRandom() {
        int l=this.getLength();
        int nth=(int)(Math.random()*l);
        for (int i =0; i<nodes.length;i++)
        {if(nodes[i]!=null){if(nth==0){Node n=nodes[i];nodes[i]=null; return n;}else{nth--;}}}
        return null;
    }

    @Override
    public int getLength() {
        int length=0;
        for (int i =0; i<this.nodes.length; i++)
        {
            if (nodes[i]!=null)
                length++;
        }
        return length;
    }

    @Override
    public Node extractNode(Node A) {
        for (int i=0;i<nodes.length; i++)
        {
            if(nodes[i]==A)
            {nodes[i]=null;}
        }
        return A;
        
    }

    @Override
    public Node extractNext() {
        Node t;
        for (int i=0; i<nodes.length; i++)
        {
            if(nodes[i]!=null)
            {t=nodes[i]; nodes[i]=null; return t;}
        }
        return null;
    }
    public String toString()
    {
        String x="";
        for (Node n:this.nodes)
            x+=n.toString();
        
        return x;
    }

    @Override
    public Node[] getData() {
       return this.nodes;
    }
    
}
