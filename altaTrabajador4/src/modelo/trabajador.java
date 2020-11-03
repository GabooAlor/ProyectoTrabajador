
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


public class trabajador {
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty fecha_nacimiento;
    private StringProperty rfc;
    private StringProperty nss;
    private StringProperty curp;
    private StringProperty doc_identidad;
    private StringProperty num_documento;
    private StringProperty tipo_licencia;
    private StringProperty num_licencia;
    private StringProperty fecha_expicion;
    private StringProperty fecha_vigencia;
    private StringProperty status;

    public trabajador(Integer id, String nombre, String fecha_nacimiento, String rfc, String nss, String curp, String doc_identidad, String num_documento, String tipo_licencia, String num_licencia, String fecha_expicion, String fecha_vigencia, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.fecha_nacimiento = new SimpleStringProperty(fecha_nacimiento);
        this.rfc = new SimpleStringProperty(rfc);
        this.nss = new SimpleStringProperty(nss);
        this.curp = new SimpleStringProperty(curp);
        this.doc_identidad = new SimpleStringProperty(doc_identidad);
        this.num_documento = new SimpleStringProperty(num_documento);
        this.tipo_licencia = new SimpleStringProperty(tipo_licencia);
        this.num_licencia = new SimpleStringProperty(num_licencia);
        this.fecha_expicion = new SimpleStringProperty(fecha_expicion);
        this.fecha_vigencia = new SimpleStringProperty(fecha_vigencia);
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

    public String getFecha_nacimiento() {
        return fecha_nacimiento.get();
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = new SimpleStringProperty(fecha_nacimiento);
    }

    public String getRfc() {
        return rfc.get();
    }

    public void setRfc(String rfc) {
        this.rfc = new SimpleStringProperty(rfc);
    }

    public String getNss() {
        return nss.get();
    }

    public void setNss(String nss) {
        this.nss = new SimpleStringProperty(nss);
    }

    public String getCurp() {
        return curp.get();
    }

    public void setCurp(String curp) {
        this.curp = new SimpleStringProperty(curp);
    }

    public String getDoc_identidad() {
        return doc_identidad.get();
    }

    public void setDoc_identidad(String doc_identidad) {
        this.doc_identidad = new SimpleStringProperty(doc_identidad);
    }

    public String getNum_documento() {
        return num_documento.get();
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = new SimpleStringProperty(num_documento);
    }

    public String getTipo_licencia() {
        return tipo_licencia.get();
    }

    public void setTipo_licencia(String tipo_licencia) {
        this.tipo_licencia = new SimpleStringProperty(tipo_licencia);
    }

    public String getNum_licencia() {
        return num_licencia.get();
    }

    public void setNum_licencia(String num_licencia) {
        this.num_licencia = new SimpleStringProperty(num_licencia);
    }

    public String getFecha_expicion() {
        return fecha_expicion.get();
    }

    public void setFecha_expicion(String fecha_expicion) {
        this.fecha_expicion = new SimpleStringProperty(fecha_expicion);
    }

    public String getFecha_vigencia() {
        return fecha_vigencia.get();
    }

    public void setFecha_vigencia(String fecha_vigencia) {
        this.fecha_vigencia = new SimpleStringProperty(fecha_vigencia);
    }

    public String status() {
        return status.get();
    }

    public void status(String status) {
        this.status = new SimpleStringProperty(status);
    }
    
    public int guardarRegistro(Connection connection){
            try {
                //Evitar inyeccion SQL.
                PreparedStatement instruccion =
                connection.prepareStatement("INSERT INTO trabajador (nombre, fecha_nacimiento, rfc, nss , curp, doc_identidad, num_documento, "
                        + "tipo_licencia, num_licencia, fecha_expedicion, fecha_vgencia) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                instruccion.setString(1, nombre.get());
                instruccion.setString(2, fecha_nacimiento.get());
                instruccion.setString(3, rfc.get());
                instruccion.setString(4, nss.get());
                instruccion.setString(5, curp.get());
                instruccion.setString(6, doc_identidad.get());
                instruccion.setString(7, num_documento.get());
                instruccion.setString(8, tipo_licencia.get());
                instruccion.setString(9, num_licencia.get());
                instruccion.setString(10, fecha_expicion.get());
                instruccion.setString(11, fecha_vigencia.get());

                
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    public static void llenarDatos(Connection connection, ObservableList<trabajador> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery("SELECT id, nombre, fecha_nacimiento, rfc, nss, curp, doc_identidad, num_documento, tipo_licencia, num_licencia, fecha_expedicion, fecha_vgencia, status  FROM trabajador");
            
            while (resultado.next()){
                lista.add(
                   new trabajador(
                      resultado.getInt("id"),
                      resultado.getString("nombre"),
                      resultado.getString("fecha_nacimiento"),
                      resultado.getString("rfc"),
                      resultado.getString("nss"),
                      resultado.getString("curp"),
                      resultado.getString("doc_identidad"),
                      resultado.getString("num_documento"),
                      resultado.getString("tipo_licencia"),
                      resultado.getString("num_licencia"),
                      resultado.getString("fecha_expedicion"),
                      resultado.getString("fecha_vgencia"),
                      resultado.getString("status")
                   )  
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public int EditarRegistro(Connection connection){
          try {
                PreparedStatement instruccion =
                connection.prepareStatement(
                "UPDATE trabajador "+
                " SET 	nombre = ?,  "+
                " fecha_nacimiento = ?,  "+
                " rfc = ?, "+
                " nss = ?,  "+
                " curp = ?, "+
                " doc_identidad = ?,  "+
                " num_documento = ?  "+
                " tipo_licencia = ?,  "+
                " num_licencia = ?,  "+
                " fecha_expedicion = ?,  "+
                " fecha_vgencia = ?,  "+
                " WHERE rfc = ?"
                );
                 instruccion.setString(1, nombre.get());
                instruccion.setString(2, fecha_nacimiento.get());
                instruccion.setString(3, rfc.get());
                instruccion.setString(4, nss.get());
                instruccion.setString(5, curp.get());
                instruccion.setString(6, doc_identidad.get());
                instruccion.setString(7, num_documento.get());
                instruccion.setString(8, tipo_licencia.get());
                instruccion.setString(9, num_licencia.get());
                instruccion.setString(10, fecha_expicion.get());
                instruccion.setString(11, fecha_vigencia.get());
                instruccion.setString(12, rfc.get());
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    
}