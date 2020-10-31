
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
public class FrmLlenadoDireccionController implements Initializable {

    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtNumExt;
    @FXML
    private TextField txtNumInt;
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
    private Button btnAgregar;
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
    private void GuardarDireccion(ActionEvent event) {
        direccion a = new direccion(0,
            txtCalle.getText(),
            txtNumExt.getText(),
            txtNumInt.getText(),
            txtColonia.getText(),
            txtCp.getText(),
            txtLocalidad.getText(),
            txtEstado.getText(),
            txtPais.getText(),
            0,
            "a"  
        );
        
        conexion.establecerConexion();
        int resultado = a.guardarRegistro(conexion.getConnection());
        System.out.println(resultado);
        conexion.cerrarConexion();
        
        if (resultado == 1){
            listaDireccion.add(a);
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
    private void CancelarDireccion(ActionEvent event) {
    }
    
    public void limpiarComponentes(){
        txtCalle.setText(null);
        txtNumExt.setText(null);
        txtNumInt.setText(null);
        txtColonia.setText(null);
        txtCp.setText(null);
        txtLocalidad.setText(null);
        txtEstado.setText(null);
        txtPais.setText(null); 
    }
    
}
