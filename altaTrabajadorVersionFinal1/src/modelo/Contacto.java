
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

public class Contacto {
        
        private IntegerProperty id;
        private StringProperty nombre;
        private StringProperty telefono;
        private StringProperty celular;
        private StringProperty tel_emergencia;
        private StringProperty email;
        private StringProperty status;

    public Contacto(Integer id, String nombre, String telefono, String celular, String tel_emergencia, String email, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.celular = new SimpleStringProperty(celular);
        this.tel_emergencia = new SimpleStringProperty(tel_emergencia);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono = new SimpleStringProperty(telefono);
    }

    public String getCelular() {
        return celular.get();
    }

    public void setCelular(String celular) {
        this.celular = new SimpleStringProperty(celular);
    }

    public String getTel_emergencia() {
        return tel_emergencia.get();
    }

    public void setTel_emergencia(String tel_emergencia) {
        this.tel_emergencia = new SimpleStringProperty(tel_emergencia);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }   
    
    
     public int guardarRegistro(Connection connection){
        try {
                //Evitar inyeccion SQL.
                PreparedStatement instruccion =
                connection.prepareStatement("INSERT INTO trabajador_contacto (nombre, telefono, celular, tel_emergencia , email ) " +
                "VALUES ( ?, ?, ?, ?, ?)");
                instruccion.setString(1, nombre.get());
                instruccion.setString(2, telefono.get());
                instruccion.setString(3, celular.get());
                instruccion.setString(4, tel_emergencia.get());
                instruccion.setString(5, email.get());
       
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
     
     
      public static void llenarDatos(Connection connection, ObservableList<Contacto> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT id, nombre, telefono, celular, tel_emergencia, email, status  FROM trabajador_contacto");
            
            while (resultado.next()){
                lista.add(new Contacto(
                      resultado.getInt("id"),
                      resultado.getString("nombre"),
                      resultado.getString("telefono"),
                      resultado.getString("celular"),
                      resultado.getString("tel_emergencia"),
                      resultado.getString("email"),
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
                "UPDATE trabajador_contacto "+
                " SET 	nombre = ?,  "+
                " telefono = ?,  "+
                " celular = ?, "+
                " tel_emergencia = ?,  "+
                " email = ? "+
                " WHERE id = ?"
                );
                 instruccion.setString(1, nombre.get());
                instruccion.setString(2, telefono.get());
                instruccion.setString(3, celular.get());
                instruccion.setString(4, tel_emergencia.get());
                instruccion.setString(5, email.get());
                instruccion.setInt(6, id.get());
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
}
