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
import modelo.trabajador;

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
    private TableView<trabajador> tableConcentradoTrabajador;
    @FXML
    private TableColumn<trabajador, String> colNombre;
    @FXML
    private TableColumn<trabajador, String> colRfc;
    @FXML
    private TableColumn<trabajador, String> colNss;
    @FXML
    private TableColumn<trabajador, String> colPuesto;
    @FXML
    private TableColumn<?, ?> colAcciones;
    @FXML
    private Button btnNuevo;
    
    @FXML ObservableList<trabajador> listaTrabajador;
    @FXML FilteredList<trabajador> BuscarTrabajador;
    private trabajador trabajadors;
    private Conexion conexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conexion = new Conexion();
        conexion.establecerConexion();
        
        listaTrabajador = FXCollections.observableArrayList();
        trabajador.llenarDatos(conexion.getConnection(), listaTrabajador);
        BuscarTrabajador = new FilteredList<>(listaTrabajador, p -> true);
        tableConcentradoTrabajador.setItems(BuscarTrabajador);
        
        colNombre.setCellValueFactory(new PropertyValueFactory<trabajador, String>("nombre"));
        colRfc.setCellValueFactory(new PropertyValueFactory<trabajador, String>("rfc"));
        colNss.setCellValueFactory(new PropertyValueFactory<trabajador, String>("nss"));
        gestionarEventos();
       
       conexion.cerrarConexion();
    }    
    
    public void gestionarEventos(){
        
    }

    @FXML
    private void buscarRegistro(ActionEvent event) {
       
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
            
            
            trabajador a = controlador.getTrabajador();
            if( a != null){
                this.listaTrabajador.add(a);
                this.tableConcentradoTrabajador.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
