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
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmLlenadoTrabajadorController implements Initializable {

    @FXML
    private TextField txtNombre;
    private TextField txtDia;
    private TextField txtMes;
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
    
    private Trabajador trabajador;
    

   ObservableList<Trabajador> listaTrabajador;
    private Conexion conexion;
    @FXML
    private DatePicker dtpkrFecha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        //listaTrabajador = FXCollections.observableArrayList();
        gestionarEventos();
        conexion.cerrarConexion();  
    }   
    
    public void initAttributes(ObservableList<Trabajador> listaTrabajador){
        this.listaTrabajador = listaTrabajador;
    }
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void GuardarRegistro() {
           Trabajador a = new Trabajador(0,
           txtNombre.getText(),
           String.valueOf(dtpkrFecha.getValue()),
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

    public Trabajador getTrabajador() {
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
        dtpkrFecha.setValue(null);
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
