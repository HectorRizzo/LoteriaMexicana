/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicana;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import modelo.Carta;
import modelo.MazoCartas;
import modelo.Tabla;

/**
 *
 * @author carolinaoc
 */
public class LoteriaMexicana extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Tabla tabla=new Tabla(new MazoCartas());
        
        Scene scene = new Scene(tabla.getRoot(),400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    } 
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
