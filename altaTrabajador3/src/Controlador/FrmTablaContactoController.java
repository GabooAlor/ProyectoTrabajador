/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author GABRIEL GARCIA ALOR
 */
public class FrmTablaContactoController implements Initializable {

    @FXML
    private TableView<?> tableContacto;
    @FXML
    private TableColumn<?, ?> colExt;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colLada;
    @FXML
    private TableColumn<?, ?> colCelular;
    @FXML
    private TableColumn<?, ?> ColNumeroEmergencia;
    @FXML
    private TableColumn<?, ?> ColCorreoElectronico;
    @FXML
    private TableColumn<?, ?> ColAcciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
