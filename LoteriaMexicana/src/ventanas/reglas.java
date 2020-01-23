/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author daymo
 */
public class reglas {

    public TreeMap<Integer, String> getDicRules() {
        return dicRules;
    }

    public VBox getVbreglas() {
        return vbreglas;
    }
    

    public reglas() {
    }
    
    
    TreeMap <Integer,String> dicRules=new TreeMap<Integer,String>();
    VBox vbreglas= new VBox();
    ImageView ivrul;
    Integer idRule=1;
    public ImageView getIvrul() {
        return ivrul;
    }

    public Integer getIdRule() {
        return idRule;
    }

    public void cargarRegla(){
        dicRules.put(1, "columna");
        dicRules.put(2,"fila");
        dicRules.put(3, "cuatro_esquinas");
        dicRules.put(4, "cuatro_esquinas_juntas");

        Image rul;
        
        idRule = (int)(Math.random()*(4)+1);
        try {
            rul = new Image(new FileInputStream("src/images/"+dicRules.get(idRule)+".jpeg"));
            ivrul=new ImageView(rul);
            ivrul.setFitHeight(300);
            ivrul.setFitWidth(200);
            vbreglas.getChildren().clear();
            vbreglas.setPadding(new Insets(50));
            vbreglas.getChildren().add(ivrul);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void LoteriaClicked() {
    }
    public void formacion(){
        
    }
    
    public String obtenerRegla(){
        return dicRules.get(idRule);
    }

    
}
