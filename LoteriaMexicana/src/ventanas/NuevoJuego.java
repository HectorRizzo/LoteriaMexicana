/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import Hilos.Hilo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.Carta;

/**
 *
 * @author daymo
 */
public class NuevoJuego {
    TreeMap <Integer,Carta> cartas=new TreeMap<Integer,Carta>();
    BorderPane bpNuevoJuego= new BorderPane();
    boolean estadoJuego=true;
    tablero t;
    Hilo hilo;
    tablero computerT;
    reglas r;
    Griton gt;
    VBox vbright= new VBox ();
    VBox vbleft= new VBox();
    StackPane sploteria= new StackPane();
    ArrayList<Image> imagenes;
    ArrayList <Integer> columnasTablero;
    ArrayList <Integer> filasTablero;
    String computer="si";

    public tablero getComputerT() {
        return computerT;
    }

    public reglas getR() {
        return r;
    }
    
    
    public tablero getT() {
        return t;
    }

    public Griton getGt() {
        return gt;
    }

    

    public ArrayList<Image> getImagenes() {
        return imagenes;
    }

    public String getRuta() {
        return ruta;
    }
    
    public BorderPane getBpNuevoJuego() {
           
        return bpNuevoJuego;
    }
    
    public NuevoJuego() {
        hilo= new Hilo(this);
        r= new reglas();    
        r.cargarRegla();
        t= new tablero("usuario",this,r);
        cargarDeck();
        t.crearTablero(cartas,"no");

        //griton
        gt= new Griton(this,cartas);
        gt.aparecerCarta();
        gt.getThread().setDaemon(true);
        gt.getThread().start();
        
        cargarLoteria();
        //oponente
        computerT=new tablero("computer",this,r);
        computerT.crearTableroComputer(cartas,computer);
        hilo.getThreadComputer1().setDaemon(true);
        hilo.getThreadComputer1().start();
        
        vbright.getChildren().addAll(gt.getGriton(),sploteria);
        vbleft.getChildren().addAll(r.getVbreglas(),computerT.getTablero());
        bpNuevoJuego.setCenter(t.getTablero());
        bpNuevoJuego.setRight(vbright);
        bpNuevoJuego.setLeft(vbleft);
        

    }
    public void cargarLoteria(){
        Image lot;
        try {
            lot = new Image(new FileInputStream("src/images/loteria.jpeg"));
            ImageView ivlot=new ImageView(lot);
            ivlot.setFitHeight(70);
            ivlot.setFitWidth(200);
            ivlot.setOnMouseClicked(e-> ComprobarLoteria(t,r));
            sploteria.getChildren().clear();
            sploteria.setPadding(new Insets(50));
            sploteria.getChildren().add(ivlot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    
    
    public TreeMap<Integer, Carta> getCartas() {
        return cartas;
    }
    
    String ruta="src/images/deck";
    public void cargarDeck(){
        
        try {
            String archCSV = "src/modelo/cartasloteria.csv";
            CSVReader csvReader = new CSVReader(new FileReader(archCSV));
            List<String[]> dato =csvReader.readAll();            
            for (String[] l: dato){
                
                Image img=new Image(new FileInputStream("src/deck/"+l[0]+".png"));
                Carta c= new Carta(img,l[0]);
                cartas.put(Integer.parseInt(l[0]),c);
                

            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ComprobarLoteria(tablero t, reglas r) {
       
        if(t.comprobartablero(r)){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Ganó");
            alert.showAndWait();
            estadoJuego=false;
        }
        
    }
    
    
 
    
}
