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
public class Route {
    
    ArrayList<Node> route;
    public Route()
    {}
    public Route (int n)
    {
        route.ensureCapacity(n);
    }
    
    public void addNode(Node In)
    {
        route.add(In);
    }
    
   
    
}
