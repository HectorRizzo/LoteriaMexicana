/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
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
    Button btnConf,btnNuevo,btnReporte;
    Configuracion cf;
    NuevoJuego nj;
    Reporte r;
    Stage stage;
    Scene sceneConf;
    Scene sceneNJ;
    Scene scene;
    Scene sceneReporte;

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
        btnReporte= new Button("Reporte");
        btnReporte.setPadding(new Insets(10));
        btnReporte.setOnAction(e -> IniciarReporte());
        vbmenu.getChildren().addAll(btnNuevo,btnConf,btnReporte);
        vbmenu.setSpacing(10);
        vbmenu.setAlignment(Pos.CENTER);
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
        sceneConf.getStylesheets().add("css/estilo.css");
        sceneConf.getStylesheets().add(getClass().getResource("").toExternalForm());
        stage.setScene(sceneConf);
        System.out.println("a");
    }

    private void IniciarNuevoJuego() {
        String nombre = pedirNombre();
        NuevoJuego nj= new NuevoJuego(nombre,this);
        nj.cargarDeck();
        sceneNJ= new Scene(nj.getBpNuevoJuego(),1000,900);
        stage.setScene(sceneNJ);
    }
    
    private void IniciarReporte() {
        Reporte r= new Reporte(this);
        sceneReporte= new Scene(r.getBpane(),1000,900);
        sceneReporte.getStylesheets().add("css/estilo.css");
        sceneReporte.getStylesheets().add(getClass().getResource("").toExternalForm());
        stage.setScene(sceneReporte);
        System.out.println("this");
    }
    
    private String pedirNombre(){
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(null);
        tid.setTitle("Nombre");
        tid.setContentText("Introduce tu nombre");
        Optional<String> texto = tid.showAndWait();
        return texto.get();
    }
    
    


}
