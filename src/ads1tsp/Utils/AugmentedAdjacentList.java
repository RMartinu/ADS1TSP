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
public class AugmentedAdjacentList extends AdjacentList implements Updateable {
    
    double Costs[][];
    public AugmentedAdjacentList()
    {
    }
    
    public AugmentedAdjacentList(Node [] inputList){}
    
    void setCost(Node A, Node B)
    {}
    double getCost(Node A, Node B)
    {return 0;}

    
}
