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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modelo.Carta;
import modelo.Report;

/**
 *
 * @author daymo
 */
public class NuevoJuego {
    StackPane spJuego= new StackPane();
    TreeMap <Integer,Carta> cartas=new TreeMap<Integer,Carta>();// mapa donde se guarda la carta con su id
    BorderPane bpNuevoJuego= new BorderPane(); //este es el pane de la aplicacion
    boolean estadoJuego=false; //comprueba si hubo un ganador
    tablero t; //tablero usuario
    Hilo hilo; //hilo que verifica las cartas y el tablero del oponente
    Hilo hilo2;
    tablero computerT; //tablero del oponente
    tablero oponente2;
    reglas r; // nos dice la formacion a completar
    Griton gt; //objeto griton
    Menu m; //objeto menu
    VBox vbright= new VBox(); //lena la parte derecha del tablero
    VBox vbleft= new VBox(); //llena la parte izquierda del tablero
    StackPane sploteria= new StackPane(); //layout para la imagen loteria
    ArrayList<Image> imagenes; //lista de las imagenes de la carta
    ArrayList <Integer> columnasTablero; //lista de la posición en y de las cartas del tablero
    ArrayList <Integer> filasTablero; //lista de la posición x de las del tablero
    String computer="si";
    String ruta="src/images/deck";
    String nombre;

    //constructor
    public NuevoJuego(Menu m,String nombre) { 
        this.m=m;
        //inicializa los hilos, los tableros y la imagen loteria
        this.m = m;
        this.nombre =  nombre;
        r= new reglas();    
        r.cargarRegla();
        t= new tablero(this,r);
        cargarDeck();
        t.crearTablero(cartas);
        
        //hilo creado para la sustentación 2
        if(m.getCf().getAyuda()){
            hilo=new Hilo(this,t);
            hilo.getThreadTablero().setDaemon(true);
            hilo.getThreadTablero().start();
        }
        
        
        //griton
        gt= new Griton(this,cartas);
        gt.aparecerCarta();
        gt.getThread().setDaemon(true);
        gt.getThread().start();
        
        cargarLoteria();
        //oponente1
        computerT=new tablero("computer",this,r);
        computerT.crearTableroComputer(cartas);
        hilo= new Hilo(this,computerT,"Computador 1");
        hilo.getThreadComputer1().setDaemon(true);
        hilo.getThreadComputer1().start();
        //si son 2 oponentes
        if(m.getCf().get2CantOponentes()){
            oponente2=new tablero("computer",this,r);
            oponente2.crearTableroComputer(cartas);
            hilo2= new Hilo(this,oponente2,"Computador 2");
            hilo2.getThreadComputer1().setDaemon(true);
            hilo2.getThreadComputer1().start();
            vbright.getChildren().addAll(gt.getGriton(),sploteria , oponente2.getTablero());

        }else{
            vbright.getChildren().addAll(gt.getGriton(),sploteria);
        }
        
        //agrega los elementos a su pane respectivo
        vbleft.getChildren().addAll(r.getVbreglas(),computerT.getTablero());
        
        bpNuevoJuego.setCenter(t.getTablero());
        bpNuevoJuego.setRight(vbright);
        bpNuevoJuego.setLeft(vbleft);
        Image fondo;
        try{
            fondo = new Image(new FileInputStream("src/images/fondoJuego.jpg"));
            ImageView ivFondo=new ImageView(fondo);
            ivFondo.setFitHeight(1000);
            ivFondo.setFitWidth(1000);
            spJuego.getChildren().addAll(ivFondo,bpNuevoJuego);
            
        }catch(FileNotFoundException ex ){
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);

        }
        
    }
    //carga la imagen Loteria
    public void cargarLoteria(){
        Image lot;
        try {
            lot = new Image(new FileInputStream("src/images/loteria.jpeg"));
            ImageView ivlot=new ImageView(lot);
            ivlot.setFitHeight(70);
            ivlot.setFitWidth(200);
            ivlot.setOnMouseClicked(e-> ComprobarLoteria(t,r,"Jugador"));
            sploteria.getChildren().clear();
            sploteria.setPadding(new Insets(50));
            sploteria.getChildren().add(ivlot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //carga el deck de imagenes
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

    //comprueba si se completó el juego
    public void ComprobarLoteria(tablero t, reglas r,String info) {
       
        if(t.comprobartablero(r)){
            Alert alert=new Alert(Alert.AlertType.WARNING,"Ganó " + info);
            alert.setTitle("Juego Finalizado");
            generarReport(info);
            estadoJuego=true;
            alert.showAndWait(); 
            regresarMenu();
        }   
    }
    
    //genera el reporte del juego
    private void generarReport(String info){
        String duracion=String.valueOf(hilo.getDuracion()*2);
        System.out.println(duracion);
        Report rx = null;
        if(m.getCf().get2CantOponentes()){
            rx = new Report(nombre,info,"2",t.r.obtenerRegla(),duracion+" s");
        }
        else{
            rx = new Report(nombre,info,"1",t.r.obtenerRegla(),duracion+ " s");
        }
        m.getR().getListReportes().add(rx);
        m.getR().AgregarReporte();
    }
    
    private void regresarMenu() {
        m.getStage().setScene(m.getScene());
    }

    //getters
    public Menu getM() {
        return m;
    }
    
    
    public StackPane getSpJuego() {
        return spJuego;
    }
    
    public boolean isEstadoJuego() {
        return estadoJuego;
    }

    public TreeMap<Integer, Carta> getCartas() {
        return cartas;
    }
    
    
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
    
    
    
    
 
    
}
