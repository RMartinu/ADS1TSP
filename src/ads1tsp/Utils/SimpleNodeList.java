/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import java.util.ArrayList;

/**
 *
 * @author Robert Martinu
 */
public class SimpleNodeList implements distanceFinder {

    ArrayList<Node> listOfNodes;

    public SimpleNodeList() {
        listOfNodes = new ArrayList<>();
    }

    @Override
    public void addNode(Node A) {
        listOfNodes.add(A);
    }

    @Override
    public Node extractClosestNeighbor(Node A) {
        Node candidate = null;
        double minDistance = Double.MAX_VALUE;
        double currentDistance;
        for (Node listOfNode : listOfNodes) {
            
            currentDistance = A.calculateDistance(listOfNode);
            
            if (currentDistance < 0) {
                continue;
            }
            
            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                candidate = listOfNode;
            }

        }

        return candidate;
    }

}
