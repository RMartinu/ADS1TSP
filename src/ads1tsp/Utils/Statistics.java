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
public class Statistics {
    String Message;
    int Iterations;
    
    public Statistics()
    {Iterations=0;}
    public Statistics(String in)
    {
        Message=in;
        Iterations=0;
    }
    public void increment()
    {
        Iterations++;
    }
    public int getIterations(){return Iterations;}
    
    public void setMessage(String in)
    {
        this.Message=in;
    }
    
    public String getMessage()
    {return Message;
    }
    
}
