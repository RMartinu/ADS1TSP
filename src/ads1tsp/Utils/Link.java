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
 */
public class Link {

    Node a, b;

    public Link(Node A, Node B) {
        a = A;
        b = B;

    }
    public Node getStartNode()
    {return a;}
    public Node getEndNode()
    {return b;}

}
