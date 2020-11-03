
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


public class banco {
    private IntegerProperty id;
    private StringProperty clave;
    private StringProperty nombre_corto;
    private StringProperty razon_social;
    private StringProperty nombre;
    private StringProperty status;

    public banco(Integer id, String clave, String nombre_corto, String razon_social, String nombre, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.clave = new SimpleStringProperty(clave);
        this.nombre_corto = new SimpleStringProperty(nombre_corto);
        this.razon_social = new SimpleStringProperty(razon_social);
        this.nombre = new SimpleStringProperty(nombre);
        this.status = new SimpleStringProperty(status);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public String getClave() {
        return clave.get();
    }

    public void setClave(String clave) {
        this.clave = new SimpleStringProperty(clave);
    }

    public String getNombre_corto() {
        return nombre_corto.get();
    }

    public void setNombre_corto(String nombre_corto) {
        this.nombre_corto = new SimpleStringProperty(nombre_corto);
    }

    public String getRazon_social() {
        return razon_social.get();
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = new SimpleStringProperty(razon_social);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }
    
    public static void llenarInformacion(Connection connection, ObservableList<banco> lista){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery(
		"SELECT id, "
		+ "clave, "
		+ "nombre_corto, "
                + "razon_social, "
                + "nombre, "
                + "status "
		+ "FROM banco"
            );
            while (resultado.next()){
		lista.add(
                    new banco(
                        resultado.getInt("id"),
                        resultado.getString("clave"),
                        resultado.getString("nombre_corto"),
                        resultado.getString("razon_social"),
                        resultado.getString("nombre"),
                        resultado.getString("status")
                    )
		);
            }
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
    public String toString(){
        return nombre_corto.get();
    }
    
    
}

