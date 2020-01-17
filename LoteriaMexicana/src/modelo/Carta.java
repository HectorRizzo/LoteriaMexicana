/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author daymo
 */
public class Carta {
    ImageView imgview;
    String name;
    EstadoCarta estado;

    public Carta(ImageView imgview, String name) {
        this.imgview = imgview;
        this.name = name;
    }

    
    
    public Carta(ImageView imgview, String name, EstadoCarta estado) {
        this.imgview = imgview;
        this.name = name;
        this.estado = estado;
    }
    
    
    
}
