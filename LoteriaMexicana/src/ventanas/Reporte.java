/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modelo.Report;

/**
 *
 * @author mishelle
 */
public class Reporte {
    
    Button regresar;
    BorderPane bpane;
    TableView tableView;
    Menu m;
    
    public Reporte(Menu m){
        this.m=m;
        bpane= new BorderPane();
        tableView = new TableView();
        TableColumn<String, Report> column1 = new TableColumn<>("Fecha");
        column1.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn<String, Report> column2 = new TableColumn<>("Duracion");
        column2.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        TableColumn<String, Report> column3 = new TableColumn<>("Nombre");
        column3.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<String, Report> column4 = new TableColumn<>("Oponentes");
        column4.setCellValueFactory(new PropertyValueFactory<>("oponentes"));
        TableColumn<String, Report> column5 = new TableColumn<>("Alineacion");
        column5.setCellValueFactory(new PropertyValueFactory<>("alineacion"));
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);

        //tableView.getItems().add(reporte1);
        //tableView.getItems().add(reporte2);

        VBox vbox = new VBox(tableView);
        vbox.setAlignment(Pos.CENTER);
        bpane.setCenter(vbox);
        regresar = new Button("Regresar");
        regresar.setOnAction((ActionEvent event) -> {

            });
        regresar.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(regresar);
        hbox.setAlignment(Pos.TOP_CENTER);
        bpane.setBottom(hbox);
    }
    
    private void regresarMenu() {
        m.getStage().setScene(m.getScene());
    }
    
    public BorderPane getBpane() {
        return bpane;
    }
    
   
    

}  