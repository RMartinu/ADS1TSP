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
    
    public Node (String n, double x, double y)
    {
        Name=n;
        this.x=x;
        this.y=y;
    }
    
    public Node (int i, double x, double y)
    {
        index=i;
        this.x=x;
        this.y=y;
        this.Name=Integer.toString(i);
    }
    
    public void setIndex(int i )
    {index=i;}
    public double calculateDistance(Node other)
    {
        if(this==other)
            return -1;
        double deltaX, deltaY;
        deltaX=this.x - other.x;
        deltaY = this.y - other.y;
        return deltaX*deltaX+deltaY*deltaY;
    }
    
    public static double calculateDistance (Node A, Node B)
    {
        if(A==B)
            return -1;
        return A.calculateDistance(B);
    }
    
    
}
