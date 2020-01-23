/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.Date;

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
    String ganador;
    String oponentes;
    String regla;
    String date;

    public Report(String nombre,String info, String oponentes, String regla) {
        this.nombre = nombre;
        this.ganador = info;
        this.oponentes = oponentes;
        this.regla = regla;
        this.date = new Date().toString();
    }
    
    
    
}