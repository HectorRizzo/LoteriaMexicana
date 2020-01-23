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
    
    String info;
    String j1;
    String j2;
    String regla;
    String date;

    public Report(String nombre,String info, String j1, String j2, String regla) {
        this.info = info;
        this.j1 = j1;
        this.j2 = j2;
        this.regla = regla;
        this.date = new Date().toString();
    }
    
    
    
}