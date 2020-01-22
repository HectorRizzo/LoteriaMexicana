/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loteriamexicana;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ventanas.NuevoJuego;
import ventanas.PaneOrganizer;

/**
 *
 * @author daymo
 */
public class LoteriaMexicana extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        NuevoJuego nj= new NuevoJuego();
        PaneOrganizer cf = new PaneOrganizer();
        nj.cargarDeck();      
        Scene scene = new Scene(cf.getBpane(), 800,500);
        //Scene scene = new Scene(nj.getBpNuevoJuego(), 1000,900);
        scene.setFill(Color.AQUA);
        File f = new File("src/css/estilo.css");
        scene.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        scene.getStylesheets().add(getClass().getResource("").toExternalForm());
        primaryStage.setTitle("Loteria Mexicana");
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
