/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.Utils.Report;

/**
 *
 * @author Robert Martinu
 */
public class Statistics {
    String Message;
    int Iterations;
    long totalTime;
    long avgIterationTime;
    long minIterationTime;
    long maxIterationTime;
    double bestRouteLength;
    int numberOfTowns;
    
    
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
    public void increment(long tSlice){increment();totalTime+=tSlice; if(tSlice<minIterationTime)minIterationTime=tSlice; if(tSlice>maxIterationTime)maxIterationTime=tSlice; avgIterationTime=totalTime/Iterations;};
    public int getIterations(){return Iterations;}
    
    public void setMessage(String in)
    {
        this.Message=in;
    }
    
    public String getMessage()
    {return Message;
    }
    
    public Report getReport()
    {
        return new Report();
    }
    
}
