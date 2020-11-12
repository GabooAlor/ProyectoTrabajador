
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
import modelo.Direccion;

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
    
    private Direccion direccion;
    @FXML ObservableList<Direccion> listaDireccion;
    private Conexion conexion;
    
    FrmTablaDireccionController TablaDireccionController;

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
    
  
    
    public void initAttributes(ObservableList<Direccion> listaDireccion, Direccion d){
        this.listaDireccion = listaDireccion;
        this.direccion = d;
        this.txtCalle.setText(d.getCalle());
        this.txtColonia.setText(d.getColonia());
        this.txtCp.setText(d.getCp());
        this.txtEstado.setText(d.getEstado());
        this.txtLocalidad.setText(d.getCiudad());
        this.txtNumExt.setText(d.getNumexterior());
        this.txtNumint.setText(d.getNuminterior());
        this.txtPais.setText(d.getPais());
    }

    public void gestionarEventos(){
        
    }
    
    @FXML
    public void EditarRegistro(ActionEvent event) {
        Direccion a= new Direccion(0,
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
                this.direccion.setCalle(txtCalle.getText());
                this.direccion.setNumexterior(txtNumExt.getText());
                this.direccion.setNuminterior(txtNumint.getText());
                this.direccion.setColonia(txtColonia.getText());
                this.direccion.setCp(txtCp.getText());
                this.direccion.setCiudad(txtLocalidad.getText());
                this.direccion.setEstado(txtEstado.getText());
                this.direccion.setPais(txtPais.getText());
                
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Registro actualizado");
                mensaje.setContentText("El registro ha sido actualizado exitosamente");
                mensaje.setHeaderText("Resultado:");
                mensaje.show();
            
                Stage stage = (Stage) this.btnEditar.getScene().getWindow();
                stage.close();
        }else{
                System.out.println("Algo ocurrio mal");
        }
           
    }
    
    
    public Direccion getDireccion() {
        return direccion;
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
        this.direccion = null;
        Stage stage = (Stage) this.btnEditar.getScene().getWindow();
        stage.close();
        
    }
    
}
