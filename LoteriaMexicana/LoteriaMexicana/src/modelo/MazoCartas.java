/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 *
 * @author carolinaoc
 */
public class MazoCartas extends Thread {
    private int N_CARTAS=54;
    private ArrayList<Carta> arregloCartas;
    //private StackPane imgCarta;

    public MazoCartas() {
        arregloCartas=cargarDatos();
    }

    public int getN_CARTAS() {
        return N_CARTAS;
    }

    public void setN_CARTAS(int N_CARTAS) {
        this.N_CARTAS = N_CARTAS;
    }

    public ArrayList<Carta> getArregloCartas() {
        return arregloCartas;
    }

    public void setArregloCartas(ArrayList<Carta> arregloCartas) {
        this.arregloCartas = arregloCartas;
    }
    
    /*public StackPane getImgCarta(){
        return imgCarta;
    }*/
    
    public ArrayList<Carta> cargarDatos(){
        ArrayList<Carta> arreglo=new ArrayList<>();
        try {
            FileReader txt=new FileReader("src/archivos/cartasloteria.csv");
            BufferedReader readerTxt=new BufferedReader(txt);
            String linea;
            while ((linea=readerTxt.readLine())!=null){
                String[] datos=linea.split(",");
                String id_Carta=datos[0];
                String nombre_Carta=datos[1];
                try{
                    Image img1=new Image(new FileInputStream("src/archivos/images/deck/"+id_Carta+".png"));
                    Carta carta=new Carta(nombre_Carta,img1,id_Carta);
                    arreglo.add(carta);
                }catch(IOException ex){
                    ex.getMessage();
                }
            }  
        } catch (FileNotFoundException ex) {
            System.out.println("archivos no encontrados");
        } catch (IOException ex) {
            Logger.getLogger("archivos no encontrados");
        }
        return arreglo;
    }
    
    public void barajar(){
        Collections.shuffle(arregloCartas);
    }
    
    /*@Override
    public void run() {
        for (Carta c:arregloCartas) {
                /*String ruta = "src/img/alcancia" + i + ".png";
                File romper1 = new File(ruta);

                String ruta1 = null;

                try {
                    ruta1 = romper1.toURI().toURL().toString();

                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }*/

                /*ImageView img=new ImageView(c.getImagen());
                imgCarta.getChildren().clear();
                imgCarta.getChildren().add(img);
                System.out.println("imagen cambiada a dormir");
                siesta();
                System.out.println("siesta terminada");
        }
        //ubicar primera imagen
        imgCarta.getChildren().clear();
        imgCarta.getChildren().add(new ImageView(arregloCartas.get(-1).getImagen()));
    }
    
    public void siesta(){
        try{
            sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }*/
    
    
    
    
}

