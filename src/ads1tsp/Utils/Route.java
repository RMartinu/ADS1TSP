/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;


import java.util.ArrayList;

/**
 *
 * @author Robert Martinu
 */
public class Route {
    
    ArrayList<Node> route;
    double length=0;
    public Route()
    {
        route= new ArrayList<>();
    }
    public Route (int n)
    {
        route=new ArrayList<>();
        route.ensureCapacity(n);
    }
    public Node getStartNode()
    {
        return route.get(0);
    }
    public Node getEndNode()
    {
        return route.get(route.size()-1);
    }
    
    
    public void addNode(Node In)
    {
        route.add(In);
    }
    
    
    public double getRouteLength()
    {
        if(length!=0)
            return length;
        Node A=null,B;
        for (Node n: route)
        {
            if(A==null)
            { A=n;continue;}
            B=n;
            length+=Math.sqrt(A.calculateDistance(B));
            A=B;
            
        }
        //don't forget to close the journey
        length+=Math.sqrt(this.getStartNode().calculateDistance(this.getEndNode()));
        return length;
    }
    
    
    public Link[] getLinks()
    {
        int length=this.route.size()-1;
        
        Link [] ListOfLinks =new Link[length];
        for (int i =0; i<length; i++)
        {
            ListOfLinks[i]=new Link(route.get(i),route.get(i+1));
        }
        
        return ListOfLinks;
        
    }
    
    public ArrayList<Link> getLinkArrayList()
    {
        ArrayList<Link> LiOLinks=new ArrayList<>();
        int length=route.size()-1;
        for (int i=0; i<length; i++)
        {
            LiOLinks.add(new Link(route.get(i),route.get(i+1)));
        }
        return LiOLinks;
    }
    
   
    
}
