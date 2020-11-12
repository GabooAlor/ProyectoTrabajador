
package Controlador;

import Controlador.*;
import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.TrabajadorBanco;
import modelo.Banco;


public class FrmTablaBancosController implements Initializable {

    @FXML
    private TableView<TrabajadorBanco> tableBanco;
    @FXML
    private TableColumn<TrabajadorBanco, Banco> colBanco;
    @FXML
    private TableColumn<TrabajadorBanco, String> colTipoCuenta;
    @FXML
    private TableColumn<TrabajadorBanco, String> colNumCuenta;
    @FXML
    private TableColumn<TrabajadorBanco, String> colClaveInterbancaria;
    @FXML
    private TableColumn<TrabajadorBanco, String> ColNumTarjetaDebito;
    @FXML
    private TableColumn<?, ?> ColAcciones;
    
    ObservableList<TrabajadorBanco> listaBanco;
    ObservableList<Banco> listaBancos;
    Conexion conexion;
    @FXML
    private Button btnAgregarBanco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       listaBanco = FXCollections.observableArrayList();
       listaBancos = FXCollections.observableArrayList();
       //llenar listas
       Banco.llenarInformacion(conexion.getConnection(), listaBancos);
       TrabajadorBanco.llenarDatos(conexion.getConnection(), listaBanco);
       
       tableBanco.setItems(listaBanco);
       
        colBanco.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, Banco>("banco"));
        colTipoCuenta.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("tipo_cuenta"));
        colNumCuenta.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("num_cuenta"));
        colClaveInterbancaria.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("clabe"));
        ColNumTarjetaDebito.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("num_tarjeta"));
        
        gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void agregarBanco(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmLlenadoDatosBancarios.fxml"));
            Parent root = loader.load();
            
            FrmLlenadoDatosBancariosController controlador = loader.getController();   
            controlador.initAttributes(listaBanco);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            TrabajadorBanco a = controlador.getTrabajadorBanco();
            if( a != null){
                this.listaBanco.add(a);
                this.tableBanco.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
