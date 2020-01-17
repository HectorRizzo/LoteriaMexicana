/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import modelo.Carta;

/**
 *
 * @author daymo
 */
public class NuevoJuego {
    TreeMap <Carta,String> cartas;
    BorderPane bpNuevoJuego;
    GridPane tablero;
    VBox reglas;
    VBox griton;
    
    String ruta="src/images/deck";
    public void cargarDeck(){
        try {
            String archCSV = "src/modelo/cartasloteria.csv";
            CSVReader csvReader = new CSVReader(new FileReader(archCSV));
            List<String[]> dato =csvReader.readAll();            
            for (String[] l: dato){
                System.out.println(l[0]);
            }
            } catch (FileNotFoundException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvException ex) {
            Logger.getLogger(NuevoJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void crearTablero(){
        
    }
    
    
    
    
}
