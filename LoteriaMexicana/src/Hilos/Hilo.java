/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import javafx.application.Platform;
import ventanas.NuevoJuego;
import ventanas.tablero;

/**
 *
 * @author daymo
 */
public class Hilo {
    NuevoJuego nj;
    tablero tablero;
    boolean ejecutar= true;
    String info;
    int duracion;
    //Constructores
    public Hilo(NuevoJuego nj, tablero t,String info) {
        this.nj = nj;
        this.tablero=t;
        this.info = info;
    }

    public Hilo(NuevoJuego nj, tablero tablero) {
        this.nj = nj;
        this.tablero = tablero;
    }
    
   //Hilo para la computadora
   Thread threadComputer1 = new Thread(new Runnable() {
            
            public void run() {
           
                  
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        //Comprueba la carta y comprueba si el tablero terminó la regla
                        tablero.ComprobarCartaComputer(nj , tablero.getCartasComputer(),tablero.getImvComputer(),tablero.getSpComputer());
                        nj.ComprobarLoteria(tablero,nj.getR(),info);
                        
                         }
                    
                };
             
                while (true) {
                    try {
                        duracion++;
                        Thread.sleep(2000);
                        
                    synchronized (this) {
                    while (nj.isEstadoJuego()) {
                        Thread.interrupted();
                        wait();
                    }
                        }
                        
                    } catch (InterruptedException ex) {
                      }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }  
        });

    
    //Hilo Sustentacion 2
    Thread threadTablero = new Thread(new Runnable() {
            
            public void run() {
           
                  
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        //Comprueba la carta ayuda en el tablero
                        tablero.ComprobarCartaAyuda(nj , tablero.getCartasJugador(),tablero.getImvJugador(),tablero.getSpJugador());
                         }
                    
                };
             
                while (true) {
                    try {
                        Thread.sleep(2000);
                        
                    synchronized (this) {
                    while (nj.isEstadoJuego()) {
                        Thread.interrupted();
                        wait();
                    }
                        }
                        
                    } catch (InterruptedException ex) {
                      }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }  
        });

    
    //getters y setters
    public Thread getThreadTablero() {
        return threadTablero;
    }

    public Thread getThreadComputer1() {
        return threadComputer1;
    }
        
    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public int getDuracion() {
        return duracion;
    }

    
    }


