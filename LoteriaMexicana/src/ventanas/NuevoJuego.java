/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

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
    tablero t;
    reglas r;
    Griton gt;
    VBox vbright= new VBox ();
    StackPane sploteria= new StackPane();
    ArrayList<Image> imagenes;
    ArrayList <Integer> columnasTablero;
    ArrayList <Integer> filasTablero;

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
        
        r= new reglas();    
        r.cargarRegla();
        t= new tablero(this,r);
        cargarDeck();
        t.crearTablero(cartas);
        
        gt= new Griton(cartas);
        gt.getThread().setDaemon(true);
        gt.getThread().start();
        cargarLoteria();
        vbright.getChildren().addAll(gt.getGriton(),sploteria);
        
        bpNuevoJuego.setCenter(t.getTablero());
        bpNuevoJuego.setRight(vbright);
        bpNuevoJuego.setLeft(r.getVbreglas());
        

    }
    public void cargarLoteria(){
        Image lot;
        try {
            lot = new Image(new FileInputStream("src/images/loteria.jpeg"));
            ImageView ivlot=new ImageView(lot);
            ivlot.setFitHeight(70);
            ivlot.setFitWidth(200);
            ivlot.setOnMouseClicked(e-> LoteriaClicked());
            sploteria.getChildren().clear();
            sploteria.setPadding(new Insets(50));
            sploteria.getChildren().add(ivlot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private void LoteriaClicked() {
        if(t.comprobartablero(r)){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Ganó");
            alert.showAndWait();
        }
        /*columnasTablero=t.getColumnas();
        filasTablero=t.getFilas();
        */
    }
 
    
    
}
