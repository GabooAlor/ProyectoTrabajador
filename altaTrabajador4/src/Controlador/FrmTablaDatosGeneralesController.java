/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import conexion.Conexion;
import Controlador.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.trabajador;


public class FrmTablaDatosGeneralesController implements Initializable {

    @FXML
    private TableView<trabajador> tableDatosGenerales;
    @FXML
    private TableColumn<trabajador, String> colNombre;
    @FXML
    private TableColumn<trabajador, String> colRfc;
    @FXML
    private TableColumn<trabajador, String> colNss;
    @FXML
    private TableColumn<?, ?> colAcciones;

    @FXML ObservableList<trabajador> listaTrabajador;
    private Conexion conexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       listaTrabajador = FXCollections.observableArrayList();
       trabajador.llenarDatos(conexion.getConnection(), listaTrabajador);
       tableDatosGenerales.setItems(listaTrabajador);
       
       colNombre.setCellValueFactory(new PropertyValueFactory<trabajador, String>("nombre"));
       colRfc.setCellValueFactory(new PropertyValueFactory<trabajador, String>("rfc"));
       colNss.setCellValueFactory(new PropertyValueFactory<trabajador, String>("nss"));
       gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        tableDatosGenerales.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<trabajador>(){
                    @Override
                    public void changed (ObservableValue <? extends trabajador> arg0, trabajador valorAnterior, trabajador valorSeleccionado){
                        if(valorSeleccionado != null){
                            //para mas adelante
                        }
                    }
                }
        
        );
    }
    
   
    
}
