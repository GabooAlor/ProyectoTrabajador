
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
import javafx.stage.Stage;
import modelo.Contacto;



/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmEditarContactoController implements Initializable {

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
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    Integer idContacto;
    private Contacto contacto;
    @FXML ObservableList<Contacto> listaContacto;
    private Conexion conexion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        listaContacto = FXCollections.observableArrayList();
        gestionarEventos();
        conexion.cerrarConexion();
    }   
    
    public void initAttributes(ObservableList<Contacto> listaContacto, Contacto c){
        this.idContacto = c.getId();
        this.listaContacto = listaContacto;
        this.contacto = c;
        this.txtCelular.setText(c.getCelular());
        this.txtCorreoElectronico.setText(c.getEmail());
        this.txtTelefono.setText(c.getTelefono());
        this.txtTelefonoEmergencia.setText(c.getTel_emergencia());
    }
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void EditarContacto(ActionEvent event) {
         Contacto a = new Contacto(idContacto,
        "",
        txtTelefono.getText(),
        txtCelular.getText(),
        txtTelefonoEmergencia.getText(),
        txtCorreoElectronico.getText(),
        "a"
        );
         
        conexion.establecerConexion();
        int resultado = a.editarRegistro(conexion.getConnection());
        conexion.cerrarConexion();
        
        if (resultado == 1){
            this.contacto.setCelular(txtCelular.getText());
            this.contacto.setEmail(txtCorreoElectronico.getText());
            this.contacto.setTelefono(txtTelefono.getText());
            this.contacto.setTel_emergencia(txtTelefonoEmergencia.getText());
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

    public Contacto getContacto() {
        return contacto;
    }
    
    

    @FXML
    private void Cancelar(ActionEvent event) {
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
