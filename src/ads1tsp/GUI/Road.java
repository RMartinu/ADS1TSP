/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Utils.Node;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class Road {
    double coords[];
    Color color;
    double density;
    Town start, end;
    DoubleProperty startX, startY, endY,endX;
    
    public Road (Town start, Town end)
    {
        startX=new SimpleDoubleProperty();
        startX.bind(start.XProperty());
        startY=new SimpleDoubleProperty();
        startY.bind(start.YProperty());
        endX=new SimpleDoubleProperty();
        endX.bind(end.XProperty());
        endY=new SimpleDoubleProperty();
        endY.bind(end.YProperty());
  
    }
    public Road (Node start, Node end, Color c,double density)
    {}
    public DoubleProperty startXProperty(){return startX;}
    public DoubleProperty startYProperty(){return startY;}
    public DoubleProperty endXProperty(){return endX;}
    public DoubleProperty endYProperty(){return endY;}
    
    public String toString()
    {
        StringBuilder sb=new StringBuilder();
        sb.append("From ");
        sb.append(startX.doubleValue() + ", "+startY.doubleValue()+" ");
        sb.append("to " + endX.doubleValue() + ", "+endY.doubleValue());
        return sb.toString();
    }
}
