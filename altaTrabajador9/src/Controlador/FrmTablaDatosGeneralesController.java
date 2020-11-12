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
import modelo.Trabajador;


public class FrmTablaDatosGeneralesController implements Initializable {

    @FXML
    private TableView<Trabajador> tableDatosGenerales;
    @FXML
    private TableColumn<Trabajador, String> colNombre;
    @FXML
    private TableColumn<Trabajador, String> colRfc;
    @FXML
    private TableColumn<Trabajador, String> colNss;
    @FXML
    private TableColumn<?, ?> colAcciones;

    @FXML ObservableList<Trabajador> listaTrabajador;
    private Conexion conexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       //iniciar lista Trabajador
       listaTrabajador = FXCollections.observableArrayList();
       
       //llenar lista Trabajador
       Trabajador.llenarDatos(conexion.getConnection(), listaTrabajador);
       tableDatosGenerales.setItems(listaTrabajador);
       
       colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
       colRfc.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("rfc"));
       colNss.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nss"));
       gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        tableDatosGenerales.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Trabajador>(){
                    @Override
                    public void changed (ObservableValue <? extends Trabajador> arg0, Trabajador valorAnterior, Trabajador valorSeleccionado){
                        if(valorSeleccionado != null){
                            //para mas adelante
                        }
                    }
                }
        
        );
    }
    
   
    
}
