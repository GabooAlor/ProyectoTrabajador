
package Controlador;

import Controlador.*;
import conexion.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.TrabajadorBanco;
import modelo.Banco;
import modelo.Contacto;
import modelo.Direccion;
import modelo.Trabajador;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmPrincipalAccionesController implements Initializable {

    @FXML
    private TableView<Trabajador> tableDatosGenerales;
    @FXML
    private TableColumn<Trabajador, String> colNombre;
    @FXML
    private TableColumn<Trabajador, String> colRfc;
    @FXML
    private TableColumn<Trabajador, String> colNss;
    @FXML
    private TableColumn<?, ?> colAcciones;
    
    ObservableList<Trabajador> listaTrabajador;
    private Conexion conexion;
    
   //Tabla dirección..................................
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
    private TableColumn<?, ?> colAcciones1;
    
    ObservableList<Direccion> listaDireccion;
    
    //Tabla Contacto.............................
    @FXML
    private TableView<Contacto> tableContacto;
    @FXML
    private TableColumn<Contacto, String> colExt;
    @FXML
    private TableColumn<Contacto, String> colTelefono;
    @FXML
    private TableColumn<Contacto, String> colLada;
    @FXML
    private TableColumn<Contacto, String> colCelular;
    @FXML
    private TableColumn<Contacto, String> ColNumeroEmergencia;
    @FXML
    private TableColumn<Contacto, String> ColCorreoElectronico;
    @FXML
    private TableColumn<?, ?> ColAcciones;
    
    ObservableList<Contacto> listaContacto;
    
    //Tabla Banco
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
    private TableColumn<?, ?> ColAcciones1;
    
    ObservableList<TrabajadorBanco> listaBanco;
    ObservableList<Banco> listaBancos;
    @FXML
    private TextField txtRfc;
    @FXML
    private TextField txtNss;
    @FXML
    private TextField txtCurp;
    @FXML
    private TextField txtIdentidad;
    @FXML
    private TextField txtNumDocumento;
    @FXML
    private TextField txtLicencia;
    @FXML
    private TextField txtNumLicencia;
    @FXML
    private TextField txtFechaExpedicion;
    @FXML
    private TextField txtFechaVencimiento;
    @FXML
    private Button btnAgregarDatosGenerales;
    @FXML
    private Button btnAgregarDireccion;
    @FXML
    private Button btnAgregarContacto;
    @FXML
    private Button btnAgregarBanco;
    @FXML
    private Button btnEditarDatos;
    @FXML
    private Button btnEditarDireccion;
    @FXML
    private Button btnEditarContacto;
    @FXML
    private Button btnEditarBanco;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFechaNacimiento;
    Integer idTrabajador;
    //variables de la tabla Datos generales para obtener su valor y pasarlo a la vista editar
    
    Integer id;
    String nombre;
    String fechaNacimiento;
    String rfc;
    String nss;
    String curp;
    String docIdentidad;
    String numDocumento;
    String tipoLicencia;
    String numLicencia;
    String fechaExpedicion;
    String fechaVencimiento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       conexion = new Conexion();
       conexion.establecerConexion();
       //iniciar lista Trabajador
       listaTrabajador = FXCollections.observableArrayList();
       //iniciar lista Direccion
       listaDireccion = FXCollections.observableArrayList();
       //iniciar lista Contacto
       listaContacto = FXCollections.observableArrayList();
       //iniciar lista Banco
       listaBanco = FXCollections.observableArrayList();
       listaBancos = FXCollections.observableArrayList();
       
     
       
       //llenar lista Trabajador
       Trabajador.llenarDatos(conexion.getConnection(), listaTrabajador);
       tableDatosGenerales.setItems(listaTrabajador);
       
       colNombre.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nombre"));
       colRfc.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("rfc"));
       colNss.setCellValueFactory(new PropertyValueFactory<Trabajador, String>("nss"));
      
       
       //llenar lista Direccion
       Direccion.llenarDatos(conexion.getConnection(), listaDireccion);
       tableDireccion.setItems(listaDireccion);
       
       colCalle.setCellValueFactory(new PropertyValueFactory<Direccion, String>("calle"));
       colNumExt.setCellValueFactory(new PropertyValueFactory<Direccion, String>("numexterior"));
       colNumInt.setCellValueFactory(new PropertyValueFactory<Direccion, String>("numinterior"));
       colColonia.setCellValueFactory(new PropertyValueFactory<Direccion, String>("colonia"));
       colLocalidad.setCellValueFactory(new PropertyValueFactory<Direccion, String>("ciudad"));
       colEstado.setCellValueFactory(new PropertyValueFactory<Direccion, String>("estado"));
       colPais.setCellValueFactory(new PropertyValueFactory<Direccion, String>("pais"));
       
       //llenar lista Contacto
       Contacto.llenarDatos(conexion.getConnection(), listaContacto);
       tableContacto.setItems(listaContacto);
       
       colTelefono.setCellValueFactory(new PropertyValueFactory<Contacto, String>("telefono"));
       colCelular.setCellValueFactory(new PropertyValueFactory<Contacto, String>("celular"));
       ColNumeroEmergencia.setCellValueFactory(new PropertyValueFactory<Contacto, String>("tel_emergencia"));
       ColCorreoElectronico.setCellValueFactory(new PropertyValueFactory<Contacto, String>("email"));
       
       //llenar listas
       Banco.llenarInformacion(conexion.getConnection(), listaBancos);
       TrabajadorBanco.llenarDatos(conexion.getConnection(), listaBanco);
       
       tableBanco.setItems(listaBanco);
       
        colBanco.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, Banco>("banco"));
        colTipoCuenta.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("tipo_cuenta"));
        colNumCuenta.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("num_cuenta"));
        colClaveInterbancaria.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("clabe"));
        ColNumTarjetaDebito.setCellValueFactory(new PropertyValueFactory<TrabajadorBanco, String>("num_tarjeta"));
       
       
       
       //--------------
       gestionarEventos();
       conexion.cerrarConexion();
    }  
    
    public void initAttributes(Integer idTrabajador, String nombre,String fechaNacimiento, String rfc, String nss, String curp, String docIdentidad, String numDocumento, String tipoLicencia, String numLicencia, String fechaExpedicion, String fechaVencimiento){
      this.idTrabajador = idTrabajador;
      txtNombre.setText(nombre);
      txtFechaNacimiento.setText(fechaNacimiento);
      txtRfc.setText(rfc);
      txtNss.setText(nss);
      txtCurp.setText(curp);
      txtIdentidad.setText(docIdentidad);
      txtNumDocumento.setText(numDocumento);
      txtLicencia.setText(tipoLicencia);
      txtNumLicencia.setText(numLicencia);
      txtFechaExpedicion.setText(fechaExpedicion);
      txtFechaVencimiento.setText(fechaVencimiento);
    }
    
    
    public void gestionarEventos(){
        //evento para la tabla datos generales trabajador
        tableDatosGenerales.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<Trabajador>(){
                @Override
                public void changed(ObservableValue<? extends Trabajador> arg0,
                    Trabajador valorAnterior, Trabajador valorSeleccionado) {
                     if (valorSeleccionado!=null){
                         id = valorSeleccionado.getId();
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
    private void agregarDatosGenerales(ActionEvent event) {
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
                this.tableDatosGenerales.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void agregarContacto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmLlenadoContacto.fxml"));
            Parent root = loader.load();
            
            FrmLlenadoContactoController controlador = loader.getController();   
            controlador.initAttributes(listaContacto);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            
            Contacto a = controlador.getContacto();
            if( a != null){
                this.listaContacto.add(a);
                this.tableContacto.refresh();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FrmLlenadoTrabajadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    @FXML
    private void editarDatosGenerales(ActionEvent event) {
        try {
            Trabajador t = this.tableDatosGenerales .getSelectionModel().getSelectedItem();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmEditarTrabajador.fxml"));
            Parent root = loader.load();
            
            FrmEditarTrabajadorController controlador = loader.getController();  
            controlador.initAttributes(id, nombre, fechaNacimiento, rfc, nss, curp, docIdentidad, numDocumento, tipoLicencia, numLicencia, fechaExpedicion, fechaVencimiento);
            controlador.initAttributes(listaTrabajador, t);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Trabajador aux = controlador.getTrabajador();
            if( aux != null){
                System.out.println("hola desde el controlador");
                this.tableDatosGenerales.refresh();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalAccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarDireccion(ActionEvent event) {
         try {
            Direccion d = this.tableDireccion.getSelectionModel().getSelectedItem();
            
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
                this.tableDireccion.refresh();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalAccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarContacto(ActionEvent event) {
         try {
            Contacto c = this.tableContacto.getSelectionModel().getSelectedItem();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmEditarContacto.fxml"));
            Parent root = loader.load();
            
            FrmEditarContactoController controlador = loader.getController(); 
            controlador.initAttributes(listaContacto, c);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            Contacto aux = controlador.getContacto();
            if( aux != null){
                this.tableContacto.refresh();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalAccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void editarBanco(ActionEvent event) {
         try {
            TrabajadorBanco b = this.tableBanco .getSelectionModel().getSelectedItem();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/frmEditarDatosBancarios.fxml"));
            Parent root = loader.load();
            
            FrmEditarDatosBancariosController controlador = loader.getController();
            controlador.initAttributes(listaBanco, b);
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            
            TrabajadorBanco aux = controlador.getTrabajadorBanco();
            if( aux != null){
                this.tableBanco.refresh();
            }
        } catch (IOException ex) {
            Logger.getLogger(FrmPrincipalAccionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
