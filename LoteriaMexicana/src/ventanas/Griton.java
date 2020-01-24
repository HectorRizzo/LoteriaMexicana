/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import modelo.Carta;

/**
 *
 * @author daymo
 */
public class Griton {
    VBox Griton=new VBox();
    TreeMap <Integer,Carta>tm;
    Timer timer = new Timer();
    ImageView iv;
    Set <Integer>gen= new HashSet();
    int primeraCarta= (int) (Math.random()*(52)+1);
    NuevoJuego nj;
    String id;
    ArrayList <String> registroID=new ArrayList();
    Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        aparecerCarta();
                    }
                };
                
                while (!nj.isEstadoJuego()) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
    Griton() {

    }

    
    public VBox getGriton() {
        return Griton;
    }

    public String getId() {
        return id;
    }

    
    
    public Griton(NuevoJuego nj,TreeMap<Integer, Carta> tm) {
        this.nj=nj;
        this.tm = tm;
        gen.add(primeraCarta);
    }
    
    
    public void aparecerCarta(){
        
        boolean b= true;
        int rd=1;
        while(b){
            rd = (int)(Math.random()*(52)+1);
            if(gen.contains(rd)){
                b=true;
            }else{
                gen.add(rd);
                b=false;
            }        
         }
        id=tm.get(rd).getId();
        registroID.add(id);
        iv= new ImageView(tm.get(rd).getImg()); 
        
        Griton.getChildren().setAll(iv);
        Griton.setAlignment(Pos.CENTER);
    }

    public Thread getThread() {
        return thread;
    }

    public ArrayList<String> getRegistroID() {
        return registroID;
    }
    
    
    
}
