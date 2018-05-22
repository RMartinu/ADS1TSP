/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.AugmentedAdjacentList;
import ads1tsp.Utils.Node;
import java.util.ArrayList;

/**
 *
 * @author Robert Martinu
 */
public class PlotList {
    
    ArrayList<Road> roads;
    ArrayList<Town> towns;
    public PlotList()
    {
        roads=new ArrayList<>();
        towns=new ArrayList<>();
                
    }
    public void addNode(Node input)
    {}
    
    public void generateFromAdjacentList(PlainAdjacentList input)
    {
        

    }
    
    public void generateFromAdjacentList(AugmentedAdjacentList input)
    {
        generateFromAdjacentList(input.getPlainList());
        
        
    }

}
