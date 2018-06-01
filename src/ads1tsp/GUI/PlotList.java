/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import ads1tsp.Solvers.Solver;
import ads1tsp.Updateable;
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
public class PlotList implements Updateable {
    
    double maxX=Double.MIN_VALUE, minX=Double.MAX_VALUE, maxY=Double.MIN_VALUE, minY=Double.MAX_VALUE;
    AdjacentList l;
    ArrayList<Road> roads;
    ArrayList<Town> towns;
    Updateable listener;
    Solver creator;

    
    private void sendMessage()
    {
        if(listener!=null)
            listener.Notify();
    }
    public PlotList(Solver that)
    {
        roads=new ArrayList<>();
        towns=new ArrayList<>();
        creator=that;
                
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
        
        //System.out.println("x " + t.myX + " to " + t.myY);
    }
    
    Road r1=new Road(A,B);
    Road lr=new Road(C,D,Color.CHARTREUSE);
    Road inter = new Road (B,C, Color.INDIGO,0.74);
    Road IIC = new Road(A,C,Color.AQUAMARINE,0.66);
    roads.add(r1);
    roads.add(lr);
    roads.add(inter);
    roads.add(IIC);

    
    for (Road r : roads)
    {
        if (r==null)
            continue;
        //System.out.println("from " + r.startX +", " + r.startY + " to " + r.line.endXProperty() + ", " + r.line.endYProperty());
    }
    
    }
    
    public void addNode(Node input)
    {
        //comes most likely from the input pane
                l.addNode(input);
    }
    
    public void addNode(double x, double y)
    {
        Node n=new Node(x/Town.scaleX, y/Town.scaleY);
        this.l.addNode(n);
        
    }
    private Town findTownToNode(Node in)
    {
        for (Town t: towns)
        {
            if(t.myNode.equals(in))
                return t;
        }
        return null;
    }
    public void AddTown(Town in){
        //comes fom Pane

        
    }
    
    public void removeTown(Town toKill)
    {
        this.l.removeNode(toKill.myNode);
    }
    public void addRoad(Town A, Town B, Color c, double density){
                if(this.towns.contains(A) && this.towns.contains(B))
        {
            this.roads.add(new Road(A, B,c,density));
        }
    }
    public void addRoad(Town A, Town B, Color c)
    {
        if(this.towns.contains(A) && this.towns.contains(B))
        {
            this.roads.add(new Road(A, B,c));
        }
    }
    public void addRoad(Town A, Town B)
    {
        addRoad(A, B, Color.CYAN);
    }
    public void addRoad(Node A, Node B, Color c, double density){
        Town start=findTownToNode(A);
        Town end=findTownToNode(B);
        addRoad(start, end, c, density);
    }
    public void addRoad(Node A, Node B, Color c){        Town start=findTownToNode(A);
        Town end=findTownToNode(B);
        addRoad(start, end, c);}
    public void addRoad(Node A, Node B)
    {
        Town start;
        start = findTownToNode(A);
        Town end;
        end = findTownToNode(B);
        addRoad(start, end);   
    }
    
    public void removeRoad(Node start, Node end)
    {
        Town A,B;
        A=findTownToNode(start);
        B=findTownToNode(end);
        removeRoad(A, B);
                
    }
    
    public void removeRoad(Town A, Town B)
    {
        for (Road r : this.roads)
        {
            if((r.start==A&&r.end==B)||(r.start==B && r.end==A))
            {this.roads.remove(r);return;}
        }
    }
    
    public void addRoad(Road in)
    {
        if (this.towns.contains(in.start) && this.towns.contains(in.end))
            this.roads.add(in);
    }
    
    public void evalBounds (Pane forPane)
    {
        //System.err.println("Panel Size"+forPane.getWidth() + " " + forPane.getHeight());
        double pWith=forPane.getWidth()-30, pHeight=forPane.getHeight()-50;
        if(pWith<=50)
            pWith=200;
        if(pHeight<=50)
            pHeight=200;
        
        
        for (Town t: towns)
        {
            if(t.myNode.getX()>this.maxX)
                maxX=t.myNode.getX();
            if(t.myNode.getX()<this.minX)
                minX=t.myNode.getX();
            
            if(t.myNode.getY()>this.maxY)
                maxY=t.myNode.getY();
            if(t.myNode.getY()<this.minY)
                minY=t.myNode.getY();
        }
        //System.err.println("MaxX: " + maxX + " minX: " + minX + " maxY: " + maxY + " minY: " + minY);
        
        double scaX, scaY;
        scaX=pWith/(Math.abs(maxX-minX));
        scaY=pHeight/Math.abs(maxY-minY);
        //System.err.println("scaX:" + scaX + " scaY: " + scaY);
        Town.scaleX=scaX;
        Town.scaleY=scaY;
//        Town.scaleX=4;
//        Town.scaleY=6;
        double offsetX, offsetY;
        double townCenterX=(maxX+minX)/2, townCenterY=(maxY+minY)/2;
        for (Town t : towns)
            t.Notify();
        
    }
    
    
    public void generateFromAdjacentList(PlainAdjacentList List)
    {
      
        generateFromAdjacentList(List, null);
        sendMessage();
    }
    
    public void generateFromAdjacentList(AugmentedAdjacentList List)
    {
        
        generateFromAdjacentList(List, null);
        sendMessage();
    }
    /*
    public void generateFromAdjacentList(PlainAdjacentList input, Link[] LiLi)
    {
        l=input;
        Node [] tlist=input.getNodeList();
        towns.ensureCapacity(tlist.length);
    }*/
    
    public void generateFromAdjacentList(PlainAdjacentList input, ArrayList<Link> links)
    {
        
        l=input;
        Node [] tList = input.getNodeList();
        
        towns.ensureCapacity(tList.length);
                for (int i =0;i<tList.length;++i)
        {
            Town t=new Town(tList[i], Color.RED);
            t.listener=l;
            towns.add(t);
        }
                //System.out.println("\tTowns length " + towns.size());
        if(links!=null)
                 for (Link l:links)
         {
             //System.out.print(l.getStartNode() + " " + l.getEndNode());
             //System.err.println("s: " + l.getStartNode().getIndex() + " e: " + l.getEndNode().getIndex());
             Town start=towns.get(l.getStartNode().getIndex());
             Town end=towns.get(l.getEndNode().getIndex());
             roads.add(new Road(start,end));
         }

    
    }
    
    
    public void generateFromAdjacentList(AugmentedAdjacentList input, ArrayList<Link> links)
    {
        l=input;
        generateFromAdjacentList(input.getPlainList(), links);
        
        
    }

    @Override
    public void Notify() {
       System.out.println("Plotlist feels notified");
        if ( l instanceof AugmentedAdjacentList)
        generateFromAdjacentList((AugmentedAdjacentList)l);
        else generateFromAdjacentList((PlainAdjacentList)l);
    }

    @Override
    public void setListener(Updateable that) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
