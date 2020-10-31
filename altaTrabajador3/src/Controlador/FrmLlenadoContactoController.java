
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.contacto;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmLlenadoContactoController implements Initializable {

    @FXML
    private TextField txtExt;
    @FXML
    private TextField txtLada;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCelular;
    @FXML
    private TextField txtTelefonoEmergencia;
    @FXML
    private TextField txtCorreoElectronico;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;

    
    @FXML ObservableList<contacto> listaContacto;
    private Conexion conexion;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        listaContacto = FXCollections.observableArrayList();
        gestionarEventos();
        conexion.cerrarConexion();
    }  
    
    public void gestionarEventos(){
     
    }

    @FXML
    private void GuardarContacto(ActionEvent event) {
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
    }
    
}
