/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author carolinaoc
 */
public class Tabla {
    private int N_FILAS=4;
    private int N_COLUMNAS=4;
    private BorderPane principal;
    private GridPane tabla;
    private TreeMap<String,Carta> posicionCartas;
    
    public Tabla(MazoCartas mazo){
        tabla=new GridPane();
        principal=new BorderPane();
        posicionCartas=new TreeMap<>();
        tabla.setStyle("-fx-background-color:green");
        //to set horizontal and vertical gap
        tabla.setHgap(5);
        tabla.setVgap(5);
        mazo.barajar();
        ArrayList<Carta> arregloCartas=mazo.getArregloCartas();
        int k=0;
        for (int i=0;i<N_FILAS;i++){
            for (int j=0;j<N_COLUMNAS;j++){
                Carta carta=arregloCartas.get(k);
                StackPane rootCarta= new StackPane();
                ImageView img=new ImageView(arregloCartas.get(k).getImagen());
                img.setFitHeight(100);
                img.setFitWidth(100);

                rootCarta.getChildren().add(img);
                img.setOnMouseClicked(e->{
                    agregarBean(img,rootCarta);
                });
                tabla.add(rootCarta, i, j);
                String pos=String.valueOf(i)+","+String.valueOf(j);
                posicionCartas.put(pos,carta);
                k++;
            }
        }
        System.out.println("constructor");
        System.out.println(posicionCartas);
        
        principal.setCenter(tabla);
        /*VBox box=new VBox(5);
        GridPane cntbeans= new GridPane();
        try {
            Image bean=new Image(new FileInputStream("src/archivos/images/bean.jpg"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(50);
            frijol.setFitWidth(50);
            cntbeans.add(frijol,0,0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        HBox cntBotones=new HBox(5);
        Button loteria=new Button("Loteria");
        cntBotones.getChildren().add(loteria);
        box.getChildren().addAll(cntBotones,cntbeans);
        principal.setRight(box);*/
        
        contenidoDerecho(mazo);
    }
    
    public BorderPane getRoot(){
        return principal;
    }  
    
    public void ocultarTabla(){
        MazoCartas mazo=new MazoCartas();
        principal=new BorderPane();
        tabla=new GridPane();
        posicionCartas=new TreeMap<>();
        tabla.setStyle("-fx-background-color:green");
         //to set horizontal and vertical gap
        tabla.setHgap(5);
        tabla.setVgap(5);
        mazo.barajar();
        ArrayList<Carta> arregloCartas=mazo.getArregloCartas();
        int k=0;
        System.out.println("ocultar");
        for (int i=0;i<N_FILAS;i++){
            for (int j=0;j<N_COLUMNAS;j++){
                Carta carta=arregloCartas.get(k);
                ImageView img=new ImageView(carta.ocultarCarta());
                img.setFitHeight(100);
                img.setFitWidth(100);
                StackPane rootCarta=new StackPane();
                rootCarta.getChildren().add(img);
                tabla.add(rootCarta, i, j);
                String pos=String.valueOf(i)+","+String.valueOf(j);
                posicionCartas.put(pos,carta);
                k++;
            }
        }
        System.out.println(posicionCartas);
        
        principal.setCenter(tabla);
        //contenidoDerecho(mazo);
    }  
    public void agregarBean(ImageView img,StackPane root){
        try {
            Image bean=new Image(new FileInputStream("src/archivos/images/bean.jpg"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(50);
            frijol.setFitWidth(50);
            root.getChildren().clear();
            root.getChildren().addAll(img,frijol);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void contenidoDerecho(MazoCartas mazo){
        VBox form1=new VBox(5);
        GridPane cntbeans= new GridPane();
        cntbeans.setPadding(new Insets(20));
        cntbeans.setHgap(25);
        cntbeans.setVgap(15);
        try {
            Image bean=new Image(new FileInputStream("src/archivos/images/bean.jpg"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(50);
            frijol.setFitWidth(50);
            cntbeans.add(frijol,0,0);            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Image oculta=null;
        try {
            oculta = new Image(new FileInputStream("src/archivos/images/deck/back.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageView oculta1=new ImageView(oculta);
        oculta1.setFitHeight(100);
        oculta1.setFitWidth(100);
        mazo.barajar();
        ArrayList<Carta> mazocartas=mazo.getArregloCartas();
        MostrarImagenesHilo mih=new MostrarImagenesHilo(oculta1,mazocartas);
        mih.start();
        
        HBox cntBotones=new HBox(5);
        Button loteria=new Button("Loteria");
        cntBotones.getChildren().add(loteria);
        cntBotones.setAlignment(Pos.CENTER);
        form1.getChildren().addAll(cntbeans,cntBotones,oculta1);
        principal.setRight(form1);
    }
}
