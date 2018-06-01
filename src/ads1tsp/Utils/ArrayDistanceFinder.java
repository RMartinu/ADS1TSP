/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 *
 * Holds available nodes, offers various retrieval methods
 * 
 */
public class ArrayDistanceFinder implements distanceFinder{
    Reservoir workset;
    Node []n;
    public ArrayDistanceFinder(Reservoir in)
    {
        workset=in;
        n=in.getData();
//        System.out.println("workset + "+workset.toString());
//        for (Node n1:n)
//        {
//            System.out.println(n1);
//        }
    }
   

    @Override
    public boolean addNode(Node A) {
        
        return this.workset.add(A);
        
        
        
    }

    /**
     * finds nearest neighbor amongst unused nodes
     * Uses Array as supporting datastructure. kd-TRee would search faster, but incur penalties due to frequent rebuild.
     * @param A node to find neighbor of
     * @return returns nearest neighbor
     */
    
    @Override
    public Node extractClosestNeighbor(Node A) {
        Node nearest=null;
        double minDistance=Double.MAX_VALUE;
        int nearestIndex=0;
        boolean isEmpty=true;
        for (int i=0; i<n.length; i++)
        {
            if (n[i]!=null)
            {
                isEmpty=false;
                Node t=n[i];
                double distance=A.calculateDistance(t);
                if (distance<minDistance)
                {
                    minDistance=distance;
                    nearest=t;
                    nearestIndex=i;
                }
                
            }
        }
        if(isEmpty)
            return null;
        
        n[nearestIndex]=null;

        
        return nearest;
    
    }
    
    
}
