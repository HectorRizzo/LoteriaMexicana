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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ventanas.Configuracion;
import ventanas.NuevoJuego;

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
        Configuracion cf= new Configuracion();
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(cf.getBpane(), 800,500);
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
