
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class Direccion {
        
        private IntegerProperty id;
        private StringProperty calle;
        private StringProperty numexterior;
        private StringProperty numinterior;
        private StringProperty colonia;
        private StringProperty cp;
        private StringProperty ciudad;
        private StringProperty estado;
        private StringProperty pais;
        private IntegerProperty trabajador_id;
        private StringProperty status;
        private Button button;
        
        //agregar trabajador id static
        //setter and getter trabajador_id

    public Direccion(Integer id, String calle, String numexterior, String numinterior, String colonia, String cp, String ciudad, String estado, String pais, Integer trabajador_id, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.calle = new SimpleStringProperty(calle);
        this.numexterior = new SimpleStringProperty(numexterior);
        this.numinterior = new SimpleStringProperty(numinterior);
        this.colonia = new SimpleStringProperty(colonia);
        this.cp = new SimpleStringProperty(cp);
        this.ciudad = new SimpleStringProperty(ciudad);
        this.estado = new SimpleStringProperty(estado);
        this.pais = new SimpleStringProperty(pais);
        this.trabajador_id = new SimpleIntegerProperty(trabajador_id);
        this.status = new SimpleStringProperty(status);
        this.button = new Button("Editar");
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getCalle() {
        return calle.get();
    }

    public void setCalle(String calle) {
        this.calle = new SimpleStringProperty(calle);
    }

    public String getNumexterior() {
        return numexterior.get();
    }

    public void setNumexterior(String numexterior) {
        this.numexterior = new SimpleStringProperty(numexterior);
    }

    public String getNuminterior() {
        return numinterior.get();
    }

    public void setNuminterior(String numinterior) {
        this.numinterior = new SimpleStringProperty(numinterior);
    }

    public String getColonia() {
        return colonia.get();
    }

    public void setColonia(String colonia) {
        this.colonia = new SimpleStringProperty(colonia);
    }

    public String getCp() {
        return cp.get();
    }

    public void setCp(String cp) {
        this.cp = new SimpleStringProperty(cp);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String ciudad) {
        this.ciudad = new SimpleStringProperty(ciudad);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado = new SimpleStringProperty(estado);
    }

    public String getPais() {
        return pais.get();
    }

    public void setPais(String pais) {
        this.pais = new SimpleStringProperty(pais);
    }

    public Integer getTrabajador_id() {
        return trabajador_id.get();
    }

    public void setTrabajador_id(Integer trabajador_id) {
        this.trabajador_id = new SimpleIntegerProperty(trabajador_id);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    
    
     
    public int guardarRegistro(Connection connection){
        try {
                //Evitar inyeccion SQL.
                PreparedStatement instruccion =
                connection.prepareStatement("INSERT INTO trabajador_direccion (calle, numexterior, numinterior, colonia , cp, ciudad, estado, "
                        + "pais, trabajador_id) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                instruccion.setString(1, calle.get());
                instruccion.setString(2, numexterior.get());
                instruccion.setString(3, numinterior.get());
                instruccion.setString(4, colonia.get());
                instruccion.setString(5, cp.get());
                instruccion.setString(6, ciudad.get());
                instruccion.setString(7, estado.get());
                instruccion.setString(8, pais.get());
                instruccion.setInt(9, trabajador_id.get());
       
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    
    public static void llenarDatos(Connection connection, ObservableList<Direccion> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT id, calle, numexterior, numinterior, colonia, cp, ciudad, estado, pais, trabajador_id, status  FROM trabajador_direccion");
            
            while (resultado.next()){
                lista.add(new Direccion(
                      resultado.getInt("id"),
                      resultado.getString("calle"),
                      resultado.getString("numexterior"),
                      resultado.getString("numinterior"),
                      resultado.getString("colonia"),
                      resultado.getString("cp"),
                      resultado.getString("ciudad"),
                      resultado.getString("estado"),
                      resultado.getString("pais"),
                      resultado.getInt("trabajador_id"),
                      resultado.getString("status")
                   )  
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int editarRegistro(Connection connection){
          try {
                PreparedStatement instruccion =
                connection.prepareStatement(
                "UPDATE trabajador_direccion "+
                " SET 	calle = ?,  "+
                " numexterior = ?,  "+
                " numinterior = ?, "+
                " colonia = ?,  "+
                " cp = ?, "+
                " ciudad = ?,  "+
                " estado = ?,  "+
                " pais = ?  "+
                " WHERE trabajador_id = ?"
                );
                instruccion.setString(1, calle.get());
                instruccion.setString(2, numexterior.get());
                instruccion.setString(3, numinterior.get());
                instruccion.setString(4, colonia.get());
                instruccion.setString(5, cp.get());
                instruccion.setString(6, ciudad.get());
                instruccion.setString(7, estado.get());
                instruccion.setString(8, pais.get());
   
                instruccion.setInt(9, trabajador_id.get());
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    
}
