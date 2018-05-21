/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.GUI;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 *
 * @author Robert Martinu
 */
public class PlotterPane extends Pane {
    
    private Line m,n;
    private double ulx, llx, urx, lrx, uly, lly, ury, lry;
    public PlotterPane()
    {
        repaint();
    }
    
    public void repaint()
    {
        Bounds b =this.getBoundsInLocal();
   
        llx=ulx=b.getMinX();
        urx=lrx=b.getMaxX();
        uly=ury=b.getMinY();
                lry=lly=b.getMaxY();
        m=new Line();
        m.setStartX(ulx);
        m.setStartY(uly);
        m.setEndX(lrx);
        m.setEndY(lry);
        this.getChildren().add(m);
        System.out.println(ulx + " " + uly + " "+ lrx + " " + urx);
        System.out.println("x: " + this.getLayoutX() + ", y " + this.getLayoutY());
        System.out.println("hx: " + this.getHeight() + ", hy " + this.getWidth());
        
    }
}
