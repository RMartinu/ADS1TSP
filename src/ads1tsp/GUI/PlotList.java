/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Utils.AdjacentList;
import ads1tsp.Utils.PlainAdjacentList;
import ads1tsp.Utils.AugmentedAdjacentList;
import ads1tsp.Utils.Link;
import ads1tsp.Utils.Node;
import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class PlotList {
    
    double maxX=Double.MIN_VALUE, minX=Double.MAX_VALUE, maxY=Double.MIN_VALUE, minY=Double.MAX_VALUE;
    AdjacentList l;
    ArrayList<Road> roads;
    ArrayList<Town> towns;
    public PlotList()
    {
        roads=new ArrayList<>();
        towns=new ArrayList<>();
                
    }
    public PlotList (boolean test)
    {if(test==false)return;
    roads=new ArrayList<>();
        towns=new ArrayList<>();
    Town A,B,C,D,E;
   // Town.scaleX=1;
   // Town.scaleY=1;
    A=new Town(new Node(10, 10));
    B=new Town (new Node (20,200));
    C=new Town (new Node(100,100));
    D=new Town (new Node (150,175));
    l=new PlainAdjacentList();
    
    towns.add(A);
    towns.add(B);towns.add(C);towns.add(D);
    
    for (Town t : towns)
    {
        t.addUpdateListener(l);
        System.out.println("x " + t.myX + " to " + t.myY);
    }
    
    Road r1=new Road(A,B);
    roads.add(r1);

    
    for (Road r : roads)
    {
        if (r==null)
            continue;
        System.out.println("from " + r.startX +", " + r.startY + " to " + r.line.endXProperty() + ", " + r.line.endYProperty());
    }
    
    }
    
    public void addNode(Node input)
    {}
    
    public void evalBounds (Pane forPane)
    {
        System.err.println(forPane.getWidth() + " " + forPane.getHeight());
        double pWith=forPane.getWidth()-30, pHeight=forPane.getHeight()-30;
        if(pWith==0)
            pWith=200;
        if(pHeight==0)
            pHeight=200;
        
        for (Town t: towns)
        {
            if(t.getX()>this.maxX)
                maxX=t.getX();
            if(t.getX()<this.minX)
                minX=t.getX();
            
            if(t.getY()>this.maxY)
                maxY=t.getY();
            if(t.getY()<this.minY)
                minY=t.getY();
        }
        System.err.println("MaxX: " + maxX + " minX: " + minX + " maxY: " + maxY + " minY: " + minY);
        
        double scaX, scaY;
        scaX=pWith/(Math.abs(maxX-minX));
        scaY=pHeight/Math.abs(maxY-minY);
        System.err.println("scaX:" + scaX + " scaY: " + scaY);
        double offsetX, offsetY;
        double townCenterX=(maxX+minX)/2, townCenterY=(maxY+minY)/2;
        
    }
    
    
    public void generateFromAdjacentList(PlainAdjacentList List)
    {
        generateFromAdjacentList(List, null);
    }
    
    public void generateFromAdjacentList(AugmentedAdjacentList List)
    {
        generateFromAdjacentList(List, null);
    }
    public void generateFromAdjacentList(PlainAdjacentList input, ArrayList<Link> links)
    {
        Node [] tList = input.getNodeList();
        towns.ensureCapacity(tList.length);
        for (int i =0;i<tList.length;++i)
        {
            towns.add(new Town(tList[i], Color.RED));
        }
        if(links==null)
            return;
        
//        for (Link l : links)
//        {
//            roads.add(new Road(l.getStartNode(), l.getEndNode()));
//        }

         for (Link l:links)
         {
             Town start=towns.get(l.getStartNode().getIndex());
             Town end=towns.get(l.getEndNode().getIndex());
             roads.add(new Road(start,end));
         }
        

        

    }
    
    
    public void generateFromAdjacentList(AugmentedAdjacentList input, ArrayList<Link> links)
    {
        generateFromAdjacentList(input.getPlainList(), links);
        
        
    }
    
    

}
