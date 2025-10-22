package com.mycompany.jdbc_test_adso;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_test_adso {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test_jdbc";
        String username = "root";
        String password = "";
        
        
        
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            
            stmt.execute("""
                         CREATE TABLE IF NOT EXISTS usuarios
                         (id INT AUTO_INCREMENT PRIMARY KEY, 
                         nombre VARCHAR(50));
                         """);
            
            stmt.execute("INSERT INTO `usuarios` (`id`, `nombre`) VALUES (NULL, 'JUAN');");
            System.out.println("Usuario insertado en la base de datos");
            
            obtenerUsuarios(stmt);
          
            
            stmt.executeUpdate("UPDATE `usuarios` SET `nombre` = 'JOSE';");
            System.out.println("Usuarios actualizado");           
            
            obtenerUsuarios(stmt);
            
            
            stmt.execute("DELETE FROM usuarios WHERE nombre = 'JOSE';");
            System.out.println("Usuarios eliminados");
            
            obtenerUsuarios(stmt);
            
                        
        } catch (SQLException ex) {
            System.getLogger(Jdbc_test_adso.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private static void obtenerUsuarios(Statement stmt) throws SQLException {
          ResultSet rs = stmt.executeQuery("SELECT * FROM `usuarios`");
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("Id: " + id + " Nombre: " + nombre);
            }
    }
   
}
