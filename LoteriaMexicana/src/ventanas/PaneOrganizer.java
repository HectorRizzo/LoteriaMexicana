/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author homar
 */
public class PaneOrganizer {
    BorderPane pane;
    VBox vbox;
    Button juego;
    Button configuracion;
    Button reporte;
    
    public BorderPane getBpane() {
        return pane;
    }
    
    public PaneOrganizer(){
        
        pane = new BorderPane();
        vbox = new VBox();
        juego = new Button("Nuevo Juego");
        configuracion = new Button("Configuracion");
        reporte = new Button("Reporte");
        vbox.getChildren().addAll(juego,configuracion,reporte);
        vbox.setSpacing(12);
        vbox.setAlignment(Pos.CENTER);
        pane.setCenter(vbox);
        
        juego.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        configuracion.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        reporte.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        
    }
    
}
