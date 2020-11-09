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

public class Trabajadorbanco {
        
        private IntegerProperty id;
        //private IntegerProperty banco_id;
        private StringProperty tipo_cuenta;
        private StringProperty num_cuenta;
        private StringProperty clabe;
        private StringProperty num_tarjeta;
        private StringProperty status;
        private banco banco;

    public Trabajadorbanco(Integer id, String tipo_cuenta, String num_cuenta, String clabe, String num_tarjeta, banco banco,String status) {
        this.id = new SimpleIntegerProperty(id);
        //this.banco_id = new SimpleIntegerProperty(banco_id);
        this.tipo_cuenta = new SimpleStringProperty(tipo_cuenta);
        this.num_cuenta = new SimpleStringProperty(num_cuenta);
        this.clabe = new SimpleStringProperty(clabe);
        this.num_tarjeta = new SimpleStringProperty(num_tarjeta);
        this.status = new SimpleStringProperty(status);
        this.banco = banco;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id = new SimpleIntegerProperty(id);
    }

   /* public Integer getBanco_id() {
        return banco_id.get();
    }

    public void setBanco_id(Integer banco_id) {
        this.banco_id = new SimpleIntegerProperty(banco_id);
    }
*/

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
    
     public banco getBanco(){
        return banco;
    }

    public void setBanco(banco banco){
        this.banco = banco;
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
                connection.prepareStatement("INSERT INTO trabajador_banco (banco_id, tipo_cuenta, num_cuenta, clabe , num_tarjeta) " +
                "VALUES ( ?, ?, ?, ?, ?)");
                instruccion.setInt(1, banco.getId());
                instruccion.setString(2, tipo_cuenta.get());
                instruccion.setString(3, num_cuenta.get());
                instruccion.setString(4, clabe.get());
                instruccion.setString(5, num_tarjeta.get());
       
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    public static void llenarDatos(Connection connection, ObservableList<Trabajadorbanco> lista){
        try {
            Statement instruccion = connection.createStatement();
            ResultSet resultado = instruccion.executeQuery(
                    "SELECT A.id, "
                    + "A.banco_id, "
                    + "A.tipo_cuenta, "
                    + "A.num_cuenta, "
                    + "A.clabe, "
                    + "A.num_tarjeta, "
                    + "A.status,  "
                    + "B.id, "
                    + "B.clave, "
                    + "B.nombre_corto, "
                    + "B.razon_social, "
                    + "B.nombre, "
                    + "B.status "
                    + "FROM trabajador_banco A "
                    + "INNER JOIN banco B "
                    + "ON (A.banco_id = B.id) "
                            
            
            
            );
            
            while (resultado.next()){
                lista.add(new Trabajadorbanco(
                    resultado.getInt("id"),
                    resultado.getString("tipo_cuenta"),
                    resultado.getString("num_cuenta"),
                    resultado.getString("clabe"),
                    resultado.getString("num_tarjeta"),
                     new banco(resultado.getInt("id"),
                        resultado.getString("clave"),
                        resultado.getString("nombre_corto"),
                        resultado.getString("razon_social"),
                        resultado.getString("nombre"),
                        resultado.getString("status")
                    ),
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
                "UPDATE trabajador_banco "+
                " SET 	banco_id = ?,  "+
                " tipo_cuenta = ?,  "+
                " num_cuenta = ?, "+
                " clabe = ?,  "+
                " WHERE num_cuenta = ?"
                );
                 instruccion.setInt(1, banco.getId());
                instruccion.setString(2, tipo_cuenta.get());
                instruccion.setString(3, num_cuenta.get());
                instruccion.setString(4, clabe.get());
                instruccion.setString(5, num_cuenta.get());
                return instruccion.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
    }
        
}
