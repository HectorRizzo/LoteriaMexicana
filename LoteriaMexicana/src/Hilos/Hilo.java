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

    public Thread getThreadComputer1() {
        return threadComputer1;
    }
    
    public Hilo(NuevoJuego nj, tablero t) {
        this.nj = nj;
        this.tablero=t;
    }
    
        
        Thread threadComputer1 = new Thread(new Runnable() {
            
            public void run() {
           
                  
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        tablero.ComprobarCartaComputer(nj , tablero.getCartasComputer(),tablero.getImvComputer(),tablero.getSpComputer());
                        nj.ComprobarLoteria(tablero,nj.getR());
                        
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
    }


