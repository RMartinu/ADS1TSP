/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Updateable;
import ads1tsp.Utils.Node;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Robert Martinu
 */
public class PlotterPane extends Pane implements Updateable {
   
    private Line m,n;
    private Circle c;
    private double ulx, llx, urx, lrx, uly, lly, ury, lry;
    PlotList currentData;
    ArrayList<Town> TownList;
    ArrayList<Road> RoadList;
   
    Updateable listener;
    public PlotterPane()
    {
        //TownList=new ArrayList<>();
        //RoadList=new ArrayList<>();
        m=new Line();
        
        
//        c=new Circle(150, 150, 10);
        //this.setCurrentData(new PlotList(true));
        
        
        //Make Circles for draggable Towns
//        c.setOnMouseDragged((MouseEvent me) -> {
//            System.out.println("I get dragged");
//            double dX=me.getX()-c.getCenterX();
//            double dY=me.getY()-c.getCenterY();
//            c.setCenterX(me.getX());
//            c.setCenterY(me.getY());
//            
//        });
               this.getChildren().add(m);
//        this.getChildren().add(c);

this.widthProperty().addListener((obs, oldVal, newVal)->{System.err.println("width change");
//this.setOnMousePressed(new EventHandler<MouseEvent>(){public void handle(MouseEvent event){System.out.println("THe Pane got Klicke");}});
this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){public void handle (MouseEvent ev){if(ev.getButton()==MouseButton.PRIMARY){System.out.println("No Joy");return;}System.out.println("tha pane got clocked" + ev.getButton() + ev.isControlDown()); if(ev.isControlDown()){;return;}
currentData.addNode(ev.getX()-150,ev.getY());sendNotification();}});
if(currentData!=null)currentData.evalBounds(this);
});
        repaint();
        
    }
    
 
    private void sendNotification()
    {
        if(listener==null)
            return;
        this.listener.Notify();
    }
    public void setCurrentData(PlotList input)
    {
       // System.out.println("Got new Data");
        //input.creator.setListener(this);
        
        currentData=input;
        if(currentData==null)
            return;
        this.getChildren().clear();

        TownList=input.towns;
        RoadList=input.roads;
         for(Town t : TownList)
        {
           // System.out.print(t.getX() + " " + t.XProperty() + " " + t.getY() + " "+t.YProperty());
            this.getChildren().add(t.circle);
        }
        for (Road r : RoadList)
        {
            this.getChildren().add(r.line);
        }
        
        repaint();
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
 
//        System.out.println("Coords: "+ulx + " " + uly + " "+ lrx + " " + lry);
//        System.out.println("x: " + this.getLayoutX() + ", y " + this.getLayoutY());
//        System.out.println("hx: " + this.getHeight() + ", hy " + this.getWidth());
        if(currentData!=null)
        currentData.evalBounds(this);
       
        
    }

    @Override
    public void Notify() {
        //ut.println("Plotter feels notified");
        setCurrentData(currentData);
    }

    @Override
    public void setListener(Updateable that) {
        this.listener=that;
    }
}
