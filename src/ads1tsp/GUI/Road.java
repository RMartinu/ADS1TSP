/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Utils.Node;
import javafx.beans.property.DoubleProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class Road {
    double coords[];
    Color color;
    double density;
    Node start, end;
    DoubleProperty startX, startY, endY,endX;
    
    public Road (Node start, Node end)
    {}
    public Road (Node start, Node end, Color c,double density)
    {}
    public DoubleProperty startXProperty(){return startX;}
    public DoubleProperty startYProperty(){return startY;}
    public DoubleProperty endXProperty(){return endX;}
    public DoubleProperty endYProperty(){return endY;}
}
