/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Robert Martinu
 */
public class Road {
    double coords[];
    Color color;
    double density;
    Town start, end;
    Line line;
    DoubleProperty startX, startY, endY,endX;
    
    public Road (Town start, Town end)
    {
        color=Color.RED; density=0.1;
        startX=new SimpleDoubleProperty();
        startX.bind(start.myX);
        startY=new SimpleDoubleProperty();
        startY.bind(start.myY);
        endX=new SimpleDoubleProperty();
        endX.bind(end.myX);
        endY=new SimpleDoubleProperty();
        endY.bind(end.myY);
        
        line=new Line();
        color=color.deriveColor(1, 1, 1, density);
        line.setStroke(color);
        line.setStrokeWidth(3);
       
        
        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);
  
    }
    public Road (Town start, Town end, Color c)
    {        color=c; density=0.25;
        startX=new SimpleDoubleProperty();
        startX.bind(start.myX);
        startY=new SimpleDoubleProperty();
        startY.bind(start.myY);
        endX=new SimpleDoubleProperty();
        endX.bind(end.myX);
        endY=new SimpleDoubleProperty();
        endY.bind(end.myY);
        
        line=new Line();
        color=color.deriveColor(1, 1, 1, density);
        line.setStroke(color);
        line.setStrokeWidth(3);
       
        
        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);}
    public Road (Town start, Town end, Color c,double density)
    {
                color=c;  this.density=density;
        startX=new SimpleDoubleProperty();
        startX.bind(start.myX);
        startY=new SimpleDoubleProperty();
        startY.bind(start.myY);
        endX=new SimpleDoubleProperty();
        endX.bind(end.myX);
        endY=new SimpleDoubleProperty();
        endY.bind(end.myY);
        
        line=new Line();
        color=color.deriveColor(1, 1, 1, density);
        line.setStroke(color);
        line.setStrokeWidth(3);
       
        
        line.startXProperty().bind(startX);
        line.startYProperty().bind(startY);
        line.endXProperty().bind(endX);
        line.endYProperty().bind(endY);
    }
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
