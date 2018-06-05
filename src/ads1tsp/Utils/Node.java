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
 * @author Julia Pichler
 */


/**
 * contains x and y coordinates of town in world coordinates
 * int index is used to indicate the position in the containing array and is also used to mark for deletion (negative value)
 * Updateable listener holds a reference to object that will be notified when updates occur
 * 
 */
public class Node implements Updateable{
    String Name;
    double x,y;
    int index;
    Updateable listener;
    
    
    
    public Node (double x, double y)
    {
        setCoordinates(x, y);
    }
    public Node (String n, double x, double y)
    {
        Name=n;
        setCoordinates(x, y);
    }
    
    
    public Node (int i, double x, double y)
    {
        index=i;
        setCoordinates(x, y);
        this.Name=Integer.toString(i);
    }
    public void setCoordinates(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    
    public int getIndex()
    {return index;}
    public double getX()
    {return x;}
    public double getY()
    {return y;}
    public void setIndex(int i )
    {index=i;}
    /**
     * caluclates distance to other node
     * @param other the node to calculate distance from
     * @return returns square of the distance (avoid costly square root operation)
     */
    public double calculateDistance(Node other)
    {
        if(this==other)
            return 0;
        if(other==null)
            return Double.POSITIVE_INFINITY;
        double deltaX, deltaY;
        deltaX=this.x - other.x;
        deltaY = this.y - other.y;
        return deltaX*deltaX+deltaY*deltaY;
    }
    
    public static double calculateDistance (Node A, Node B)
    {
        if(A==B)
            return 0;
        return A.calculateDistance(B);
    }
    
    @Override
    public String toString()
    {
        return ("i: " + index + " name: "  + Name + " X: " +x + " Y:" + y + "\t");
    }
/**
 * notify the previously attached listening object
 */
    @Override
    public void Notify() {
        this.sendMessage();
        
        
    }

    @Override
    public void setListener(Updateable that) {
        this.listener=that;
    }

    @Override
    public void sendMessage() {
        if(listener!=null)
            listener.Notify();
    }
    
    
}
