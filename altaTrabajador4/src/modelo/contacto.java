
package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class contacto {
        
        private IntegerProperty id;
        private StringProperty nombre;
        private StringProperty telefono;
        private StringProperty celular;
        private StringProperty tel_emergencia;
        private StringProperty email;
        private StringProperty status;

    public contacto(Integer id, String nombre, String telefono, String celular, String tel_emergencia, String email, String status) {
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
}
