/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manuell dirzo
 */
public class ConexionJDBC {
    private final String bd="dbsoap";
    private final String user="root";
    private final String driver="com.mysql.cj.jdbc.Driver";
    private final String contra = "Victorpineda";
    private final String url = "jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&useJDBC"
           + "CompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    Connection cnx;

    public Connection getConexion() {
        
            try {
                Class.forName(driver);
                cnx = (Connection) DriverManager.getConnection(url,user, contra);
            
            } catch (ClassNotFoundException ex) {
                System.out.println("Error");
            } catch (SQLException e) {
                System.out.println("Error de SQL");
            
        }
        return cnx;
    }
}
