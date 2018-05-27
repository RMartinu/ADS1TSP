/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Robert Martinu
 */
public class PlotterPane extends Pane {
    
    private Line m,n;
    private Circle c;
    private double ulx, llx, urx, lrx, uly, lly, ury, lry;
    PlotList currentData;
    ArrayList<Town> TownList;
    ArrayList<Road> RoadList;
    public PlotterPane()
    {
        //TownList=new ArrayList<>();
        //RoadList=new ArrayList<>();
        m=new Line();
        c=new Circle(150, 150, 10);
        this.setCurrentData(new PlotList(true));
        
        //Make Circles for draggable Towns
        c.setOnMouseDragged((MouseEvent me) -> {
            System.out.println("I get dragged");
            double dX=me.getX()-c.getCenterX();
            double dY=me.getY()-c.getCenterY();
            c.setCenterX(me.getX());
            c.setCenterY(me.getY());
            
        });
               this.getChildren().add(m);
        this.getChildren().add(c);
        repaint();
        
    }
    
    public void setCurrentData(PlotList input)
    {
        currentData=input;
        if(currentData==null)
            return;

        TownList=input.towns;
        RoadList=input.roads;
         for(Town t : TownList)
        {
            System.out.print(t.getX() + " " + t.XProperty() + " " + t.getY() + " "+t.YProperty());
            this.getChildren().add(t.circle);
        }
        for (Road r : RoadList)
        {
            this.getChildren().add(r.line);
        }
    }
    
    
    
    public void repaint()
    {

        Bounds b =this.getBoundsInLocal();
   
        llx=ulx=b.getMinX();
        urx=lrx=b.getMaxX();
        uly=ury=b.getMinY();
                lry=lly=b.getMaxY();
        
        m.setStartX(ulx);
        m.setStartY(uly);
        m.setEndX(lrx);
        m.setEndY(lry);
 
        System.out.println(ulx + " " + uly + " "+ lrx + " " + urx);
        System.out.println("x: " + this.getLayoutX() + ", y " + this.getLayoutY());
        System.out.println("hx: " + this.getHeight() + ", hy " + this.getWidth());
       
        
    }
}
