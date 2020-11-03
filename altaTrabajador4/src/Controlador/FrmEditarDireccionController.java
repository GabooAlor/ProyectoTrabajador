
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
import modelo.direccion;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmEditarDireccionController implements Initializable {

    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtNumExt;
    @FXML
    private TextField txtNumint;
    @FXML
    private TextField txtColonia;
    @FXML
    private TextField txtCp;
    @FXML
    private TextField txtLocalidad;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtPais;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    
    @FXML ObservableList<direccion> listaDireccion;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        listaDireccion = FXCollections.observableArrayList();
        gestionarEventos();
        conexion.cerrarConexion();
    }    

    public void gestionarEventos(){
        
    }
    
    @FXML
    private void EditarRegistro(ActionEvent event) {
        direccion a= new direccion(0,
            txtCalle.getText(),
            txtNumExt.getText(),
            txtNumint.getText(),
            txtColonia.getText(),
            txtCp.getText(),
            txtLocalidad.getText(),
            txtEstado.getText(),
            txtPais.getText(),
            0,
            "a"  
        
        );
        
        conexion.establecerConexion();
        int resultado = a.editarRegistro(conexion.getConnection());
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
    
}
