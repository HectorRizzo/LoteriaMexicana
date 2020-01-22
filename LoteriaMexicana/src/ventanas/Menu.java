/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author daymo
 */
public class Menu {
    BorderPane bp;
    VBox vbmenu;
    Button btnConf,btnNuevo;
    Configuracion cf;
    NuevoJuego nj;
    Stage stage;
    Scene sceneConf;
    Scene sceneNJ;
    Scene scene;

    public Stage getStage() {
        return stage;
    }

    public Scene getSceneNJ() {
        return sceneNJ;
    }
    
    public Menu() {
    }

    public Menu(Stage stage) {
        this.stage = stage;
    }
    
    
    public Menu(BorderPane bp, VBox vbmenu, Button btnConf, Button btnNuevo) {
        this.bp = bp;
        this.vbmenu = vbmenu;
        this.btnConf = btnConf;
        this.btnNuevo = btnNuevo;
    }
    
    public void IniciarMenu(){
        bp= new BorderPane();
        vbmenu= new VBox();
        btnConf=new Button("Configuración");
        btnConf.setPadding(new Insets(10));
        btnConf.setOnAction(e -> IniciarConfiguracion());
        btnNuevo= new Button("Nuevo Juego");
        btnNuevo.setPadding(new Insets(10));
        btnNuevo.setOnAction(e -> IniciarNuevoJuego());
        scene= new Scene(getBp(), 1000,900);
        
        vbmenu.getChildren().addAll(btnNuevo,btnConf);
        bp.setCenter(vbmenu);
        
    }

    public Scene getScene() {
        return scene;
    }
    
    public BorderPane getBp() {
        return bp;
    }

    private void IniciarConfiguracion() {
        cf= new Configuracion(this);
        sceneConf=new Scene(cf.getBpane(),500,500);
        stage.setScene(sceneConf);
        
    }

    private void IniciarNuevoJuego() {
        NuevoJuego nj= new NuevoJuego();
        nj.cargarDeck();
        sceneNJ= new Scene(nj.getBpNuevoJuego(),1000,900);
        stage.setScene(sceneNJ);
    }
    
    


}
