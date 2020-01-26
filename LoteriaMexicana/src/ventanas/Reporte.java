/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Report;
import static modelo.Report.reportes;

/**
 *
 * @author mishelle
 */
public class Reporte {
    
    Button regresar;
    BorderPane bpane;
    TableView tableView;
    Menu m;
    Scene sceneReporte;
    ArrayList <Report> listReportes = new ArrayList<>();
    
    public Reporte(Menu m){
        this.m=m;
        bpane= new BorderPane();
        tableView = new TableView();
        TableColumn<String, Report> column1 = new TableColumn<>("Nombre");
        column1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<String, Report> column2 = new TableColumn<>("Oponentes");
        column2.setCellValueFactory(new PropertyValueFactory<>("oponentes"));
        TableColumn<String, Report> column3 = new TableColumn<>("Ganador");
        column3.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        TableColumn<String, Report> column4 = new TableColumn<>("Regla");
        column4.setCellValueFactory(new PropertyValueFactory<>("regla"));
        TableColumn<String, Report> column5 = new TableColumn<>("Fecha");
        column5.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<String, Report> column6 = new TableColumn<>("Duracion");
        column6.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        
        VBox vbox = new VBox(tableView);
        vbox.setAlignment(Pos.CENTER);
        bpane.setCenter(vbox);
        regresar = new Button("Regresar");
        regresar.setOnAction((ActionEvent event) -> {
                regresarMenu();
            });
        regresar.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(regresar);
        hbox.setAlignment(Pos.TOP_CENTER);
        bpane.setBottom(hbox);
        sceneReporte= new Scene(getBpane(),1000,900);
        sceneReporte.getStylesheets().add("css/estilo.css");
        sceneReporte.getStylesheets().add(getClass().getResource("").toExternalForm());
    }
    
    private void regresarMenu() {
        m.getStage().setScene(m.getScene());
    }

    public Scene getSceneReporte() {
        return sceneReporte;
    }
    
    public BorderPane getBpane() {
        return bpane;
    }
    
    public void cargarReporte(){
        FileReader fr= null;
        try {
            File reportes= new File("src/archivos/reportes.txt");
            fr = new FileReader (reportes);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null){
                System.out.println("linea");
                System.out.println(linea);
                String[] reporte=linea.split(",");
                Report r=new Report(reporte[0],reporte[2],reporte[1],reporte[3],reporte[4],reporte[5]);
                if(listReportes.contains(r)){  
                    System.out.println("t");
                }else{
                    System.out.println("ADD TV");
                    listReportes.add(r);
                    
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public void ActualizarReporte(){
        System.out.println("entra a agregar");
        for(Report r: listReportes){
            
            System.out.println("entra agregar lista");
            System.out.println(r.toString());
            if(tableView.getItems().contains(r)){
                System.out.println("tiene");
            }else{
                tableView.getItems().add(r);
            }
        }
    }
    
    public void AgregarReporte(){
        System.out.println("entra a agregar");
        for(Report r: listReportes){
            
            System.out.println("entra agregar lista");
            System.out.println(r.toString());
            if(tableView.getItems().contains(r)){
                System.out.println("tiene");
            }else{
                
            
            FileWriter writer;
            System.out.println("sol");
            try {
                writer = new FileWriter("src/archivos/reportes.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write(r.getNombre()+","+r.getOponentes()+","+r.getGanador()+","+r.getRegla()+","+r.getDate()+","+r.getDuracion());
                bufferedWriter.newLine();                
                bufferedWriter.close();
                System.out.println("lo escribió");
            } catch (IOException ex) {
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            tableView.getItems().add(r);
            }
        }
        
    }

    public ArrayList<Report> getListReportes() {
        return listReportes;
    }
    
}  
