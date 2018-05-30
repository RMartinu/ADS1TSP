/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

/**
 *
 * @author Robert Martinu
 */
public class ArrayDistanceFinder implements distanceFinder{
    Reservoir workset;
    public ArrayDistanceFinder(Reservoir in)
    {
        workset=in;
    }

    @Override
    public void addNode(Node A) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node extractClosestNeighbor(Node A) {
        Node nearest=null;
        int nearestIndex;
        double minDistance=Double.MAX_VALUE;
        
        nearest=workset.getByIndex(0);
        for (int i=0; i<workset.getLength(); i++)
        {
            if (minDistance<Node.calculateDistance(A, workset.getByIndex(i)))
            {
                if(A==nearest)
                    continue;
                nearest=workset.getByIndex(i);
                minDistance=Node.calculateDistance(A, nearest);
            }
        }
        workset.extractNode(nearest);
        
        return nearest;
    
    }
    
    
}
