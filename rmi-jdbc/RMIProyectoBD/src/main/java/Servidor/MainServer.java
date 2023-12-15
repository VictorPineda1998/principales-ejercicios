/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

//<editor-fold defaultstate="collapsed" desc="Imports">
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//</editor-fold>



//<editor-fold defaultstate="collapsed" desc="Programa Servidor">
public class MainServer {
    public static void main(String[] args) {
        try{
            Registry mR = LocateRegistry.createRegistry(1099);
            mR.rebind("COMUNICACION", new ServerImplements());
            System.out.println("Servidor Iniciado...");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
//</editor-fold>
    