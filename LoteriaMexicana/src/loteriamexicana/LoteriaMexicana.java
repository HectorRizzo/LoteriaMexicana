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
import javafx.stage.Stage;
import ventanas.Configuracion;
import ventanas.Griton;
import ventanas.NuevoJuego;
import ventanas.tablero;

/**
 *
 * @author daymo
 */
public class LoteriaMexicana extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        NuevoJuego nj= new NuevoJuego();
        nj.cargarDeck();
        
        //Scene scene = new Scene(cf.getBpane(), 800,500);
        Scene scene = new Scene(nj.getBpNuevoJuego(), 1390,1200);
        File f = new File("src/css/estilo.css");
        scene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        scene.getStylesheets().add(getClass().getResource("").toExternalForm());
        primaryStage.setTitle("Configuración");
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
