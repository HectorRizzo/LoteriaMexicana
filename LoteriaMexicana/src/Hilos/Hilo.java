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

    public Thread getThreadComputer1() {
        return threadComputer1;
    }
    
    public Hilo(NuevoJuego nj) {
        this.nj = nj;
    }
    
        
        Thread threadComputer1 = new Thread(new Runnable() {
            
            @Override
            public void run() {
            
                  
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        nj.getComputerT().ComprobarCartaComputer(nj, nj.getComputerT().getCartasComputer(),nj.getComputerT().getImvComputer(),nj.getComputerT().getSpComputer());
                        nj.ComprobarLoteria(nj.getComputerT(),nj.getR());
                        
                        
                    }
                };
             
                while (true) {
                    try {
                        Thread.sleep(2000);
                        
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
            

        });
    }


