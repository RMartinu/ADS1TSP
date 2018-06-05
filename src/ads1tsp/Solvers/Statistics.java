/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Solvers;

import ads1tsp.Utils.Report;
import ads1tsp.Utils.Route;

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

    public Statistics() {
        Iterations = 0;
    }

    public Statistics(String in) {
        Message = in;
        Iterations = 0;

    }

    public void increment() {
        Iterations++;
    }

    public void increment(long tSlice) {
        increment();
        totalTime += tSlice;
        if (tSlice < minIterationTime) {
            minIterationTime = tSlice;
        }
        if (tSlice > maxIterationTime) {
            maxIterationTime = tSlice;
        }
        avgIterationTime = totalTime / Iterations;
    }

    ;
    public int getIterations() {
        return Iterations;
    }

    public void setMessage(String in) {
        this.Message = in;
    }

    private String timeToString(long in){return Long.toString(in%1000000/1000).concat(" ms, ").concat(Long.toString(in%1000).concat("us"));}
    public String getMessage() {
        StringBuilder sb=new StringBuilder();
        sb.append(this.Message);
        sb.append("\niteration " + timeToString(this.avgIterationTime));
        sb.append("\nTotal " + timeToString(totalTime));
        sb.append("\nTrackLength " + this.bestRouteLength);
        return sb.toString();
    }

    public Report getReport() {
        return new Report();
    }
    public void setRoute(Route in)
    {
        this.bestRouteLength=in.getRouteLength();
    }
    
    public void setNumOfTowns(int in)
    {
        this.setNumOfTowns(in);
    }

}
