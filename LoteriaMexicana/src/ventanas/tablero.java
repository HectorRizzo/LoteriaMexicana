/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import modelo.Carta;

/**
 *
 * @author daymo
 */
public class tablero {
    GridPane tablero;
    NuevoJuego nj;

    public tablero(NuevoJuego nj) {
        this.nj = nj;
    }
    
    
    public GridPane getTablero() {
        return tablero;
    }

    
    public void crearTablero(TreeMap cartas){

        tablero= new GridPane();
        int rd=1;
        int lenX= 4;
        int lenY=4;
        Set <Integer> contenedo= new HashSet();
        ArrayList<Integer> contenedor=new ArrayList();
        for(int i=1;i<=lenX;i++){
            for(int j=1; j<=lenY;j++){
                boolean b=true;
                while(b){
                    rd = (int)(Math.random()*(52)+1);
                    if( contenedo.add(rd)){
                        b=false;
                        System.out.println("cambio");
                        Carta c= (Carta) cartas.get(rd);
                        ImageView imv=new ImageView(c.getImg());
                        imv.setFitHeight(150);
                        imv.setFitWidth(100);
                        StackPane sp= new StackPane();
                        sp.getChildren().add(imv);
                        sp.setOnMouseClicked(e->ComprobarCarta(nj,c,imv,sp));
                        tablero.add(sp, i, j);
                        System.out.println(rd);
                        System.out.println("Numero pos :"+ i +", "+j);
                    }else{
                        b=true;
                    }        
                }
                
                
            }
        }
    }

    private void PonerBean(ImageView imgv,StackPane sp) {
        
            Image bean;
        try {
            bean = new Image(new FileInputStream("src/images/bean.jpg"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(50);
            frijol.setFitWidth(50);
            sp.getChildren().clear();
            sp.getChildren().addAll(imgv,frijol);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }
    private void PonerX(ImageView imgv,StackPane sp) {
        
            Image equis;
        try {
            equis = new Image(new FileInputStream("src/images/equis.png"));
            ImageView ivequis=new ImageView(equis);
            ivequis.setFitHeight(50);
            ivequis.setFitWidth(50);
            sp.getChildren().clear();
            sp.getChildren().addAll(imgv,ivequis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }

    private void ComprobarCarta(NuevoJuego nj,Carta c,ImageView imv, StackPane sp) {
        if(nj.getGt().getId().equals(c.getId())){
        PonerBean(imv,sp);
        }else{
            PonerX(imv,sp);
        }
    }
}
