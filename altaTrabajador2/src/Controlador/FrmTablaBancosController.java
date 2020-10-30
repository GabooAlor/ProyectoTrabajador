
package Controlador;

import Controlador.*;
import conexion.Conexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.banco;


public class FrmTablaBancosController implements Initializable {

    @FXML
    private TableView<banco> tableBanco;
    @FXML
    private TableColumn<?, ?> colBanco;
    @FXML
    private TableColumn<banco, String> colTipoCuenta;
    @FXML
    private TableColumn<banco, String> colNumCuenta;
    @FXML
    private TableColumn<banco, String> colClaveInterbancaria;
    @FXML
    private TableColumn<banco, String> ColNumTarjetaDebito;
    @FXML
    private TableColumn<?, ?> ColAcciones;
    
    @FXML ObservableList<banco> listaBanco;
    Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       listaBanco = FXCollections.observableArrayList();
       banco.llenarDatos(conexion.getConnection(), listaBanco);
       tableBanco.setItems(listaBanco);
       
        colTipoCuenta.setCellValueFactory(new PropertyValueFactory<banco, String>("tipo_cuenta"));
        colNumCuenta.setCellValueFactory(new PropertyValueFactory<banco, String>("num_cuenta"));
        colClaveInterbancaria.setCellValueFactory(new PropertyValueFactory<banco, String>("clabe"));
        ColNumTarjetaDebito.setCellValueFactory(new PropertyValueFactory<banco, String>("num_tarjeta"));
        
        gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        
    }
    
}
