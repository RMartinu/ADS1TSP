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
public interface Reservoir {
    
    public void add(Node a);
    public void add(Node []a);
    public Node getNext();
    public Node getByName(String s);
    public Node getByIndex(int i);
    public Node extractRandom();
    public Node extractNode(Node A);
    public int getLength();
           
    
}
