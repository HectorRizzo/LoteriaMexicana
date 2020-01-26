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
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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
    boolean volver=true;
    boolean visible= true;
    GridPane tablero;
    String tipo="";
    reglas r;
    NuevoJuego nj;
    //Para computer
    ArrayList <Carta> cartasComputer=new ArrayList();
    ArrayList <StackPane> spComputer=new ArrayList();
    ArrayList <ImageView> imvComputer=new ArrayList();
    //Para Jugador
    ArrayList <Carta> cartasJugador=new ArrayList();
    ArrayList <StackPane> spJugador=new ArrayList();
    ArrayList <ImageView> imvJugador=new ArrayList();
    //General
    ArrayList<Integer> columnas=new ArrayList();
    ArrayList <Integer> filas= new ArrayList();

    
//Constructores
    public tablero(NuevoJuego nj ,reglas r) {
        this.r = r;
        this.nj = nj;
    }
    
    public tablero(String tipo,NuevoJuego nj, reglas r) {
        this.tipo=tipo;
        this.nj = nj;
        this.r=r;
    }


    public void crearTablero(TreeMap cartas){
        tablero= new GridPane();
        int rd=1;
        int lenX= 4;
        int lenY=4;
        Set <Integer> contenedo= new HashSet(); //Conjunto para comprobar si la carta ya apareció
        //agrega cartas al tablero
        for(int i=1;i<=lenX;i++){
            for(int j=1; j<=lenY;j++){
                boolean b=true;
                while(b){
                    rd = (int)(Math.random()*(52)+1);
                    if( contenedo.add(rd)){
                        b=false;
                        Carta c= (Carta) cartas.get(rd);
                         ImageView imv=new ImageView(c.getImg());
                        imv.setFitHeight(150);
                        imv.setFitWidth(100);
                        StackPane sp= new StackPane();
                        sp.getChildren().add(imv);
                        sp.setOnMouseClicked(e->ComprobarCarta(nj,c,imv,sp));
                        //agrega al tablero y a los ArrayLists
                        tablero.add(sp, i, j);
                        cartasJugador.add(c);
                        imvJugador.add(imv);
                        spJugador.add(sp);
                    }else{
                        b=true;
                    }        
                }
   
            }
        }
    }

  
    //Comprueba la carta del jugador 
    public void ComprobarCarta(NuevoJuego nj,Carta c,ImageView imv, StackPane sp) {
        //Tema sustentación 1
        if(nj.getGt().getRegistroID().contains(c.getId())){//si la carta si está en el arraylist
            if(c.getEstado()==EstadoCarta.JUGADO){   
            }else{
                if(this.getTipo().equals("computer")){
                
                }else{
                    PonerBean(imv,sp);

                }
                columnas.add(tablero.getColumnIndex(sp));
                filas.add(tablero.getRowIndex(sp));
                
             c.setEstado(EstadoCarta.JUGADO);
             System.out.println(c.getEstado().toString());
            }
            
        
        }  else{
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{   
            PonerX(imv,sp);
            //Hilo para poner la X por 1,5 segundos 
            Thread thread = new Thread(new Runnable() {
             
            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        RegresarOriginal(imv,sp);
                    }
                };
                System.out.println(volver);
                
                    try {
                        while(volver){
                        Thread.sleep(1500);
                        volver=false;
                        } 
                        volver=true;
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                
            }

        });
            thread.setDaemon(true);
            thread.start();
                 }
        }
    }
    //Crea el tablero del jugador 
    public void crearTableroComputer(TreeMap cartas){
        System.out.println(nj.getM());
        visible=nj.getM().getCf().getTableroVisible();
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
                        Carta c= (Carta) cartas.get(rd);
                        Carta c_pc= new Carta(c.getImg(),c.getId());
                        ImageView imv = null;
                        //Comprueba la configuracion de visibilidad
                        if(visible){
                        imv=new ImageView(c_pc.getImg());
                        imv.setFitHeight(80);
                        imv.setFitWidth(60);
                        }else{
                            try {
                                imv=new ImageView(new Image(new FileInputStream("src/deck/back.png")));
                                imv.setFitHeight(80);
                                imv.setFitWidth(60);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        StackPane sp= new StackPane();
                        sp.getChildren().add(imv);
                        cartasComputer.add(c_pc);
                        spComputer.add(sp);
                        imvComputer.add(imv);
                        
                        tablero.add(sp, i, j);
                       
                    }else{
                        b=true;
                    }        
                }
                
                
            }
        }
    }

    //Comprueba el tablero según la regla 
    public boolean comprobartablero(reglas r){
        boolean comp=false;
        int idregla=r.getIdRule();
        TreeMap <Integer,Integer> acumulador= new TreeMap();
        Set <Integer> contadorFC= new HashSet();
        
        int count=0;
        switch(idregla){
            //Regla : columna
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
            break;
                //Regla fila
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
            break;
                //Regla 4 esquinas 
            case 3:
                int index=0;
                for(Integer i: columnas){
                    
                     if(i==1 || i==4){
                         if(filas.get(index)==1||filas.get(index)==4){
                             
                             count++;
                         }
                     }
                     index++;
                    
                }
                if(count==4){
                    comp=true;
                }
            break;
                //Regla cuatro esquinas juntas 
            case 4:
                int verificador1=0,verificador2=0,verificador3=0,verificador4=0; // si alguna esquina está jugada y sus alrededores
                int i=0;
                for(Integer j: columnas){ //Para cada esquina comprueba la esquina y sus alrededores
                    if(j==1&& filas.get(i)==1){
                        int g= 0;
                        for(Integer h:columnas){    
                            if(h==1||h==2){
                                if(filas.get(g)==1||filas.get(g)==2){
                                    verificador1++;  
                                }
                            }
                            g++;
                        }   
                    }
                    if(j==1&& filas.get(i)==4){
                        int g= 0;
                        for(Integer h:columnas){
                            if(h==1||h==2){
                                if(filas.get(g)==4||filas.get(g)==3){
                                    verificador2++;  
                                }
                            }
                            g++;

                        }   
                    }
                    if(j==4&& filas.get(i)==1){
                        int g= 0;
                        for(Integer h:columnas){
                            if(h==3||h==4){
                                if(filas.get(g)==1||filas.get(g)==2){
                                    verificador3++;  
                                }
                            }
                            g++;
                        }
                        
                    }
                    if(j==4&& filas.get(i)==4){
                        int g= 0;
                        for(Integer h:columnas){
                            if(h==3||h==4){
                                if(filas.get(g)==3||filas.get(g)==4){
                                    verificador4++;  
                                }
                            } 
                        g++;
                        }   
                    }
                    i++;
                    
                }
                if(verificador1==4||verificador2==4||verificador3==4||verificador4==4){
                    comp=true;
                }
            break;
                
                
        }
        return comp;
    }
    
    //Comprueba la carta automaticamente de la computadora 
    public void ComprobarCartaComputer(NuevoJuego nj,ArrayList<Carta> cartas,ArrayList <ImageView> imvcomputer, ArrayList <StackPane> spcomputer) {
        int index=0;
        for(Carta c:cartas){
        if(nj.getGt().getId().equals(c.getId())){
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{
                if(visible){
                PonerBean(imvcomputer.get(index),spcomputer.get(index));

                }else{
                    
                    Image bean;
            try {
            bean = new Image(new FileInputStream("src/deck/match.png"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(80);
            frijol.setFitWidth(60);
            spcomputer.get(index).getChildren().clear();
            spcomputer.get(index).getChildren().addAll(imvcomputer.get(index),frijol);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
                }
                columnas.add(tablero.getColumnIndex(spcomputer.get(index)));
                filas.add(tablero.getRowIndex(spcomputer.get(index)));
                
                c.setEstado(EstadoCarta.JUGADO);
            }
        
        }else{
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{
                
            }
        }
        index++;
        }
    }

    //tema 2 sustentacion
    public void ComprobarCartaAyuda(NuevoJuego nj,ArrayList<Carta> cartas,ArrayList <ImageView> imv, ArrayList <StackPane> sp) {
        int index=0;
        for(Carta c:cartas){
        if(nj.getGt().getId().equals(c.getId())){
            if(c.getEstado()==EstadoCarta.JUGADO){
                
            }else{
                
                PonerAlerta(imv.get(index),sp.get(index));

                
                columnas.add(tablero.getColumnIndex(sp.get(index)));
                filas.add(tablero.getRowIndex(sp.get(index)));
            }
                
            
        
        }else{
            
            
        }
        index++;
        }
    }

      //Pone las imagenes al frente
    private void PonerBean(ImageView imgv,StackPane sp) {
        
        Image bean;
        try {
            bean = new Image(new FileInputStream("src/images/bean.png"));
            ImageView frijol=new ImageView(bean);
            frijol.setFitHeight(60);
            frijol.setFitWidth(40);
            sp.getChildren().clear();
            sp.getChildren().addAll(imgv,frijol);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }
    //Sustentacion 2
    private void PonerAlerta(ImageView imgv,StackPane sp) {
        
        Image alerta;
        try {
            alerta = new Image(new FileInputStream("src/images/ayuda.png"));
            ImageView help=new ImageView(alerta);
            help.setFitHeight(50);
            help.setFitWidth(40);
            
            help.setTranslateX(26);
            help.setTranslateY(-48);
            sp.getChildren().clear();
            sp.getChildren().addAll(imgv,help);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }
        
    private void RegresarOriginal(ImageView imgv,StackPane sp){
        sp.getChildren().clear();
        sp.getChildren().add(imgv);
        
    }
    private void PonerX(ImageView imgv,StackPane sp) {
        
            Image equis;
        try {
            equis = new Image(new FileInputStream("src/images/equis.png"));
            ImageView ivequis=new ImageView(equis);
            ivequis.setFitHeight(120);
            ivequis.setFitWidth(100);
            
            sp.getChildren().clear();
            sp.getChildren().addAll(imgv,ivequis);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }

    
    //Getters
    public ArrayList<StackPane> getSpJugador() {
        return spJugador;
    }

    public ArrayList<ImageView> getImvJugador() {
        return imvJugador;
    }
    
        public ArrayList<StackPane> getSpComputer() {
        return spComputer;
    }

    public ArrayList<ImageView> getImvComputer() {
        return imvComputer;
    }
    
    public GridPane getTablero() {
        return tablero;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<Carta> getCartasComputer() {
        return cartasComputer;
    }

    public ArrayList<Carta> getCartasJugador() {
        return cartasJugador;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public ArrayList<Integer> getColumnas() {
        return columnas;
    }

    public ArrayList<Integer> getFilas() {
        return filas;
    }


}
