
package Controlador;

import Controlador.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class FrmEditarDatosBancariosController implements Initializable {

    @FXML
    private TextField txtBanco;
    @FXML
    private TextField txtNumCIta;
    @FXML
    private TextField txtTipoCuenta;
    @FXML
    private TextField txtClaveInterbancaria;
    @FXML
    private TextField txtTarjetaDebito;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;

    //PENDIENTE
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void EditarRegistro(ActionEvent event) {
    }

    @FXML
    private void CancelarRegistro(ActionEvent event) {
    }
    
}
