/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.*;
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
import modelo.contacto;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaContactoController implements Initializable {

    @FXML
    private TableView<contacto> tableContacto;
    @FXML
    private TableColumn<contacto, String> colExt;
    @FXML
    private TableColumn<contacto, String> colTelefono;
    @FXML
    private TableColumn<contacto, String> colLada;
    @FXML
    private TableColumn<contacto, String> colCelular;
    @FXML
    private TableColumn<contacto, String> ColNumeroEmergencia;
    @FXML
    private TableColumn<contacto, String> ColCorreoElectronico;
    @FXML
    private TableColumn<contacto, String> ColAcciones;
    
    @FXML ObservableList<contacto> listaDireccion;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
       conexion.establecerConexion();
       listaDireccion = FXCollections.observableArrayList();
       contacto.llenarDatos(conexion.getConnection(), listaDireccion);
       tableContacto.setItems(listaDireccion);
       
       colTelefono.setCellValueFactory(new PropertyValueFactory<contacto, String>("telefono"));
       colCelular.setCellValueFactory(new PropertyValueFactory<contacto, String>("celular"));
       ColNumeroEmergencia.setCellValueFactory(new PropertyValueFactory<contacto, String>("tel_emergencia"));
       ColCorreoElectronico.setCellValueFactory(new PropertyValueFactory<contacto, String>("email"));
       
       gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
     public void gestionarEventos(){
         
     }
    
}
