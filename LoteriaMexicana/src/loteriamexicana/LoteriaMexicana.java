/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicana;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Report;
import ventanas.Configuracion;
import ventanas.Griton;
import ventanas.Menu;
import ventanas.NuevoJuego;
import ventanas.tablero;

/**
 *
 * @author daymo
 */
public class LoteriaMexicana extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       Menu m= new Menu(primaryStage);
       m.IniciarMenu();
       Report.lecturaReportes();
        
        
        //Scene scene = new Scene(cf.getBpane(), 800,500);
        m.getScene().setFill(Color.AQUA);
        File f = new File("src/css/estilo.css");
        m.getScene().getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        m.getScene().getStylesheets().add(getClass().getResource("").toExternalForm());
        primaryStage.setTitle("Loteria Mexicana");
        primaryStage.setScene(m.getScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
