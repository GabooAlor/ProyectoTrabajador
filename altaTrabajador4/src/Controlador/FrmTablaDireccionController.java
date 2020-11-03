/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.direccion;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaDireccionController implements Initializable {

    @FXML
    private TableView<direccion> tableDireccion;
    @FXML
    private TableColumn<direccion, String> colCalle;
    @FXML
    private TableColumn<direccion, String> colNumExt;
    @FXML
    private TableColumn<direccion, String> colNumInt;
    @FXML
    private TableColumn<direccion, String> colColonia;
    @FXML
    private TableColumn<direccion, String> colLocalidad;
    @FXML
    private TableColumn<direccion, String> colEstado;
    @FXML
    private TableColumn<direccion, String> colPais;
    @FXML
    private TableColumn<?, ?> colAcciones;
    
    @FXML ObservableList<direccion> listaDireccion;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       listaDireccion = FXCollections.observableArrayList();
       direccion.llenarDatos(conexion.getConnection(), listaDireccion);
       tableDireccion.setItems(listaDireccion);
       
       colCalle.setCellValueFactory(new PropertyValueFactory<direccion, String>("calle"));
       colNumExt.setCellValueFactory(new PropertyValueFactory<direccion, String>("numexterior"));
       colNumInt.setCellValueFactory(new PropertyValueFactory<direccion, String>("numinterior"));
       colColonia.setCellValueFactory(new PropertyValueFactory<direccion, String>("colonia"));
       colLocalidad.setCellValueFactory(new PropertyValueFactory<direccion, String>("ciudad"));
       colEstado.setCellValueFactory(new PropertyValueFactory<direccion, String>("estado"));
       colPais.setCellValueFactory(new PropertyValueFactory<direccion, String>("pais"));
       
       gestionarEventos();
       
       conexion.cerrarConexion();
    } 
    
    public void gestionarEventos(){
        
    }
    
}
