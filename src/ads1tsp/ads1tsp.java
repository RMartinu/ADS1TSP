/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp;

import ads1tsp.GUI.Plotter;
import ads1tsp.GUI.PlotterPane;
import ads1tsp.Solvers.DummySettingsPane;
import ads1tsp.Solvers.DummySolver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class ads1tsp extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        PlotterPane p = new PlotterPane();
//        PlotterPane q = new PlotterPane();
//        DummySettingsPane dp=new DummySettingsPane(new DummySolver());
//        p.setMinSize(300, 300);
//        q.setMinSize(300, 300);
//        //VBox v= new VBox(p,q);
//        BorderPane bp = new BorderPane();
//        bp.setBottom(q);
//        bp.setTop(p);
//        bp.setCenter(dp);
//        p.repaint();
//        q.repaint();
Plotter myPlotter=new Plotter();
        Scene s = new Scene(myPlotter, 1000, 700);
//        bp.setMinSize(300, 300);
        primaryStage.setScene(s);
        s.widthProperty().addListener((obs,a,b)->{System.err.println("scene res");});
        primaryStage.widthProperty().addListener((obs,a,b)->{System.err.println("Stage res");});
        primaryStage.show();
     
    }

}
