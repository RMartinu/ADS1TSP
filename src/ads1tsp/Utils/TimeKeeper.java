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
public class TimeKeeper {
    long totalEecutionTime;
    long timeForLastIteration;
    long begin, end;
    public TimeKeeper()
    {
       
        long nanoTime = System.nanoTime();
        
    }
    public void reset(){totalEecutionTime=0; timeForLastIteration=0;}
    public void start(){begin=System.nanoTime();}
    public void stop(){end=System.nanoTime();timeForLastIteration=Math.abs(end-begin);totalEecutionTime+=timeForLastIteration;}
    public long getTotalTime(){return totalEecutionTime;}
    public long getIterationZime(){return timeForLastIteration;}    
}
