/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQL {
    
    private Connection conexion;
    
    public SQL(Connection miConexion)
    {
        conexion = miConexion;
    }
    
    public ResultSet getProducto()
    {
        
        try {
            Statement sentencia;
            sentencia = conexion.createStatement();
            String query = "select * from producto";
            return sentencia.executeQuery(query);
        } catch (Exception e) {
            System.err.println("Error SQL en el metodo getProducto");
        }
        return null;
    }
    
}
