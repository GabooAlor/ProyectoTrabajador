/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.*;
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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmConcentradoTrabajadorController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Trabajador> tableConcentradoTrabajador;
    @FXML
    private TableColumn<Trabajador, String> colNombre;
    @FXML
    private TableColumn<Trabajador, String> colRfc;
    @FXML
    private TableColumn<Trabajador, String> colNss;
    @FXML
    private TableColumn<Trabajador, String> colPuesto;
    @FXML
    private TableColumn<?, ?> colAcciones;
    @FXML
    private Button btnNuevo;
    Integer idTrabajador;
    String nombre;
    String rfc;
    String nss;
    String curp;
    String docIdentidad;
    String numDocumento;
    String tipoLicencia;
    String numLicencia;
    String fechaExpedicion;
    String fechaVencimiento;
    String fechaNacimiento;
    
    ObservableList<Trabajador> listaTrabajador;
    FilteredList<Trabajador> BuscarTrabajador;
    private Trabajador trabajadors;
    private Conexion conexion;
    @FXML
    private Button btnAcciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        
        listaTrabajador = FXCollections.observableArrayList();
        Trabajador.llenarDatos(conexion.getConnection(), listaTrabajador);
        BuscarTrabajador = new FilteredList<>(listaTrabajador, p -> true);
        tableConcentradoTrabajador.setItems(BuscarTrabajador);
        
        colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
        colRfc.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("rfc"));
        colNss.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nss"));
        gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        tableConcentradoTrabajador.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Trabajador>(){
                @Override
                public void changed(ObservableValue<? extends Trabajador> arg0,
                    Trabajador valorAnterior, Trabajador valorSeleccionado) {
                     if (valorSeleccionado!=null){
                         idTrabajador =  valorSeleccionado.getId();
                         nombre = valorSeleccionado.getNombre();
                         fechaNacimiento = valorSeleccionado.getFecha_nacimiento();
                         rfc = valorSeleccionado.getRfc();
                         nss = valorSeleccionado.getNss();
                         curp = valorSeleccionado.getCurp();
                         docIdentidad = valorSeleccionado.getDoc_identidad();
                         numDocumento = valorSeleccionado.getNum_documento();
                         tipoLicencia = valorSeleccionado.getTipo_licencia();
                         numLicencia = valorSeleccionado.getNum_licencia();
                         fechaExpedicion = valorSeleccionado.getFecha_expicion();
                         fechaVencimiento = valorSeleccionado.getFecha_vigencia();
                         
                     }
                }
            }
        );        
    }

    @FXML
    private void buscarRegistro(ActionEvent event) {
       //Esto se corregira
       txtBuscar.setPromptText("Buscar...");
       txtBuscar.textProperty().addListener((prop, old, text) -> {
            BuscarTrabajador.setPredicate(trabajadors ->{
                if(text == null || text.isEmpty()) return true;
                
                String name = trabajadors.getNombre().toLowerCase();
                return name.contains(text.toLowerCase());
            });
       });
    }

    @FXML
    private void agregarRegistro(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmLlenadoTrabajador.fxml"));
            Parent root = loader.load();
            
            FrmLlenadoTrabajadorController controlador = loader.getController();   
            controlador.initAttributes(listaTrabajador);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            Trabajador a = controlador.getTrabajador();
            if( a != null){
                this.listaTrabajador.add(a);
                this.tableConcentradoTrabajador.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void accederAcciones(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmPrincipalAcciones.fxml"));
            Parent root = loader.load();
            
            FrmPrincipalAccionesController controlador = loader.getController();   
            controlador.initAttributes(idTrabajador, nombre, fechaNacimiento, rfc, nss, curp, docIdentidad, numDocumento, tipoLicencia, numLicencia, fechaExpedicion, fechaVencimiento);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
