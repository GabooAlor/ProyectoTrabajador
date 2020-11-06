
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
        contacto a = new contacto(0,
        "",
        txtTelefono.getText(),
        txtCelular.getText(),
        txtTelefonoEmergencia.getText(),
        txtCorreoElectronico.getText(),
        "a"
        );
        
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        System.out.println(resultado);
        conexion.cerrarConexion();
        
        if (resultado == 1){
            listaContacto.add(a);
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
    private void CancelarRegistro(ActionEvent event) {
    }
    
    public void limpiarComponentes(){
        txtExt.setText(null);
        txtLada.setText(null);
        txtTelefono.setText(null);
        txtCelular.setText(null);
        txtTelefonoEmergencia.setText(null);
        txtCorreoElectronico.setText(null);

    }
}
