package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class banco {
        
        private IntegerProperty id;
        private IntegerProperty banco_id;
        private StringProperty tipo_cuenta;
        private StringProperty num_cuenta;
        private StringProperty clabe;
        private StringProperty num_tarjeta;
        private StringProperty status;

    public banco(Integer id, Integer banco_id, String tipo_cuenta, String num_cuenta, String clabe, String num_tarjeta, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.banco_id = new SimpleIntegerProperty(banco_id);
        this.tipo_cuenta = new SimpleStringProperty(tipo_cuenta);
        this.num_cuenta = new SimpleStringProperty(num_cuenta);
        this.clabe = new SimpleStringProperty(clabe);
        this.num_tarjeta = new SimpleStringProperty(num_tarjeta);
        this.status = new SimpleStringProperty(status);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public Integer getBanco_id() {
        return banco_id.get();
    }

    public void setBanco_id(Integer banco_id) {
        this.banco_id = new SimpleIntegerProperty(banco_id);
    }

    public String getTipo_cuenta() {
        return tipo_cuenta.get();
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = new SimpleStringProperty(tipo_cuenta);
    }

    public String getNum_cuenta() {
        return num_cuenta.get();
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = new SimpleStringProperty(num_cuenta);
    }

    public String getClabe() {
        return clabe.get();
    }

    public void setClabe(String clabe) {
        this.clabe = new SimpleStringProperty(clabe);
    }

    public String getNum_tarjeta() {
        return num_tarjeta.get();
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = new SimpleStringProperty(num_tarjeta);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }
        
}
