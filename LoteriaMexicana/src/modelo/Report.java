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

    public static ArrayList<Report> getReportes() {
        return reportes;
    }

    public static void setReportes(ArrayList<Report> reportes) {
        Report.reportes = reportes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public String getOponentes() {
        return oponentes;
    }

    public void setOponentes(String oponentes) {
        this.oponentes = oponentes;
    }

    public String getRegla() {
        return regla;
    }

    public void setRegla(String regla) {
        this.regla = regla;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
    
    
}