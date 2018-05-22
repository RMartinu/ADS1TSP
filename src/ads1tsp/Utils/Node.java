/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

/**
 *
 * @author Robert Martinu
 */
public class Node {
    String Name;
    double x,y;
    int index;
    
    
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
    public double calculateDistance(Node other)
    {
        if(this==other)
            return 0;
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
        return ("i: " + index + " name: "  + Name + " X: " +x + " Y:" + y);
    }
    
    
}
