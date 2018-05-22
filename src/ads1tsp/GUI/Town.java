/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;
import ads1tsp.Updateable;
import ads1tsp.Utils.*;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 *
 * @author Robert Martinu
 */
public class Town {
    Node myNode;
    Color myColor;
    ArrayList<Updateable> clients;
    
    public Town (Node n)
    {myNode=n; myColor=Color.RED; clients=new ArrayList<>();}
    public Town (Node n, Color c)
    {myNode=n; myColor=c; clients = new ArrayList<>();
    }
    
    public void  addUpdateListener(Updateable client)
    {
        this.clients.add(client);
    }
    public void removeUpdateListener(Updateable client)
    {
        this.clients.remove(client);
    }
    
    private void Update()
    {
        for (Updateable client: clients)
            client.Notify();
    }
    
    
}
