/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.rmi.Remote;
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Autor">
/**
 *
 * @author Manuell Dirzo
 */
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Interfaz RMI">
public interface InterfazRemoto extends Remote{
    public int suma(int n1, int n2) throws Exception;
    public int resta(int n1, int n2) throws Exception;
    public int multiplicacion(int n1, int n2) throws Exception;
    public int division(int n1, int n2) throws Exception;
    public String actualizar()throws Exception;
    public String conversacion(String fecha, String usuario, String mensaje, String clave)throws Exception;
    public boolean peticion(String usuario, String clave)throws Exception;
    public void mostrarMensajes()throws Exception;
}
//</editor-fold>
