
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
import javafx.scene.control.TextField;
import modelo.trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmEditarTrabajadorController implements Initializable {

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
    private Button btnEditar;
    @FXML
    private Button bntCancelar;
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
        gestionarEventos();
        conexion.cerrarConexion();
    }   
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void EditarRegistro(ActionEvent event) {
        trabajador a = new trabajador(0,
                txtNombre.getText(),
                txtDia.getText()+"/"+txtMes.getText()+"/"+txtAnio.getText(),
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
        int resultado = a.EditarRegistro(conexion.getConnection());
        conexion.cerrarConexion();
        
        if (resultado == 1){
            
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
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
        
        
        btnEditar.setDisable(false);
        bntCancelar.setDisable(true);

    }
    
}
