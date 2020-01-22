/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author daymo
 */
public class Configuracion {
    Menu m;
    tablero t;
    NuevoJuego nj;
    GridPane gpane;
    BorderPane bpane;
    Label lbCant;
    Label lbVisible;
    RadioButton rbCant1, rbCant2;
    RadioButton rbVisible, rbOculto;
    Button regresar;

    public BorderPane getBpane() {
        return bpane;
    }

    public Configuracion(Menu m) {
        this.m=m;
        gpane= new GridPane();
         bpane= new BorderPane();
        VBox vconf= new VBox();
         /*
        HBox cant= new HBox();
        HBox cant= new HBox();
        */
        //Labels
        lbCant=new Label("Cantidad de oponentes: ");
        lbVisible= new Label("Visibilidad de cartas de oponentes: ");
        
        //Button
        ToggleGroup tgVisible = new ToggleGroup();
        ToggleGroup oponentes = new ToggleGroup();
        rbCant1= new RadioButton("1 Oponente");
        rbCant2= new RadioButton("2 Oponentes");
        rbVisible=new RadioButton("Visible");
        rbOculto= new RadioButton("Oculto");
        rbCant1.setToggleGroup(oponentes);
        rbCant2.setToggleGroup(oponentes);
        rbVisible.setToggleGroup(tgVisible);
        rbOculto.setToggleGroup(tgVisible);
        regresar=new Button("Regresar");
        regresar.setOnAction(e-> regresarMenu());
        gpane.setPadding(new Insets(10));
        gpane.setHgap(20);
        gpane.setVgap(20);
        GridPane.setConstraints(lbCant, 1,1 );
        GridPane.setConstraints(rbCant1,2,1 );
        GridPane.setConstraints(rbCant2, 3,1 );
        GridPane.setConstraints(lbVisible,1,2 );
        GridPane.setConstraints(rbVisible,2,2 );
        GridPane.setConstraints(rbOculto, 3,2 );
        bpane.setCenter(gpane);
        bpane.setTop(vconf);
        gpane.getChildren().addAll(lbCant,lbVisible,rbCant1,rbCant2,rbVisible,rbOculto);
        bpane.setBottom(regresar);
        
        if(tgVisible.getSelectedToggle()==rbOculto){
            t.setVisible(false);
            //nj.getComputerT().setVisible(false);
        }



        
    }

    public GridPane getGpane() {
        return gpane;
    }

    private void regresarMenu() {
        m.getStage().setScene(m.getScene());
    }
    
    
}
