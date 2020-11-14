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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.TrabajadorBanco;
import modelo.Banco;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmLlenadoDatosBancariosController implements Initializable {

    @FXML
    private TextField txtNumCta;
    @FXML
    private TextField txtTipoCuenta;
    @FXML
    private TextField txtClaveInterbancaria;
    @FXML
    private ComboBox<Banco> cmbBanco;
    @FXML
    private TextField txtTarjetaDebito;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;
    
    private TrabajadorBanco trabajadorBanco;
    
    @FXML ObservableList<TrabajadorBanco> listaBanco;
    @FXML ObservableList<Banco> listaBancos;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       //inicializar vistas
       listaBanco = FXCollections.observableArrayList();
       listaBancos = FXCollections.observableArrayList();
       
       //llenas la lista
       Banco.llenarInformacion(conexion.getConnection(), listaBancos);
       
       //enlazar la lista con el comboBox
       cmbBanco.setItems(listaBancos);
       gestionarEventos();
       conexion.cerrarConexion();
    }    
    
     public void initAttributes(ObservableList<TrabajadorBanco> listaBanco){
         this.listaBanco = listaBanco;
     }

    public void gestionarEventos(){
        
    }
    
    @FXML
    private void GuardarDatosBancarios(ActionEvent event) {
        TrabajadorBanco a = new TrabajadorBanco(0,
            txtTipoCuenta.getText(),
            txtNumCta.getText(),
            txtClaveInterbancaria.getText(),
            txtTarjetaDebito.getText(),
            cmbBanco.getSelectionModel().getSelectedItem(),
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
            
            Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
            stage.close();
            
        }
    }

    public TrabajadorBanco getTrabajadorBanco() {
        return trabajadorBanco;
    }
    
    

    @FXML
    private void CancelarDatosBancarios(ActionEvent event) {
        this.trabajadorBanco = null;
        Stage stage = (Stage) this.btnAgregar.getScene().getWindow();
        stage.close();
    }
    
    public void limpiarComponentes(){
        cmbBanco.setValue(null);
        txtNumCta.setText(null);
        txtTipoCuenta.setText(null);
        txtClaveInterbancaria.setText(null);
        txtTarjetaDebito.setText(null);
    }
    
}
