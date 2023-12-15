        /*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
         */
        package Servidor;

        //<editor-fold defaultstate="collapsed" desc="Imports">
        import RMI.InterfazRemoto;
        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.File;
        import java.io.FileReader;
        import java.io.FileWriter;
        import java.io.IOException;
        import java.rmi.RemoteException;
        import java.rmi.server.UnicastRemoteObject;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Scanner;
        import javax.swing.JOptionPane;
        //</editor-fold>



        //<editor-fold defaultstate="collapsed" desc="Implementacion Del Servidor">
        public class ServerImplements extends UnicastRemoteObject implements InterfazRemoto {

            Conexion con = new Conexion();
            Connection conn = con.getConexion();
            PreparedStatement ps = null;
            Scanner sc = new Scanner(System.in);
            String historial = "";
            String nombreArchivo = "historial.txt";
            // Crear un objeto File con el nombre del archivo
            File archivo = new File(nombreArchivo);

            public ServerImplements() throws Exception {
                super();
            }

            @Override
            public int suma(int n1, int n2) {
                return (n1 + n2);
            }

            @Override
            public int resta(int n1, int n2) {
                return (n1 - n2);
            }

            @Override
            public int multiplicacion(int n1, int n2) {
                return (n1 * n2);
            }

            @Override
            public int division(int n1, int n2) {
                return (n1 / n2);
            }

            @Override
            public boolean peticion(String usuario, String clave) {
                System.out.println("El usuario: " + usuario
                        + "\nquiere conectarse con la clave: " + clave
                        + "\nAcepta la peticion?");
                String respuesta = sc.next();
                if (respuesta.equalsIgnoreCase("si")) {
                    return true;
                }
                return false;
            }

            @Override
            public String actualizar() {
                /*try {
                    historial = leerTextoDeArchivo(nombreArchivo);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }*/

                return historial;
            }

            @Override
            public String conversacion(String fecha, String usuario, String mensaje, String clave) {
                if (clave.equals("1234")) {
                    historial += usuario + ": " + mensaje + "\n" + fecha + " " + "\n\n";
                } else {
                    historial += "\t\t\t" + usuario + ": " + mensaje + "\n\t\t\t" + fecha + " " + "\n\n";
                }
                AgregarMensaje(fecha, usuario, mensaje, clave);
                guardarTextoEnArchivo(historial, nombreArchivo);
                return historial;
            }

            public void mostrarMensajes() {
                ps = null;
                ResultSet rs = null;

                try {
                    ps = conn.prepareStatement("select * from mensaje");
                    rs = ps.executeQuery();
                    int i = 0; 
                    while (rs.next()) {
                        historial += rs.getString("usuario") + 
                                        " : " + rs.getString("mensaje") +
                                "\nFecha: " + rs.getString("fecha") + "\n\n";
                        /*System.out.println("Mensaje " + (i+1) + ":\nFecha: " + rs.getString("fecha") + 
                                "\nUsuario: " + rs.getString("usuario") + 
                                        "Mensaje: " + rs.getString("mensaje"));*/
                        i++;
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            public void AgregarMensaje(String fecha, String usuario, String mensaje, String clave) {
                ps = null;
                try {
                    ps = conn.prepareCall("insert into mensaje(fecha,usuario,mensaje,clave) values (?,?,?,?)");
                    ps.setString(1, fecha);//leemos la caja de texto
                    ps.setString(2, usuario);
                    ps.setString(3, mensaje);
                    ps.setString(4, clave);
                    ps.execute();
                    //JOptionPane.showMessageDialog(null, "Se ha agregado correctamente");
                    System.out.println("Mensaje guardado correctamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }

            // Método para guardar una cadena de texto en un archivo
            public static void guardarTextoEnArchivo(String texto, String nombreArchivo) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
                    writer.write(texto);
                    System.out.println("Texto guardado en el archivo: " + nombreArchivo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Método para leer el contenido de un archivo de texto y almacenarlo en una variable String
            String contenido;
            public static String leerTextoDeArchivo(String nombreArchivo) {
                StringBuilder contenido = new StringBuilder();

                try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return contenido.toString();
            }
        }
        //</editor-fold>
