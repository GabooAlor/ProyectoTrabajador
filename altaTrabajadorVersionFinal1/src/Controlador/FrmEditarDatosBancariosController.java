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
public class FrmEditarDatosBancariosController implements Initializable {

    @FXML
    private TextField txtNumCIta;
    @FXML
    private TextField txtTipoCuenta;
    @FXML
    private TextField txtClaveInterbancaria;
    @FXML
    private ComboBox<Banco> cmbBanco;
    @FXML
    private TextField txtTarjetaDebito;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    private Integer idTrabajadorBanco;
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
       
       cmbBanco.setItems(listaBancos);
       gestionarEventos();
       conexion.cerrarConexion();
       
    }    
    
    public void initAttributes(ObservableList<TrabajadorBanco> listaBanco, TrabajadorBanco b){
        this.idTrabajadorBanco = b.getId();
        this.listaBanco = listaBanco;
        this.trabajadorBanco = b;
        this.txtNumCIta.setText(b.getNum_cuenta());
        this.txtClaveInterbancaria.setText(b.getClabe());
        this.txtTarjetaDebito.setText(b.getNum_tarjeta());
        this.txtTipoCuenta.setText(b.getTipo_cuenta());
        this.cmbBanco.setValue(b.getBanco());
    }

    public void gestionarEventos(){
        
    }
    
    @FXML
    private void EditarRegistro(ActionEvent event) {
        TrabajadorBanco a = new TrabajadorBanco(idTrabajadorBanco,
            txtTipoCuenta.getText(),
            txtNumCIta.getText(),
            txtClaveInterbancaria.getText(),
            txtTarjetaDebito.getText(),
            cmbBanco.getSelectionModel().getSelectedItem(),
            "a"
        );
        
        conexion.establecerConexion();
        int resultado = a.EditarRegistro(conexion.getConnection());
        conexion.cerrarConexion();
        
        if (resultado == 1){
            this.trabajadorBanco.setId(idTrabajadorBanco);
            this.trabajadorBanco.setBanco(cmbBanco.getSelectionModel().getSelectedItem());
            this.trabajadorBanco.setClabe(txtClaveInterbancaria.getText());
            this.trabajadorBanco.setNum_cuenta(txtNumCIta.getText());
            this.trabajadorBanco.setNum_tarjeta(txtTarjetaDebito.getText());
            this.trabajadorBanco.setTipo_cuenta(txtTipoCuenta.getText());
            
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
            limpiarComponentes();
            
             Stage stage = (Stage) this.btnEditar.getScene().getWindow();
             stage.close();
        }
    }

    public TrabajadorBanco getTrabajadorBanco() {
        return trabajadorBanco;
    }
    
    

    @FXML
    private void CancelarRegistro(ActionEvent event) {
    }
    
      public void limpiarComponentes(){
        cmbBanco.setValue(null);
        txtNumCIta.setText(null);
        txtTipoCuenta.setText(null);
        txtClaveInterbancaria.setText(null);
        txtTarjetaDebito.setText(null);
    }
    
}
