/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import ws.WSOperaciones;
import ws.WSOperaciones_Service;

/**
 *
 * @author manuell dirzo
 */
public class TestWS {

    /**
     * @param args the command line arguments
     */
    ConexionJDBC con = new ConexionJDBC();
    Connection conn = con.getConexion();
    PreparedStatement ps = null;

    public static void main(String[] args) {
        // Crear el cliente
        WSOperaciones_Service servicio = new WSOperaciones_Service();
        WSOperaciones cliente = servicio.getWSOperacionesPort();

        //Utilizar Metodos
        //1.- Login
        if (cliente.login("Manuell", "Manuell2023")) {
            System.out.println("Credenciales correctas");
        } else {
            System.out.println("Credenciales incorrectas");
        }

        // 2.- procesar pago
        System.out.println(cliente.procesarPago(5000, 20000));
        
    }

    public void AgregarDatos(String validaruser, String resultadooper) {
        ps = null;
        try {
            ps = conn.prepareCall("insert into info(validaruser,resultadooper) values (?,?)");
            ps.setString(1, validaruser);//leemos la caja de texto
            ps.setString(2, resultadooper);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Base de datos actualizada");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

}
