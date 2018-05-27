/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;
import ads1tsp.Updateable;
import ads1tsp.Utils.*;
import java.util.ArrayList;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Robert Martinu
 */
public class Town {
    static double scaleX, scaleY;
    Node myNode;
    Color myColor;
    SimpleDoubleProperty myX, myY;
    ArrayList<Updateable> clients;
    Circle circle;
    public static void setScaleX(double x){scaleX=x;}
    public static void setScaleY(double y){scaleY=y;}
    
    public Town (Node n)
    {myNode=n; myColor=Color.RED; clients=new ArrayList<>();myX=new SimpleDoubleProperty(myNode.getX()*scaleX); myY=new SimpleDoubleProperty(myNode.getY()*scaleY); circle=new Circle(20);
    circle.centerXProperty().bindBidirectional(myX);
    circle.centerYProperty().bindBidirectional(myY);
    addListen();
    
    }
    public Town (Node n, Color c)
    {myNode=n; myColor=c; clients = new ArrayList<>(); myX=new SimpleDoubleProperty(myNode.getX()*scaleX); myY=new SimpleDoubleProperty(myNode.getY()*scaleY);circle=new Circle(20);
        circle.centerXProperty().bindBidirectional(myX);
    circle.centerYProperty().bindBidirectional(myX); addListen();
    }
    
    private void addListen()
    {
        circle.setOnMouseDragged((MouseEvent me)->{
                        //System.out.println("I get drougged");
            double dX=me.getX()-circle.getCenterX();
            double dY=me.getY()-circle.getCenterY();
            circle.setCenterX(me.getX());
            circle.setCenterY(me.getY());
            this.Update();
        });
    }
    
    public void  addUpdateListener(Updateable client)
    {
        this.clients.add(client);
    }
    public void removeUpdateListener(Updateable client)
    {
        this.clients.remove(client);
    }
    
    
    public DoubleProperty XProperty(){return myX;}
    public DoubleProperty YProperty(){return myY;}
    public double getX(){return myX.doubleValue();}
    public double getY(){return myY.doubleValue();}
    
    public void Update()
    {
        for (Updateable client: clients)
            client.Notify();
        System.out.println("Said hello to my little friends");
    }
    
    
}
