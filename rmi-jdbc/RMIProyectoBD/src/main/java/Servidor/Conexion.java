
package Servidor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private final String bd="mensajes";
    private final String user="root";
    private final String driver="com.mysql.cj.jdbc.Driver";
    private final String contra = "Poncho138";
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
