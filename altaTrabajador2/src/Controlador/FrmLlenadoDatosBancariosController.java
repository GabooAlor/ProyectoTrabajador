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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.banco;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmLlenadoDatosBancariosController implements Initializable {

    @FXML
    private TextField txtBanco;
    @FXML
    private TextField txtNumCta;
    @FXML
    private TextField txtTipoCuenta;
    @FXML
    private TextField txtClaveInterbancaria;
    @FXML
    private TextField txtTarjetaDebito;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;

    @FXML ObservableList<banco> listaBanco;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();    
       listaBanco = FXCollections.observableArrayList();
       gestionarEventos();
       conexion.cerrarConexion();
    }  
    
    public void gestionarEventos(){
        
    }  

    @FXML
    private void GuardarDatosBancarios(ActionEvent event) {
        banco a = new banco(0,
            1,
            txtTipoCuenta.getText(),
            txtNumCta.getText(),
            txtClaveInterbancaria.getText(),
            txtTarjetaDebito.getText(),
            "a"
        
        );
        
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        System.out.println(resultado);
        conexion.cerrarConexion();
        
        if (resultado == 1){
            listaBanco.add(a);
            //JDK 8u>40
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro agregado");
            mensaje.setContentText("El registro ha sido agregado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
            limpiarComponentes();
            
        }
    }

    @FXML
    private void CancelarDatosBancarios(ActionEvent event) {
    }
    
    public void limpiarComponentes(){
        txtBanco.setText(null);
        txtNumCta.setText(null);
        txtTipoCuenta.setText(null);
        txtClaveInterbancaria.setText(null);
        txtTarjetaDebito.setText(null);
    }
    
}
