/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
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
    Reporte r= new Reporte(this);
    Stage stage;
    Scene sceneConf;
    Scene sceneNJ;
    Scene scene;
    Scene sceneReporte;
    StackPane spMenu= new StackPane();
    
    
//Constructores
    public Menu(Stage stage) {
        this.stage = stage;
    }
    public Menu(BorderPane bp, VBox vbmenu, Button btnConf, Button btnNuevo) {
        this.bp = bp;
        this.vbmenu = vbmenu;
        this.btnConf = btnConf;
        this.btnNuevo = btnNuevo;
    }
    
    //Inicia el menu con todos sus panes y botones
    public void IniciarMenu(){
        bp= new BorderPane();
        vbmenu= new VBox();
        btnConf=new Button("Configuración");
        btnConf.setPadding(new Insets(10));
        btnConf.setOnAction(e -> IniciarConfiguracion());
        btnNuevo= new Button("Nuevo Juego");
        btnNuevo.setPadding(new Insets(10));
        btnNuevo.setOnAction(e -> IniciarNuevoJuego());
        btnReporte= new Button("Reporte");
        btnReporte.setPadding(new Insets(10));
        btnReporte.setOnAction(e -> IniciarReporte());
        vbmenu.getChildren().addAll(btnConf,btnNuevo,btnReporte);
        vbmenu.setSpacing(10);
        vbmenu.setAlignment(Pos.CENTER);
        bp.setCenter(vbmenu);
        Image fondo;
        try{
            fondo = new Image(new FileInputStream("src/images/fondo.jpg"));
            ImageView ivFondo=new ImageView(fondo);
            ivFondo.setFitHeight(1000);
            ivFondo.setFitWidth(1000);
            spMenu.getChildren().addAll(ivFondo,bp);
            
        }catch(FileNotFoundException ex ){
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);

        }
        scene= new Scene(getSpMenu(),900,900);
        r.cargarReporte(); //carga el archivo de juegos anteriores
    }

    private void IniciarConfiguracion() {
        cf= new Configuracion(this);
        sceneConf=new Scene(cf.getSpConf(),500,500);
        sceneConf.getStylesheets().add("css/estilo.css");
        sceneConf.getStylesheets().add(getClass().getResource("").toExternalForm());
        stage.setScene(sceneConf);
        System.out.println("a");
    }

    private void IniciarNuevoJuego() {
        String nombre = pedirNombre();
        System.out.println(getCf());
        if(getCf()==null){
            cf= new Configuracion(this);
        }
        NuevoJuego nj= new NuevoJuego(this,nombre);
        nj.cargarDeck();
        sceneNJ= new Scene(nj.getSpJuego());
        stage.setScene(sceneNJ);
    }
    
    private void IniciarReporte() {
        System.out.println("df");
        r.ActualizarReporte();
        stage.setScene(r.getSceneReporte());
    }
    
    private String pedirNombre(){
        TextInputDialog tid = new TextInputDialog();
        tid.setHeaderText(null);
        tid.setTitle("Nombre");
        tid.setContentText("Introduce tu nombre");
        Optional<String> texto = tid.showAndWait();
        return texto.get();
    }

    public Configuracion getCf() {
        return cf;
    }

    public Reporte getR() {
        return r;
    }

    public StackPane getSpMenu() {
        return spMenu;
    }
    
    public Stage getStage() {
        return stage;
    }

    public Scene getSceneNJ() {
        return sceneNJ;
    }
    
      public Scene getScene() {
        return scene;
    }
    
    public BorderPane getBp() {
        return bp;
    }

}
