/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import aplicacion.*;
import Controlador.*;
import Controlador.*;
import conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmLlenadoTrabajadorController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDia;
    @FXML
    private TextField txtMes;
    @FXML
    private TextField txtAnio;
    @FXML
    private TextField txtRfc;
    @FXML
    private TextField txtNss;
    @FXML
    private TextField txtCurp;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtDocidentidad;
    @FXML
    private TextField txtLicencia;
    @FXML
    private TextField txtFechaExpedicion;
    @FXML
    private TextField txtDocumento;
    @FXML
    private TextField txtNumLicencia;
    @FXML
    private TextField txtFechaVigencia;
    
    private trabajador trabajador;
    

   @FXML ObservableList<trabajador> listaTrabajador;
    private Conexion conexion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        //listaTrabajador = FXCollections.observableArrayList();
        gestionarEventos();
        conexion.cerrarConexion();  
    }   
    
    public void initAttributes(ObservableList<trabajador> listaTrabajador){
        this.listaTrabajador = listaTrabajador;
    }
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void GuardarRegistro() {
           trabajador a = new trabajador(0,
           txtNombre.getText(),
           txtDia.getText()+ "/" +txtMes.getText()+ "/" +txtAnio.getText(),
           txtRfc.getText(),
           txtNss.getText(),
           txtCurp.getText(),
           txtDocidentidad.getText(),
           txtDocumento.getText(),
           txtLicencia.getText(),
           txtNumLicencia.getText(),
           txtFechaExpedicion.getText(),
           txtFechaVigencia.getText(),
           "a"
 
        );
        
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        System.out.println(resultado);
        conexion.cerrarConexion();
        
        if (resultado == 1){
            listaTrabajador.add(a);
            //JDK 8u>40
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
            limpiarComponentes();
            //this.tableConcentradoTrabajador.refresh();
            
            Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
            stage.close();
        }  
        
        
    }

    public trabajador getTrabajador() {
        return trabajador;
    }
    
   

    @FXML
    private void CancelarRegistro() {
        this.trabajador = null;
        Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
        stage.close();
    }
    
     public void limpiarComponentes(){
        txtNombre.setText(null);
        txtRfc.setText(null);
        txtDia.setText(null);
        txtMes.setText(null);
        txtAnio.setText(null);
        txtNss.setText(null);
        txtCurp.setText(null);
        txtDocidentidad.setText(null);
        txtDocumento.setText(null);
        txtLicencia.setText(null);
        txtNumLicencia.setText(null);
        txtFechaExpedicion.setText(null);
        txtFechaVigencia.setText(null);
        
        
        btnAgregar.setDisable(false);
        btnCancelar.setDisable(true);

    }
}
