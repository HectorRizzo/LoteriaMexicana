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
    Image img;
    String id;
    EstadoCarta estado;

    public Carta(Image img, String id) {
        this.img= img;
        this.id = id;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEstado(EstadoCarta estado) {
        this.estado = estado;
    }

    
    
    public Carta(Image img, String id, EstadoCarta estado) {
        this.img = img;
        this.id = id;
        this.estado = estado;
    }

    public Image getImg() {
        return img;
    }

    public String getId() {
        return id;
    }

    public EstadoCarta getEstado() {
        return estado;
    }
    
    
    
}
