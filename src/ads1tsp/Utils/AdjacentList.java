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
public interface AdjacentList extends Updateable {
         
    public int getLength();

    /**
     *
     * @return
     */
    public Node[] getNodeList();
    public void rebuildAdjacentList();
    public void addNode( Node in);
    public void removeNode(Node in);
    
    public void clearUnusedNodes();

    /**
     *
     * @param a
     * @param b
     * @param w
     */

    
    public void print();
    public boolean isReady();
    
}
