
package Controlador;

import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Direccion;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaDireccionController implements Initializable {

    @FXML
    private TableView<Direccion> tableDireccion;
    @FXML
    private TableColumn<Direccion, String> colCalle;
    @FXML
    private TableColumn<Direccion, String> colNumExt;
    @FXML
    private TableColumn<Direccion, String> colNumInt;
    @FXML
    private TableColumn<Direccion, String> colColonia;
    @FXML
    private TableColumn<Direccion, String> colLocalidad;
    @FXML
    private TableColumn<Direccion, String> colEstado;
    @FXML
    private TableColumn<Direccion, String> colPais;
    @FXML
    private TableColumn<Direccion, Button> colAcciones;
    @FXML
    private Button btnEditar;
    
    ObservableList<Direccion> listaDireccion;
    private Conexion conexion;
    
    FrmTablaDireccionController TablaDireccionController;
    @FXML
    private Button btnAgregarDireccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       TablaDireccionController = this;
       conexion = new Conexion();
       conexion.establecerConexion();
       listaDireccion = FXCollections.observableArrayList();
       Direccion.llenarDatos(conexion.getConnection(), listaDireccion);
       tableDireccion.setItems(listaDireccion);
       
       colCalle.setCellValueFactory(new PropertyValueFactory<Direccion, String>("calle"));
       colNumExt.setCellValueFactory(new PropertyValueFactory<Direccion, String>("numexterior"));
       colNumInt.setCellValueFactory(new PropertyValueFactory<Direccion, String>("numinterior"));
       colColonia.setCellValueFactory(new PropertyValueFactory<Direccion, String>("colonia"));
       colLocalidad.setCellValueFactory(new PropertyValueFactory<Direccion, String>("ciudad"));
       colEstado.setCellValueFactory(new PropertyValueFactory<Direccion, String>("estado"));
       colPais.setCellValueFactory(new PropertyValueFactory<Direccion, String>("pais"));
       colAcciones.setCellValueFactory(new PropertyValueFactory<Direccion, Button>("button"));
       gestionarEventos();
       
       conexion.cerrarConexion();
    } 
    
    public void gestionarEventos(){
    
    }
    
    @FXML
    private  void EditarDireccion(ActionEvent event) {
        
        Direccion d = this.tableDireccion.getSelectionModel().getSelectedItem();
        
        if (d == null){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
            mensaje.setHeaderText(null);
            mensaje.setTitle("Error");
            mensaje.setContentText("Debes seleccionar una persona");
            mensaje.setHeaderText("Resultado:");
            mensaje.show();
        }else{
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmEditarDireccion.fxml"));
            Parent root = loader.load();
            
            FrmEditarDireccionController controlador = loader.getController();   
            controlador.initAttributes(listaDireccion, d);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            Direccion aux = controlador.getDireccion();
            if( aux != null){
                this.listaDireccion.add(aux);
                this.tableDireccion.refresh();
            }
            
            } catch (IOException ex) {
                Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        
        
    }

    @FXML
    private void agregarDireccion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmLlenadoDireccion.fxml"));
            Parent root = loader.load();
            
            FrmLlenadoDireccionController controlador = loader.getController();   
            controlador.initAttributes(listaDireccion);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            Direccion a = controlador.getDireccion();
            if( a != null){
                this.listaDireccion.add(a);
                this.tableDireccion.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    
}
