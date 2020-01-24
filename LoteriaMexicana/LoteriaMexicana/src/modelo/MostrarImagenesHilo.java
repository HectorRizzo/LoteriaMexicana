/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author lfrei
 */
public class MostrarImagenesHilo extends Thread {
    private ImageView imgView;
    private Image img;
    private ArrayList<Carta> cartas;
    
    public MostrarImagenesHilo(ImageView imgView,ArrayList<Carta> cartas){
        this.imgView=imgView;
        this.cartas=cartas;
    }
    
    @Override
    public void run(){
        for(Carta c:cartas){
            String ruta="src/archivos/images/deck/"+c.getId()+".png";
            File mostrar=new File(ruta);
            String ruta1=null;
            try {
                ruta1=mostrar.toURI().toURL().toString();
            } catch (MalformedURLException ex) {
                Logger.getLogger(MostrarImagenesHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image mostrar1=new Image(ruta1,150,150,true,true);
            imgView.setImage(mostrar1);
            System.out.println("imagen cambiada a dormir");
            siesta();
            System.out.println("siesta terminada");
            
        }
        
    }
    
    public void siesta(){
        try{
            sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    
}
