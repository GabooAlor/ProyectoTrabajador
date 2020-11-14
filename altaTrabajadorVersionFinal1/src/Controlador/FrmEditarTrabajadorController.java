
package Controlador;

import Controlador.*;
import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmEditarTrabajadorController implements Initializable {

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
    private Trabajador trabajador;
    
    ObservableList<Trabajador> listaTrabajador;
    private Conexion conexion;
    @FXML
    private DatePicker dtpkrFecha;
    
    Integer idTrabajador;

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
    
    public void initAttributes(Integer id, String nombre,String fechaNacimiento, String rfc, String nss, String curp, String docIdentidad, String numDocumento, String tipoLicencia, String numLicencia, String fechaExpedicion, String fechaVencimiento){
      idTrabajador = id;
      txtNombre.setText(nombre);
      txtRfc.setText(rfc);
      txtNss.setText(nss);
      txtCurp.setText(curp);
      txtDocidentidad.setText(docIdentidad);
      txtDocumento.setText(numDocumento);
      txtLicencia.setText(tipoLicencia);
      txtNumLicencia.setText(numLicencia);
      txtFechaExpedicion.setText(fechaExpedicion);
      txtFechaVigencia.setText(fechaVencimiento);
    }
    
    public void initAttributes(ObservableList<Trabajador> listaTrabajador, Trabajador t){
        this.listaTrabajador = listaTrabajador;
        this.trabajador = t;
    }
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void EditarRegistro(ActionEvent event) {
        Trabajador a = new Trabajador(idTrabajador,
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
        int resultado = a.EditarRegistro(conexion.getConnection());
        conexion.cerrarConexion();

        if (resultado == 1){
            trabajador.setNombre(txtNombre.getText());
            trabajador.setFecha_nacimiento(String.valueOf(dtpkrFecha.getValue()));
            trabajador.setRfc(txtRfc.getText());
            trabajador.setNss(txtNss.getText());

            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setTitle("Registro actualizado");
            mensaje.setContentText("El registro ha sido actualizado exitosamente");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
            
            Stage stage = (Stage) this.btnEditar.getScene().getWindow();
            stage.close();
        }
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }
    
    

    @FXML
    private void CancelarRegistro(ActionEvent event) {
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
        
        
        btnEditar.setDisable(false);
        bntCancelar.setDisable(true);

    }
    
}
