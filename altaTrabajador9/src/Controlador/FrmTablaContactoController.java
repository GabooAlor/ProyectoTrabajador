/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.*;
import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Contacto;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaContactoController implements Initializable {

    @FXML
    private TableView<Contacto> tableContacto;
    @FXML
    private TableColumn<Contacto, String> colExt;
    @FXML
    private TableColumn<Contacto, String> colTelefono;
    @FXML
    private TableColumn<Contacto, String> colLada;
    @FXML
    private TableColumn<Contacto, String> colCelular;
    @FXML
    private TableColumn<Contacto, String> ColNumeroEmergencia;
    @FXML
    private TableColumn<Contacto, String> ColCorreoElectronico;
    @FXML
    private TableColumn<Contacto, String> ColAcciones;
    
    ObservableList<Contacto> listaDireccion;
    private Conexion conexion;
    @FXML
    private Button btnAgregarContacto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       listaDireccion = FXCollections.observableArrayList();
       Contacto.llenarDatos(conexion.getConnection(), listaDireccion);
       tableContacto.setItems(listaDireccion);
       
       colTelefono.setCellValueFactory(new PropertyValueFactory<Contacto, String>("telefono"));
       colCelular.setCellValueFactory(new PropertyValueFactory<Contacto, String>("celular"));
       ColNumeroEmergencia.setCellValueFactory(new PropertyValueFactory<Contacto, String>("tel_emergencia"));
       ColCorreoElectronico.setCellValueFactory(new PropertyValueFactory<Contacto, String>("email"));
       
       gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
     public void gestionarEventos(){
         
     }

    @FXML
    private void agregarContacto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmLlenadoContacto.fxml"));
            Parent root = loader.load();
            
            FrmLlenadoContactoController controlador = loader.getController();   
            controlador.initAttributes(listaDireccion);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            Contacto a = controlador.getContacto();
            if( a != null){
                this.listaDireccion.add(a);
                this.tableContacto.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
