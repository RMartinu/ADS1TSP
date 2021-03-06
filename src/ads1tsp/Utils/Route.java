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
 * @author Julia Pichler
 */
public class Route
{

    ArrayList<Node> route;
    double length = 0;

    public Route()
    {
        route = new ArrayList<>();
    }

    public Route(int n)
    {
        route = new ArrayList<>();
        route.ensureCapacity(n);
    }

    public Node getStartNode()
    {
        return route.get(0);
    }

    public Node getEndNode()
    {
        if(route.get(route.size() - 1)!=this.getStartNode())
        return route.get(route.size() - 1);
        else return route.get(route.size()-2);
    }
    public Node[] getNodeList()
            
    {
        Node [] t= new Node[route.size()];
        t=route.toArray(t);
        return t;
    }

    public void addNode(Node In)
    {
        route.add(In);
        length = 0;
    }
    
    public void addNodes (Node [] in)
    {
        for (Node n:in)
        {route.add(n);
        }
        length=0;
    }
    
    

    public double getRouteLength()
    {
        if (length != 0)
        {
            return length;
        }
        Node A = null, B;
        for (Node n : route)
        {
            if (A == null)
            {
                A = n;
                continue;
            }
            B = n;
            length += Math.sqrt(A.calculateDistance(B));
            A = B;

        }
        //don't forget to close the journey
        length += Math.sqrt(this.getStartNode().calculateDistance(this.getEndNode()));
        return length;
    }

    /**
     * 
     * @return returns list of all used links between nodes 
     */
    
    public Link[] getLinks()
    {
        int length = this.route.size() - 1;

        Link[] ListOfLinks = new Link[length];
        for (int i = 0; i < length; i++)
        {
            ListOfLinks[i] = new Link(route.get(i), route.get(i + 1));
        }

        return ListOfLinks;

    }
    

    /**
     * 
     * @return returns array list of all used links between nodes
     */
    
    public ArrayList<Link> getLinkArrayList()
    {
        ArrayList<Link> LiOLinks = new ArrayList<>();
        int length = route.size() - 1;
        for (int i = 0; i < length; i++)
        {
            LiOLinks.add(new Link(route.get(i), route.get(i + 1)));
        }
        return LiOLinks;
    }
    public ArrayList<Link> gtLinksClosedArrayList()
    {
        ArrayList<Link> t=getLinkArrayList();
        t.add(new Link(this.getStartNode(), this.getEndNode()));
        return t;
                
    }
    
    public String toString()
    {
        StringBuilder s= new StringBuilder();
        this.route.forEach((x)->{s.append(x.toString());});
        return s.toString();
    }

}
