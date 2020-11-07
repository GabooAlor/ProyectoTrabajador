
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
import modelo.Trabajadorbanco;
import modelo.banco;


public class FrmTablaBancosController implements Initializable {

    @FXML
    private TableView<Trabajadorbanco> tableBanco;
    @FXML
    private TableColumn<Trabajadorbanco, banco> colBanco;
    @FXML
    private TableColumn<Trabajadorbanco, String> colTipoCuenta;
    @FXML
    private TableColumn<Trabajadorbanco, String> colNumCuenta;
    @FXML
    private TableColumn<Trabajadorbanco, String> colClaveInterbancaria;
    @FXML
    private TableColumn<Trabajadorbanco, String> ColNumTarjetaDebito;
    @FXML
    private TableColumn<?, ?> ColAcciones;
    
    @FXML 
    ObservableList<Trabajadorbanco> listaBanco;
    ObservableList<banco> listaBancos;
    Conexion conexion;

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
       banco.llenarInformacion(conexion.getConnection(), listaBancos);
       Trabajadorbanco.llenarDatos(conexion.getConnection(), listaBanco);
       
       tableBanco.setItems(listaBanco);
       
        colBanco.setCellValueFactory(new PropertyValueFactory<Trabajadorbanco, banco>("banco"));
        colTipoCuenta.setCellValueFactory(new PropertyValueFactory<Trabajadorbanco, String>("tipo_cuenta"));
        colNumCuenta.setCellValueFactory(new PropertyValueFactory<Trabajadorbanco, String>("num_cuenta"));
        colClaveInterbancaria.setCellValueFactory(new PropertyValueFactory<Trabajadorbanco, String>("clabe"));
        ColNumTarjetaDebito.setCellValueFactory(new PropertyValueFactory<Trabajadorbanco, String>("num_tarjeta"));
        
        gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        
    }
    
}
