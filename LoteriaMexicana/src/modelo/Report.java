/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author mishelle
 */
public class Report {
    
    public static ArrayList<Report> reportes;
    
    
    public static void lecturaReportes(){
        reportes = new ArrayList<>();
    }
    
    String nombre;
    boolean j1;
    boolean j2;
    String ganador;

    public Report(String nombre, boolean j1, boolean j2, String ganador) {
        this.nombre = nombre;
        this.j1 = j1;
        this.j2 = j2;
        this.ganador = ganador;
    }
    
    
    
}