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
public interface distanceFinder {

    public void addNode(Node A);
    
    public Node extractClosestNeighbor( Node A);
    
    
}
