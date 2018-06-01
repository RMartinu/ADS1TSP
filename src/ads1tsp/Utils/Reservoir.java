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
public interface Reservoir {
    
    public boolean add(Node a);
    public boolean add(Node []a);
    public Node getNext();
    public Node getByName(String s);
    public Node getByIndex(int i);
    public Node extractRandom();
    public Node extractNode(Node A);
    public Node extractNext();
    public Node[] getData();
    public int getLength();
           
    
}
