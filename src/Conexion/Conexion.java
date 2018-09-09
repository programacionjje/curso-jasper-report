
package Conexion;

import datos.Propiedades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
    
    private String usuario;
    private String password;
    private String path;
    
    
    public Conexion(){
        //se crea una instancia a nuestra clase
        Properties mispropiedades = new Propiedades().getProperties();
        //se leen las propiedades
        usuario = mispropiedades.getProperty("user");
        password = mispropiedades.getProperty("pass");
        path = mispropiedades.getProperty("path");
    }
    public Connection conectar(){
        Connection conexion = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = (Connection) DriverManager.getConnection(path,usuario,password);
            System.out.println("se ha conectado correctamente");
            return conexion;
        }catch(Exception e){
            System.out.println("Conexion no Realizada\n"+e);
        }
        return conexion;
    }
    public void cerrarConexion(Connection c){
        try{
            c.close();
        }catch(Exception ex){ex.printStackTrace();}
    }
}
