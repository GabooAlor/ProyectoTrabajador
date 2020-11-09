
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.direccion;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaDireccionController implements Initializable {

    @FXML
    private TableView<direccion> tableDireccion;
    @FXML
    private TableColumn<direccion, String> colCalle;
    @FXML
    private TableColumn<direccion, String> colNumExt;
    @FXML
    private TableColumn<direccion, String> colNumInt;
    @FXML
    private TableColumn<direccion, String> colColonia;
    @FXML
    private TableColumn<direccion, String> colLocalidad;
    @FXML
    private TableColumn<direccion, String> colEstado;
    @FXML
    private TableColumn<direccion, String> colPais;
    @FXML
    private TableColumn<?, ?> colAcciones;
    @FXML
    private Button btnEditar;
    
    
    ObservableList<direccion> listaDireccion;
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
       direccion.llenarDatos(conexion.getConnection(), listaDireccion);
       tableDireccion.setItems(listaDireccion);
       
       colCalle.setCellValueFactory(new PropertyValueFactory<direccion, String>("calle"));
       colNumExt.setCellValueFactory(new PropertyValueFactory<direccion, String>("numexterior"));
       colNumInt.setCellValueFactory(new PropertyValueFactory<direccion, String>("numinterior"));
       colColonia.setCellValueFactory(new PropertyValueFactory<direccion, String>("colonia"));
       colLocalidad.setCellValueFactory(new PropertyValueFactory<direccion, String>("ciudad"));
       colEstado.setCellValueFactory(new PropertyValueFactory<direccion, String>("estado"));
       colPais.setCellValueFactory(new PropertyValueFactory<direccion, String>("pais"));
       
       gestionarEventos();
       
       conexion.cerrarConexion();
    } 
    
    public void gestionarEventos(){
        tableDireccion.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<direccion>(){
                @Override
                public void changed(ObservableValue<? extends direccion> arg0,
                    direccion valorAnterior, direccion valorSeleccionado) {
                    if (valorSeleccionado!=null){
                        Stage fxmlEditarDireccion = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        try {
                            AnchorPane root  = (AnchorPane)loader.load(getClass().getResource("/vista/frmEditarDireccion.fxml").openStream());
                            FrmEditarDireccionController FrmEditarDireccionInstancia = (FrmEditarDireccionController)loader.getController();
                            FrmEditarDireccionInstancia.gestionarEventos(TablaDireccionController, String.valueOf(valorSeleccionado.getCalle()),String.valueOf(valorSeleccionado.getNumexterior()), String.valueOf(valorSeleccionado.getNuminterior()), String.valueOf(valorSeleccionado.getColonia()), String.valueOf(valorSeleccionado.getCiudad()), String.valueOf(valorSeleccionado.getEstado()), String.valueOf(valorSeleccionado.getPais()));
                            
                            Scene scene = new Scene(root);
                            fxmlEditarDireccion.setScene(scene);
                            fxmlEditarDireccion.alwaysOnTopProperty();
                            fxmlEditarDireccion.initModality(Modality.APPLICATION_MODAL);
                            fxmlEditarDireccion.show();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FrmTablaDireccionController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        );
    }
    
    @FXML
    private  void EditarDireccion(ActionEvent event) {
        
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
            
            
            direccion a = controlador.getDireccion();
            if( a != null){
                this.listaDireccion.add(a);
                this.tableDireccion.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
    
    
}
