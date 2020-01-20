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
import modelo.EstadoCarta;

/**
 *
 * @author daymo
 */
public class tablero {
    GridPane tablero;
    reglas r;
    NuevoJuego nj;
    ArrayList<Integer> columnas=new ArrayList();
    ArrayList <Integer> filas= new ArrayList();
    public tablero(NuevoJuego nj, reglas r) {
        this.nj = nj;
        this.r=r;
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
                        tablero.setStyle("-fx-background-color: yellow, orange ; -fx-grid-lines-visible: true");
                        
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

    private void ComprobarCarta(NuevoJuego nj,Carta c,ImageView imv, StackPane sp) 
    
    {
        System.out.println(c.getEstado()==EstadoCarta.JUGADO);
        if(nj.getGt().getId().equals(c.getId())){
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{
                PonerBean(imv,sp);
                System.out.println(tablero.getColumnIndex(sp));
                columnas.add(tablero.getColumnIndex(sp));
                filas.add(tablero.getRowIndex(sp));
                
        c.setEstado(EstadoCarta.JUGADO);
            }
        
        }else{
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{
                PonerX(imv,sp);
            }
        }
    }

    public ArrayList<Integer> getColumnas() {
        return columnas;
    }

    public ArrayList<Integer> getFilas() {
        return filas;
    }

    public boolean comprobartablero(reglas r){
        boolean comp=false;
        int idregla=r.getIdRule();
        TreeMap <Integer,Integer> acumulador= new TreeMap();
        Set <Integer> contadorFC= new HashSet();
        switch(idregla){
            case 1:
                for(Integer i:columnas){
                    if(contadorFC.add(i)){
                        contadorFC.add(i);
                        acumulador.put(i,1);
                    }else{
                        int valor=acumulador.get(i);
                        acumulador.put(i,++valor);
                        if(valor==4){
                            comp=true;
                        }
                    }
                }
            case 2:
                for(Integer i:filas){
                    if(contadorFC.add(i)){
                        contadorFC.add(i);
                        acumulador.put(i,1);
                    }else{
                        int valor=acumulador.get(i);
                        acumulador.put(i,++valor);
                        if(valor==4){
                            comp=true;
                        }
                    }
                }
                
                
        }
        return comp;
    }

   
}
